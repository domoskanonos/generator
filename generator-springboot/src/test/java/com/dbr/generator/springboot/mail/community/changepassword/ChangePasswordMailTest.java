package com.dbr.generator.springboot.mail.community.changepassword;

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
public class ChangePasswordMailTest {

    @Test
    public void create() throws IOException, TemplateException {
        ChangePasswordModel changePasswordModel = new ChangePasswordModel();
        changePasswordModel.setEmail("email@email.de");
        changePasswordModel.setNewPassword("newPassword");
        ChangePasswordMailMergerInterface changePasswordMailMerger = new ChangePasswordMailMergerInterface();
        String content = changePasswordMailMerger.create(changePasswordModel);
        assertThat(content).isNotBlank();
    }
}