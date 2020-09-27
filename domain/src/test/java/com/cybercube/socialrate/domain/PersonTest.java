package com.cybercube.socialrate.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {
    @Test
    public void shouldNotAcceptPersonWithFirstNameNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(null, "Andrade", 41);
        });
    }

    @Test
    public void shouldNotAcceptPersonWithLastNameNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Pedro", null, 41);
        });
    }

    @Test
    public void shouldNotAcceptPersonWithNameNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Pedro", "Andrade", null);
        });
    }


}