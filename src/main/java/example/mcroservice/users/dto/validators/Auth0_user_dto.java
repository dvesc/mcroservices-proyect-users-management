package example.mcroservice.users.dto.validators;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import example.mcroservice.users.utils.custom_annotations.Valid_password;

public class Auth0_user_dto {

  @NotNull(message = "first_name cannot be null")
  @NotBlank(message = "first_name cannot be in blank")
  @Valid_password
  private String password;


  //GETTERS AND SETTERS--------------------------------------------------------
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
