package br.com.challenge.itau.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordResponseModel {

    private Integer code;
    private String message;
}
