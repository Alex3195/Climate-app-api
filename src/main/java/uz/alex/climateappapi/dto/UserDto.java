package uz.alex.climateappapi.dto;

import lombok.Data;
import uz.alex.climateappapi.constants.Role;
import uz.alex.climateappapi.dto.base.BaseServerModifierDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class UserDto extends BaseServerModifierDto {
    @NotEmpty
    private String fullName;

    @NotEmpty(message = "Пожалуйста, введите аккоунт название пользователя")
    private String username;

    private String password;

    private String newPassword;

    @NotNull
    private Role role;

    private Boolean isActive;

}
