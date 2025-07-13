package kh.edu.cstad.mobilebankingapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CustomerPhoneNumberRequest(
        @NotBlank(message = "Phone number is required")
        String phoneNumber
) { }

