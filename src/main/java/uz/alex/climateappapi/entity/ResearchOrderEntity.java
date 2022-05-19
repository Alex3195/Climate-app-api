package uz.alex.climateappapi.entity;

import lombok.Getter;
import lombok.Setter;
import uz.alex.climateappapi.constants.ProcessStatus;
import uz.alex.climateappapi.dto.ResearcherOrderDto;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "researcher_orders_for_training_model")
public class ResearchOrderEntity extends BaseServerModifierEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "period")
    private Long periodTrainingData;
    @Column(name = "period_display_data")
    private Long periodGraphicsData;
    @Column(name = "param_name")
    private String paramName;
    @Column(name = "param_code")
    private String paramCode;
    @Column(name = "file_id")
    private Long fileId;
    @Column(name = "process_status")
    @Enumerated(EnumType.STRING)
    private ProcessStatus processStatus;

    public ResearcherOrderDto getDto() {
        ResearcherOrderDto dto = new ResearcherOrderDto();
        dto.setId(getId());
        dto.setCreatedBy(getCreatedBy());
        dto.setCreatedDate(getCreatedDate());
        dto.setTitle(getTitle());
        dto.setPeriodTrainingData(getPeriodTrainingData());
        dto.setPeriodGraphicsData(getPeriodGraphicsData());
        dto.setParamName(getParamName());
        dto.setParamCode(getParamCode());
        dto.setFileId(getFileId());
        dto.setProcessStatus(getProcessStatus());
        return dto;
    }
}
