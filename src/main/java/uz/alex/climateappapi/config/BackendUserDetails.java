package uz.alex.climateappapi.config;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import uz.alex.climateappapi.constants.Role;

import java.util.Collection;

@Getter
public class BackendUserDetails extends User {

    private Long userId;
    private String fullName;
    private String username;
    private Role role;

    public BackendUserDetails(String username, String password, Long userId, String fullName, Role role, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
        this.fullName = fullName;
        this.role = role;
        this.username = username;
    }
}
