package br.com.carlosalexandredevv.management_job.modules.company.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

    @Schema(description = "Description of the job", requiredMode = RequiredMode.REQUIRED)
    private String description;

    @Schema(description = "Benefits of the job", requiredMode = RequiredMode.REQUIRED)
    private String benefits;
    
    @Schema(description = "Level of the job", requiredMode = RequiredMode.REQUIRED)
    private String level;
}
