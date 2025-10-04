package br.com.carlosalexandredevv.management_job.modules.candidate.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosalexandredevv.management_job.modules.candidate.CandidateEntity;
import br.com.carlosalexandredevv.management_job.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.carlosalexandredevv.management_job.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.carlosalexandredevv.management_job.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.carlosalexandredevv.management_job.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.carlosalexandredevv.management_job.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.carlosalexandredevv.management_job.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Candidate API")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping("/") 
    @Operation(summary = "create candidate", description = "This function is responsible for creating a candidate")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = CandidateEntity.class))
      }),
      @ApiResponse(responseCode = "400", description = "User already exists")
  })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", "Candidate created successfully");
        }});
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Candidate profile", description = "This function is responsible for retrieving candidate profile information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "User not found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = this.profileCandidateUseCase
                    .execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }  

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Find job by filter", description = "Find job by filter")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of filtered jobs", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
        })
    })
    public ResponseEntity<List<JobEntity>> findJobByFilter(@RequestParam String filter) {
        List<JobEntity> jobs = this.listAllJobsByFilterUseCase.execute(filter);
        return ResponseEntity.ok(jobs);
    }
    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Inscrição do candidato para uma vaga", description = "Essa função é responsável por realizar a inscrição do candidato em uma vaga.")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob){
  
      var idCandidate = request.getAttribute("candidate_id");
  
      try{
            var result = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), idJob);
            return ResponseEntity.ok().body(result);
      }catch(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
}
