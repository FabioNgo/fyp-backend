package ast;
import jastadd.JastAddTask;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.types.AbstractFileSet;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
public class SemanticAnalyzerGenerator implements Constants {
  public static void main(String... args) {
    Ant ant = new Ant();
    Project p = new Project();
    p.setBaseDir(new File("."));
    p.setDefault("gen");
    JastAddTask task = new JastAddTask();
    FileSet fileSet = new FileSet();
    fileSet.setDir(new File(ROOT_DIR));
    System.out.println(ROOT_DIR);
    fileSet.setIncludes("*.ast");
    fileSet.setIncludes("*.jrag");
    fileSet.setIncludes("*.jadd");
    task.setProject(p);
    task.addConfiguredFileSet(fileSet);
    task.setPackage("ast");
    File outDir = new File(ROOT_DIR);
    task.setOutdir(outDir.getParentFile().toPath().toAbsolutePath().toString());
    task.setBeaver(true);
    task.setRewrite(false);
//    task.setFile(new File("C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab2\\src\\frontend\\parser.beaver"));
//    task.setDestdir(new File("C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab2\\src"));
    task.execute();
    File outputFile = new File(ROOT_DIR+"/ast");
    File file = new File(ROOT_DIR);
    try {
      Files.move(outputFile.toPath(),file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
