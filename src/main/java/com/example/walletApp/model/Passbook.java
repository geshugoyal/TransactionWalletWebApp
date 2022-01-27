package com.example.walletApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@ApiModel(value = "Passbook", description = "User Transaction Details")
public class Passbook {

    @ApiModelProperty(value = "Transaction unique Id")
    UUID transactionId;

    @ApiModelProperty(value = "User unique Id")
    String userId;

    @ApiModelProperty(value = "Transaction Type", required = true, example = "CREDIT|DEBIT")
    String transactionType;

    @ApiModelProperty(value = "Numeric value of transaction amount")
    Long amount;

    @ApiModelProperty(value = "Date of transaction")
    Date transactionDate;

    @ApiModelProperty(value = "Numeric value of wallet current balance")
    Long walletBalance;

    @ApiModelProperty(value = "Transacted to/from user Id")
    String description;

}
