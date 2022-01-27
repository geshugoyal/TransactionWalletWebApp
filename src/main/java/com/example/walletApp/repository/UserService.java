package com.example.walletApp.repository;

import com.example.walletApp.model.UpdateUserRequest;
import com.example.walletApp.model.User;

public interface UserService {

    boolean signIn(String username, String password);

    int signUp(User user);

    int updateProfile(String username, String password, UpdateUserRequest user);

    User viewProfile(String username, String password);

}
