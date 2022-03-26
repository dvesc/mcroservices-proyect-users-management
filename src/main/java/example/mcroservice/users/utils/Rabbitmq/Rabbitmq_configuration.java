package example.mcroservice.users.utils.Rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Rabbitmq_configuration {
  
  //EXCHANGES------------------------------------------------------------------
  //Tipo Direct
  @Bean  
  TopicExchange exchange1(){
    return new TopicExchange(
      "user_notifications" //nombre del exchange
    );
  }

  
  //COLAS----------------------------------------------------------------------
  @Bean
  Queue email_msg(){
    return new Queue(
      "email_msg", //queue name
      true //durable
    );
  }

 
  //UNIONES--------------------------------------------------------------------
  @Bean
  Binding binding_queue1_to_exchange1( 
  Queue queue,
  TopicExchange exchange
  ){
    return BindingBuilder
      .bind(queue)
      .to(exchange)
      .with("email.*"); //la route key
  }
  

  

  //MANEJANDO EL MENSAJE-------------------------------------------------------
  //se transforma a json
  @Bean 
  MessageConverter message_converter(){
    return new Jackson2JsonMessageConverter();
  }
  //Es un template para enviar el mensage convertido
  @Bean
  RabbitTemplate rabbit_template(ConnectionFactory factory){
    RabbitTemplate rabbit_template = new RabbitTemplate(factory);
    rabbit_template.setMessageConverter(message_converter());

    return rabbit_template;
  }


}
