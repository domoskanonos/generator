package com.dbr.generator.springboot.system.auth.listener;

import com.dbr.generator.springboot.system.auth.enumeration.AuthPrivilegeEnum;
import com.dbr.generator.springboot.system.auth.enumeration.AuthRoleEnum;
import com.dbr.generator.springboot.system.auth.repository.AuthUserPrivilegeRepository;
import com.dbr.generator.springboot.system.auth.repository.AuthUserRepository;
import com.dbr.generator.springboot.system.auth.repository.AuthUserRoleRepository;
import com.dbr.generator.springboot.system.ApplicationProperties;
import com.dbr.generator.springboot.system.auth.entity.AuthUser;
import com.dbr.generator.springboot.system.auth.entity.AuthUserPrivilege;
import com.dbr.generator.springboot.system.auth.entity.AuthUserRole;
import com.dbr.generator.springboot.system.enumeration.BuildEnvironment;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
public class InitialAuthUserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthUserRepository userRepository;

    private final AuthUserRoleRepository roleRepository;

    private final AuthUserPrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationProperties applicationProperties;

    public InitialAuthUserLoader(AuthUserRepository userRepository, AuthUserRoleRepository roleRepository,
            AuthUserPrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder,
            ApplicationProperties applicationProperties) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationProperties = applicationProperties;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (!applicationProperties.getBuildEnvironments().contains(BuildEnvironment.PROD)
                && userRepository.findByEmail("admin@admin.de") == null) {

            AuthUserPrivilege readPrivilege = createPrivilegeIfNotFound(AuthPrivilegeEnum.PRIVILEGE_READ);
            AuthUserPrivilege writePrivilege = createPrivilegeIfNotFound(AuthPrivilegeEnum.PRIVILEGE_WRITE);

            List<AuthUserPrivilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
            createRoleIfNotFound(AuthRoleEnum.ROLE_ADMIN, adminPrivileges);
            createRoleIfNotFound(AuthRoleEnum.ROLE_USER, Arrays.asList(readPrivilege));

            AuthUserRole adminRole = roleRepository.findByName(AuthRoleEnum.ROLE_ADMIN);
            AuthUser user = new AuthUser();
            user.setFirstName("ADMIN");
            user.setLastName("ADMIN");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setEmail("admin@admin.de");
            user.setActive(true);
            user.setRoles(new HashSet<>(Arrays.asList(adminRole)));
            userRepository.save(user);

        }

    }

    @Transactional
    public AuthUserPrivilege createPrivilegeIfNotFound(AuthPrivilegeEnum authPrivilegeEnum) {

        AuthUserPrivilege privilege = privilegeRepository.findByName(authPrivilegeEnum);
        if (privilege == null) {
            privilege = new AuthUserPrivilege(authPrivilegeEnum);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public AuthUserRole createRoleIfNotFound(AuthRoleEnum authRoleEnum, Collection<AuthUserPrivilege> privileges) {
        AuthUserRole role = roleRepository.findByName(authRoleEnum);
        if (role == null) {
            role = new AuthUserRole(authRoleEnum);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

}
