package br.com.carlosalexandredevv.management_job.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosalexandredevv.management_job.modules.company.repositories.CompanyRepository;
import br.com.carlosalexandredevv.management_job.exceptions.UserFoundException;
import br.com.carlosalexandredevv.management_job.modules.company.entities.CompanyEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent(company -> {
            throw new UserFoundException();
        });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
    
}
