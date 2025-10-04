package br.com.carlosalexandredevv.management_job.modules.candidate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlosalexandredevv.management_job.modules.candidate.entity.ApplyJobEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
    
}