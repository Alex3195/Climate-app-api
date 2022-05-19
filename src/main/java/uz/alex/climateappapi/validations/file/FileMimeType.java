package uz.alex.climateappapi.validations.file;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(FileMimeType.List.class)
@Documented
@Constraint(validatedBy = FileMimeTypeValidator.class)
public @interface FileMimeType {

    String message() default "Загруженный файл формат должен быть рисунком. .PNG, JPEG, JPG!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] targetTypes() default {};

    /**
     * Defines several {@link FileMimeType} annotations on the same element.
     *
     * @see FileMimeType
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        FileMimeType[] value();
    }
}
