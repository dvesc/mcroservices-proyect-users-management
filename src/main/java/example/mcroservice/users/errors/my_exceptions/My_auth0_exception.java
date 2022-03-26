package example.mcroservice.users.errors.my_exceptions;

public class My_auth0_exception extends RuntimeException{
  private String message;
  private String original_name;

  //CONSTRUCTOR----------------------------------------------------------------
  public My_auth0_exception(String message, String original_name) {
    this.message = message;
    this.original_name = original_name;

  }


  //GETTERS AND SETTERS--------------------------------------------------------
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getOriginal_name() {
    return original_name;
  }

  public void setOriginal_name(String original_name) {
    this.original_name = original_name;
  }

  
}
