package org.MK.PopRegister.tools;

import org.MK.PopRegister.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CurrentWorkingDirectoryPathTest {
    Person personTest;
    CurrentWorkingDirectoryPath currentWorkingDirectoryPathTest;
    @BeforeEach
    void setUp() {
        personTest = new Person("Jan", "Kowalski", 1985, 05, 25, "85052512345",
                "Kowalewo", "Kowalska", "5", "5", "12-345", "jankowal1234@gmail.com",
                "123456789");
        currentWorkingDirectoryPathTest = new CurrentWorkingDirectoryPath() {
        };
    }

    @AfterEach
    void tearDown() {
        personTest = null;
        currentWorkingDirectoryPathTest = null;
    }

    @Test
    void pathToString() {
        Path path = Paths.get("");
        assertEquals(path.toAbsolutePath().toString() + "/src/main/IDs/" +
                "85052512345.jpg", currentWorkingDirectoryPathTest.pathToString(personTest));
    }
}