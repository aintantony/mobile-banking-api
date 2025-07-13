package kh.edu.cstad.mobilebankingapi.dto.response;

import lombok.Builder;

@Builder
public record AccountTypeResponse(
        String typeName
) {
}