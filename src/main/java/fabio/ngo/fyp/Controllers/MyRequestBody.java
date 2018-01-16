package fabio.ngo.fyp.Controllers;

public class MyRequestBody {

  public void setToken(String token) {
    this.token = token;
  }

  public void setContent(String[] content) {
    this.content = content;
  }

  String[] content;
  String token;

  MyRequestBody() {
  }
}
