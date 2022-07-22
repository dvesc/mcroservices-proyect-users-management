package example.mcroservice.users.utils.AWS.sqs.messages_to_send;

import org.springframework.stereotype.Component;

@Component
public class MsgToContractsMgmt {
  private String user_email;
  private String auth_id;
  private String contract_id;

  // CONSTRUCTOR-----------------------------------------------------------
  public MsgToContractsMgmt(String user_email, String auth_id, String contract_roll) {
    this.user_email = user_email;
    this.auth_id = auth_id;
    this.contract_id = contract_roll;
  }

  public MsgToContractsMgmt() {
  }

  // GETTERS AND SETTERS---------------------------------------------------
  public String getUser_email() {
    return user_email;
  }

  public void setUser_email(String user_email) {
    this.user_email = user_email;
  }

  public String getAuth_id() {
    return auth_id;
  }

  public void setAuth_id(String auth_id) {
    this.auth_id = auth_id;
  }

  public String getContract_id() {
    return contract_id;
  }

  public void setContract_id(String contract_roll) {
    this.contract_id = contract_roll;
  }

}
