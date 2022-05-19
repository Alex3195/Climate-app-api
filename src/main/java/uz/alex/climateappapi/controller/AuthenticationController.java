package uz.alex.climateappapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.alex.climateappapi.config.BackendUserDetailsService;
import uz.alex.climateappapi.constants.AuthenticationRequest;
import uz.alex.climateappapi.dto.UserDto;
import uz.alex.climateappapi.filter.JwtUtils;
import uz.alex.climateappapi.service.impl.UserServiceImpl;
import uz.alex.climateappapi.utils.Utils;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    private final BackendUserDetailsService userDetailsService;
    private final UserServiceImpl userServiceImpl;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest auth) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);

        HashMap<String, Object> token = new HashMap<String, Object>() {{
            put("token", jwt);
            put("user", userDetailsService.getId(auth.getUsername()).getDto());
        }};

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.add(dto));
    }

    @GetMapping("/login{logout}")
    public void logoutAndDeleteUserToken(@PathVariable("logout") String logout) {
        //@Todo: service need to be written
        System.out.println(Utils.getUsername());
    }

    @GetMapping(path = "/user")
    public ResponseEntity<UserDto> getUserDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.getCurrentUserDetails());
    }
}
