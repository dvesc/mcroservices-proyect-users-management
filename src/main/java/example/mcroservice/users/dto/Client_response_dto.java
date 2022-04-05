package example.mcroservice.users.dto;

import org.springframework.http.HttpStatus;

public class Client_response_dto {
  private HttpStatus http_status;
  private String message;
  private Data_object data = null;
  

  //CONSTRUCTORES----------------------------------------------------------------
  public Client_response_dto(
  HttpStatus http_status, 
  String message, 
  Data_object data) {
    this.http_status = http_status;
    this.message = message;
    this.data = data;
  }

  public Client_response_dto(
  HttpStatus http_status, 
  String message) {
    this.http_status = http_status;
    this.message = message;
  }



  //GETTERS AND SETTERS---------------------------------------------------------
  public HttpStatus getHttp_status() {
    return http_status;
  }


  public void setHttp_status(HttpStatus http_status) {
    this.http_status = http_status;
  }


  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }


  public Data_object getData() {
    return data;
  }


  public void setData(Data_object data) {
    this.data = data;
  }



}
