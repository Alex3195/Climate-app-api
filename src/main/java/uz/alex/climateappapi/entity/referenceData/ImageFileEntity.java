package uz.alex.climateappapi.entity.referenceData;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.alex.climateappapi.dto.ImageFileDto;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ImageFileEntity extends BaseServerModifierEntity {
    @Column(name = "displayName")
    private String displayName;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "filePath")
    private String filePath;

    @Column(name = "fileSize")
    private Long fileSize;

    public ImageFileDto getDto() {
        ImageFileDto dto = new ImageFileDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
