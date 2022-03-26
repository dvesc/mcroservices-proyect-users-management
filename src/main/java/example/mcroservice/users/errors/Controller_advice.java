package example.mcroservice.users.errors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import example.mcroservice.users.errors.my_exceptions.My_api_exception;
import example.mcroservice.users.errors.my_exceptions.My_auth0_exception;

//Se encarga de capturar cualquier excepcion que le indiquemos aqui
//Es muy util porque no necesitamos un try catch para cuando declaramos excepciones
@RestControllerAdvice
public class Controller_advice {
  //Para imprimir en consola
  private static final Logger LOG = LoggerFactory.getLogger(Controller_advice.class.getName());
  

  //MANEJADORES DE ERRORES-----------------------------------------------------

  //Excepciones de tipo runtime (las que generadas x nosotros). . . . . . . . .
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Error_handler_dto runtimeExceptionHandler(
  RuntimeException ex){
    LOG.error(ex.getMessage());

    Error_handler_dto err = new Error_handler_dto(
      HttpStatus.BAD_REQUEST,
      ex.getClass().getName(),
      ex.getMessage()
    );

    return err;
  }




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




  //ERRORES DE AUTH0. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  @ExceptionHandler(My_api_exception.class)
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Error_handler_dto my_api_exception(My_api_exception ex){
    LOG.error(ex.getMessage());
    Error_handler_dto err = new Error_handler_dto(
      HttpStatus.NO_CONTENT,
      ex.getOriginal_name(),
      ex.getMessage()
    );
    err.setDescription(ex.getDescription());
    return err;
  }  

  @ExceptionHandler(My_auth0_exception.class)
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Error_handler_dto my_auth0_exception(My_auth0_exception ex){
    LOG.error(ex.getMessage());
    Error_handler_dto err = new Error_handler_dto(
      HttpStatus.NO_CONTENT,
      ex.getOriginal_name(),
      ex.getMessage()
    );
   
    return err;
  }  


}
