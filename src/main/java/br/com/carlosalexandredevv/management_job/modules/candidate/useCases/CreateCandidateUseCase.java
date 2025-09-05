package br.com.carlosalexandredevv.management_job.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosalexandredevv.management_job.modules.candidate.exceptions.UserFoundExcpetion;
import br.com.carlosalexandredevv.management_job.modules.candidate.CandidateEntity;
import br.com.carlosalexandredevv.management_job.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
        candidateEntity.getEmail()).ifPresent(user -> {
          throw new UserFoundExcpetion();
        });

    return this.candidateRepository.save(candidateEntity);
  }
}