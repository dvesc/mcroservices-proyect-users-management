package example.mcroservice.users.dto.validators;

import javax.validation.constraints.NotBlank;

import example.mcroservice.users.utils.custom_annotations.Valid_password;

public class Change_password_dto {
  @NotBlank(message="first_name cannot be in blank")
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
