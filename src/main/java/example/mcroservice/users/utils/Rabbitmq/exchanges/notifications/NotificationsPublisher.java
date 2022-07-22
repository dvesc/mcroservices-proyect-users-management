package example.mcroservice.users.utils.Rabbitmq.exchanges.notifications;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.mcroservice.users.utils.Rabbitmq.exchanges.Msg;

@Component
public class NotificationsPublisher {
  // lo que nos transforma los obj en json para rabbitmq
  @Autowired
  private RabbitTemplate rabbit_template;

  // ENVIAR MENSAJES------------------------------------------------------------
  public void send(Msg msg, String routing_key) {
    // Envia el msg
    rabbit_template.convertAndSend(
        NotificationsConfig.exchange_name, // El exchange de rabbitmq al que mandaremos el msg
        routing_key, // Routing key
        msg);

    System.out.println(
        "Sending rabbitmq msg to exchange: " + NotificationsConfig.exchange_name);
  }

}