package com.gmail.vpshulgaa.controllers.handlers;

import com.gmail.vpshulgaa.config.PageProperties;
import com.gmail.vpshulgaa.service.exception.ApiUserException;
import com.gmail.vpshulgaa.service.exception.CustomUnmarshalException;
import com.gmail.vpshulgaa.service.exception.EntityNotFoundException;
import com.gmail.vpshulgaa.service.exception.UnsupportedOperationException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    private final PageProperties pageProperties;

    @Autowired
    public AppExceptionHandler(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @ExceptionHandler
    public String apiException(HttpServletRequest req, ApiUserException e) {
        req.setAttribute("exception", e);
        return pageProperties.getApiErrorPagePath();
    }

    @ExceptionHandler
    public String accessDeniedHandler(HttpServletRequest req, AccessDeniedException e) {
        req.setAttribute("exception", e);
        return pageProperties.getAccessDeniedPagePath();
    }

    @ExceptionHandler
    public String unmarshalException(HttpServletRequest req, CustomUnmarshalException e) {
        req.setAttribute("exception", e);
        return pageProperties.getUnmarshalErrorPagePath();
    }

    @ExceptionHandler
    public String entytyNotFoundException(HttpServletRequest req, EntityNotFoundException e) {
        req.setAttribute("exception", e);
        return pageProperties.getEntityNotFoundPagePath();
    }

    @ExceptionHandler
    public String unsupportedOperationException(HttpServletRequest req, UnsupportedOperationException e) {
        req.setAttribute("exception", e);
        return pageProperties.getUnsupportedErrorPagePath();
    }

    @ExceptionHandler
    public String defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error("Unknown error", e);
        req.setAttribute("exception", e);
        req.setAttribute("url", req.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }
}
