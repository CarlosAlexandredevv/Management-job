package br.com.carlosalexandredevv.management_job.modules.candidate.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosalexandredevv.management_job.modules.candidate.CandidateEntity;
import br.com.carlosalexandredevv.management_job.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/") 
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", "Candidate created successfully");
        }});
    }
    
}
