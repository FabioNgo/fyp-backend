package fabio.ngo.fyp.Models;

import fabio.ngo.fyp.Managers.FrontEndContentManager;

import java.util.ArrayList;

public class Terminal {
  private String identifier;
  private String rule;
  private ArrayList<String> semanticActions = new ArrayList<>();

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getRule() {
    return rule;
  }

  public void setRule(String rule) {
    this.rule = rule;
  }

  public void addSemanticAction() {
    ArrayList<Rule> terminals = FrontEndContentManager.getInstance().getFrontEndContentRequest().getRules();
    ArrayList<String> arguments = new ArrayList<>();
    String[] tokens = rule.split("[\\s']");
    tokensloop: for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      for (Rule terminal : terminals) {
        if (terminal.getToken().equals(token) ) {
          if(terminal.getToken().equals("ID")) {
            semanticActions.add(String.format("Symbol var%d = _symbols[offset + %d];", i + 1, i + 1));
            arguments.add(String.format("var%d", i + 1));

          }
          continue tokensloop;
        }
      }
      semanticActions.add(String.format("%s var%d = (%s)_symbols[offset + %d].value;",token,i+1,token,i+1));
      arguments.add(String.format("var%d",i+1));
    }
    //add return action
    StringBuilder argumentString = new StringBuilder();
    if(arguments.size() > 0){
      for(String argument: arguments){
        argumentString.append(argument).append(",");
      }
      argumentString.deleteCharAt(argumentString.length()-1);
    }
    semanticActions.add(String.format("return new %s (%s);",identifier, argumentString.toString()));
  }
  public String getSemanticActionString(){
    StringBuilder result = new StringBuilder();
    for(String semanticAction: semanticActions){
      result.append(semanticAction).append("\n");
    }
    return result.toString();
  }
}
