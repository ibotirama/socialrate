package com.cybercube.socialrate.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Objects;
import java.util.stream.Stream;

@Value
public class Person {
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name")
    private String lastName;
    @JsonProperty(value = "age")
    private Integer age;

    @JsonCreator
    public Person(String firstName, String lastName, Integer age) {
        boolean match = Stream.of(firstName, lastName, age).anyMatch(Objects::isNull);
        if (match) {
            throw new IllegalArgumentException("All attributes of Person are required.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
