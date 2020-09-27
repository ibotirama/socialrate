package com.cybercube.socialrate.webapi.message;

import com.cybercube.socialrate.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, Person> personKafkaTemplate;

    @Value(value = "${com.cybercube.socialrate.kafka.topic}")
    private String topicName;

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Person> personKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.personKafkaTemplate = personKafkaTemplate;
    }

    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendPersonMessage(Person person) {
        personKafkaTemplate.send(topicName, person);
        log.info("Send message with Person : "+person);
    }
}