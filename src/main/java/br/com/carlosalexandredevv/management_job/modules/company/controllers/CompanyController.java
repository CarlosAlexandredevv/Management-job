package br.com.carlosalexandredevv.management_job.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import br.com.carlosalexandredevv.management_job.modules.company.entities.CompanyEntity;
import br.com.carlosalexandredevv.management_job.modules.company.useCases.CreateCompanyUseCase;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        createCompanyUseCase.execute(companyEntity);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", "Company created successfully");
        }});
    }
}
