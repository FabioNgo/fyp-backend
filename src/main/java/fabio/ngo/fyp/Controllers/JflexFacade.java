package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Managers.FrontEndContentManager;
import fabio.ngo.fyp.Models.Definition;
import fabio.ngo.fyp.Models.Rule;
import fabio.ngo.fyp.Ultilities.CompilingJavaFiles;
import fabio.ngo.fyp.Ultilities.Constants;
import fabio.ngo.fyp.Ultilities.RunningJavaFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JflexFacade implements Constants {
  private static final String DEFINITION_HOLDER = "#DEFINITIONS HOLDER#";
  private static final String KEYWORD_HOLDER = "#KEYWORDS HOLDER#";
  private static JflexFacade instance;
  public static JflexFacade getInstance(){
    if(instance == null){
      instance = new JflexFacade();
    }
    return instance;
  }
  private File jflexTemplateFile;
  private ArrayList<String> outputFileContent;
  public void writeFile(String DES_SOURCE_DIR){
    this.generateFileContent();
    File jflexFile = new File(DES_SOURCE_DIR + "\\lexer\\lexer.flex");
    try {
      jflexFile.createNewFile();
      FileWriter fileWriter = new FileWriter(jflexFile);
      for(String line: outputFileContent){
        fileWriter.write(line +"\n");
      }
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void generateLexerFile(String CLONE_ROOT_DIR){
    ConstantFacade.getInstance().writeFile(CLONE_ROOT_DIR+"src\\lexer");
    String[] jarFiles = {"jflex", "ant"};
    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_ROOT_DIR+"src\\lexer", CLONE_ROOT_DIR+"lib", "",jarFiles);
    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_ROOT_DIR+"src", CLONE_ROOT_DIR+"lib", "",jarFiles);
    compilingJavaFiles.compile();
    runningJavaFiles.run("lexer.LexerGenerator", null);
  }
  public ArrayList<String> generateFileContent (){
    outputFileContent = new ArrayList<>();
    jflexTemplateFile = new File(RESOURCE_DIR_ROOT_SRC+"\\lexer\\lexer.flex");
    try {
      List<String> lines = Files.readAllLines(jflexTemplateFile.toPath());
      for (String line :lines){
        if(line.contains(DEFINITION_HOLDER)){
          outputFileContent.addAll(getDefinitionStrings());
          continue;
        }
        if(line.contains(KEYWORD_HOLDER)){
          outputFileContent.addAll(getKeywordStrings());
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

  private ArrayList<String> getKeywordStrings() {
    ArrayList<String> result = new ArrayList<>();
    ArrayList<Rule> rules = FrontEndContentManager.getInstance().getFrontEndContentRequest().getRules();
    ruleLoop: for(Rule rule: rules){
      ArrayList<Definition> definitions = FrontEndContentManager.getInstance().getFrontEndContentRequest().getDefinitions();
      for(Definition definition: definitions){
        if(rule.getKeyword().equals(definition.getIdentifier())){
          if(rule.isIgnore()){
            result.add(String.format("{%s}+  { }",rule.getKeyword()));
          }else {
            result.add(String.format("{%s}  { return token(%s);}", rule.getKeyword(), rule.getToken()));
          }
          continue ruleLoop;
        }
      }
      if(rule.isIgnore()){
        result.add(String.format("\"%s\"+  { }",rule.getKeyword()));
      }else {
        result.add(String.format("\"%s\"  { return token(%s);}", rule.getKeyword(), rule.getToken()));
      }
    }
    return result;
  }

  private ArrayList<String> getDefinitionStrings() {
    ArrayList<String> result = new ArrayList<>();
    ArrayList<Definition> definitions = FrontEndContentManager.getInstance().getFrontEndContentRequest().getDefinitions();
    for(Definition definition: definitions){
      result.add(String.format("%s = %s",definition.getIdentifier(),definition.getExpression()));
    }
    return result;
  }

}
