package com.example.walletApp.controller;

import com.example.walletApp.config.ResponseListingDto;
import com.example.walletApp.model.UpdateUserRequest;
import com.example.walletApp.model.User;
import com.example.walletApp.repository.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController(value = "/user")
@Api(value = "Transaction API")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signIn")
    @ApiOperation(
            value = "Sign In",
            notes = "To check user sign in status.",
            response = ResponseEntity.class
    )
    public ResponseEntity signIn(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password){
        try {
            return ResponseEntity.ok(userService.signIn(username, password) ? "SUCCESS" : "FAILED");
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(new ResponseListingDto(iae.getMessage(), BAD_REQUEST));
        }
    }

    @PostMapping(value = "/signUp")
    @ApiOperation(
            value = "Sign Up",
            notes = "To register new user.",
            response = ResponseEntity.class
    )
    public ResponseEntity signUp(@RequestBody User userDetail) {
        try {
            return ResponseEntity.ok(userService.signUp(userDetail));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(new ResponseListingDto(iae.getMessage(), BAD_REQUEST));
        }
    }

    @GetMapping(value = "/viewProfile")
    @ApiOperation(
            value = "View User Detail",
            notes = "To view user personal details.",
            response = ResponseEntity.class
    )
    public ResponseEntity viewProfile(@RequestParam(value = "username") String username,
                                      @RequestParam(value = "password") String password){
        try {
            return ResponseEntity.ok(userService.viewProfile(username, password));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(new ResponseListingDto(iae.getMessage(), BAD_REQUEST));
        }
    }

    @PutMapping(value = "/updateProfile")
    @ApiOperation(
            value = "Update User Detail",
            notes = "To update existing user details.",
            response = ResponseEntity.class
    )
    public ResponseEntity updateProfile(@RequestParam(value = "username") String username,
                                        @RequestParam(value = "password") String password,
                                        @RequestBody UpdateUserRequest userDetail) {
        try {
            return ResponseEntity.ok(userService.updateProfile(username, password, userDetail));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(new ResponseListingDto(iae.getMessage(), BAD_REQUEST));
        }
    }

}
