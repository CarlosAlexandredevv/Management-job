package br.com.carlosalexandredevv.management_job.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import br.com.carlosalexandredevv.management_job.exceptions.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.carlosalexandredevv.management_job.modules.company.dto.AuthCompanyDTO;
import br.com.carlosalexandredevv.management_job.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
        throw new UsernameNotFoundException("Username/password incorrect");
    });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException("Username/password incorrect");
    }

    try{
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("management_job")
            .withSubject(company.getId().toString()).withExpiresAt(Instant.now().plus(Duration.ofHours(2))).sign(algorithm);

        return token;
    } catch (Exception e) {
        throw new AuthenticationException( e.getMessage());
    }
  }
}