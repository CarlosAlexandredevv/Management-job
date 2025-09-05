package br.com.carlosalexandredevv.management_job.exceptions;

public class AuthenticationException extends RuntimeException {
    
    public AuthenticationException(String message) {
        super(message);
    }
}
