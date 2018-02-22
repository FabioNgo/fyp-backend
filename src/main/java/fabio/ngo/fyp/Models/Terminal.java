package fabio.ngo.fyp.Models;

import java.util.ArrayList;

public class Terminal {
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Terminal && ((Terminal) obj).getIdentifier().equals(this.getIdentifier());
  }

  public String getBaseTerminal() {
    return baseTerminal;
  }

  public void setBaseTerminal(String baseTerminal) {
    this.baseTerminal = baseTerminal;
  }

  public ArrayList<Terminal> getSubTerminals() {
    return subTerminals;
  }

  public void setSubTerminals(ArrayList<Terminal> subTerminals) {
    this.subTerminals = subTerminals;
  }

  public void addSubTerminal(Terminal terminal) {
    if (terminal.baseTerminal.equals(this.identifier)) {
      if (subTerminals.contains(terminal)) {
      } else {
        subTerminals.add(terminal);
      }
    }

  }

  public void setBeaverRule(String beaverRule) {
    this.beaverRule = beaverRule;
  }

  public enum TokenExtendedEnum {
    Optional,
    NonEmpty,
    PossibleEmpty,
    Undefined
  }

  private String identifier = "";
  private String rule = "";
  private String beaverRule ="";
  private boolean isStartSymbol;
  private String baseTerminal ="";
  private ArrayList<Terminal> subTerminals = new ArrayList<>();
  private ArrayList<String> semanticTokens = new ArrayList<>();
  private ArrayList<String> semanticActions = new ArrayList<>();

  public void addSemanticToken(String semanticToken) {
    this.semanticTokens.add(semanticToken);
  }
  public void addSemanticAction(String semanticAction) {
    this.semanticActions.add(semanticAction);
  }
  public ArrayList<String> getSemanticTokens() {
    return semanticTokens;
  }

  public ArrayList<String> getSemanticActions() {
    return semanticActions;
  }

  public void setSemanticTokens(ArrayList<String> semanticTokens) {
    this.semanticTokens = semanticTokens;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getRule() {
    return rule;
  }
  public String getBeaverRule(){
    return this.beaverRule;
  }
  public void setRule(String rule) {
    this.rule = rule;
  }





  public boolean isStartSymbol() {
    return isStartSymbol;
  }

  public void setIsStartSymbol(String isStartSymbol) {
    if (isStartSymbol.equals("true")) {
      this.isStartSymbol = true;
    } else {
      this.isStartSymbol = false;
    }
  }
}
