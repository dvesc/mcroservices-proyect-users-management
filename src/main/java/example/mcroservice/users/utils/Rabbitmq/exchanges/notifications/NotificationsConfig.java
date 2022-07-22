package example.mcroservice.users.utils.Rabbitmq.exchanges.notifications;

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
public class NotificationsConfig {

  public static final String queue_name = "email_msg";
  public static final String exchange_name = "user_notifications";
  public static final String routing_key = "email.*";

  // LA CONFIGURACION-------------------------------------------------------
  @Bean
  TopicExchange exchange1() {
    return new TopicExchange(exchange_name);
  }

  @Bean
  Queue queue1() { // esta es la cola que consume el mcroservicio de notificaiones
    return new Queue(queue_name); // queue name
  }

  @Bean
  Binding binding1(
      Queue queue1,
      TopicExchange exchange1) {
    return BindingBuilder
        .bind(queue1)
        .to(exchange1)
        .with(routing_key); // la route key por la que los unimos
  }

  // MANEJANDO EL MENSAJE-------------------------------------------------------
  // se transforma a json
  @Bean
  MessageConverter message_converter1() {
    return new Jackson2JsonMessageConverter();
  }

  // Es un elemento de rabbitqm para enviar la data convertida (de obj a json)
  // para los exchange de rabbitmq
  @Bean
  RabbitTemplate rabbit_template1(ConnectionFactory factory) {
    RabbitTemplate rabbit_template1 = new RabbitTemplate(factory);
    rabbit_template1.setMessageConverter(message_converter1());

    return rabbit_template1;
  }

}
