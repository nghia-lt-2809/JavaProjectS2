package MainSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class GenerateRandom {
    private final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private final String digits = "0123456789"; // 0-9
    private final String billID = alpha + alphaUpperCase + digits;
    private final String customerID = alphaUpperCase + digits;
    private final String bookingCode = digits;
    private Random generator = new Random();

    public String randomBillId(int numberCharacter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberCharacter; i++) {
            int number = randomNumber(0, billID.length() - 1);
            char ch = billID.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public String randomCustomerId(int numberCharacter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberCharacter; i++) {
            int number = randomNumber(0, customerID.length() - 1);
            char ch = customerID.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public String randomBookingCode(int numberCharacter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberCharacter; i++) {
            int number = randomNumber(0, bookingCode.length() - 1);
            char ch = bookingCode.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
}