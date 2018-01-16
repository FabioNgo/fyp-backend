package fabio.ngo.fyp.Ultilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GetJavaFilesList extends CommandExecutor {
  private ArrayList<String> files = new ArrayList<>();
  public String getJavaFilesList (String SOURCE_DIR){
    File dir = new File(SOURCE_DIR);
    getJavaFiles(dir);
    File file = new File(SOURCE_DIR+"\\files.txt");
    try {
      file.createNewFile();
      FileWriter fileWriter = new FileWriter(file);
      for(String filePath : files){
        fileWriter.write(filePath+"\n");
      }
      fileWriter.close();
      return file.getAbsolutePath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void getJavaFiles(File dir) {
    if(dir.isDirectory()){
      for(File file : dir.listFiles()){
        getJavaFiles(file);
      }
    }else{
      if(dir.getName().endsWith(".java")){
        files.add(dir.getAbsolutePath());
      }
    }
  }
}
