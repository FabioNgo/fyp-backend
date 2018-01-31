package fabio.ngo.fyp.Controllers;

public class MyRequestBody {

  public void setToken(String token) {
    this.token = token;
  }

  public void setContent(Object content) {
    this.content = content;
  }

  Object content;
  String token;

  MyRequestBody() {
  }
}
