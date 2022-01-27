package com.example.walletApp.service;

import com.example.walletApp.exception.PSQLException;
import com.example.walletApp.mapper.UserMapper;
import com.example.walletApp.model.UpdateUserRequest;
import com.example.walletApp.model.User;
import com.example.walletApp.repository.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public boolean signIn(String username, String password) {
        return userMapper.isValidUser(username, password);
    }

    @Override
    public int signUp(User user) {
        try {
            if(checkIfUserNameExists(user.getUsername())){
                throw new IllegalArgumentException("Profile already exists with username: "+ user.getUsername());
            }
            return userMapper.addProfile(user);
        } catch(Exception e){
            log.error("Profile update unsuccessful with username: {}", user.getUsername());
            throw new PSQLException("Profile update unsuccessful. Please contact administrator.");
        }
    }

    @Override
    public int updateProfile(String username, String password, UpdateUserRequest user) {
        try {
            User currentDetail = userMapper.viewProfile(username, password);

            if(checkIfUserNameExists(username))
                throw new IllegalArgumentException("Profile already exists with username: "+ username);

//            to update existing username if not present
            if(user.getNewUsername().isEmpty())
                user.setNewUsername(currentDetail.getUsername());

//            to update existing email Id if not present
            if(user.getEmailId().isEmpty())
                user.setEmailId(currentDetail.getEmailId());

            return userMapper.updateProfile(username, password, user);

        } catch(Exception e){
            log.error("Profile update unsuccessful for username: {}", username);
            throw new PSQLException("Profile update unsuccessful. Please contact administrator.");
        }
    }

    @Override
    public User viewProfile(String username, String password) {

        try {
            return userMapper.viewProfile(username, password);
        } catch(Exception e){
            log.error("Profile update unsuccessful for username: {}", username);
            throw new PSQLException("Profile update unsuccessful. Please contact administrator.");
        }
    }

    public boolean checkIfUserNameExists(String username){
        try {
            String profileExists = userMapper.getUserIdByUserName(username);
            return !profileExists.isEmpty();
        } catch(Exception e){
            log.error("Profile update unsuccessful with username: {}", username);
            throw new PSQLException("Profile update unsuccessful. Please contact administrator.");
        }
    }

}
