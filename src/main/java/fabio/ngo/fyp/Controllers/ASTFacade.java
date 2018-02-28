package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Ultilities.CompilingJavaFiles;
import fabio.ngo.fyp.Ultilities.Constants;
import fabio.ngo.fyp.Ultilities.RunningJavaFiles;

public class ASTFacade implements Constants {
  private static ASTFacade instance;
  public static ASTFacade getInstance(){
    if(instance == null){
      instance = new ASTFacade();
    }
    return instance;
  }
  public void generateAST (String CLONE_ROOT_DIR){
    ConstantFacade.getInstance().writeFile(CLONE_ROOT_DIR+"src\\ast1");
    String[] jarFiles = {"jastadd2","ant"};
    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_ROOT_DIR+"src\\ast1", CLONE_ROOT_DIR+"lib", "",jarFiles);
    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_ROOT_DIR+"src", CLONE_ROOT_DIR+"lib", "",jarFiles);
    compilingJavaFiles.compile();
    runningJavaFiles.run("ast1.SemanticAnalyzerGenerator", null);
  }
  public void compileAST (String CLONE_ROOT_DIR){
    ConstantFacade.getInstance().writeFile(CLONE_ROOT_DIR+"src\\ast");
    String[] jarFiles = {"jastadd2","ant"};
    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_ROOT_DIR+"src\\ast", CLONE_ROOT_DIR+"lib", "",jarFiles);
//    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_ROOT_DIR+"src", CLONE_ROOT_DIR+"lib", "",jarFiles);
    compilingJavaFiles.compile();
//    runningJavaFiles.run("ast.SemanticAnalyzerGenerator", null);
  }



}
