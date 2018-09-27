package com.gmail.vpshulgaa.service.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceUtils {
    private static final Logger logger = LogManager.getLogger(ServiceUtils.class);

    public static Transaction getStartedTransaction(Session session) {
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        return transaction;
    }

    public static Long countOfPages(Long countOfEntities, int maxResults){
        Long countOfPages = 0L;
        if (countOfEntities > 0 && countOfEntities <= maxResults) {
            countOfPages = 1L;
        } else if (countOfEntities % maxResults == 0) {
            countOfPages = countOfEntities / maxResults;
        } else {
            countOfPages = countOfEntities / maxResults + 1;
        }
        return countOfPages;
    }
}
