package fabio.ngo.fyp.Controllers;

public class MyRequestBody {

  public void setToken(String token) {
    this.token = token;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getToken() {
    return token;
  }

  public String getContent() {
    return content;
  }

  String content;
  String token;

  MyRequestBody() {
  }
}
