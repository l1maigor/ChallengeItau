package br.com.challenge.itau.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PasswordResponseModel {

    private Integer code;
    private String message;
}
