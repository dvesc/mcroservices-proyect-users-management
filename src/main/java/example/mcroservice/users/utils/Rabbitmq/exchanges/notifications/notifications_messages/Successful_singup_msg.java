package example.mcroservice.users.utils.Rabbitmq.exchanges.notifications.notifications_messages;

import example.mcroservice.users.utils.Rabbitmq.exchanges.Msg;

public class Successful_singup_msg implements Msg {
  private String email;
  private String subject = "Welcome to our Service";
  private String html;

  // CONSTRUCTOR---------------------------------------------------------------
  public Successful_singup_msg(String email) {
    this.email = email;
    this.html = "<b>Hey! Thank you for trusting us and what we do <3</b>";
  }

  // GETTERS AND SETTERS--------------------------------------------------------
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }
}
