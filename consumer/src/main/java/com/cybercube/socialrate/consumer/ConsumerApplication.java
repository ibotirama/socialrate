package com.cybercube.socialrate.consumer;

import com.cybercube.socialrate.consumer.message.MessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.cybercube.socialrate"})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class
        );
    }

    @Bean
    public MessageListener getMessageListener() {
        return new MessageListener();
    }
}
