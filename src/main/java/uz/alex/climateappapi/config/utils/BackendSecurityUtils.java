package uz.alex.climateappapi.config.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import uz.alex.climateappapi.config.BackendUserDetails;

public class BackendSecurityUtils {
    public static Long getUserId(){
        if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if(token.getPrincipal() instanceof UserDetails){
                BackendUserDetails userDetails = (BackendUserDetails) token.getPrincipal();
                return userDetails.getUserId();
            }
        }
        return null;
    }

    public static BackendSecurityUtils getUserDetails(){
        if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if(token.getPrincipal() instanceof UserDetails){
                return (BackendSecurityUtils) token.getPrincipal();
            }
        }
        return null;
    }
}
