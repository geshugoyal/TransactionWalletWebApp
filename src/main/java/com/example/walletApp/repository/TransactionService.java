package com.example.walletApp.repository;

import com.example.walletApp.model.Passbook;
import com.example.walletApp.model.TransactionRequest;
import com.example.walletApp.model.TransactionResponse;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Optional<List<Passbook>> viewPassbook(String username, String password);

    TransactionResponse pay(TransactionRequest transactionDetails);

    int addBalanceToWallet(String username, String password, Long addAmount);

}
