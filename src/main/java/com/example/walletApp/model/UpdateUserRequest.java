package com.example.walletApp.model;

import lombok.Data;

@Data
public class UpdateUserRequest {

    String newUsername;

    String emailId;

    String firstName;

    String lastName;

    String phoneNumber;

}
