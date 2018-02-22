package fabio.ngo.fyp.Managers;

import fabio.ngo.fyp.Models.Grammar;
import fabio.ngo.fyp.Models.Rule;
import fabio.ngo.fyp.Models.Terminal;

import java.util.ArrayList;

public class GrammarsManager {
  static GrammarsManager instance;

  public static GrammarsManager getInstance() {
    if (instance == null) {
      instance = new GrammarsManager();
    }
    return instance;
  }

  private ArrayList<Grammar> grammars;


  public ArrayList<String> getStringContent() {

    ArrayList<String> results = new ArrayList<>();
    for (Grammar grammar : grammars) {
      if (grammar.getRhs().length() == 0) {
        if (grammar.isAbstract()) {
          results.add(String.format("abstract %s;", grammar.getLhs()));
        } else {
          results.add(String.format("%s;", grammar.getLhs()));
        }

      } else {

        results.add(String.format("%s::=%s;", grammar.getLhs(), grammar.getRhs()));


      }


    }
    return results;
  }

  public void generateGrammarRule() {
    grammars = new ArrayList<>();
//    String identifier = "";
//    for (Rule rule : FrontEndContentManager.getInstance().getFrontEndContentRequest().getRules()) {
//      if (rule.isIdentifier()) {
//        identifier = rule.getToken();
//      }
//    }
    ArrayList<Terminal> terminals = FrontEndContentManager.getInstance().getFrontEndContentRequest().getTerminals();
    for (Terminal terminal : terminals) {
      if (terminal.isStartSymbol()) {
        grammars.add(new Grammar("Program", terminal.getIdentifier() + "*", false));
      }
      String rhs = "";
      String lhs = "";
      //lhs
      if (terminal.getBaseTerminal().equals("")) {
        lhs = terminal.getIdentifier();
      } else {
        lhs = String.format("%s:%s", terminal.getIdentifier(), terminal.getBaseTerminal());
      }
      //rhs
      ArrayList<String> semanticTokens = terminal.getSemanticTokens();

      for (int i = 0; i < semanticTokens.size(); i++) {
        boolean isJavaType = false;
        String token = semanticTokens.get(i);
//        if (semanticTokens.size() == 1) {
        ArrayList<Rule> rules = FrontEndContentManager.getInstance().getFrontEndContentRequest().getRules();
        for (Rule rule : rules) {
          String tokenRule = rule.getToken();
          if (tokenRule.equals(token)) {
            if (rule.getJavaType() != null) {
              token = rule.getJavaType();
              isJavaType = true;
            }

//              rules.add(new Grammar(String.format("%s", token), "",false));
            break;
          }
        }

//        }
//        if (token.equals(identifier)) {
//          token = String.format("<%s:String>", token);
//        } else {

        if (token.contains("[")) {
          token = String.format("[var%d:%s] ", i, token.substring(1, token.length() - 1));
        } else {
          if(isJavaType){
            token = String.format("<var%d:%s> ", i, token);
          }else{
            token = String.format("var%d:%s ", i, token);
          }

        }


//        }
        rhs += token;
      }
      if (terminal.getRule().equals("")) {
        grammars.add(new Grammar(lhs, rhs, true));
      } else {
        grammars.add(new Grammar(lhs, rhs, false));
      }

    }
  }

  public ArrayList<Grammar> getGrammars() {
    return grammars;
  }

  public void setGrammars(ArrayList<Grammar> grammars) {
    this.grammars = grammars;
  }
}
