package kh.edu.cstad.mobilebankingapi.dto.request;

public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}
