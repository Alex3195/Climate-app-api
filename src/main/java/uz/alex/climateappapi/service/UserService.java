package uz.alex.climateappapi.service;

import uz.alex.climateappapi.dto.UserDto;
import uz.alex.climateappapi.model.ApiResponse;

public interface UserService {
    ApiResponse add(UserDto dto);

    ApiResponse edit(UserDto dto);

    ApiResponse getById(Long id);

    ApiResponse archivingUserById(Long id);

    UserDto getCurrentUserDetails();
}
