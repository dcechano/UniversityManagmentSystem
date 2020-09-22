package com.example.ums.config.security;

import com.example.ums.entities.person.Person;
import com.example.ums.repos.PersonRepo;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomAuthentication implements AuthenticationSuccessHandler {

    private PersonRepo personRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Logger logger = Logger.getLogger(getClass().toString());
        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());
        logger.info("Inside the onAuthenticationSuccess method!");
        BasicConfigurator.configure();
        log.warn("Inside the onAuthenticationSuccess method!");
        String username = authentication.getName();

        Person person = personRepo.findByUsername(username);

        HttpSession session = httpServletRequest.getSession();

        session.setAttribute("user", person);
        logger.info("Stored user in the session. Redirecting ... ");

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");

    }

    @Autowired
    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }
}
