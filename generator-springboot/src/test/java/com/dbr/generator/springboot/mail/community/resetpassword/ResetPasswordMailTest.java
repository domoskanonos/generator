package com.dbr.generator.springboot.mail.community.resetpassword;

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
public class ResetPasswordMailTest {

    @Test
    public void create() throws IOException, TemplateException {
        ResetPasswordModel resetPasswordModel = new ResetPasswordModel();
        resetPasswordModel.setEmail("email@email.de");
        resetPasswordModel.setNewPassword("newPassword");
        ResetPasswordMailMergerInterface resetPasswordMailMerger = new ResetPasswordMailMergerInterface();
        String content = resetPasswordMailMerger.create(resetPasswordModel);
        assertThat(content).isNotBlank();
    }
}