package frontend;

import JFlex.anttask.JFlexTask;
import org.apache.tools.ant.Project;

import java.io.File;

public class Start implements Constants{
  public static void main(String... args) {
    Project p = new Project();
    p.setBasedir(".");
    p.setName("jflex");

    JFlexTask task = new JFlexTask();
    task.setProject(p);
    task.setOutdir(new File(ROOT_DIR+"/src/lexer"));
    task.setFile(new File(ROOT_DIR+"/src/frontend/lexer.flex"));
    task.execute();
  }

}
