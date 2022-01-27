package com.example.walletApp.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "Transaction Request")
public class TransactionRequest {

    @ApiModelProperty(value = "Transaction unique Id")
    String transactionId;

    @ApiModelProperty(value = "Unique User Name")
    String username;

    @ApiModelProperty(value = "Username of Receiver")
    String sendTo;

    @NotNull(message = "Please enter valid amount.")
    @Max(value = 15000, message = "Maximum amount allowed is Rs.15000")
    @Min(value = 1, message = "Minimum amount allowed is Rs.1")
    @ApiModelProperty(value = "Numeric value of transaction amount")
    Long amount;

}
