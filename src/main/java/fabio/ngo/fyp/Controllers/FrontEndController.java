package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Managers.FrontEndContentManager;
import fabio.ngo.fyp.Managers.GrammarsManager;
import fabio.ngo.fyp.Managers.TerminalsManager;
import fabio.ngo.fyp.Models.FrontEndBodyRequest;
import fabio.ngo.fyp.Ultilities.*;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@CrossOrigin("http://localhost:4200")
//@RequestMapping(value="/compiler")
public class FrontEndController implements Constants{
  @RequestMapping(value="/create", method = RequestMethod.POST)
  public ProcessResult create(@RequestBody FrontEndBodyRequest requestWrapper){
    File src = new File(RESOURCE_DIR_ROOT);
    File dst = new File(WORKING_DIR+"\\"+requestWrapper.getToken());
    dst.mkdir();

    FrontEndContentManager.getInstance().setFrontEndContentRequest(requestWrapper.getContent());
    TerminalsManager.getInstance().generateSemanticAction();
    GrammarsManager.getInstance().generateGrammarRule();
    //copy files
    try {
      FileSystemUtils.copyRecursively(src,dst);
      JflexFacade.getInstance().writeFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\src\\");
      JflexFacade.getInstance().generateLexerFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\");
      BeaverFacade.getInstance().writeFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\src\\");
      BeaverFacade.getInstance().generateBeaverFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\");
      GrammarFacade.getInstance().writeFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\src\\");
      ASTFacade.getInstance().generateAST(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\");

    } catch (IOException e) {
      e.printStackTrace();
    }
//    ArrayList<String> semanticActions = FrontEndContentManager.getInstance().getSemanticAction();
//    for (String semanticRule: semanticActions){
//
////      System.out.println(semanticRule);
//    }
    System.out.println("*****************************************************");
//    ArrayList<String> semanticrules = GrammarsManager.getInstance().getStringContent();
//    for (String semanticRule: semanticrules){
//
////      System.out.println(semanticRule);
//    }
    return new ProcessResult("","","");
  }

  @RequestMapping(value="/run", method = RequestMethod.POST)
  public ProcessResult run (@RequestBody MyRequestBody requestWrapper){
    String CLONE_ROOT_DIR = WORKING_DIR+"\\"+requestWrapper.getToken()+"\\";
    ConstantFacade.getInstance().writeFile(CLONE_ROOT_DIR+"src\\ast");
    String[] jarFiles = {"jastadd2","soot"};
    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_ROOT_DIR+"src\\ast", CLONE_ROOT_DIR+"lib", "",jarFiles);
//    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_ROOT_DIR+"src", CLONE_ROOT_DIR+"lib", "",jarFiles);

    File inputFile=  new File(CLONE_ROOT_DIR+"src\\input.txt");
    try {
      inputFile.createNewFile();
      FileWriter fileWriter = new FileWriter(inputFile);
      String testContent = requestWrapper.content.replace("//TO DO","");
      fileWriter.write(requestWrapper.content);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    compilingJavaFiles.compile();
    String[] jarFiles2 = {"ant","jastadd2","jflex","soot"};
    ConstantFacade.getInstance().writeFile(CLONE_ROOT_DIR+"src");
    String[] args = {"Test","main"};
    compilingJavaFiles = new CompilingJavaFiles(CLONE_ROOT_DIR+"src", CLONE_ROOT_DIR+"lib", CommandExecutor.JAVA_PATH_7,jarFiles2);
    compilingJavaFiles.compile();
    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_ROOT_DIR+"src", CLONE_ROOT_DIR+"lib", CommandExecutor.JAVA_PATH_7,jarFiles2,args);
    ProcessResult processResult = runningJavaFiles.run("CompilerTests", null);
    return processResult;
  }
}
