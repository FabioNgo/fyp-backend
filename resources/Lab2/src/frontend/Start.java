package frontend;
import beaver.comp.run.AntTask;

import java.io.File;

public class Start implements Constants{
  public static void main(String[] args) {
    File file = new File(ROOT_DIR+"/parser/Parser.java");
    if(file.exists()){
      file.delete();
    }
    AntTask task = new AntTask();
    task.setFile(new File(ROOT_DIR+"/src/frontend/parser.beaver"));
    task.setDestdir(new File(ROOT_DIR+"/src"));
    task.execute();
  }

}
