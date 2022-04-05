package example.mcroservice.users.errors.my_exceptions;

public class My_exceptions extends RuntimeException{
  private String message;
  private String error_name;

  //CONSTRUCTOR
  public My_exceptions (String msg, String error_msg){
    this.message = msg;
    this.error_name = error_msg;
  }

  //GETTERS AND SETTERS
  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }


  public String getError_name() {
    return error_name;
  }


  public void setError_name(String error_name) {
    this.error_name = error_name;
  }

  


}