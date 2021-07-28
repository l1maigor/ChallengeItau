package br.com.challenge.itau.service;

import br.com.challenge.itau.model.PasswordResponseModel;

public interface ChallengeService {

    PasswordResponseModel validatePassword(String password);

    Boolean validatePasswordBoolean(String password);

}
