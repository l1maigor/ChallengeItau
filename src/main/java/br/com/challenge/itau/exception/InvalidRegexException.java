package br.com.challenge.itau.exception;

public class InvalidRegexException extends RuntimeException {

    public InvalidRegexException(String message) {
        super(message);
    }
}
