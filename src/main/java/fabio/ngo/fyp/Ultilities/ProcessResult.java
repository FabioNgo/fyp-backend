package fabio.ngo.fyp.Ultilities;

import java.io.Serializable;
public class ProcessResult implements Serializable{
  private String errorMessage;
  private String outputMessage;
  private String title;
  private boolean isError;
  public ProcessResult(String title, String errorMessage, String outputMessage){
    this.title = title;
    this.errorMessage = errorMessage;
    this.outputMessage = outputMessage;
    if(this.errorMessage.compareTo("")!=0){
      isError = true;
    }else{
      isError = false;
    }
  }
  public String getErrorMessage() {
    return errorMessage;
  }

  public String getOutputMessage() {
    return outputMessage;
  }

  public String getTitle() {
    return title;
  }

  public boolean isError() {
    return isError;
  }
}
