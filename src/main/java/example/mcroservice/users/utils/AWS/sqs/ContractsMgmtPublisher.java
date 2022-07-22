package example.mcroservice.users.utils.AWS.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import example.mcroservice.users.utils.AWS.sqs.messages_to_send.MsgToContractsMgmt;

@Component
public class ContractsMgmtPublisher {
  @Autowired
  private QueueMessagingTemplate queue_messaging_template;

  @Value("${cloud.aws.sqs.end-point.url}")
  private String queue_url;

  // --------------------------------------------------------------
  public void send(MsgToContractsMgmt msg) {
    // convertimos el objeto de mensaje a json
    ObjectMapper to_json = new ObjectMapper();
    String msg_json = "";
    // puede generar errores por ello lo metemos en un try catch
    try {
      msg_json = to_json.writeValueAsString(msg);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    queue_messaging_template.send(
        // convertimos el json a otro obj manejado por sqs
        queue_url, MessageBuilder.withPayload(msg_json).build());
  }
}

//