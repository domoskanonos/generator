package com.dbr.generator.gen.server.springboot.modules.secure;

import com.dbr.generator.gen.server.springboot.modules.AbstractModuleGenerator;
import com.dbr.generator.gen.server.springboot.modules.model[0].ModulItem;
import com.dbr.generator.gen.server.springboot.modules.model[0].ResourceItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecureModulGenerator extends AbstractModuleGenerator {

    public static void main(String[] args) throws Exception {
        SecureModulGenerator secureModulGenerator = new SecureModulGenerator("de.rhenus.com");
        secureModulGenerator.generate();
    }

    public SecureModulGenerator(String packageName) {
        super(packageName);
    }

    @Override
    public void generate() throws Exception {
        List<ModulItem> items = new ArrayList<>();
        items.add(new ModulItem(".system.auth.config", "secure/src/system/auth/config/SecurityConfiguration.vm",
                "SecurityConfiguration.java"));
        items.add(new ModulItem(".system.auth.config", "secure/src/system/auth/config/WebMvcConfig.vm",
                "WebMvcConfig.java"));
        items.add(new ModulItem(".system.auth.entity", "secure/src/system/auth/entity/AuthUser.vm", "AuthUser.java"));
        items.add(new ModulItem(".system.auth.entity", "secure/src/system/auth/entity/AuthUserPrivilege.vm",
                "AuthUserPrivilege.java"));
        items.add(new ModulItem(".system.auth.entity", "secure/src/system/auth/entity/AuthUserRole.vm",
                "AuthUserRole.java"));
        items.add(new ModulItem(".system.auth.enumeration", "secure/src/system/auth/enumeration/AuthPrivilegeEnum.vm",
                "AuthPrivilegeEnum.java"));
        items.add(new ModulItem(".system.auth.enumeration", "secure/src/system/auth/enumeration/AuthRoleEnum.vm",
                "AuthRoleEnum.java"));
        items.add(new ModulItem(".system.auth.handler",
                "secure/src/system/auth/handler/OAuth2AuthenticationFailureHandler.vm",
                "OAuth2AuthenticationFailureHandler.java"));
        items.add(new ModulItem(".system.auth.handler",
                "secure/src/system/auth/handler/OAuth2AuthenticationSuccessHandler.vm",
                "OAuth2AuthenticationSuccessHandler.java"));
        items.add(new ModulItem(".system.auth.handler",
                "secure/src/system/auth/handler/SpringBootBasicAuthenticationFailureHandler.vm",
                "SpringBootBasicAuthenticationFailureHandler.java"));
        items.add(new ModulItem(".system.auth.handler",
                "secure/src/system/auth/handler/SpringBootBasicAuthenticationSuccessHandler.vm",
                "SpringBootBasicAuthenticationSuccessHandler.java"));
        items.add(new ModulItem(".system.auth.listener", "secure/src/system/auth/listener/InitialAuthUserLoader.vm",
                "InitialAuthUserLoader.java"));
        items.add(new ModulItem(".system.auth.repository",
                "secure/src/system/auth/repository/AuthUserPrivilegeRepository.vm",
                "AuthUserPrivilegeRepository.java"));
        items.add(new ModulItem(".system.auth.repository", "secure/src/system/auth/repository/AuthUserRepository.vm",
                "AuthUserRepository.java"));
        items.add(new ModulItem(".system.auth.repository",
                "secure/src/system/auth/repository/AuthUserRoleRepository.vm", "AuthUserRoleRepository.java"));
        items.add(new ModulItem(".system.auth.rest", "secure/src/system/auth/rest/AuthenticationController.vm",
                "AuthenticationController.java"));
        items.add(new ModulItem(".system.auth.service", "secure/src/system/auth/service/AuthenticationService.vm",
                "AuthenticationService.java"));

        this.generate(items);

        List<ResourceItem> resourceItems = new ArrayList<>();
        resourceItems.add(new ResourceItem("secure", "mail/de-change-password.vm"));
        resourceItems.add(new ResourceItem("secure", "mail/de-registration-confirm.vm"));
        resourceItems.add(new ResourceItem("secure", "mail/de-reset-password.vm"));
        resourceItems.add(new ResourceItem("secure", "public/index.html"));
        resourceItems.add(new ResourceItem("secure", "public/admin.html"));
        resourceItems.add(new ResourceItem("secure", "public/access-denied-page.html"));
        resourceItems.add(new ResourceItem("secure", "application-disable-security.properties"));

        this.copyResources(resourceItems);

    }

}
