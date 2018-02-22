package parser;
import parser.beaver.comp.run.AntTask;
import java.io.IOException;

import java.io.File;
import java.nio.file.Files;

public class ParserGenerator implements Constants{
  public static void main(String[] args) {
    File file = new File(ROOT_DIR+"/Parser.java");
    if(file.exists()){
      file.delete();
    }
    AntTask task = new AntTask();
    task.setFile(new File(ROOT_DIR+"/parser.beaver"));
    task.setDestdir(new File(ROOT_DIR));
    task.execute();
    File outputFile = new File(ROOT_DIR+"/parser/Parser.java");
    try {
      Files.move(outputFile.toPath(),file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
