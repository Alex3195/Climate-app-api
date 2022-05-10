package uz.alex.climateappapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.alex.climateappapi.dto.TopicDto;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TopicEntity extends BaseServerModifierEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "topicFileId")
    private Long topicFileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicThemeId", insertable = false, updatable = false)
    private TopicFileEntity topicFileEntity;

    public TopicDto getDto() {
        TopicDto dto = new TopicDto();
        BeanUtils.copyProperties(this, dto);
        if (this.title != null)
            dto.setDefaultTitle(this.title);
        return dto;
    }
}
