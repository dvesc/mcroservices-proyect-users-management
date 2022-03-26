package example.mcroservice.users.utils.custom_annotations.Null_or;

import example.mcroservice.users.utils.custom_annotations.Null_or_not_blank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;




public class Null_or_not_blank_validator implements ConstraintValidator<Null_or_not_blank, String>{
  
  public void initialize(Null_or_not_blank parameters) {
    // Nothing to do here
  }

  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
      return value == null || value.trim().length() > 0;
  }
}
