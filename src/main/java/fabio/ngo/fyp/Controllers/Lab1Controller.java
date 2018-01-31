//package fabio.ngo.fyp.Controllers;
//
//import fabio.ngo.fyp.Ultilities.CompilingJavaFiles;
//import fabio.ngo.fyp.Ultilities.ProcessResult;
//import fabio.ngo.fyp.Ultilities.RunningJavaFiles;
//import org.springframework.util.FileSystemUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
//
//@RestController
//@RequestMapping("/lab1")
//
//public class Lab1Controller {
//
//
//  private final AtomicLong counter = new AtomicLong();
//
//  @CrossOrigin("http://localhost:4200")
//  @RequestMapping(value = "/generate", method = RequestMethod.POST)
//  @ResponseBody
//  public ArrayList<ProcessResult> generate(@RequestBody MyRequestBody requestWrapper) {
//    String token = requestWrapper.token;
//    String content = requestWrapper.content[0];
//    final String ORIGIN_DIR = "C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab1\\";
//    final String WORKING_DIR = "C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\working\\Lab1";
//
//    final String CLONE_SRC_ROOT_DIR = WORKING_DIR + "\\" + token + "\\src\\frontend\\";
//    final String CLONE_LIB_ROOT_DIR = WORKING_DIR+"\\" +token+"\\lib\\";
//    final String CLONE_EXC_ROOT_DIR = WORKING_DIR+"\\"+ token +"\\src\\";
//    ArrayList<ProcessResult> results = new ArrayList<>();
//    /** Inititalize **/
//    File src = new File(ORIGIN_DIR);
//    File dst = new File(WORKING_DIR+"\\"+token);
//    dst.mkdir();
//
//
//    try {
//
//      FileSystemUtils.copyRecursively(src,dst);
//      //create lexer.jflex
//      File lexer = new File(CLONE_SRC_ROOT_DIR+"\\lexer.flex");
//      lexer.createNewFile();
//      FileWriter fileWriter = new FileWriter(lexer,false);
//      fileWriter.write(content);
//      fileWriter.close();
//      // create constants file
//      File constants = new File(CLONE_SRC_ROOT_DIR+"\\Constants.java");
//      List<String> constantsTemplateParts = Files.readAllLines(constants.toPath());
//      String constantsContentTemplate = "";
//      for(String constantsTemplatePart: constantsTemplateParts){
//        constantsContentTemplate += (constantsTemplatePart+"\n");
//      }
//      fileWriter = new FileWriter(constants,false);
//      fileWriter.write(String.format(constantsContentTemplate,(WORKING_DIR+"\\"+token).replace("\\","/")));
//      fileWriter.close();
//    } catch (IOException e) {
//      results.add(new ProcessResult("initialization",e.getMessage(),""));
//    }
//    /**execute **/
//    String[] jarFiles = {"jflex", "ant"};
//    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_SRC_ROOT_DIR, CLONE_LIB_ROOT_DIR, "",jarFiles);
//    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_EXC_ROOT_DIR, CLONE_LIB_ROOT_DIR, "",jarFiles);
//
//    results.add(compilingJavaFiles.compile());
//    results.add(runningJavaFiles.run("frontend.Start", null));
//    return results;
//
//  }
//  @CrossOrigin("http://localhost:4200")
//  @RequestMapping(value = "/run", method = RequestMethod.POST)
//  @ResponseBody
//  public ArrayList<ProcessResult> run(@RequestBody MyRequestBody requestWrapper) {
//    String token = requestWrapper.token;
//    String content = requestWrapper.content[0];
//    final String ORIGIN_DIR = "C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\resources\\Lab1\\";
//    final String WORKING_DIR = "C:\\Users\\fabiongo\\WebstormProjects\\fyp\\backend\\working\\Lab1";
//
//    final String CLONE_SRC_ROOT_DIR = WORKING_DIR + "\\" + token + "\\src\\lexer\\";
//    final String CLONE_LIB_ROOT_DIR = WORKING_DIR+"\\" +token+"\\lib\\";
//    final String CLONE_EXC_ROOT_DIR = WORKING_DIR+"\\"+ token +"\\src\\";
//    ArrayList<ProcessResult> results = new ArrayList<>();
//    try {
//      //create lexerTest file
//      File lexerTest = new File(CLONE_SRC_ROOT_DIR+"\\LexerTests.java");
////      String lexerTestContent = new Scanner(lexerTest).useDelimiter("\\Z").next();
//      String lexerTestContent = content;
////      System.out.println(lexerTestContent);
////      lexerTestContent = lexerTestContent.replace("//TestHolder",content);
//      FileWriter fileWriter = new FileWriter(lexerTest,false);
//      fileWriter.write(lexerTestContent);
//      fileWriter.close();
//    } catch (IOException e) {
//      results.add(new ProcessResult("initialization",e.getMessage(),""));
//    }
//    String[] jarFiles = {"junit","hamcrest"};
//
//    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_SRC_ROOT_DIR, CLONE_LIB_ROOT_DIR, "",jarFiles);
//    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_SRC_ROOT_DIR, CLONE_LIB_ROOT_DIR, "",jarFiles);
//
//    results.add(compilingJavaFiles.compile());
//    results.add(runningJavaFiles.run("lexer.LexerTests", CLONE_EXC_ROOT_DIR));
//    return results;
//  }
//}
