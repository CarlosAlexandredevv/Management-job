package br.com.carlosalexandredevv.management_job.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosalexandredevv.management_job.modules.company.repositories.CompanyRepository;
import br.com.carlosalexandredevv.management_job.exceptions.UserFoundException;
import br.com.carlosalexandredevv.management_job.modules.company.entities.CompanyEntity;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity){

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent(company -> {
            throw new UserFoundException();
        });

        return this.companyRepository.save(companyEntity);
    }
    
}
