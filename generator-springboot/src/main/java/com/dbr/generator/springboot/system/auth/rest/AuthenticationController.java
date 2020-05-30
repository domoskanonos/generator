package com.dbr.generator.springboot.system.auth.rest;

import com.dbr.generator.springboot.system.auth.service.AuthenticationService;
import com.dbr.generator.springboot.system.auth.entity.AuthUser;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@Api(tags = AuthenticationController.PATH_PREFIX)
@RestController
@RequiredArgsConstructor
@RequestMapping(AuthenticationController.PATH_PREFIX)
@ConditionalOnProperty(name = "spring.security.enabled", havingValue = "true", matchIfMissing = true)
public class AuthenticationController {

    public static final String PATH_PREFIX = "SYSTEM/AUTH";

    private AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping(value = "/REGISTER")
    public ResponseEntity<Boolean> register(@RequestParam String email, @RequestParam String password)
            throws MessagingException, IOException, TemplateException {
        return ResponseEntity.ok(service.registerUser(email, password));
    }

    @CrossOrigin
    @GetMapping(value = "/ACTIVATE_USER")
    public ResponseEntity<Boolean> activateUser(@RequestParam String email, @RequestParam String passwordHash) {
        return ResponseEntity.ok(service.activateUser(email, passwordHash));
    }

    @CrossOrigin
    @PostMapping("/RESET_PASSWORD")
    public ResponseEntity<Boolean> resetPasswort(@RequestParam String email) throws MessagingException, IOException, TemplateException {
        return ResponseEntity.ok(service.resetPassword(email));
    }

    @CrossOrigin
    @PostMapping(value = "/DEACTIVATE_USER", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deactivateUser(@ApiIgnore @AuthenticationPrincipal Authentication authentication) {
        return ResponseEntity.ok(service.deactivateUser(authentication));
    }

    @CrossOrigin
    @PostMapping(value = "/UPDATE_PASSWORD")
    public ResponseEntity<Boolean> updatePassword(@ApiIgnore @AuthenticationPrincipal Authentication authentication,
            @RequestParam() String passwordCurrent, @RequestParam() String passwordNew) throws MessagingException, IOException, TemplateException {
        return ResponseEntity.ok(service.updatePassword(authentication, passwordCurrent, passwordNew));
    }

    @CrossOrigin
    @PutMapping("/UPDATE_USER/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @Valid @RequestBody AuthUser authUser) {
        return ResponseEntity.ok(service.updateUser(id, authUser));
    }

    @CrossOrigin
    @GetMapping(value = "/USER_DETAILS", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetails> userDetails(@ApiIgnore @AuthenticationPrincipal Authentication authentication) {
        return ResponseEntity.ok(service.findUserDetailsByAuthentication(authentication));
    }

    @CrossOrigin
    @GetMapping(value = "/USER", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthUser> user(@ApiIgnore @AuthenticationPrincipal Authentication authentication) {
        return ResponseEntity.ok(service.findAuthUserByAuthentication(authentication));
    }

}
