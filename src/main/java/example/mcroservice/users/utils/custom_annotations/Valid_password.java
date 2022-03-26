package example.mcroservice.users.utils.custom_annotations;

import javax.validation.Constraint;
import javax.validation.Payload;

import example.mcroservice.users.utils.custom_annotations.password_constraint.Password_constraint_validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = Password_constraint_validator.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Valid_password {

  String message() default "Invalid Password format";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};




}

//https://pharos.sh/validacion-de-contrasena-personalizada-de-spring/
//https://www.adictosaltrabajo.com/2015/08/10/crear-anotaciones-propias-en-java/