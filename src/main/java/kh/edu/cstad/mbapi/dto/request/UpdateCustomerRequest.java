package kh.edu.cstad.mbapi.dto.request;

public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}
