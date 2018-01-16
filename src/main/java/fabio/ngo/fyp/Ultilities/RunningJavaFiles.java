package fabio.ngo.fyp.Ultilities;

public class RunningJavaFiles extends CommandExecutor {
  private String SRC_ROOT_DIR;
  private String LIB_ROOT_DIR;
  private String fileName;
  private String[] jarFiles;
  private String JAVA_PATH;
  public RunningJavaFiles(String SRC_ROOT_DIR, String LIB_ROOT_DIR, String JAVA_PATH,String... jarFiles) {
    this.type = "Running";
    this.LIB_ROOT_DIR = LIB_ROOT_DIR;
    this.SRC_ROOT_DIR = SRC_ROOT_DIR;
    this.jarFiles = jarFiles;
    this.JAVA_PATH = JAVA_PATH;
  }

  @Override
  public String toString() {
    String output = "";
    for (int i = 0; i < this.command.length; i++) {
      output += this.command[i];
    }
    return output;
  }

  public ProcessResult run(String fileName, String fileDir) {
    if(fileDir == null){
      fileDir = SRC_ROOT_DIR;
    }
    this.fileName = fileName;
    String cpArg = "";
    if(this.jarFiles.length > 0){
      cpArg= "-cp ";

      for(int i=0;i<jarFiles.length;i++){
        cpArg += LIB_ROOT_DIR + jarFiles[i] +".jar;";
      }
      cpArg += fileDir;
    }else{
      cpArg = "-cp "+ fileDir;
    }
    this.command = new String[]{JAVA_PATH+"java.exe",
      cpArg,
      this.fileName
    };
    return this.exeCommand();
  }
}
