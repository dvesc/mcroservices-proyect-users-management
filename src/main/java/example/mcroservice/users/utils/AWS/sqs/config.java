package example.mcroservice.users.utils.AWS.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//para que spring sepa que es una clase de configuracion
@Configuration
public class config {

  @Value("${cloud.aws.sqs.credentials.access-key}")
  private String access_key;
  @Value("${cloud.aws.sqs.credentials.secret-key}")
  private String secret_key;

  // Bean lo que hace es que cuando nuestro servicio arranque corra esto
  // a su vez con esto genera una instancia que podremos meter en cualquer
  // otra clase con la notacion @Autowired (PERO OJO AQUI NO xd)
  @Bean
  QueueMessagingTemplate queue_messaging_template() {
    return new QueueMessagingTemplate(amazon_sqs_async());
  }// Con esto creamos nuestra configuracion inical para conectarnos a sqs

  private AmazonSQSAsync amazon_sqs_async() {
    // ojo toda esta mamada es estandar XD
    return AmazonSQSAsyncClientBuilder
        .standard()
        .withRegion(Regions.US_WEST_2)
        .withCredentials(
            new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(access_key, secret_key)))
        .build();
  }
}
