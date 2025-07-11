package kh.edu.cstad.mobilebankingapi.dto.request;

public record CreateCustomerRequest(
        String fullName,
        String gender,
        String email,
        String phoneNumber,
        String remark
) {
}
