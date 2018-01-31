package fabio.ngo.fyp.Models;

import java.util.ArrayList;

public class FrontEndContentRequest {
  public FrontEndContentRequest() {

  }


  public ArrayList<Definition> getDefinitions() {
    return definitions;
  }

  public void setDefinitions(ArrayList<Definition> definitions) {
    this.definitions = definitions;
  }

  public ArrayList<Terminal> getTerminals() {
    return terminals;
  }

  public void setTerminals(ArrayList<Terminal> terminals) {
    this.terminals = terminals;
  }

  public ArrayList<Rule> getRules() {
    return rules;
  }

  public void setRules(ArrayList<Rule> rules) {
    this.rules = rules;
  }

  public String getStartSymbol() {
    return startSymbol;
  }

  public void setStartSymbol(String startSymbol) {
    this.startSymbol = startSymbol;
  }

  ArrayList<Definition> definitions;
  ArrayList<Terminal> terminals;
  ArrayList<Rule> rules;
  String startSymbol;
}
