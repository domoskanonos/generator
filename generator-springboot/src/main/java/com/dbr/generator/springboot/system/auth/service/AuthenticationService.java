package com.dbr.generator.springboot.system.auth.service;

import com.dbr.generator.springboot.mail.community.changepassword.ChangePasswordMailMergerInterface;
import com.dbr.generator.springboot.mail.community.changepassword.ChangePasswordModel;
import com.dbr.generator.springboot.mail.community.registrationconfirm.RegistrationConfirmMailMergerInterface;
import com.dbr.generator.springboot.mail.community.registrationconfirm.RegistrationConfirmModel;
import com.dbr.generator.springboot.mail.community.resetpassword.ResetPasswordMailMergerInterface;
import com.dbr.generator.springboot.mail.community.resetpassword.ResetPasswordModel;
import com.dbr.generator.springboot.system.auth.enumeration.AuthRoleEnum;
import com.dbr.generator.springboot.system.auth.repository.AuthUserRepository;
import com.dbr.generator.springboot.system.auth.repository.AuthUserRoleRepository;
import com.dbr.generator.springboot.system.ApplicationProperties;
import com.dbr.generator.springboot.system.auth.entity.AuthUser;
import com.dbr.generator.springboot.system.auth.entity.AuthUserPrivilege;
import com.dbr.generator.springboot.system.auth.entity.AuthUserRole;
import com.dbr.generator.springboot.system.mail.service.MailService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    ApplicationProperties applicationProperties;

    private AuthUserRepository repository;

    private AuthUserRoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private MailService mailService;

    @Autowired
    public AuthenticationService(ApplicationProperties applicationProperties, AuthUserRepository repository,
                                 AuthUserRoleRepository roleRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.applicationProperties = applicationProperties;
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public AuthUser save(AuthUser user) {
        log.info("save user: {}", user);
        return repository.save(user);
    }

    /**
     * excecute on login
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        log.info("load user by email: {}", email);
        AuthUser authUser = repository.findByEmail(email);

        if (authUser == null) {
            throw new UsernameNotFoundException("username not found: " + email);
        }
        if (Boolean.FALSE.equals(authUser.getActive())) {
            throw new UsernameNotFoundException("user not active: " + email);
        }
        return toUserDetails(authUser);
    }

    public AuthUser findAuthUserByAuthentication(Authentication authentication) {
        log.info("load user by authentication: {}", authentication.getName());
        return repository.findByEmail(authentication.getName());
    }

    public UserDetails findUserDetailsByAuthentication(Authentication authentication) {
        AuthUser authUser = findAuthUserByAuthentication(authentication);
        return toUserDetails(authUser);
    }

    private UserDetails toUserDetails(AuthUser authUser) {
        return User.builder().username(authUser.getEmail()).password(authUser.getPassword())
                .authorities(getAuthorities(authUser.getRoles())).accountLocked(false).accountExpired(false)
                .credentialsExpired(false).disabled(!authUser.getActive()).build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<AuthUserRole> authUserRoles) {
        List<String> all = new ArrayList<>();
        all.addAll(getPrivileges(authUserRoles));
        all.addAll(getRoles(authUserRoles));
        return getGrantedAuthorities(all);
    }

    private List<String> getRoles(Collection<AuthUserRole> authUserRoles) {
        List<String> roles = new ArrayList<>();
        authUserRoles.forEach(authUserRole -> roles.add(authUserRole.getName().name()));
        return roles;
    }

    private List<String> getPrivileges(Collection<AuthUserRole> roles) {

        List<String> privileges = new ArrayList<>();
        List<AuthUserPrivilege> collection = new ArrayList<>();
        for (AuthUserRole role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (AuthUserPrivilege item : collection) {
            privileges.add(item.getName().name());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    /**
     * Register a user and send confirm link by mail.
     *
     * @param email
     * @param password
     * @return
     * @throws MessagingException
     */
    public Boolean registerUser(String email, String password) throws MessagingException, IOException, TemplateException {
        log.info("register user, email: {}", email);
        AuthUser authUser = new AuthUser();
        authUser.setActive(false);
        authUser.setEmail(email);
        String passwordHash = passwordEncoder.encode(password);
        authUser.setPassword(passwordHash);
        authUser.getRoles().add(roleRepository.findByName(AuthRoleEnum.ROLE_USER));
        save(authUser);


        RegistrationConfirmModel registrationConfirmModel = new RegistrationConfirmModel();
        registrationConfirmModel.setEmail(email);
        registrationConfirmModel.setLinkActivate(new StringBuilder().append("http://").append(applicationProperties.getHostName()).append(":").append(applicationProperties.getPort()).append("/SYSTEM/AUTH/ACTIVATE_USER?email=").append(email).append("&passwordHash=").append(passwordHash).toString());
        registrationConfirmModel.setLinkDelete(applicationProperties.getHostName());

        RegistrationConfirmMailMergerInterface registrationConfirmMail = new RegistrationConfirmMailMergerInterface();
        String content = registrationConfirmMail.create(registrationConfirmModel);

        mailService.sendMail(email, "Neue Anmeldung", content, true, null);


        return true;
    }

    /**
     * Find hibernate entity of type <code>AuthUser</code> by id, mapped to <code>AuthUser</code>.
     *
     * @param id of hibernate entity
     * @return optional object of type <code>Optional<AuthUser></code>
     */
    public Optional<AuthUser> findById(Long id) {
        log.debug("findById: {}", id);
        return repository.findById(id);
    }

    public boolean updatePassword(Authentication authentication, String currentPassword, String newPassword)
            throws MessagingException, IOException, TemplateException {
        if (authentication.isAuthenticated()) {
            AuthUser user = findAuthUserByAuthentication(authentication);

            String newPasswordEncoded = passwordEncoder.encode(newPassword);

            if (passwordEncoder.matches(passwordEncoder.encode(currentPassword), user.getPassword())) {
                return false;
            }

            user.setPassword(newPasswordEncoded);
            save(user);

            ChangePasswordModel changePasswordModel = new ChangePasswordModel();
            changePasswordModel.setNewPassword(newPassword);

            ChangePasswordMailMergerInterface changePasswordMail = new ChangePasswordMailMergerInterface();
            String content = changePasswordMail.create(changePasswordModel);
            mailService.sendMail(user.getEmail(), "Du hast dir ein neues Passwort gegeben", content, true, null);


            return true;
        } else {
            return false;
        }
    }

    public Boolean deactivateUser(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            AuthUser user = findAuthUserByAuthentication(authentication);
            user.setActive(false);
            return save(user).getActive();
        }
        return false;
    }

    /**
     * get a list of all available database <code>AuthUser</code> object's, mapped to <code>AuthUser</code> objects.
     *
     * @return a list of <code>AuthUser's</code>
     */
    public List<AuthUser> findAll() {
        log.debug("findAll");
        return repository.findAll();
    }

    /**
     * delete hibernate entity by id.
     *
     * @param id
     */
    public void deleteById(Long id) {
        log.debug("delete by id: {}", id);
        repository.deleteById(id);
    }

    /**
     * reset user password and send mail with new login data.
     *
     * @param email
     * @return
     */
    public Boolean resetPassword(String email) throws MessagingException, IOException, TemplateException {

        AuthUser au = repository.findByEmail(email);
        if (au == null) {
            return false;
        }

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z')
                .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS).build();
        String newPassword = generator.generate(10);

        au.setPassword(passwordEncoder.encode(newPassword));
        repository.save(au);


        ResetPasswordModel resetPasswordModel = new ResetPasswordModel();
        resetPasswordModel.setNewPassword(newPassword);

        ResetPasswordMailMergerInterface resetPasswordMailMerger = new ResetPasswordMailMergerInterface();
        String content = resetPasswordMailMerger.create(resetPasswordModel);

        mailService.sendMail(email, "Neues PAsswort", content, true, null);


        return true;

    }

    public Boolean activateUser(String email, String passwordHash) {
        AuthUser authUser = repository.findByEmail(email);
        if (authUser == null) {
            log.info("user not found: {}", email);
            return false;
        } else if (authUser.isActive()) {
            log.info("user already active: {}", email);
            return false;
        } else if (!authUser.getPassword().equals(passwordHash)) {
            log.info("passwordHash incorrect: {}", passwordHash);
            return false;
        } else {
            log.info("activate user: {}", email);
            authUser.setActive(true);
            save(authUser);
            return true;
        }
    }

    public Boolean updateUser(Long id, AuthUser authUser) {
        Optional<AuthUser> storedAuthUserOptional = findById(id);
        if (!storedAuthUserOptional.isPresent()) {
            log.error("id {} not found", id);
            return false;
        }

        AuthUser storedAuthUser = storedAuthUserOptional.get();
        storedAuthUser.setFirstName(authUser.getFirstName());
        storedAuthUser.setLastName(authUser.getLastName());
        storedAuthUser.setCity(authUser.getCity());
        storedAuthUser.setBirthday(authUser.getBirthday());

        return save(storedAuthUser) != null;
    }
}
