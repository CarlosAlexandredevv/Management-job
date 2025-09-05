package br.com.carlosalexandredevv.management_job.modules.candidate.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("User already exists");
    }
}
