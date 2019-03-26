package com.pimp.finance.mfCalculator.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class FundsResponse {

    String fundName;
    BigDecimal profit;
    String date = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(LocalDateTime.now());

    public FundsResponse(String fundName, BigDecimal profit) {
        this.fundName = fundName;
        this.profit = profit;
    }
}
