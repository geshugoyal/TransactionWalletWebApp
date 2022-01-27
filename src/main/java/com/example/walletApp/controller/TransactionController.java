package com.example.walletApp.controller;

import com.example.walletApp.config.ResponseListingDto;
import com.example.walletApp.model.Passbook;
import com.example.walletApp.model.TransactionRequest;
import com.example.walletApp.repository.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController(value = "/transaction")
@Api(value = "Transaction API")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/pay")
    @ApiOperation(
            value = "Send Money",
            notes = "To send money to another user.",
            response = ResponseEntity.class
    )
    public ResponseEntity payMoney(@RequestBody TransactionRequest transactionDetails) {
        try {
            return ResponseEntity.ok(transactionService.pay(transactionDetails));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseListingDto(e.getMessage(), BAD_REQUEST));
        }
    }

    @GetMapping(value = "/viewPassbook")
    @ApiOperation(
            value = "View Passbook",
            notes = "To view user complete transaction history.",
            response = ResponseEntity.class
    )
    public ResponseEntity viewPassbook(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "password") String password) {

        try {
            Optional<List<Passbook>> passbook = transactionService.viewPassbook(username, password);

            if(passbook.isPresent()){
                return ResponseEntity.ok(passbook.get());
            } else
                throw new NotFoundException("No Record Present");

        } catch (NotFoundException nfe) {
            return ResponseEntity.badRequest().body(new ResponseListingDto(nfe.getMessage(), BAD_REQUEST));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(new ResponseListingDto(iae.getMessage(), BAD_REQUEST));
        }
    }

    @PostMapping(value = "/addBalance")
    @ApiOperation(
            value = "Add Wallet Balance",
            notes = "To add wallet balance.",
            response = ResponseEntity.class
    )
    public ResponseEntity addBalanceToWallet(@RequestParam(value = "username") String username,
                                             @RequestParam(value = "password") String password,
                                             @RequestParam(value = "addAmount") Long addAmount){
        try {
            return ResponseEntity.ok(transactionService.addBalanceToWallet(username, password, addAmount));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseListingDto(e.getMessage(), BAD_REQUEST));
        }
    }

}
