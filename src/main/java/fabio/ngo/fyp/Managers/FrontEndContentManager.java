package fabio.ngo.fyp.Managers;

import fabio.ngo.fyp.Models.FrontEndContentRequest;

public class FrontEndContentManager {
  private FrontEndContentRequest frontEndContentRequest;
  private static FrontEndContentManager instance;
  public static FrontEndContentManager getInstance(){
    if(instance == null){
      instance = new FrontEndContentManager();
    }
    return instance;
  }


  public FrontEndContentRequest getFrontEndContentRequest() {
    return frontEndContentRequest;
  }

  public void setFrontEndContentRequest(FrontEndContentRequest frontEndContentRequest) {
    this.frontEndContentRequest = frontEndContentRequest;
  }
}
