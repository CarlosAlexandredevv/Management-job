package br.com.carlosalexandredevv.management_job.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import br.com.carlosalexandredevv.management_job.modules.company.entities.JobEntity;
import br.com.carlosalexandredevv.management_job.modules.company.useCases.CreateJobUseCase;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity) {
        createJobUseCase.execute(jobEntity);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", "Job created successfully");
        }});
    }
    
}
