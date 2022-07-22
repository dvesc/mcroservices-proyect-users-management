package example.mcroservice.users.utils.Rabbitmq.exchanges.users_query;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersQueryConfig {

  public static final String queue_name = "query_by_auth0_id";
  public static final String exchange_name = "users_query";
  public static final String routing_key = "by.auth0_id";

  // LA CONFIGURACION-------------------------------------------------------
  @Bean
  DirectExchange exchange2() {
    return new DirectExchange(exchange_name);
  }

  @Bean
  Queue queue2() {
    return new Queue(queue_name); // queue name
  }

  @Bean
  Binding binding2(
      Queue queue2,
      DirectExchange exchange2) {
    return BindingBuilder
        .bind(queue2)
        .to(exchange2)
        .with(routing_key); // la route key por la que los unimos
  }

  // Aca no manejamos el template porque solo vamos a consumir

}
