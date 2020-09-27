package com.cybercube.socialrate.webapi.services;

import com.cybercube.socialrate.domain.Person;
import com.cybercube.socialrate.webapi.message.MessageProducer;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageProducer messageProducer;

    public MessageService(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void send(Person person) {
        messageProducer.sendPersonMessage(person);
    }

}
