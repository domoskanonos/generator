package com.dbr.generator.springboot.system.mail.service;

import com.dbr.generator.springboot.SpringBootTemplateApplication;
import com.dbr.generator.springboot.system.mail.SmtpServerRule;
import org.apache.commons.io.IOUtils;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles({ "green-mail" })
@SpringBootTest(classes = {
        SpringBootTemplateApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Rule
    public SmtpServerRule smtpServerRule = new SmtpServerRule(2525);

    private String to = "example@email.de";
    private String subject = "my example $%&";
    private String text = "my text $%&";
    private String exampleFile = "exampleFile";
    private String originalFilename = "exampleFile.pdf";
    private byte[] content = "content".getBytes();
    private MockMultipartFile attachment = new MockMultipartFile(exampleFile, originalFilename, null, content);

    @Test
    public void sendMail() throws MessagingException, IOException {
        mailService.sendMail(to, subject, text, true, null);
        checkResult();
    }

    @Test
    public void sendMailWithAttachment() throws MessagingException, IOException {
        mailService.sendMail(to, subject, text, true, attachment);
        checkResult();
    }

    private void checkResult() throws MessagingException, IOException {
        MimeMessage[] receivedMessages = smtpServerRule.getMessages();
        assertThat(receivedMessages.length).isEqualTo(1);
        MimeMessage current = receivedMessages[0];
        assertThat(current.getAllRecipients()[0].toString()).isEqualTo(to);
        assertThat(current.getSubject()).isEqualTo(subject);
        String result = IOUtils.toString(current.getInputStream(), StandardCharsets.UTF_8);
        assertThat(String.valueOf(result)).contains(text);
    }

}
