package example.mcroservice.users.errors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import example.mcroservice.users.errors.my_exceptions.Delete_data_exception;
import example.mcroservice.users.errors.my_exceptions.My_api_exception;
import example.mcroservice.users.errors.my_exceptions.My_auth0_exception;
import example.mcroservice.users.errors.my_exceptions.Phone_number_exception;
import example.mcroservice.users.errors.my_exceptions.Restore_password_exception;
import example.mcroservice.users.errors.my_exceptions.Sing_up_exception;
import example.mcroservice.users.errors.my_exceptions.Unautorized_token_exception;
import example.mcroservice.users.errors.my_exceptions.Update_data_exception;

//Se encarga de capturar cualquier excepcion que le indiquemos aqui
//Es muy util porque no necesitamos un try catch para cuando declaramos excepciones
@RestControllerAdvice
public class Controller_advice {
  //Para imprimir en consola
  private static final Logger LOG = LoggerFactory.getLogger(
    Controller_advice.class.getName()
  );
  

  //MANEJADORES DE ERRORES-----------------------------------------------------

 



  //ERRORES DE LOS DTO (@VALID). . . . . . . . . . . . . . . . . . . . . . . . 
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Error_handler_dto method_argument_not_valid(
    MethodArgumentNotValidException ex){
      LOG.error(ex.getMessage());
      Error_handler_dto err = new Error_handler_dto(
        HttpStatus.BAD_REQUEST,
        ex.getClass().getName(),
        ex.getMessage()
      );
      
      ex.getBindingResult();

      return err;
    }

  
  //Errores por el phone number. . . . . . . . . . . . . . . . . . . . . . . . .
  @ExceptionHandler(Phone_number_exception.class)
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Error_handler_dto phone_number_exception(Phone_number_exception ex){
    LOG.error(ex.getMessage());
    Error_handler_dto err = new Error_handler_dto(
      HttpStatus.BAD_REQUEST,
      ex.getError_name(),
      ex.getMessage()
    );
    return err;
  }  

   //Error cuando se trata de crear un usuario que ya existe. . . . . . . . . .
   @ExceptionHandler(Sing_up_exception.class)
   @ResponseStatus (HttpStatus.CONFLICT)
   @ResponseBody
   public Error_handler_dto sing_up_exception(Sing_up_exception ex){
     LOG.error(ex.getMessage());
     Error_handler_dto err = new Error_handler_dto(
       HttpStatus.CONFLICT,
       ex.getError_name(),
       ex.getMessage()
     );
     return err;
   }  
   
  //OJO-> aqui es diferente, ya que el error "update_data_exception" puede tener
  //varios https status y debemos mandar diferentes dependiendo de la situacion
  @ExceptionHandler(Update_data_exception.class)
  public ResponseEntity<Error_handler_dto> update_data_exception(
  Update_data_exception ex){
    LOG.error(ex.getMessage());
    Error_handler_dto err = new Error_handler_dto(
      ex.getStatus(),
      ex.getError_name(),
      ex.getMessage()
    );
    return new ResponseEntity<Error_handler_dto>(err,ex.getStatus());
   }  


  //ERRORES EN EL MIDDLEWARE. . . . . . . . . . . . . . . . . . . . . . . . . .
  @ExceptionHandler(Unautorized_token_exception.class)
  public ResponseEntity<Error_handler_dto> unautorized_token_exception(
  Unautorized_token_exception ex){
    LOG.error(ex.getMessage());
    Error_handler_dto err = new Error_handler_dto(
      ex.getStatus(),
      ex.getError_name(),
      ex.getMessage()
    );
    return new ResponseEntity<Error_handler_dto>(err,ex.getStatus());
  }  
   

  //ERROR CUANDO SE ELIMINAN DATOS. . . . . . . . . . . . . . . . . . . . . . .
  @ExceptionHandler(Delete_data_exception.class)
   @ResponseStatus (HttpStatus.NOT_FOUND)
   @ResponseBody
   public Error_handler_dto delete_data_exception(Delete_data_exception ex){
     LOG.error(ex.getMessage());
     Error_handler_dto err = new Error_handler_dto(
       HttpStatus.NOT_FOUND,
       ex.getError_name(),
       ex.getMessage()
     );
     return err;
   }  
   
   @ExceptionHandler(Restore_password_exception.class)
   public ResponseEntity<Error_handler_dto> restore_password_exception(
    Restore_password_exception ex){
     LOG.error(ex.getMessage());
     Error_handler_dto err = new Error_handler_dto(
       ex.getStatus(),
       ex.getError_name(),
       ex.getMessage()
     );
     return new ResponseEntity<Error_handler_dto>(err,ex.getStatus());
    }  
 


  //ERRORES DE AUTH0. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  @ExceptionHandler(My_api_exception.class)
  @ResponseStatus (HttpStatus.BAD_GATEWAY)
  @ResponseBody
  public Error_handler_dto my_api_exception(My_api_exception ex){
    LOG.error(ex.getMessage());
    Error_handler_dto err = new Error_handler_dto(
      HttpStatus.BAD_GATEWAY,
      ex.getOriginal_name(),
      ex.getMessage()
    );
    err.setDescription(ex.getDescription());
    return err;
  }  

  @ExceptionHandler(My_auth0_exception.class)
  @ResponseStatus (HttpStatus.BAD_GATEWAY)
  @ResponseBody
  public Error_handler_dto my_auth0_exception(My_auth0_exception ex){
    LOG.error(ex.getMessage());
    Error_handler_dto err = new Error_handler_dto(
      HttpStatus.BAD_GATEWAY,
      ex.getOriginal_name(),
      ex.getMessage()
    );
   
    return err;
  }  


}
