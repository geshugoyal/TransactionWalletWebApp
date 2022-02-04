package com.example.walletApp.mapper;

import com.example.walletApp.model.UpdateUserRequest;
import com.example.walletApp.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int createUserTable();

    boolean isValidUser(@Param("username") String username,
                    @Param("password") String password);

    int addMoneyToWallet(@Param("username") String username,
                            @Param("password") String password,
                            @Param("walletBalance") Long walletBalance);

    int addProfile(@Param("userDetails") User userDetail);

    int updateProfile(@Param("username") String username,
                      @Param("password") String password,
                      @Param("userDetail") UpdateUserRequest userDetail);

    User viewProfile(@Param("username") String username,
                     @Param("password") String password);

    String getUserIdByUserName(@Param("username") String username);

}
