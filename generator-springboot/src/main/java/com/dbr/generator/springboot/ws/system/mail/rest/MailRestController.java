package com.dbr.generator.springboot.ws.system.mail.rest;

import com.dbr.generator.springboot.ws.system.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

@Api(tags = MailRestController.PATH_PREFIX)
@RestController
@RequiredArgsConstructor
@RequestMapping(MailRestController.PATH_PREFIX)
public class MailRestController {

    public static final String PATH_PREFIX = "SYSTEM/MAIL";

    @Autowired
    private MailService service;

    @ApiOperation(value = "Send mail. No attachment.")
    @GetMapping
    public ResponseEntity sendMail(String to, String subject, String text) throws MessagingException {
        service.sendMail(to, subject, text, false, null);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Send mail with attachment.")
    @PostMapping(path = "/ATTACHMENT")
    public ResponseEntity sendMailWithAttachment(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("text") String text, @RequestParam("attachmentFile") MultipartFile attachmentFile) throws MessagingException {
        service.sendMail(to, subject, text, false, attachmentFile);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Send html mail. No attachment.")
    @GetMapping(path = "/HTML")
    public ResponseEntity sendHTMLMail(String to, String subject, String text) throws MessagingException {
        service.sendMail(to, subject, text, true, null);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Send html mail with attachment.")
    @PostMapping(path = "/HTML/ATTACHMENT")
    public ResponseEntity sendHTMLMailWithAttachment(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("text") String text, @RequestParam("attachmentFile") MultipartFile attachmentFile) throws MessagingException {
        service.sendMail(to, subject, text, true, attachmentFile);
        return ResponseEntity.ok().build();
    }

}
