package com.example.datagenerator;

import java.util.Random;

public class MisspellingUtil {

    public static String maybeMisspell(String text, Random random) {
        if (random.nextDouble() < 0.05) {  // 5% misspelling rate
            return text.replace("Samsung", "Samsng")
                    .replace("iPhone", "iphne")
                    .replace("Adidas", "Addidas");
        }
        return text;
    }
}
