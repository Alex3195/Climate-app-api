package uz.alex.climateappapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.alex.climateappapi.dto.TopicFileDto;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TopicFileEntity extends BaseServerModifierEntity {
    @Column(name = "displayName")
    private String displayName;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "filePath")
    private String filePath;

    @Column(name = "fileSize")
    private Long fileSize;

    @Column(name = "isPublished", columnDefinition = "boolean default false")
    private Boolean isPublished;

    @Column(name = "topicId")
    private Long topicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicId", insertable = false, updatable = false)
    private TopicEntity topicEntity;

    public TopicFileDto getDto() {
        TopicFileDto dto = new TopicFileDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
