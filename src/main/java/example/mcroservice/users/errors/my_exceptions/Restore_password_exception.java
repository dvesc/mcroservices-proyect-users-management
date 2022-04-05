package example.mcroservice.users.errors.my_exceptions;

import org.springframework.http.HttpStatus;

public class Restore_password_exception extends My_exceptions {
  private HttpStatus status;

  //CONSTRUCTOR----------------------------------------------------------------
  public Restore_password_exception(String msg, HttpStatus status) {
    super(msg, "RestorePasswordException");
    this.status = status;
  }

  //GETTERS AND SETTERS--------------------------------------------------------
  public HttpStatus getStatus() {
    return status;
  }
  public void setStatus(HttpStatus status) {
    this.status = status;
  }  
}
