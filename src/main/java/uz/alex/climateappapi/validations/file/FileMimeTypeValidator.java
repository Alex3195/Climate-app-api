package uz.alex.climateappapi.validations.file;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FileMimeTypeValidator implements ConstraintValidator<FileMimeType, MultipartFile> {

    protected String[] targetTypes;

    public void initialize(FileMimeType parameters) {
        targetTypes = parameters.targetTypes();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        if (targetTypes == null || targetTypes.length == 0) return true;

        return Arrays.stream(targetTypes).anyMatch(s -> s.equalsIgnoreCase(multipartFile.getContentType()));
    }
}
