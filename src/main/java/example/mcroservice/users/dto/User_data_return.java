package example.mcroservice.users.dto;

import example.mcroservice.users.dto.validators.New_phone_format_dto;


//Este es el objeto que se retorna cuando el frontend crea un usuario y/o actualiza
public class User_data_return implements Data_object {
  private Long id;
  private String first_name;
  private String last_name;
  private String email;
  public New_phone_format_dto phone = new New_phone_format_dto();


  //Constructor----------------------------------------------------------------
  public User_data_return(
  Long id, 
  String first_name,
  String last_name, 
  String email, 
  String country_code,
  String phone_number) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    phone.setCountryCode(country_code);
    phone.setPhoneNumber(phone_number);
  }

  //GETTERS AND SETTERS--------------------------------------------------------
  public Long getId() {
    return id;
  }


  public void setId(Long id) {
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


  public New_phone_format_dto getPhone() {
    return phone;
  }


  public void setPhone(New_phone_format_dto phone) {
    this.phone = phone;
  } 

  

}
