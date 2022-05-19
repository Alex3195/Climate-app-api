package uz.alex.climateappapi.validations.user;

import org.springframework.beans.factory.annotation.Autowired;
import uz.alex.climateappapi.dto.UserDto;
import uz.alex.climateappapi.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUniqueUsernameValidator implements ConstraintValidator<CheckUniqueUsername, UserDto> {

    @Autowired
    private UserRepository userRepository;

   @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        if(userDto.getId() == null && userRepository.findByUsernameIgnoreCase(userDto.getUsername()).isPresent()){
            return false;
        }
        return true;
    }
}
