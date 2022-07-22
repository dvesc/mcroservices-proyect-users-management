package example.mcroservice.users.utils.Rabbitmq.exchanges.users_query_response;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.mcroservice.users.utils.Rabbitmq.exchanges.Msg;

@Component
public class UsersQueryResonsePublisher {
  // lo que nos transforma los obj en json para rabbitmq
  @Autowired
  private RabbitTemplate rabbit_template;

  // ENVIAR MENSAJES------------------------------------------------------------
  public void send(Msg msg, String routing_key) {

    // Envia el msg
    rabbit_template.convertAndSend(
        UsersQueryResponseConfig.exchange_name, // El exchange de rabbitmq al que mandaremos el msg
        routing_key, // Routing key
        msg);

    System.out.println(
        "Sending rabbitmq msg to exchange: " + UsersQueryResponseConfig.exchange_name);
  }

}
