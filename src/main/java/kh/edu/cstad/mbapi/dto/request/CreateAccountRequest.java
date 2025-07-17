package kh.edu.cstad.mbapi.dto.request;

import kh.edu.cstad.mbapi.util.CurrencyUtil;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String actNo,
        String actName,
        CurrencyUtil actCurrency,
        BigDecimal balance,
        String accountType,
        String phoneNumber
) {
}
