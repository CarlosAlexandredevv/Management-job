package br.com.carlosalexandredevv.management_job.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosalexandredevv.management_job.modules.company.repositories.JobRepository;
import br.com.carlosalexandredevv.management_job.modules.company.entities.JobEntity;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;
    
    public JobEntity execute(JobEntity jobEntity) {

        return this.jobRepository.save(jobEntity);
    }
}
