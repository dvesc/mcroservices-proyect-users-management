package example.mcroservice.users.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import example.mcroservice.users.utils.Auth0.Auth0;

@Component
public class Login_interceptor implements HandlerInterceptor {
  @Autowired
  private Auth0 auth0 = new Auth0();
  //Aqui el System.out.println() no funciona, debe ser asi para poder imprimir en consola
  private final Logger LOG = LoggerFactory.getLogger(Login_interceptor.class);


  //MIDDLEWARE-----------------------------------------------------------------
  @Override 
  public boolean preHandle( //Se ejecuta antes del controller
    HttpServletRequest req, HttpServletResponse res,  Object handler
  ) throws Exception { 
      String token = req.getHeader("Authorization"); //obtenemos el valor del header authorization
  
      if(token != null){
        String formated_token = (token.split(" "))[1]; //Bearer y243ccrj3j2crj20j...
        boolean validated_token = auth0.valid_token(formated_token);
        
      } else 
        throw new RuntimeException("Header 'Authorization' is required");
      //Recordemos que los runtimeException los manejamos en el controller_advice
     
  
   
    return HandlerInterceptor.super.preHandle(req, res, handler);
  }
  
  
}
