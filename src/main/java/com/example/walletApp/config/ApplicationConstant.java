package com.example.walletApp.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConstant {

    public static final String PASSWORD_PATTERN= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    public  static  final  String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,}$";
}
