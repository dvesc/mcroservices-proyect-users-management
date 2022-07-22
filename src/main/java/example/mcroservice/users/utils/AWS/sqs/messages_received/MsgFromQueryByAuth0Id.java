package example.mcroservice.users.utils.AWS.sqs.messages_received;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//bueno esto es porque meh me dio pereza escribir
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MsgFromQueryByAuth0Id {
  private String auth0_id;
  private String contract_id;

  public String getAuth0_id() {
    return auth0_id;
  }

  public String getContract_id() {
    return contract_id;
  }

  public void setContract_roll(String contract_roll) {
    this.contract_id = contract_roll;
  }

  public void setAuth0_id(String auth0_id) {
    this.auth0_id = auth0_id;
  }
}
