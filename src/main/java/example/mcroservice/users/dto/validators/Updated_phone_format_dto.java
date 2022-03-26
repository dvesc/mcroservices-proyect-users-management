package example.mcroservice.users.dto.validators;

import com.fasterxml.jackson.annotation.JsonProperty;

import example.mcroservice.users.utils.custom_annotations.Null_or_not_blank;

public class Updated_phone_format_dto {
  @Null_or_not_blank
  @JsonProperty("phone_number") //Para que reconozca los valores a la hora de crear el dto
  private String phone_number;

  @Null_or_not_blank
  @JsonProperty("country_code") 
  private String country_code; //Para que reconozca los valores a la hora de crear el dto

  
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
