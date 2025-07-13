package kh.edu.cstad.mobilebankingapi.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdateAccountRequest(
        String actName,
        BigDecimal balance
) { }
