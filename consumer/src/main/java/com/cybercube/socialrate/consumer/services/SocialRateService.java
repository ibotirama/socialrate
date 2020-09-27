package com.cybercube.socialrate.consumer.services;

import com.cybercube.socialrate.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SocialRateService {
    private final Double seed;

    public SocialRateService(@Value(value = "${com.cybercube.socialrate.seed}") Double seed) {
        this.seed = seed;
    }

    public void calc(Person person) {
        double score = person.getAge() * seed;
        String message = String.format("%s %s has %f score", person.getFirstName(), person.getLastName(), score);
        log.info(message);
    }

}
