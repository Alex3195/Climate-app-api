package uz.alex.climateappapi.constants;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRATOR,
    USER;


    @Override
    public String getAuthority() {
        return name();
    }
}
