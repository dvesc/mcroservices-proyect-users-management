package example.mcroservice.users.dto;

public class Client_response_dto {
  private Integer status_code = 200;
  private String message;
  

  //CONSTRUCTOR----------------------------------------------------------------
  public Client_response_dto(String message) {
    this.message = message;
  }


  //GETTERS AND SETTERS---------------------------------------------------------
  public Integer getStatus_code() {
    return status_code;
  }

  public void setStatus_code(Integer status_code) {
    this.status_code = status_code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  //GETTERS AND SETTERS--------------------------------------------------------
  

  
  



}
