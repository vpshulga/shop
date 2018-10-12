package com.gmail.vpshulgaa.service.util;

import java.util.Random;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceUtils {
    private static final Logger logger = LogManager.getLogger(ServiceUtils.class);

    public static Long countOfPages(Long countOfEntities, int maxResults) {
        Long countOfPages;
        if (countOfEntities > 0 && countOfEntities <= maxResults) {
            countOfPages = 1L;
        } else if (countOfEntities % maxResults == 0) {
            countOfPages = countOfEntities / maxResults;
        } else {
            countOfPages = countOfEntities / maxResults + 1;
        }
        return countOfPages;
    }

    public static String generateUniqueId() {
        String uId = UUID.randomUUID().toString();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(uId.charAt(random.nextInt(uId.length()-1)));
        }
        return sb.toString();
    }
}
