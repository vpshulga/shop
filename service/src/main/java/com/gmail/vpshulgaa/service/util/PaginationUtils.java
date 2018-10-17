package com.gmail.vpshulgaa.service.util;

public class PaginationUtils {
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
}
