package fabio.ngo.fyp.Models;

public class FrontEndBodyRequest {
  String token;


  public FrontEndContentRequest getContent() {
    return content;
  }

  public void setContent(FrontEndContentRequest content) {
    this.content = content;
  }

  FrontEndContentRequest content;
  public FrontEndBodyRequest() {
  }
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


}
