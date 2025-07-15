package kh.edu.cstad.mobilebankingapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kh.edu.cstad.mobilebankingapi.domain.KYC;
import kh.edu.cstad.mobilebankingapi.repository.KYCRepository;
import kh.edu.cstad.mobilebankingapi.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KYCServiceImpl implements KYCService {
    private final KYCRepository kycRepository;


    @Override
    public void verifyCustomerByNationalCardId(String nationalCardId) {
        KYC kyc = kycRepository
                .findByNationalCardId(nationalCardId)
                .orElseThrow(()->new EntityNotFoundException("NationalCardId not found"));

        kyc.setIsVerified(true);
        kycRepository.save(kyc);
    }
}