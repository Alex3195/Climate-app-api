package uz.alex.climateappapi.dto.base;

import lombok.Getter;
import lombok.Setter;
import uz.alex.climateappapi.constants.Status;

@Getter
@Setter
public class BaseServerDto {
    private Long id;

    private Status status;
}
