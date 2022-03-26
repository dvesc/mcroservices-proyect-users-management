package example.mcroservice.users.dto.validators;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class New_phone_format_dto {
  @NotNull(message = "first_name cannot be null")
  @NotBlank(message = "first_name cannot be in blank")
  @JsonProperty("phone_number") //Para que reconozca los valores a la hora de crear el dto
  public String phone_number;

  @NotNull(message = "first_name cannot be null")
  @NotBlank(message = "first_name cannot be in blank")
  @JsonProperty("country_code") 
  public String country_code; //Para que reconozca los valores a la hora de crear el dto

  
  //GETTERS AND SETTERS--------------------------------------------------------
  public String getPhoneNumber() {
    return phone_number;
  }

  public void setPhoneNumber(String phone_number) {
    this.phone_number = phone_number;
  }

  public String getCountryCode() {
    return country_code;
  }

  public void setCountryCode(String country_code) {
    this.country_code = country_code;
  }


 

  

}
