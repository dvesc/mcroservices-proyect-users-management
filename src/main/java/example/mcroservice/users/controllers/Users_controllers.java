package example.mcroservice.users.controllers;

import com.auth0.json.auth.CreatedUser;
import example.mcroservice.users.dto.Client_response_dto;
import example.mcroservice.users.dto.Get_method_response;
import example.mcroservice.users.dto.validators.Change_password_dto;
import example.mcroservice.users.dto.validators.Forgot_password_dto;
import example.mcroservice.users.dto.validators.New_user_dto;
import example.mcroservice.users.dto.validators.Updated_user_dto;
import example.mcroservice.users.mappers.Users_mapper;
import example.mcroservice.users.routes.Users_routes;
import example.mcroservice.users.services.Pass_req_services;
import example.mcroservice.users.services.User_services;
import example.mcroservice.users.utils.Auth0.Auth0;
import example.mcroservice.users.utils.Rabbitmq.Rabbitmq;
import example.mcroservice.users.utils.messages.Forgotten_password_msg;
import example.mcroservice.users.utils.messages.Password_reset_msg;
import example.mcroservice.users.utils.phone_validator.Phone_validator;
import example.mcroservice.users.utils.random_strings.Random_string;
import example.mcroservice.users.vo.Pass_req_vo;
import example.mcroservice.users.vo.Users_vo;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Users_controllers implements Users_routes {
  @Autowired
  private User_services user_services;

  @Autowired
  private  Pass_req_services pass_req_services;
 
  @Autowired
  private Auth0 auth0;

  @Autowired 
  private Rabbitmq rabbitmq;


  //CREAR UN USUARIO-----------------------------------------------------------
  @Override
  public ResponseEntity<Client_response_dto> create_a_user(New_user_dto body) {
    String password = body.getPassword(),
        name =  body.getFirst_name()+" "+body.getLast_name(),
        email = body.getEmail(),
        country_code = body.phone.getCountryCode(),
        phone_number =  body.phone.getPhoneNumber();
    Phone_validator validated_phone = new Phone_validator(
      phone_number, country_code);

    //Nos permite saber si un objeto trae valor o no
    Optional<Users_vo> coincidence = Optional.ofNullable(
        user_services.get_user_by_email(email)
    );
    //Si no hay coincidencias en mi db
    if(!coincidence.isPresent()){
      Users_vo user = Users_mapper.INSTANCE.new_user_dto_to_vo(body); //De DTO a VO
     
      //Validamos que sea un numero de telefono valido
      if(validated_phone.validate_a_phone()){
        user.setCountry_code(country_code); 
        user.setPhone_number(phone_number);
      }else
       throw new RuntimeException("Invalid phone number for region");
        
      //Guardamos en Auth0
      CreatedUser auth0_user = auth0.create_a_user(
        name,
        email,
        password,
        country_code+phone_number
      ); 
      user.setAuth0_id( auth0_user.getUserId()); //Guardamos la id de Auth0
      user_services.create_user(user); //Guardamos el usuario en nuestra db
      ResponseEntity.ok().body(user);
    } else
      throw new RuntimeException("This user already exist");

    //Enviamos la respuesta
    return new ResponseEntity<Client_response_dto>(
      new Client_response_dto("User created successfully"),
      HttpStatus.CREATED
    );
  }


  

  //ACTUALIZAR UN USUARIO------------------------------------------------------
  @Override
  public ResponseEntity<Client_response_dto> update_a_user(
  Long user_id,
  Updated_user_dto body) {
    String country_code = body.phone.getCountryCode(),
      phone_number =  body.phone.getPhoneNumber();

    Optional<Users_vo> coincidence = Optional.ofNullable(
        user_services.get_user_by_id(user_id)
    );
    //si encuentra coincidencias por el ID
    if(coincidence.isPresent()){
      Users_vo old_user = coincidence.get();

      //Actualiza el VO


      if(body.getEmail() != null) {
        //Comprobamos que el email no exista ya en mi db
        Optional<Users_vo> same_email = Optional.ofNullable(
          user_services.get_user_by_email(body.getEmail())
        );
        if(same_email.isPresent())
          throw new RuntimeException(
            "There is already a user with that email");
      
        old_user.setEmail(body.getEmail());
      }
        

      if(country_code != null && phone_number != null){
        Phone_validator validated_phone = new Phone_validator(
          phone_number, country_code);
        //Validamos que sea un numero de telefono valido
        if(!validated_phone.validate_a_phone()){
          throw new RuntimeException("Invalid phone number for region");
        }
        old_user.setPhone_number(body.phone.getPhoneNumber());
        old_user.setCountry_code(body.phone.getCountryCode());
      }


      if(body.getFirst_name() != null)
        old_user.setFirst_name(body.getFirst_name());
      if(body.getLast_name() != null)
        old_user.setLast_name(body.getLast_name());
      

      //Actualiza en auth0
      auth0.update_all(
        old_user.getAuth0_id(),
        body.getFirst_name()+" "+body.getLast_name(),
        body.getEmail(),
        null //me sigue dando error el phone_number
      );
      user_services.update_a_user(old_user); //Actualizamos en nuestra base de datos
    } else 
      throw new RuntimeException("The user does not exist");


    return new ResponseEntity<Client_response_dto>(
      new Client_response_dto("User has been updated successfully"),
      HttpStatus.CREATED
    );
  }






  //ELIMINAR USUARIO-----------------------------------------------------------
  @Override
  public ResponseEntity<Client_response_dto> delete_a_user(
  Long user_id) {
    Optional<Users_vo> coincidence = Optional.ofNullable(
      user_services.get_user_by_id(user_id)
    );

    if(coincidence.isPresent()){
      Users_vo old_user = coincidence.get();
      old_user.setDischarge_date(new Date());
    
      user_services.update_a_user(old_user);   //Actualizo en mi db
    } else
      throw new RuntimeException("The user does not exist");


    return new ResponseEntity<Client_response_dto>(
      new Client_response_dto("User has been deleted successfully"),
      HttpStatus.OK
    );
  }



  

  
  //HE OLVIDADO LA CONTRASEÑA--------------------------------------------------
  @Override
  public ResponseEntity<Client_response_dto> send_email(
  Forgot_password_dto body) {
    Forgot_password_dto body_data = body;

    Optional<Users_vo> coincidence = Optional.ofNullable(
      user_services.get_user_by_email(body_data.getEmail())
    );

    if(coincidence.isPresent()){
      //generar cadena alfanumerica
      String process_code = new Random_string(6).getRandom_string();
      
      //creamos el vo que ira en la tabla de request
      Pass_req_vo vo = new Pass_req_vo();
      vo.setProcess_code(process_code);
      vo.setProcess_status("issued"); 
      vo.setUsers_vo(coincidence.get()); //y si, por muy raro que sea debe tomar todo el vo de users
      
      //guardo datos en la tabla de password change request
      pass_req_services.create_pass_req(vo);


      //creamos el objeto mensaje
      Forgotten_password_msg msg = new Forgotten_password_msg(
        body_data.getEmail(), //el email
        process_code //la cadena
      );
      //enviamos
      rabbitmq.send(
        msg, 
        "email.forgot_password"
      );
    } else 
      throw new RuntimeException("The user does not exist");

      
    return new ResponseEntity<Client_response_dto>(
      new Client_response_dto("An email has been sent to your email address"),
      HttpStatus.OK
    );
  }




  //issued, consumed
  //CAMBIAR CONTRASEÑA---------------------------------------------------------
  @Override
  public ResponseEntity<Client_response_dto> new_password(
  String process_code,
  Change_password_dto body) {
    System.out.println(process_code);
    //Obtenemos de la db
    Optional<Pass_req_vo> coincidence = Optional.ofNullable(
      pass_req_services.find_by_process_code(process_code)
    );

    //Si retorna algo
    if(coincidence.isPresent()){
      Pass_req_vo pass_req = coincidence.get();

      //Comprobamos que siga vigente
      if(pass_req.getProcess_status().equals("issued")){

        //actualizamos el pass_req para que ya no se pueda consumir
        pass_req.setProcess_status("consumed");
        pass_req_services.update_a_pass_req(pass_req);

        //cambiamos la contraseña en auth0
        auth0.update_password(
          pass_req.getUsers_vo().getAuth0_id(), //si, podemos acceder asi
          body.getPassword()
        );
        System.out.println("Ya cambiamos en auth0");

        //creamos el objeto mensaje
        Password_reset_msg msg = new Password_reset_msg(
          pass_req.getUsers_vo().getEmail() //el email
        );
        //enviamos email de confirmacion
        rabbitmq.send(
          msg, "email.password_reset"
        );

      }else
        throw new RuntimeException("Process_code has already been consumed");
    } else 
      throw new RuntimeException("Invalid process_code!");
    

    return new ResponseEntity<Client_response_dto>(
      new Client_response_dto("User has been updated successfully"),
      HttpStatus.CREATED
    );
  }





  //GET USERS------------------------------------------------------------------
  @Override
  public ResponseEntity<Get_method_response> get_users(
  String filterby, 
  String filtervalue, 
  String orderby, 
  String order,
  Integer page,
  Integer size,
  HttpServletRequest request //esto es solo para obtener la url en el obj de retorno
  ) {
    Direction direction =  Sort.Direction.DESC;         
    String filter_by = filterby,
      filter_value = (filtervalue == null)
        ? "" : filtervalue,
      order_by = orderby;
    int pag = (page == null || page <= 1) ? 0 : page-1, //la primera pagina debe ser 0
       size_pag = (size == null || size <= 0) ? 10 : size; 


    //Calculamos el valor que tendra direction (DESC o ASC)
    if(order != null){
      if(!order.toUpperCase().equals("DESC")) 
        direction = Sort.Direction.ASC;
    } else  direction = Sort.Direction.ASC;
            

    //calculamos el valor que tendra order_by
    if(orderby != null){
      Pattern regex = Pattern.compile("^first_name|last_name|email|registration_date$");
      //Si no es ninguno de los valores admitidos le asignamos uno por defecto
      if(!regex.matcher(order_by).matches())
        order_by = "registration_date";
    }
    else order_by = "registration_date";


    //Calculamos el valor que tendra filter_by
    if(filter_by != null){
      Pattern regex = Pattern.compile("^all|first_name|last_name|email");
      //Si no es ninguno de los valores admitidos le asignamos uno por defecto
      if(!regex.matcher(filter_by).matches())
        filter_by = "all";
    }
    else filter_by = "all";


    //Hacemos el query
    Page<Users_vo> page_coincidences = user_services.get_coincidences(
      filter_by,
      filter_value, 
      order_by, 
      direction,
      pag,
      size_pag
    );
    

    return new ResponseEntity<Get_method_response>(
      new Get_method_response(request,page_coincidences),
      HttpStatus.OK
    );
  }
}
