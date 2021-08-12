package br.com.challenge.itau.controller;

import br.com.challenge.itau.model.PasswordRequestModel;
import br.com.challenge.itau.model.PasswordResponseModel;
import br.com.challenge.itau.service.ChallengeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/validate")
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping(value = "/password/v1")
    public ResponseEntity<Boolean> validateStringBoolean(@RequestBody PasswordRequestModel passwordRequestModel) {
        return ResponseEntity.ok(challengeService.validatePasswordBoolean(passwordRequestModel.getPassword()));
    }

    @GetMapping(value = "/password/v2")
    public ResponseEntity<PasswordResponseModel> validateString(@RequestBody PasswordRequestModel passwordRequestModel) {
        return ResponseEntity.ok(challengeService.validatePassword(passwordRequestModel.getPassword()));
    }
}