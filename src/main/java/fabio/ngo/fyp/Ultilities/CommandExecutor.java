package fabio.ngo.fyp.Ultilities;

import java.io.*;

public class CommandExecutor {
  protected String type;
  public String[] command;
  public static final String JAVA_PATH_7 = "C:\\Program Files\\Java\\jdk1.7.0_80\\bin\\";
  public static final String JAVA_PATH = "";
  public CommandExecutor(String... command){
    this.command = command;
  }
  public ProcessResult exeCommand(){
    try {
      ProcessBuilder   ps=new ProcessBuilder(command);
      ps.redirectErrorStream(true);
      System.out.println(toStringCommand());
      Process pr = Runtime.getRuntime().exec(toStringCommand());
      BufferedReader errorReader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
      String errorMessage = "";
      String line = "";
      while ((line = errorReader.readLine()) != null) {
        errorMessage += (line + "\n");
      }
      String outputMessage = "";
      BufferedReader ouputReader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
      while ((line = ouputReader.readLine()) != null) {
        outputMessage += (line + "\n");
      }
      return new ProcessResult(type,errorMessage,outputMessage);
    } catch (IOException e) {
      new ProcessResult(type,e.getMessage(),"");
    }
    return new ProcessResult(type,"","");
  }
  private String toStringCommand(){
    String result ="";
    for(int i=0;i< this.command.length;i++){
      result += (this.command[i]+" ");
    }
    return result;
  }
}
