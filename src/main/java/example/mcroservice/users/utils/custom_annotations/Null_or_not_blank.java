package example.mcroservice.users.utils.custom_annotations;
import javax.validation.Constraint;
import javax.validation.Payload;

import example.mcroservice.users.utils.custom_annotations.Null_or.Null_or_not_blank_validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = Null_or_not_blank_validator.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Null_or_not_blank {

  String message() default "The field cannot be in blank";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};


}
