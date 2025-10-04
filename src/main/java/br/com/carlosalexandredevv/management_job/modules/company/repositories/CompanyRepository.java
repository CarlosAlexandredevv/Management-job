package br.com.carlosalexandredevv.management_job.modules.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import br.com.carlosalexandredevv.management_job.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
    Optional<CompanyEntity> findById(UUID id);
    Optional<CompanyEntity> findByUsername(String username);

}
