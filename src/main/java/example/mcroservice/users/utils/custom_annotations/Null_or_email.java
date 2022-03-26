package example.mcroservice.users.utils.custom_annotations;
import javax.validation.Constraint;
import javax.validation.Payload;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import example.mcroservice.users.utils.custom_annotations.Null_or.Null_or_email_validator;

@Documented
@Constraint(validatedBy = Null_or_email_validator.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface Null_or_email {
  String message() default "'Email' must be a valid email";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
