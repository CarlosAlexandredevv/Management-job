package br.com.carlosalexandredevv.management_job.modules.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import br.com.carlosalexandredevv.management_job.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    List<JobEntity> findByDescriptionContaining(String title);
    
}
