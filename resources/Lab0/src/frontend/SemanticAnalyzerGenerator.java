package frontend;
import jastadd.JastAddTask;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.types.AbstractFileSet;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.selectors.FilenameSelector;

import java.io.File;

public class Start {
  public static void main(String... args) {
    Ant ant = new Ant();
    Project p = new Project();
    p.setBaseDir(new File("."));
    p.setDefault("gen");
    JastAddTask task = new JastAddTask();
    FileSet fileSet = new FileSet();
    fileSet.setDir(new File("C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab3\\src\\frontend"));
    fileSet.setIncludes("**/*.ast");
    fileSet.setIncludes("**/*.jrag");
    fileSet.setIncludes("**/*.jadd");
    task.setProject(p);
    task.addConfiguredFileSet(fileSet);
    task.setPackage("ast");
    task.setOutdir("C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab3\\src");
    task.setBeaver(true);
    task.setRewrite(false);
//    task.setFile(new File("C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab2\\src\\frontend\\parser.beaver"));
//    task.setDestdir(new File("C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab2\\src"));
    task.execute();
  }

}
