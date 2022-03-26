package example.mcroservice.users.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Web_configuration implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    
    registry.addInterceptor(new Login_interceptor()) //Especificamos un interceptor a paths especificos
      .addPathPatterns(
        "/user/*"
      )
      .excludePathPatterns(
        "/user/login",
        "/user/singup",
        "/user/forgotpassword",
        "/user/newpassword*"
      );


    WebMvcConfigurer.super.addInterceptors(registry); 
  }

  
  
}