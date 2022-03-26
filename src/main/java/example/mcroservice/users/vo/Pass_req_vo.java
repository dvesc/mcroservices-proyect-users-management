package example.mcroservice.users.vo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Password_change_requests_entity")
@Table(name = "Password_change_requests")
public class Pass_req_vo {
  @Id
  @SequenceGenerator(
      name = "password_change_requests_sequence",
      sequenceName = "password_change_requests_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "password_change_requests_sequence"
  )
  private Long id;

  //Puede ser null porque su valor lo asignamos despues
  @Column(name = "process_code", length = 10)
  @Size(max=30)
  private String process_code;

  //STATUS_CODE -> issued, consumed
  @NotNull
  @Column(name = "process_status",length = 10)
  @Size(max=30)
  private String process_status;
  

  //RELACIONES-----------------------------------------------------------------
  @ManyToOne(
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE,
      CascadeType.DETACH,
      CascadeType.REFRESH
    }
  )
  @JoinColumn(
    name="user_id", // el nombre de la columna que hace ref a la otra tabla
    nullable=false //cuando se hagan nuevos registros ¿puede ser null? NO
  )
  private Users_vo users_vo;

  


  //GETTERS_AND_SETTERS--------------------------------------------------------
  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public String getProcess_code() {
    return process_code;
  }


  public void setProcess_code(String process_code) {
    this.process_code = process_code;
  }


  public String getProcess_status() {
    return process_status;
  }


  public void setProcess_status(String process_status) {
    this.process_status = process_status;
  }


  public Users_vo getUsers_vo() {
    return users_vo;
  }


  public void setUsers_vo(Users_vo users_vo) {
    this.users_vo = users_vo;
  }


 


 
  

  
}
