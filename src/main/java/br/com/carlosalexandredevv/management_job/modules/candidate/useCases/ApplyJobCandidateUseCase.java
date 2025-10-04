package br.com.carlosalexandredevv.management_job.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosalexandredevv.management_job.exceptions.JobNotFoundException;
import br.com.carlosalexandredevv.management_job.exceptions.UserNotFoundException;
import br.com.carlosalexandredevv.management_job.modules.candidate.CandidateRepository;
import br.com.carlosalexandredevv.management_job.modules.candidate.entity.ApplyJobEntity;
import br.com.carlosalexandredevv.management_job.modules.candidate.repository.ApplyJobRepository;
import br.com.carlosalexandredevv.management_job.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;
    
    // ID do candidato
    // ID da vaga
    public ApplyJobEntity execute(UUID idCandidate, UUID idJob){
        // Validar se o candidato existe
        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        // Validar se a vaga existe
        this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
            throw new JobNotFoundException();
        });

        // Candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}