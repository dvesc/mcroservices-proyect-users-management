package example.mcroservice.users.utils.Rabbitmq.exchanges.users_query;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.mcroservice.users.repositories.Users_repository;
import example.mcroservice.users.utils.AWS.sqs.ContractsMgmtPublisher;
import example.mcroservice.users.utils.AWS.sqs.messages_to_send.MsgToContractsMgmt;
import example.mcroservice.users.utils.Rabbitmq.exchanges.users_query.request_received.QueryByAuth0Id;
import example.mcroservice.users.vo.Users_vo;

@Component
public class UsersQueryConsumer {

  @Autowired
  private ContractsMgmtPublisher contracts_mgmt_sqs_queue;

  @Autowired
  private Users_repository users_repo;

  @RabbitListener(queues = UsersQueryConfig.queue_name)
  public void consume_data_from_queue(QueryByAuth0Id data) {
    // consultamos ese usuario con el fin de obtener su correo
    Users_vo user = users_repo.find_auth0_id(data.getAuth0_id());
    System.out.println(user.getEmail());

    // Mediante SQS enviamos esta data a las lambdas
    MsgToContractsMgmt msg = new MsgToContractsMgmt(
        user.getEmail(),
        user.getAuth0_id(),
        data.getContract_id());

    contracts_mgmt_sqs_queue.send(msg);

    /*
     * // enviamos esta data para que contracts la pueda manejar
     * QueryResponseMsg msg = new QueryResponseMsg(
     * user.getEmail(),
     * user.getAuth0_id(),
     * data.getContract_id());
     * 
     * user_query_response_publisher.send(
     * msg,
     * data.getNext_routing_key());
     */
  }
}
