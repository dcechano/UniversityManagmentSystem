package com.example.ums.config.security;

import com.example.ums.entities.person.Person;
import com.example.ums.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

// TODO clean up this class and remove the debug code and unnecessary fields.
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    Logger logger = Logger.getLogger(getClass().toString());

    PersonRepo personRepo;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("inside the " + getClass().getSimpleName());

        if (auth != null) {
            logger.warning("User: " + auth.getName() + "Attempted to access the protected URL");

        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/accessDenied");

    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
