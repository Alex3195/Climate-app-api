package uz.alex.climateappapi.entity;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import uz.alex.climateappapi.constants.Role;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;
import uz.alex.climateappapi.dto.UserDto;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class UserEntity extends BaseServerModifierEntity {
    @Column(name = "fullName")
    private String fullName;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "isActive", columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;


    public UserDto getDto() {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(this, userDto, "password");
        return userDto;
    }
}
