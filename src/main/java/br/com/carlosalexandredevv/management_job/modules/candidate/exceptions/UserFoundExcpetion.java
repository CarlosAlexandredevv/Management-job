package br.com.carlosalexandredevv.management_job.modules.candidate.exceptions;

public class UserFoundExcpetion extends RuntimeException{
    public UserFoundExcpetion() {
        super("User already exists");
    }
}
