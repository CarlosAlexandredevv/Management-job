package br.com.carlosalexandredevv.management_job.modules.company.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CreateJobDTO {
    
    private String description;
    private String benefits;
    private String level;
}
