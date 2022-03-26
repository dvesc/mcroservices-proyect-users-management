package example.mcroservice.users.utils.Rabbitmq;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import example.mcroservice.users.utils.messages.Forgotten_password_msg;
import example.mcroservice.users.utils.messages.Msg;

@Component
public class Rabbitmq {
  @Autowired
  private RabbitTemplate rabbit_template;

  @Autowired
  private TopicExchange exchange;

  //ENVIAR MENSAJES------------------------------------------------------------
  public void send(Msg msg, String routing_key){
    //Envia el msg
    rabbit_template.convertAndSend(
      exchange.getName(), //El exchange de rabbitmq a mandar el msg
      routing_key, //Routing key
      msg
    );
    
    System.out.println(
      "Sending rabbitmq msg to exchange: "+exchange.getName());
  }

}