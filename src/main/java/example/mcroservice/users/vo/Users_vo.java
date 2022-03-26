package example.mcroservice.users.vo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class Users_vo {
  @Id
  @SequenceGenerator(
      name = "users_sequence",
      sequenceName = "users_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "users_sequence"
  )
  private Long id;

  
  //Puede ser null ya que su valor lo asignamos despues en los controllers
  @Column(name = "auth0_id", length = 30)
  @Size(max= 30)
  private String auth0_id;


  @NotNull
  @Column(name = "first_name", length = 50)
  @Size(max = 50)
  private String first_name;


  @NotNull
  @Column(name = "last_name", length = 50)
  @Size(max = 50)
  private String last_name;


  @NotNull
  @Column(name = "email", length = 50)
  @Size(max = 50)
  private String email;

  //Puede ser null ya que su valor lo asignamos despues en los controllers
  @Column(name = "phone_number", length = 30)
  @Size(max= 30)
  private String phone_number;
  @Column(name = "country_code", length = 8)
  @Size(max = 18)
  private String country_code;


  @NotNull
  @Column(name = "registration_date", nullable = false)
  private Date registration_date = new Date();

  //Puede ser null ya que su valor lo asignamos luego luego
  @Column(name = "discharge_date", nullable = true)
  private Date discharge_date = null;

   //RELACIONE  S-----------------------------------------------------------------
   @OneToMany(
      mappedBy = "users_vo", //el nombre del parametro en la otra tabla
      cascade = { //cascadas?????
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH
      }
     )
   private List<Pass_req_vo> Password_change_requests_vo; 
 

  //SETTERS--------------------------------------------------------------------
  
  public void setAuth0_id(String auth0_id) { this.auth0_id = auth0_id; }

  public void setId(Long id) {
    this.id = id;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setRegistration_date(Date registration_date) {
    this.registration_date = registration_date;
  }

  public void setDischarge_date(Date discharge_date) {
    this.discharge_date = discharge_date;
  }

 

  //GETTERS--------------------------------------------------------------------
  public Long getId() {
    return id;
  }
  
  public String getAuth0_id() { return auth0_id; }

  public String getFirst_name() {
    return first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public String getEmail() {
    return email;
  }

  public Date getRegistration_date() {
    return registration_date;
  }

  public Date getDischarge_date() {
    return discharge_date;
  }


  public String getCountry_code() {
    return country_code;
  }

  public void setCountry_code(String country_code) {
    this.country_code = country_code;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public List<Pass_req_vo> getPassword_change_requests_vo() {
    return Password_change_requests_vo;
  }

  public void setPassword_change_requests_vo(List<Pass_req_vo> password_change_requests_vo) {
    Password_change_requests_vo = password_change_requests_vo;
  }
  
  
  
}


