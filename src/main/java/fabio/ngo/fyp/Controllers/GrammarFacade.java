package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Managers.GrammarsManager;
import fabio.ngo.fyp.Ultilities.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GrammarFacade implements Constants {
  private static final String GRAMMARS_HOLDER = "#GRAMMARS HOLDER#";
  private static GrammarFacade instance;
  public static GrammarFacade getInstance(){
    if(instance == null){
      instance = new GrammarFacade();
    }
    return instance;
  }
  private File grammarTemplateFile;
  private ArrayList<String> outputFileContent;
  public void writeFile(String DES_SOURCE_DIR){
    this.generateFileContent();
    File grammarFile = new File(DES_SOURCE_DIR + "\\ast1\\grammar.ast");
    try {
//      grammarFile.createNewFile();
      FileWriter fileWriter = new FileWriter(grammarFile);
      for(String line: outputFileContent){
        fileWriter.write(line +"\n");
      }
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> generateFileContent (){
    outputFileContent = new ArrayList<>();
    grammarTemplateFile = new File(RESOURCE_DIR_ROOT_SRC+"\\ast1\\grammar.ast");
    try {
      List<String> lines = Files.readAllLines(grammarTemplateFile.toPath());
      for (String line :lines){
        if(line.contains(GRAMMARS_HOLDER)){
          outputFileContent.addAll(getGrammarsStrings());
          continue;
        }
        outputFileContent.add(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return outputFileContent;
  }

  private ArrayList<String> getGrammarsStrings() {
    return GrammarsManager.getInstance().getStringContent();
  }


}
