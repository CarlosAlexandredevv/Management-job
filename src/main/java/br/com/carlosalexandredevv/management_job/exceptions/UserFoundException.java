package br.com.carlosalexandredevv.management_job.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("User already exists");
    }
}
