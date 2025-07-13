package kh.edu.cstad.mobilebankingapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateAccountTypeRequest(
        @NotBlank(message = "Account type is required")
        String typeName
) {
}

