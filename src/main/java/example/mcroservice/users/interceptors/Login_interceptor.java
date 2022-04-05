package example.mcroservice.users.interceptors;

import java.util.Base64;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import example.mcroservice.users.errors.my_exceptions.Unautorized_token_exception;
import example.mcroservice.users.errors.my_exceptions.Update_data_exception;
import example.mcroservice.users.services.User_services;
import example.mcroservice.users.utils.Auth0.Auth0;
import example.mcroservice.users.vo.Users_vo;

@Component
public class Login_interceptor implements HandlerInterceptor {
  @Autowired
  private User_services user_services;
  @Autowired
  private Auth0 auth0;

  //MIDDLEWARE-----------------------------------------------------------------
  @Override 
  public boolean preHandle( //Se ejecuta antes del controller
    HttpServletRequest req, HttpServletResponse res,  Object handler
  ) throws Exception { 
      //El path comienza con "/" -> "/xxx/xxx/xxxx etc" 
      //obtenemos el path y lo dividimos para obtener el user_id (El ultimo elem de la array)
      String[] url_path = req.getServletPath().split("/"); 
      String user_id = url_path[url_path.length-1], //el ultimo elem es el id 
        token = req.getHeader("Authorization"); //obtenemos el valor del header authorization
     
      if(token != null){
        Base64.Decoder decoder;
        String[] parts = token.split("\\."), //El token pero en partes
          payload_split;
        String formated_token = (token.split(" "))[1], //Bearer y243ccrj3j2crj20j...
          payload,
          token_auth0_id = "";


        //Decodificamos el jwt
        decoder = Base64.getUrlDecoder(); //Decodifica base64
        payload = new String(decoder.decode(parts[1])); //Obtenemos el payload decodificado
        //dividimos el payload por las "
        payload_split = payload.split("\"");
        //buscamos el elem con el valor del auth0 id (auth0|xxxxxx) dentro del payload
        Pattern regexp = Pattern.compile("^auth0+[|]+[a-zA-Z0-9]{0,}");
        for (int i = 0; i < payload_split.length; i++) {
          if(regexp.matcher(payload_split[i]).matches())
            token_auth0_id = (payload_split[i]);
        } 
        //buscamos al usuario en la db de acuerdo al user id
        Optional<Users_vo> coincidence = Optional.ofNullable(
          user_services.get_user_by_id(
            Long.parseLong(user_id)
          )
        );
        //si encuentra coincidencias por el ID
        if(coincidence.isPresent()){
          Users_vo user_vo = coincidence.get();
          //comparamos los auth0 id
          if(!token_auth0_id.equals("auth0|"+user_vo.getAuth0_id()))
            throw new Unautorized_token_exception(
              "The user does not issue this token", 
              HttpStatus.UNAUTHORIZED
            );
        } else 
          throw new Update_data_exception(
            "The user does not exist",
            HttpStatus.NOT_FOUND
          );

        //Finalmente validamos desde auth0
        auth0.valid_token(formated_token); 
      } else 
        throw new Unautorized_token_exception(
          "Header 'Authorization' is required", 
          HttpStatus.BAD_REQUEST
        );
    
    return HandlerInterceptor.super.preHandle(req, res, handler);
  }
}
