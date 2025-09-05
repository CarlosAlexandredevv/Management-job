package br.com.carlosalexandredevv.management_job.modules.company.controllers;

import br.com.carlosalexandredevv.management_job.exceptions.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosalexandredevv.management_job.modules.company.dto.AuthCompanyDTO;
import br.com.carlosalexandredevv.management_job.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/company")
  public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException { 
      var result = authCompanyUseCase.execute(authCompanyDTO);
      return ResponseEntity.ok(result);
  }
}