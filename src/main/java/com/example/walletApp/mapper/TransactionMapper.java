package com.example.walletApp.mapper;

import com.example.walletApp.model.Passbook;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionMapper {

    List<Passbook> getUserTransactionById(@Param("username") String username,
                                          @Param("password") String password);

    void addTransactionForId(@Param("transactionId") UUID transactionId,
                             @Param("username")String username,
                             @Param("transactionType") String transactionType,
                             @Param("amount") Long amount,
                             @Param("updatedBalance") Long updatedBalance,
                             @Param("transactingUser") String transactingUser,
                             @Param("transactionDate") LocalDateTime transactionDate);

    Long getUserCurrentBalance(@Param("username") String username);

    void updateUserCurrentBalance(@Param("username") String username,
                                  @Param("walletBalance") Long walletBalance);

}
