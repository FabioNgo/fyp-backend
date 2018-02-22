package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Ultilities.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ConstantFacade implements Constants {
  private static ConstantFacade instance;
  public static ConstantFacade getInstance(){
    if(instance == null){
      instance = new ConstantFacade();
    }
    return instance;
  }
  public void writeFile(String DES_SOURCE_DIR){

    File constantsFile = new File(DES_SOURCE_DIR + "\\Constants.java");
    try {
      List<String> constantsTemplateParts = Files.readAllLines(constantsFile.toPath());
      String constantsContentTemplate = "";
      for(String constantsTemplatePart: constantsTemplateParts){
        constantsContentTemplate += (constantsTemplatePart+"\n");
      }
      FileWriter fileWriter = new FileWriter(constantsFile,false);
      fileWriter.write(String.format(constantsContentTemplate,DES_SOURCE_DIR).replace("\\","/"));
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
