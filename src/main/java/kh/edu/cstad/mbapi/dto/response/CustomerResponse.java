package kh.edu.cstad.mbapi.dto.response;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String fullName,
        String gender,
        String email
) {
}
