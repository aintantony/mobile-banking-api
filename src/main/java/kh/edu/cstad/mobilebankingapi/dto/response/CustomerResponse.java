package kh.edu.cstad.mobilebankingapi.dto.response;

public record CustomerResponse(
        String fullName,
        String email,
        String phoneNumber,
        String gender
) {
}
