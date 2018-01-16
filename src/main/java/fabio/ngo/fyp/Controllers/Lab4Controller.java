package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Ultilities.*;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/lab4")
public class Lab4Controller {


  private final AtomicLong counter = new AtomicLong();


  @CrossOrigin("http://localhost:4200")
  @RequestMapping(value = "/generate", method = RequestMethod.POST)
  @ResponseBody
  public ArrayList<ProcessResult> generate(@RequestBody MyRequestBody requestWrapper){
    String token = requestWrapper.token;
    String content1 = requestWrapper.content[0];
    String content2 = requestWrapper.content[1];
    final String ORIGIN_DIR = "C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab4\\";
    final String WORKING_DIR = "C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\working\\Lab4\\";
    final String CLONE_SRC_ROOT_DIR = WORKING_DIR + "\\" + token + "\\src\\";
    final String CLONE_LIB_ROOT_DIR = WORKING_DIR+"\\" +token+"\\lib\\";
    final String CLONE_EXC_ROOT_DIR = WORKING_DIR+"\\"+ token +"\\src\\";
    ArrayList<ProcessResult> results = new ArrayList<>();
    /** Inititalize **/
    File src = new File(ORIGIN_DIR);
    File dst = new File(WORKING_DIR+"\\"+token);
    dst.mkdir();
    try {
      FileSystemUtils.copyRecursively(src,dst);
      //Write ExprCodeGenerator.java
      File exprCode = new File(CLONE_SRC_ROOT_DIR+"\\ast\\ExprCodeGenerator.java");
      exprCode.createNewFile();
      FileWriter fileWriter = new FileWriter(exprCode,false);
      fileWriter.write(content1);
      fileWriter.close();

      //Write StmtCodeGenerator.java
      File stmtCode = new File(CLONE_SRC_ROOT_DIR+"\\ast\\StmtCodeGenerator.java");
      stmtCode.createNewFile();
      fileWriter = new FileWriter(stmtCode,false);
      fileWriter.write(content2);
      fileWriter.close();
      // create constants file
      File constants = new File(CLONE_SRC_ROOT_DIR+"\\frontend\\Constants.java");
      List<String> constantsTemplateParts = Files.readAllLines(constants.toPath());
      String constantsContentTemplate = "";
      for(String constantsTemplatePart: constantsTemplateParts){
        constantsContentTemplate += (constantsTemplatePart+"\n");
      }
      fileWriter = new FileWriter(constants,false);
      fileWriter.write(String.format(constantsContentTemplate,(WORKING_DIR+"\\"+token).replace("\\","/")));
      fileWriter.close();
    } catch (IOException e) {
      results.add(new ProcessResult("initialization",e.getMessage(),""));
    }
    ProcessResult compileResult = new ProcessResult("Compiling", "", "done");
    ProcessResult runningResult = new ProcessResult("Running", "", "done");

    results.add(compileResult);
    results.add(runningResult);
    return results;

  }

  @CrossOrigin("http://localhost:4200")
  @RequestMapping(value = "/run", method = RequestMethod.POST)
  @ResponseBody
  public ArrayList<ProcessResult> run(@RequestBody MyRequestBody requestWrapper) {
    String token = requestWrapper.token;
    String content = requestWrapper.content[0];
    final String ORIGIN_DIR = "C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab4\\";
    final String WORKING_DIR = "C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\working\\Lab4";

    final String CLONE_SRC_ROOT_DIR = WORKING_DIR + "\\" + token + "\\src\\";
    final String CLONE_LIB_ROOT_DIR = WORKING_DIR+"\\" +token+"\\lib\\";
    final String CLONE_EXC_ROOT_DIR = WORKING_DIR+"\\"+ token +"\\src\\";
    String[] jarFiles = {"junit","lexer","parser","hamcrest","beaver-rt","soot","ast"};
    ArrayList<ProcessResult> results = new ArrayList<>();
    try {
      //create lexerTest file
      File compilerTest = new File(CLONE_SRC_ROOT_DIR+"ast\\CompilerTests.java");
      String compilerTestContent = new Scanner(compilerTest).useDelimiter("\\Z").next();
//      System.out.println(lexerTestContent);
      compilerTestContent = compilerTestContent.replace("//TestHolder",content);
      FileWriter fileWriter = new FileWriter(compilerTest,false);
      fileWriter.write(compilerTestContent);
      fileWriter.close();
    } catch (IOException e) {
      results.add(new ProcessResult("initialization",e.getMessage(),""));
    }
    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_SRC_ROOT_DIR, CLONE_LIB_ROOT_DIR, CommandExecutor.JAVA_PATH_7,jarFiles);
    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_SRC_ROOT_DIR, CLONE_LIB_ROOT_DIR,CommandExecutor.JAVA_PATH_7,jarFiles);

    results.add(compilingJavaFiles.compile());
    results.add(runningJavaFiles.run("ast.CompilerTests",CLONE_EXC_ROOT_DIR));
    File dir = new File(WORKING_DIR+"\\"+token);
    Ultilities.deleteFiles(dir);
    return results;
  }
}
