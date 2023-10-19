package team.placeholder.internalprojectsmanagementsystem.util;

import java.security.SecureRandom;

public class PasswordGenerator {

    int length = 8; // Minimum length of 8 characters
    String password = generatePassword(length);

    public static String generatePassword(int length) {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numericChars = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\"<>,.?/";

        String allChars = uppercaseChars + lowercaseChars + numericChars + specialChars;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one character from each character set
        password.append(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
        password.append(lowercaseChars.charAt(random.nextInt(lowercaseChars.length())));
        password.append(numericChars.charAt(random.nextInt(numericChars.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Generate the remaining characters
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle the characters to make the password more random
        char[] passwordArray = password.toString().toCharArray();
        for (int i = length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[index];
            passwordArray[index] = temp;
        }
        return new String(passwordArray);
    }

}
