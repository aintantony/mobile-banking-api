package kh.edu.cstad.mbapi.controller;

import kh.edu.cstad.mbapi.domain.KYC;
import kh.edu.cstad.mbapi.repository.KYCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KYCController {

    private final KYCRepository kycRepository;

}
