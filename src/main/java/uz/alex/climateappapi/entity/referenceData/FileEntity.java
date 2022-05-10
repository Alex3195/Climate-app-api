package uz.alex.climateappapi.entity.referenceData;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.alex.climateappapi.dto.FileDto;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class FileEntity extends BaseServerModifierEntity {
    @Column(name = "displayName")
    private String displayName;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "filePath")
    private String filePath;

    @Column(name = "fileSize")
    private Long fileSize;

    public FileDto getDto(){
        FileDto fileDto = new FileDto();
        BeanUtils.copyProperties(this,fileDto);
        return fileDto;
    }
}
