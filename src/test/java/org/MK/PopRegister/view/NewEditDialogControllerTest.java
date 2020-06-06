package org.MK.PopRegister.view;

import javafx.scene.control.TextField;
import org.MK.PopRegister.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewEditDialogControllerTest {

    NewEditDialogController ctrlTest;
    Person personTest;

    @BeforeEach
    void setUp() {
        ctrlTest = new NewEditDialogController();
        personTest = new Person("Jan", "Kowalski", 1985, 05, 25, "85052512345",
                "Kowalewo", "Kowalska", "5", "5", "12-345", "jankowal1234@gmail.com",
                "123456789");
    }

    @AfterEach
    void tearDown() {
        ctrlTest = null;
        personTest = null;
    }

    @Test
    void isEmailValid() {
        assertTrue(ctrlTest.isEmailValid(personTest.getEmail()));
    }

    @Test
    void isContainingInt() {
        assertTrue(ctrlTest.isContainingInt(personTest.getFlatNumber()));
        assertNotEquals(false, ctrlTest.isContainingInt(personTest.getPhoneNumber()));
    }

    @Test
    void isContainingLong() {
        assertTrue(ctrlTest.isContainingLong(personTest.getPesel()));
    }

    @Test
    void isContainingLetters() {
        assertTrue(ctrlTest.isContainingLetters(personTest.getName()));
        assertTrue(ctrlTest.isContainingLetters(personTest.getLocality()));
        assertNotEquals(false, ctrlTest.isContainingLetters(personTest.getStreet()));

    }

    @Test
    void validateSurname() {
        assertTrue(ctrlTest.validateSurname(personTest.getSurname()));
    }

    @Test
    void isContainingIntLet() {
        assertEquals(true, ctrlTest.isContainingIntLet(personTest.getHouseblockNumber()));
    }

    @Test
    void isPostalValid() {
        assertNotEquals(false, ctrlTest.isPostalValid(personTest.getPostalCode()));
    }
}