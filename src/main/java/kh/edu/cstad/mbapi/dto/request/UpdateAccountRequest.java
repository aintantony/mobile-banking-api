package kh.edu.cstad.mbapi.dto.request;

import java.math.BigDecimal;

public record UpdateAccountRequest(

        String actName,
        BigDecimal overLimit
) {
}
