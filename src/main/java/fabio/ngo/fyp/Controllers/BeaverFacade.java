package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Managers.FrontEndContentManager;
import fabio.ngo.fyp.Managers.TerminalsManager;
import fabio.ngo.fyp.Models.Rule;
import fabio.ngo.fyp.Models.Terminal;
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

public class BeaverFacade implements Constants {
  private static final String TERMINALS_HOLDER = "#TERMINALS HOLDERS#";
  private static final String GOAL_HOLDER = "#GOAL HOLDER#";
  private static final String NONTERMINALS_HOLDER = "#NONTERMINALS HOLDER#";

  private static BeaverFacade instance;

  public static BeaverFacade getInstance() {
    if (instance == null) {
      instance = new BeaverFacade();
    }
    return instance;
  }

  private File beaverTemplateFile;
  private ArrayList<String> outputFileContent;

  public void writeFile(String DES_SOURCE_DIR) {
    this.generateFileContent();
    File beaverFile = new File(DES_SOURCE_DIR + "\\parser\\parser.beaver");


    try {
      beaverFile.createNewFile();
      FileWriter fileWriter = new FileWriter(beaverFile);
      for (String line : outputFileContent) {
        fileWriter.write(line + "\n");
      }
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void generateBeaverFile(String CLONE_ROOT_DIR) {
    ConstantFacade.getInstance().writeFile(CLONE_ROOT_DIR + "src\\parser");
    String[] jarFiles = {"ant"};
    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_ROOT_DIR + "src\\parser", CLONE_ROOT_DIR + "lib", "", jarFiles);
    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_ROOT_DIR + "src", CLONE_ROOT_DIR + "lib", "", jarFiles);
    compilingJavaFiles.compile();
    runningJavaFiles.run("parser.ParserGenerator", null);
  }

  public ArrayList<String> generateFileContent() {

    outputFileContent = new ArrayList<>();
    beaverTemplateFile = new File(RESOURCE_DIR_ROOT_SRC + "\\parser\\parser.beaver");
    try {
      List<String> lines = Files.readAllLines(beaverTemplateFile.toPath());
      for (String line : lines) {
        if (line.contains(TERMINALS_HOLDER)) {
          outputFileContent.add(getTerminalString());
          continue;
        }
        if (line.contains(GOAL_HOLDER)) {
          outputFileContent.add(getGoalString());
          continue;
        }
        if (line.contains(NONTERMINALS_HOLDER)) {
          outputFileContent.addAll(getNonTerminalsString());
          outputFileContent.addAll(this.setupBaseTerminal());
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

  private ArrayList<String> setupBaseTerminal() {
    ArrayList<Terminal> terminals = TerminalsManager.getInstance().getTerminals();
    for (Terminal terminal : terminals) {
      for (Terminal terminal1 : terminals) {
        terminal.addSubTerminal(terminal1);
      }
    }
    ArrayList<String> result = new ArrayList<>();
    for (Terminal terminal : terminals) {
      ArrayList<Terminal> subTerminals = terminal.getSubTerminals();
      if (subTerminals.size() == 0) {
        continue;
      } else {
        String temp = String.format("%s = ", terminal.getIdentifier());
        for (int i = 0; i < subTerminals.size(); i++) {
          Terminal subTerminal = subTerminals.get(i);
          String temp2 = "";
          if (i == 0) {
            temp2 = String.format("%s {:\nreturn (%s)_symbols[offset + 1].value;\n:}\n", subTerminal.getIdentifier(), subTerminal.getIdentifier());
          } else {
            temp2 = String.format("|%s {:\nreturn (%s)_symbols[offset + 1].value;\n:}\n", subTerminal.getIdentifier(), subTerminal.getIdentifier());
          }
          temp += temp2;
        }
        result.add(temp + ";");
      }

    }
    return result;
  }


  private String getGoalString() {
    ArrayList<Terminal> terminals = FrontEndContentManager.getInstance().getFrontEndContentRequest().getTerminals();
    for (Terminal terminal : terminals) {
      if (terminal.isStartSymbol()) {
        return terminal.getIdentifier();
      }
    }
//    return FrontEndContentManager.getInstance().getFrontEndContentRequest().getStartSymbol();
//    return "Module";
    return "";
  }

  private ArrayList<String> getNonTerminalsString() {
    TerminalsManager terminalsManager = TerminalsManager.getInstance();
    ArrayList<String> result = new ArrayList<>();
    ArrayList<Terminal> terminals = TerminalsManager.getInstance().getAllTerminals();
    terminalLoop:
    for (Terminal terminal : terminals) {
      if (!terminal.getRule().equals("")) {
        String semanticActionString = terminalsManager.getSemanticActionString(terminal);
        String beaverRule = terminal.getBeaverRule();
        if(semanticActionString.equals("")){
          result.add(String.format("%s = %s;", terminal.getIdentifier(), beaverRule));
        }else{
          if(beaverRule.equals("")){
            result.add(String.format("%s = {:%s:};", terminal.getIdentifier(), semanticActionString));
          }else {
            result.add(String.format("%s = %s {:%s:};", terminal.getIdentifier(), beaverRule, semanticActionString));
          }
        }

      }

    }
    return result;
  }

  private String getTerminalString() {
    StringBuilder sb = new StringBuilder();
    ArrayList<Rule> rules = FrontEndContentManager.getInstance().getFrontEndContentRequest().getRules();
    for (Rule rule : rules) {
      if (!rule.isIgnore()) {
        sb.append(String.format("%s,", rule.getToken()));
      }

    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

}
