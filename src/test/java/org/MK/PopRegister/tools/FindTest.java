package org.MK.PopRegister.tools;

import javafx.collections.FXCollections;
import org.MK.PopRegister.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindTest {
        Person personTest1;
        Person personTest2;
        Person personTest3;
        List<Person> sourceListTest;
        List<Person> destListTest;
        List<Person> compareListTest;
        String phraseTest;
    @BeforeEach
    void setUp() {
        personTest1 = new Person("Jan", "Kowalski", 1985, 05, 25, "85052512345",
                "Kowalewo", "Kowalska", "5", "5", "12-345", "jankowal1234@gmail.com",
                "123456789");
        personTest2 = new Person("Anna", "Kowalska", 1991, 11, 30, "9111300811",
                "Bąkowo", "Bąkowska", "11", "7", "13-447", "",
                "");
        personTest3 = new Person("Sebastian", "Kowalski", 1946, 11, 20, "46112020881",
                "Gdynia", "Traugutta", "21", "1", "80-554", "",
                "");

        sourceListTest = FXCollections.observableArrayList();
        destListTest = FXCollections.observableArrayList();
        compareListTest = FXCollections.observableArrayList();

        sourceListTest.add(personTest1);
        sourceListTest.add(personTest2);
        sourceListTest.add(personTest3);

        phraseTest = "Kowalski";
    }

    @AfterEach
    void tearDown() {
        personTest1 = null;
        personTest2 = null;
        personTest3 = null;
        sourceListTest = null;
        destListTest = null;
        compareListTest = null;
        phraseTest = null;
    }

    @Test
    void findThePhrase() {
        destListTest = Find.findThePhrase(phraseTest, sourceListTest, destListTest);
        compareListTest.add(personTest1);
        compareListTest.add(personTest3);
        assertEquals(compareListTest, destListTest);
    }
}