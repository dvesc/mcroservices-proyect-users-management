package example.mcroservice.users.utils.AWS.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import example.mcroservice.users.utils.AWS.sqs.messages_to_send.MsgToContractsMgmt;
import example.mcroservice.users.repositories.Users_repository;
import example.mcroservice.users.utils.AWS.sqs.messages_received.MsgFromQueryByAuth0Id;
import example.mcroservice.users.vo.Users_vo;

//OJO-> tenia problemas a la hora de escuchar los mensajes pero se arregla de
// una forma un tanto rara, ir a "UsersApplication.java" y ver la solucion
@Component
public class QueryByAuth0IdListener {
  @Autowired
  private Users_repository users_repo;

  @Autowired
  private ContractsMgmtPublisher contracts_mgmt_sqs_queue;

  // ----------------------------------------------------------------
  @SqsListener(value = "query-by-auth0-id", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void listener_body(String obj_json) {
    // convertimos el obj_json a MsgFromQueryByAuth0Id
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    // puede generar errores la conversion por eso el trycatch
    try {
      MsgFromQueryByAuth0Id data = mapper.readValue(obj_json, MsgFromQueryByAuth0Id.class);

      // consultamos ese usuario con el fin de obtener su correo
      Users_vo user = users_repo.find_auth0_id(data.getAuth0_id());

      // Mediante SQS enviamos esta data a la lambda
      MsgToContractsMgmt msg = new MsgToContractsMgmt(
          user.getEmail(),
          user.getAuth0_id(),
          data.getContract_id());

      System.out.println(("Sending msg to contracts-mgmt on SQS"));
      contracts_mgmt_sqs_queue.send(msg);

    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
