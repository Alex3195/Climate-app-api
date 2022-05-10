package uz.alex.climateappapi.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseServerModifierEntity extends BaseServerEntity {
    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Column(name = "createdBy")
    private Long createdBy;

    @Column(name = "modifiedBy")
    private Long modifiedBy;
}
