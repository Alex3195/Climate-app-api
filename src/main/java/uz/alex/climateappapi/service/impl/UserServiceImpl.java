package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import uz.alex.climateappapi.config.utils.BackendSecurityUtils;
import uz.alex.climateappapi.constants.Status;
import uz.alex.climateappapi.entity.UserEntity;
import uz.alex.climateappapi.dto.UserDto;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.repository.UserRepository;
import uz.alex.climateappapi.service.UserService;
import uz.alex.climateappapi.utils.Utils;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public ApiResponse add(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(dto, userEntity, "isActive");
        if (!StringUtils.isEmpty(dto.getPassword()))
            userEntity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userEntity.setStatus(Status.CREATED);
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setCreatedBy(BackendSecurityUtils.getUserId());
        userEntity = userRepository.save(userEntity);
        return ApiResponse.success(true, userEntity.getDto());
    }

    @Transactional
    @Override
    public ApiResponse edit(UserDto dto) {
        if (dto.getId() == null)
            return ApiResponse.success(false, "Этот ID не должно быть пустым!");

        Optional<UserEntity> userEntityOptional = userRepository.findById(dto.getId());
        if (userEntityOptional.isPresent()) {
            BeanUtils.copyProperties(dto, userEntityOptional.get(), "password", "createdDate", "createdBy");
            userEntityOptional.get().setUpdatedDate(LocalDateTime.now());
            userEntityOptional.get().setModifiedBy(BackendSecurityUtils.getUserId());
            userEntityOptional.get().setStatus(Status.ACTIVE);
            userEntityOptional.get().setIsActive(true);
            userRepository.save(userEntityOptional.get());
            return ApiResponse.success(true, userEntityOptional.get().getDto());
        } else {
            return ApiResponse.success(false, "Такой ID не существует");
        }
    }

    @Override
    public ApiResponse getById(Long id) {
        if (id == null)
            return ApiResponse.success(false, "Этот ID не должно быть пустым!");
        Optional<UserEntity> findbyId = userRepository.findById(id);
        return findbyId.map(userEntity -> ApiResponse.success(true, userEntity)).orElse(null);
    }

    @Transactional
    @Override
    public ApiResponse archivingUserById(Long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setStatus(Status.DELETED);
            userRepository.save(byId.get());
            return ApiResponse.ok();
        } else {
            return ApiResponse.success(false, "Такой ID не существует");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getCurrentUserDetails() {
        Optional<UserEntity> optional = userRepository.findByUsernameIgnoreCase(Utils.getUsername());
        return optional.map(UserEntity::getDto).orElse(null);
    }
}
