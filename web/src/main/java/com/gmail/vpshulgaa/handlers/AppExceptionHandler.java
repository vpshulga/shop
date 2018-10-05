package com.gmail.vpshulgaa.handlers;

import com.gmail.vpshulgaa.config.PageProperties;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    private final PageProperties pageProperties;

    @Autowired
    public AppExceptionHandler(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @ExceptionHandler
    public String defaultErrorHandler(HttpServletRequest req, Exception e) {
        req.setAttribute("exception", e);
        req.setAttribute("url", req.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }
}
