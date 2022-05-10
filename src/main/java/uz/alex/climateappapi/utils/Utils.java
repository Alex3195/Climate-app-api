package uz.alex.climateappapi.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.alex.climateappapi.config.BackendUserDetails;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static boolean empty( final String s ) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }

    public static String getRootPath() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    public static BackendUserDetails getUserDetails() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (token.getPrincipal() instanceof BackendUserDetails) {
                return (BackendUserDetails) token.getPrincipal();
            }
        }
        return null;
    }

    public static String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Long getUserId() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (token.getPrincipal() instanceof BackendUserDetails) {
                BackendUserDetails userDetails = (BackendUserDetails) token.getPrincipal();
                return userDetails.getUserId();
            }
        }
        return null;
    }
}


