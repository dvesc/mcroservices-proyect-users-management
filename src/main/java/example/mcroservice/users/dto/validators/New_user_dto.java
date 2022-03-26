package example.mcroservice.users.dto.validators;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import example.mcroservice.users.utils.custom_annotations.Valid_password;


public class New_user_dto {
  @NotNull(message = "first_name cannot be null")
  @NotBlank(message = "first_name cannot be in blank")
  @Size(message = "Path value cannot be so long", max = 50)
  private String first_name;


  @NotNull(message = "first_name cannot be null")
  @NotBlank(message = "first_name cannot be in blank")
  @Size(message = "Path value cannot be so long", max = 50)
  private String last_name;


  @NotNull(message = "Email cannot be null")
  @Email(message = "Must be an valid email format")
  @Size(message = "Path value cannot be so long",max = 50)
  private String email;


  @NotNull(message = "Password cannot be null")
  @Valid_password
  private String password;


  @NotNull(message = "Phone cannot be null")
  public New_phone_format_dto phone; //Es publico para poder acceder a sus getter y setters con el "." 
  

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @JsonProperty("phone") //Esto se usa para que reconozca a phone como un obj
  public void setPhone(New_phone_format_dto phone) {
    this.phone = phone;
  }
}