package uz.alex.climateappapi.dto.interfaces;

import uz.alex.climateappapi.constants.Role;

public interface UserListInterFace {
    Long getId();

    Boolean getIsActive();

    String getUserName();

    String getFullName();

    String getPassword();

    Role getRole();
}
