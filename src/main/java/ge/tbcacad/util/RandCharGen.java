package ge.tbcacad.util;

import java.util.Random;

public class RandCharGen {
    public static String generateRandomSpecialChars(int length) {
        String specialChars = "!@#$%^&*()";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(specialChars.length());
            char randomChar = specialChars.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
