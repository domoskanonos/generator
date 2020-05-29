package com.dbr.generator.springboot.mail.community.registrationconfirm;

import freemarker.template.TemplateException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationConfirmMailTest {

    @Test
    public void create() throws IOException, TemplateException {
        RegistrationConfirmModel registrationConfirmModel = new RegistrationConfirmModel();
        registrationConfirmModel.setEmail("email@email.de");
        registrationConfirmModel.setLinkActivate("http://activate.de");
        registrationConfirmModel.setLinkDelete("http://delete.de");
        RegistrationConfirmMailMergerInterface registrationConfirmMail = new RegistrationConfirmMailMergerInterface();
        String content = registrationConfirmMail.create(registrationConfirmModel);
        assertThat(content).isNotBlank();
    }
}