package team.placeholder.internalprojectsmanagementsystem.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void generatePassword() {
        String password = PasswordGenerator.generatePassword(8);
        assertEquals(8, password.length());
        // Ensure the generated password contains at least one uppercase character
        assertTrue(password.chars().anyMatch(Character::isUpperCase));

        // Ensure the generated password contains at least one lowercase character
        assertTrue(password.chars().anyMatch(Character::isLowerCase));

        // Ensure the generated password contains at least one numeric character
        assertTrue(password.chars().anyMatch(Character::isDigit));

        // Ensure the generated password contains at least one special character
        assertTrue(password.chars().anyMatch(ch -> "!@#$%^&*()-_=+[]{}|;:'\"<>,.?/".contains(String.valueOf((char) ch))));

    }

}