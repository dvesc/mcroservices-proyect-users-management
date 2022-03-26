package example.mcroservice.users.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import example.mcroservice.users.dto.validators.New_phone_format_dto;

public class User_in_list_dto {
  private String id; //la de auth0
  private String first_name;
  private String last_name;
  private String email;
  public New_phone_format_dto phone;
  private Date registration_date;


  //GETTERS AND SETTERS--------------------------------------------------------
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getFirst_name() {
    return first_name;
  }
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }
  public String getLast_name() {
    return last_name;
  }
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  
  public Date getRegistration_date() {
    return registration_date;
  }
  public void setRegistration_date(Date registration_date) {
    this.registration_date = registration_date;
  }
  @JsonProperty("phone")
  public void setPhone(New_phone_format_dto phone) {
    this.phone = phone;
  }
}
