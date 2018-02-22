package fabio.ngo.fyp.Ultilities;

public class CompilingJavaFiles extends CommandExecutor {
  public CompilingJavaFiles (String SRC_ROOT_DIR, String LIB_ROOT_DIR,String JAVA_PATH, String... jarFiles){
    this.type = "Compiling";
    String cpArg = "-cp ";
    if(jarFiles.length > 0){
      for(int i=0;i<jarFiles.length;i++) {
        cpArg += (LIB_ROOT_DIR+"\\" + jarFiles[i] + ".jar;");
      }
    }

    if(jarFiles.length == 0){
      cpArg = "";
    }
    String soureFile = new GetJavaFilesList().getJavaFilesList(SRC_ROOT_DIR);
    this.command = new String[]{JAVA_PATH+"javac.exe -Xlint:unchecked",
      cpArg,
      "@"+soureFile
    };
  }

  @Override
  public String toString() {
    String output = "";
    for(int i =0;i<this.command.length;i++){
      output += this.command[i];
    }
    return output;
  }

  public ProcessResult compile(){
    return this.exeCommand();
  }
}
