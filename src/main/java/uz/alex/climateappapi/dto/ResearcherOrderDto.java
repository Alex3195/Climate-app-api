package uz.alex.climateappapi.dto;

import lombok.Data;
import uz.alex.climateappapi.constants.ProcessStatus;
import uz.alex.climateappapi.dto.base.BaseServerModifierDto;

@Data
public class ResearcherOrderDto extends BaseServerModifierDto {
    private String title;
    private Long periodTrainingData;
    private Long periodGraphicsData;
    private String paramName;
    private String paramCode;
    private Long fileId;
    private ProcessStatus processStatus;
}
