package br.com.challenge.itau.service.impl;

import br.com.challenge.itau.exception.InvalidRegexException;
import br.com.challenge.itau.model.PasswordResponseModel;
import br.com.challenge.itau.service.ChallengeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Log4j2
public class ChallengeServiceImpl implements ChallengeService {

    public PasswordResponseModel validatePassword(String password) {

        if(!regex(password)) {
            throw new InvalidRegexException("Password does not have all parameters");
        }

        log.info("The password has all the necessary parameters");

        return PasswordResponseModel.builder()
                .code(0)
                .message("Password successfully validated")
                .build();
    }

    @Override
    public Boolean validatePasswordBoolean(String password) {

        if(password == null || password.isEmpty()) {
            return Boolean.FALSE;
        }

        if(!regex(password)) {
            return Boolean.FALSE;
        }

        log.info("The password has all the necessary parameters");

        return Boolean.TRUE;
    }

    private Boolean regex(String password){

        //Visando a manutenibilidade e legibilidade do método, foi ajustado da forma a seguir
        //Dessa forma, futuramente é possível também retornar o motivo exato da falha

        if(!Pattern.compile("(?=.*[0-9])", Pattern.MULTILINE).matcher(password).find()) {
            log.warn("Password has no numeric characters");
            return Boolean.FALSE;
        }
        if(!Pattern.compile("(?=.*[a-z])", Pattern.MULTILINE).matcher(password).find()) {
            log.warn("Password does not contain lowercase characters");
            return Boolean.FALSE;
        }
        if(!Pattern.compile("(?=.*[A-Z])", Pattern.MULTILINE).matcher(password).find()) {
            log.warn("Password does not contain uppercase characters");
            return Boolean.FALSE;
        }
        if(!Pattern.compile("(?=.*[!@#$%^&*()-+])", Pattern.MULTILINE).matcher(password).find()) {
            log.warn("Password does not contain special characters");
            return Boolean.FALSE;
        }
        if(!Pattern.compile("(?=\\S+$)", Pattern.MULTILINE).matcher(password).find()) {
            log.warn("Password contains blank characters");
            return Boolean.FALSE;
        }
        if(Pattern.compile("(.)(?=.*\\1)", Pattern.MULTILINE).matcher(password).find()) {
            log.warn("Password contains duplicate characters");
            return Boolean.FALSE;
        }
        if(!password.matches(".{9,}")) {
            log.warn("Password must contain nine or more characters.");
            return Boolean.FALSE;
        }
        else {
            return Boolean.TRUE;
        }
    }
}