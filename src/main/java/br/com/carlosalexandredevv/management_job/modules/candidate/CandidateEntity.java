package br.com.carlosalexandredevv.management_job.modules.candidate;

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

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
@Data
@Entity(name = "candidate")
public class CandidateEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Schema(example = "Daniel de Souza", requiredMode = RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaço")
    @Schema(example = "daniel_souza", requiredMode = RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;

    @Email(message="O campo email deve ser um email válido")
    @Schema(example = "daniel.souza@example.com", requiredMode = RequiredMode.REQUIRED, description = "Email do candidato")
    private String email;

    @Length(min = 8, max = 100, message="A senha deve ter entre 8 e 100 caracteres")
    @Schema(example = "12345678", requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;
    
    private String description;
    @Schema(example = "Curriculum do candidato", requiredMode = RequiredMode.REQUIRED, description = "Curriculum do candidato")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
