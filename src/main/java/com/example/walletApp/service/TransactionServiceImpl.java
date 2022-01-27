package com.example.walletApp.service;

import com.example.walletApp.exception.PSQLException;
import com.example.walletApp.mapper.TransactionMapper;
import com.example.walletApp.mapper.UserMapper;
import com.example.walletApp.model.Passbook;
import com.example.walletApp.model.TransactionRequest;
import com.example.walletApp.model.TransactionResponse;
import com.example.walletApp.repository.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionMapper transactionMapper;
    private UserMapper userMapper;

    @Override
    public Optional<List<Passbook>> viewPassbook(String username, String password) {
        return Optional.ofNullable(transactionMapper.getUserTransactionById(username, password));
    }

    @Override
    public TransactionResponse pay(TransactionRequest transactionDetails) {

        UUID transactionId = UUID.randomUUID();
        LocalDateTime transactionDate = LocalDateTime.now();

        Long senderCurrentBalance = transactionMapper.getUserCurrentBalance(transactionDetails.getUsername());
        Long receiverCurrentBalance = transactionMapper.getUserCurrentBalance(transactionDetails.getUsername());

        Long updatedSenderBalance = senderCurrentBalance - transactionDetails.getAmount();

        if(updatedSenderBalance<=0){
            log.error("Insufficient balance in wallet for username: '{}'", transactionDetails.getUsername());
            throw new IllegalArgumentException("Insufficient balance in wallet.");
        }

        Long updatedReceiverBalance = receiverCurrentBalance + transactionDetails.getAmount();

        try {
            transactionMapper.addTransactionForId(transactionId, userMapper.getUserIdByUserName(transactionDetails.getUsername()),
                    "DEBIT", transactionDetails.getAmount(), updatedSenderBalance, transactionDetails.getSendTo(), transactionDate);
            log.info("Rs. {} debited from account: {} with transaction id: {}", transactionDetails.getAmount(), transactionDetails.getUsername(), transactionId );

            transactionMapper.addTransactionForId(transactionId, userMapper.getUserIdByUserName(transactionDetails.getSendTo()),
                    "CREDIT", transactionDetails.getAmount(), updatedReceiverBalance, transactionDetails.getUsername(), transactionDate);
            log.info("Rs. {} credited to account: {} with transaction id: {}", transactionDetails.getAmount(), transactionDetails.getSendTo(), transactionId);

            return new TransactionResponse(
                    transactionId,
                    transactionDetails.getUsername(),
                    transactionDetails.getSendTo(),
                    transactionDetails.getAmount(),
                    "SUCCESS",
                    transactionDate
            );

        } catch(Exception e){
            log.error("Transaction Unsuccessful for username: {}", transactionDetails.getUsername());
            throw new PSQLException("Transaction Unsuccessful with transaction id:"+ transactionId);
        }
    }

    public int addBalanceToWallet(String username, String password, Long addAmount){
        UUID transactionId = UUID.randomUUID();
        LocalDateTime transactionDate = LocalDateTime.now();

        try {
            Long currentUserBalance = transactionMapper.getUserCurrentBalance(username);
            Long updatedBalance = currentUserBalance + addAmount;

            int updatedDetail =  userMapper.addMoneyToWallet(username, password, updatedBalance);
            log.info("Updated wallet balance successfully for username: {} and transaction id:");

            transactionMapper.addTransactionForId(transactionId, username, "CREDIT", addAmount, updatedBalance, "SELF", transactionDate);
            log.info("Transaction to update wallet balance is successful for username: {} and transaction id:{}", username, transactionId);

            return updatedDetail;
        } catch(Exception e){
            log.error("Updating wallet balance is unsuccessful for username: {}", username);
            throw new PSQLException("Transaction Unsuccessful with transaction id:"+ transactionId);
        }
    }

}
