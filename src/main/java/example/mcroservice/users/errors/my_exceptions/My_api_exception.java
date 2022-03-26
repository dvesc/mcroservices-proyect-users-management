package example.mcroservice.users.errors.my_exceptions;

public class My_api_exception extends RuntimeException {
  private String message;
  private String original_name;
  private String description;


  public My_api_exception(String message, String original_name, String description) {
    this.message = message;
    this.original_name = original_name;
    this.description = description;
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


  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }
  
    
  
    


}
