package example.mcroservice.users.utils.Rabbitmq.exchanges.users_query_response;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersQueryResponseConfig {

  public static final String queue_name = "contracts_mgmt";
  public static final String exchange_name = "users_query_response";
  public static final String routing_key = "to.contracts_mgmt";

  // LA CONFIGURACION-------------------------------------------------------
  @Bean
  DirectExchange exchange3() {
    return new DirectExchange(exchange_name);
  }

  @Bean
  Queue queue3() { // esta es la cola que consume el mcroservicio de contracts
    return new Queue(queue_name); // queue name
  }

  @Bean
  Binding binding3(
      Queue queue3,
      DirectExchange exchange3) {
    return BindingBuilder
        .bind(queue3)
        .to(exchange3)
        .with(routing_key); // la route key por la que los unimos
  }

  // MANEJANDO EL MENSAJE-------------------------------------------------------
  // se transforma a json

  // OJO con el nombre debe ser diferente al de los beans de UsersQueryConfig

  /*
   * @Bean
   * MessageConverter message_converter1() {
   * return new Jackson2JsonMessageConverter();
   * }
   * 
   * // Es un elemento de rabbitqm para enviar la data convertida (de obj a json)
   * // para los exchange de rabbitmq
   * 
   * @Bean
   * RabbitTemplate rabbit_template1(ConnectionFactory factory) {
   * RabbitTemplate rabbit_template1 = new RabbitTemplate(factory);
   * rabbit_template1.setMessageConverter(message_converter1());
   * return rabbit_template1;
   * }
   */

}
