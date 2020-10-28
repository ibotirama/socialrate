package com.cybercube.socialrate.webapi.controller;

import com.cybercube.socialrate.domain.Person;
import com.cybercube.socialrate.webapi.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class SocialRateController {

    private MessageService messageService;

    public SocialRateController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<?> processMessage(@RequestBody Person person) {
        try {
            log.info("Receiving api call with " + person);
            messageService.send(person);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
