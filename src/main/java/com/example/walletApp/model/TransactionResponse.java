package com.example.walletApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

import java.util.UUID;

@Data
@Value
@AllArgsConstructor
public class TransactionResponse {

    UUID transactionId;

    String sender;

    String receiver;

    Long amount;

    String transactionStatus;

    LocalDateTime transactionDate;

}
