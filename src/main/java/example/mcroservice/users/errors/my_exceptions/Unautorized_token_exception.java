package example.mcroservice.users.errors.my_exceptions;

import org.springframework.http.HttpStatus;

public class Unautorized_token_exception extends My_exceptions {
  private HttpStatus status;

  //CONSTRUCTOR----------------------------------------------------------------
  public Unautorized_token_exception(String msg, HttpStatus status) {
    super(msg,"UnautorizedTokenException");   
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
