package com.gmail.vpshulgaa.service.util;

import java.util.Random;
import java.util.UUID;

public class UIDGeneratorUtils {

    public static String generateUniqueId() {

        String uId = UUID.randomUUID().toString();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(uId.charAt(random.nextInt(uId.length() - 1)));
        }
        return sb.toString();
    }
}
