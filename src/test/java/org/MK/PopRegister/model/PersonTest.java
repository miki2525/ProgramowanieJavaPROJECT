package org.MK.PopRegister.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person personTest;
    @BeforeEach
    void setUp() {
        personTest = new Person("Jan", "Kowalski", 1985, 05, 25, "85052512345",
                "Kowalewo", "Kowalska", "5", "5", "12-345", "jankowal1234@gmail.com",
                "123456789");
    }

    @AfterEach
    void tearDown() {
        personTest = null;
    }

    @Test
    void toStringStreetNumber() {
        assertEquals("Kowalska 5", personTest.toStringStreetNumber());
        assertNotEquals("Kowalska5", personTest.toStringStreetNumber());
    }
}