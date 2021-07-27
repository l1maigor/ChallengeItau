package br.com.challenge.itau.service.impl;

import br.com.challenge.itau.exception.InvalidRegexException;
import br.com.challenge.itau.model.PasswordResponseModel;
import br.com.challenge.itau.service.ChallengeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ChallengeServiceImpl implements ChallengeService {

    public PasswordResponseModel validatePassword(String password) {

        var parameters = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+])(?=\\S+$).{9,}";

        if(!password.matches(parameters)) {
            throw new InvalidRegexException("Password does not have all parameters");
        }

        log.info("The password has all the necessary parameters");

        return PasswordResponseModel.builder()
                .code(0)
                .message("Password successfully validated")
                .build();
    }

}