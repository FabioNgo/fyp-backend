package fabio.ngo.fyp.Ultilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Ultilities {
  public static boolean deleteFiles (File dir){
    if(dir.isDirectory()){
      for(File file: dir.listFiles()){
        deleteFiles(file);
      }

    }
    try {
      return Files.deleteIfExists(dir.toPath());
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
