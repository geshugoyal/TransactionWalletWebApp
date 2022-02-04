package com.example.walletApp.config;

import com.example.walletApp.model.User;
import com.example.walletApp.repository.UserService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Component
public class EnsureIsValidUser implements Filter {

    private final UserService userService;
    public static final List<String> EXCLUDE_URI = Arrays.asList("/signUp");

    public EnsureIsValidUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String requestURI = ((HttpServletRequest) request).getRequestURI();

        if(!EXCLUDE_URI.contains(requestURI)){
            boolean isValidUser = userService.signIn(username, password);

            if(!isValidUser){
                log.error("Invalid user login with username '{}' and password '{}'", username, password);
                String errorMessage = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(new InvalidUserLogin(HttpStatus.BAD_REQUEST.value(), "Username or password is incorrect"));
                response.setContentLength(errorMessage.length());
                ((HttpServletResponse) response).setStatus(HttpStatus.BAD_REQUEST.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(errorMessage);
            }
        }else {
            chain.doFilter(request, response);
        }
    }


    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private static class InvalidUserLogin {
        int status;
        String message;
    }
}
