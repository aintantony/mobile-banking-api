package kh.edu.cstad.mbapi.service;

import kh.edu.cstad.mbapi.dto.response.AccountResponse;
import kh.edu.cstad.mbapi.dto.request.CreateAccountRequest;

/**
 * Service interface for managing account operations.
 *
 * @since 1.0
 * @author Antony
 */
public interface AccountService {

    /**
     * Creates a new account based on the provided request data.
     *
     * @param createAccountRequest {@link CreateAccountRequest} the request object containing account details
     * @return the response object containing created account information - {@link AccountResponse}
     */
    AccountResponse createNew(CreateAccountRequest createAccountRequest);

}

