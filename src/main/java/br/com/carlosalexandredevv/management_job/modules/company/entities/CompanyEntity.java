package br.com.carlosalexandredevv.management_job.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaço")
    private String username;

    @Email(message="O campo email deve ser um email válido")
    private String email;

    @Length(min = 8, max = 100, message="A senha deve ter entre 8 e 100 caracteres")
    private String password;

    @NotBlank
    @Pattern(regexp = "^https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_.~#?&//=]*)$", message = "O campo website deve ser uma URL válida")
    private String website;
    
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
