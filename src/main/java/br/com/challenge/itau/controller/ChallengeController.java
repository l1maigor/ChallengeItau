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

    @GetMapping(value = "/password")
    public ResponseEntity<PasswordResponseModel> validateString(@RequestBody PasswordRequestModel passwordRequestModel) {
        return ResponseEntity.ok(challengeService.validatePassword(passwordRequestModel.getPassword()));
    }
}