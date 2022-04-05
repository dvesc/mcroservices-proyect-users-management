package example.mcroservice.users.errors;

import org.springframework.http.HttpStatus;

public class Error_handler_dto {
  private HttpStatus http_status = null;
  private String error_name = null;
  private String message = null;
  private String description = null;


  //CONSTRUCTOR----------------------------------------------------------------
  public Error_handler_dto(HttpStatus http_status, String error_name, String message) {
    this.http_status = http_status;
    this.error_name = error_name;
    this.message = message;
  }

  

  //GETTERS AND SETTERS--------------------------------------------------------
  public HttpStatus getHttp_status() {
    return http_status;
  }


  public void setHttp_status(HttpStatus http_status) {
    this.http_status = http_status;
  }


  public String getError_name() {
    return error_name;
  }


  public void setError_name(String error_name) {
    this.error_name = error_name;
  }


  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }


  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }





}
 