package example.mcroservice.users.utils.messages;

public class Password_reset_msg implements Msg{
  private String email;
  private String subject = "Passsword reset successfully";
  private String html;

  //CONSTRUCTOR----------------------------------------------------------------
  public Password_reset_msg(String email) {
    this.email = email;
    this.html = "<b>The password has been successfully reset</b>";
    
  }

  //GETTERS AND SETTERS--------------------------------------------------------

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
