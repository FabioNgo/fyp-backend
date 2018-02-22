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
//  public static String toUpper1stCharCase(String input){
//    StringBuilder sb = new StringBuilder(input.toLowerCase());
//    sb.setCharAt(0, (char) (sb.charAt(0)+'A'-'a'));
//    return sb.toString();
//  }
}
