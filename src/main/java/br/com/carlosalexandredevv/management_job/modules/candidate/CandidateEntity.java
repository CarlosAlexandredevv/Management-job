package br.com.carlosalexandredevv.management_job.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    private UUID id;
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message="O campo username não pode ser vazio")
    private String username;

    @Email(message="O campo email deve ser um email válido")
    private String email;

    @Length(min = 8, max = 100, message="A senha deve ter entre 8 e 100 caracteres")
    private String password;
    
    private String description;
    private String curriculum;
}
