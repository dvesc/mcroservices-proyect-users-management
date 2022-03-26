package example.mcroservice.users.utils.custom_annotations.Null_or;

import example.mcroservice.users.utils.custom_annotations.Null_or_email;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class Null_or_email_validator 
implements ConstraintValidator<Null_or_email, String>{

  public void initialize(Null_or_email parameters) {
    // Nothing to do here
  }

  //VALIDACION-----------------------------------------------------------------
  public boolean isValid(String value, 
  ConstraintValidatorContext constraintValidatorContext) {  
    return value == null || Pattern.matches(".+[@].+[\\.].+", value);
  }
}
