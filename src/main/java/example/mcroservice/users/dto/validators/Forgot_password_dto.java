package example.mcroservice.users.dto.validators;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Forgot_password_dto {
  @NotNull(message = "Email cannot be null")
  @Email(message = "Must be an valid email format")
  @Size(message = "Path value cannot be so long",max = 50)
  private String email;


  //Su valor se lo aplicamos dentro del controller
  private Long user_id;


  //GETTERS AND SETTERS--------------------------------------------------------
  public String getEmail() {
    return email;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
