package example.mcroservice.users.utils.Rabbitmq.exchanges.notifications.notifications_messages;

import example.mcroservice.users.utils.Rabbitmq.exchanges.Msg;

public class Forgotten_password_msg implements Msg {
  private String email;
  private String subject = "Reset your password";
  private String html;

  // CONSTRUCTOR---------------------------------------------------------------
  public Forgotten_password_msg(String email, String code) {
    this.email = email;
    this.html = "<b>click on the following link:</b>" +
        "<a href=\"\">Restore new password</a>" +
        "<b> and use this secret code: " + code + "</b>";
  }

  // GETTERS AND SETTERS--------------------------------------------------------
  public String getEmail() {
    return email;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }
}
