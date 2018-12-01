package com.rlouro.provider.controller.error;

/**
 * Mensagem de erro simples.
 */
public class ErrorMessage {

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
