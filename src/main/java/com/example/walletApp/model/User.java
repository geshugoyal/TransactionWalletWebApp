package com.example.walletApp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.example.walletApp.config.ApplicationConstant.PASSWORD_PATTERN;
import static com.example.walletApp.config.ApplicationConstant.USERNAME_PATTERN;

@Data
@Value
@ApiModel(value = "User Information")
public class User {

    @ApiModelProperty(value = "User unique Id")
    int userId;

    @NotNull
    @Pattern(regexp = USERNAME_PATTERN, message = "Invalid Username.")
    @ApiModelProperty(value = "Unique User Name")
    String username;

    @NotNull
    @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain combination of at least 1 numeric, small letter, capital letter and special character.")
    @ApiModelProperty(value = "User Password to login")
    String password;

    @NotNull
    @ApiModelProperty(value = "User Mail Id")
    String emailId;

    @ApiModelProperty(value = "User First Name")
    String firstName;

    @ApiModelProperty(value = "User Last Name")
    String lastName;

    @ApiModelProperty(value = "Numeric value of wallet current balance")
    Long walletBalance;

    @ApiModelProperty(value = "User Phone Number")
    String phoneNumber;

}
