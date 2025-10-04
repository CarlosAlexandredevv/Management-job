package br.com.carlosalexandredevv.gestao_vagas.modules.company.controllers;


import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.carlosalexandredevv.management_job.modules.company.dto.CreateJobDTO;
import br.com.carlosalexandredevv.management_job.modules.company.entities.CompanyEntity;
import br.com.carlosalexandredevv.management_job.modules.company.repositories.CompanyRepository;
import br.com.carlosalexandredevv.gestao_vagas.utils.TestUtils;

@SpringBootTest(classes = br.com.carlosalexandredevv.management_job.GestaoVagasApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {
    
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception{

        var company = new CompanyEntity();
        company.setDescription("COMPANY_DESCRIPTION");
        company.setEmail("email@company.com");
        company.setPassword("1234567890");
        company.setUsername("COMPANY_USERNAME");
        company.setName("COMPANY_NAME");
        company.setWebsite("https://company.com");

        company = companyRepository.saveAndFlush(company);

        var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(createdJobDTO))
            .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_@123#"))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }

    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception{
        var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(createdJobDTO))
            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "JAVAGAS_@123#")))    
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }    
}