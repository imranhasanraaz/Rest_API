package utils;

import java.util.Random;
import java.util.stream.Collectors;

public class StringGenerator {
    public static String generateRandomAlphabaticString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int startIndex = 0;
        int endIndex = characters.length();
        return new Random()
                .ints(length, startIndex, endIndex)
                .mapToObj(characters::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}