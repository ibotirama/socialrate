package com.cybercube.socialrate.consumer.message;

import com.cybercube.socialrate.consumer.services.SocialRateService;
import com.cybercube.socialrate.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class MessageListener {
    @Autowired
    private SocialRateService socialRateService;
    private CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${com.cybercube.socialrate.kafka.topic}", containerFactory = "personKafkaListenerContainerFactory")
    public void personListener(Person person) {
        log.info("Received person message: " + person);
        socialRateService.calc(person);
        this.latch.countDown();
    }

}