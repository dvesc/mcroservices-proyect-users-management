package example.mcroservice.users.dto.validators;


import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import example.mcroservice.users.utils.custom_annotations.Null_or_email;
import example.mcroservice.users.utils.custom_annotations.Null_or_not_blank;

public class Updated_user_dto {

  @Null_or_not_blank
  @Size(message = "Path value cannot be so long", max = 50)
  private String first_name;

 
  @Null_or_not_blank
  @Size(message = "Path value cannot be so long", max = 50)
  private String last_name;

  @Null_or_email
  private  String email;

  //Es public para acceder a sus propiedades con "."
  //Puede ser null pero las propiedades dentro NO.
  public Updated_phone_format_dto phone = null;


  //GETTERS AND SETTERS--------------------------------------------------------
  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  @JsonProperty("phone") //Para que lo reconozca como obj
  public void setPhone(Updated_phone_format_dto phone) {
    this.phone = phone;
  }


}
