package kh.edu.cstad.mobilebankingapi.controller;

import kh.edu.cstad.mobilebankingapi.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class KYCController {

    private final KYCService kycService;

    @PutMapping("/customers/{nationalCardId}/verify")
    ResponseEntity<String> verify(@PathVariable String nationalCardId) {
        kycService.verifyCustomerByNationalCardId(nationalCardId);
        return ResponseEntity.ok().body("Customer has been verified successfully");
    }

}