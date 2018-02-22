package fabio.ngo.fyp.Managers;

import fabio.ngo.fyp.Models.Rule;
import fabio.ngo.fyp.Models.Terminal;

import java.util.ArrayList;

public class TerminalsManager {
  private ArrayList<Terminal> terminals;
  private ArrayList<Terminal> auxTerminals;
  private static TerminalsManager instance = null;

  public TerminalsManager() {
    terminals = new ArrayList<>();
    auxTerminals = new ArrayList<>();
  }

  public static TerminalsManager getInstance() {
    if (instance == null) {
      instance = new TerminalsManager();
    }
    return instance;
  }

  public void setTerminals(ArrayList<Terminal> terminals) {
    this.terminals = terminals;
  }

  public ArrayList<Terminal> getTerminals() {
    return terminals;
  }

  public ArrayList<Terminal> getAllTerminals() {
    ArrayList<Terminal> results = new ArrayList<>();
    results.addAll(terminals);
    results.addAll(auxTerminals);
    return results;
  }

  public void setAuxTerminals(ArrayList<Terminal> auxTerminals) {
    this.auxTerminals = auxTerminals;
  }

  public ArrayList<Terminal> getAuxTerminals() {
    return auxTerminals;
  }

  public void addAuxTerminal(Terminal terminal) {
    if (!auxTerminals.contains(terminal)) {
      auxTerminals.add(terminal);
    }

  }

  public String getSemanticActionString(Terminal terminal) {
    StringBuilder result = new StringBuilder();
    for (String semanticAction : terminal.getSemanticActions()) {
      result.append(semanticAction).append("\n");
    }
    return result.toString();
  }

  public void addSemanticActions(Terminal terminal) {
    ArrayList<Rule> rules = FrontEndContentManager.getInstance().getFrontEndContentRequest().getRules();
    ArrayList<String> arguments = new ArrayList<>();
    String rule = terminal.getRule();
    if (rule.length() == 0) {
      return;
    }
    String[] tokens = rule.trim().split("[\\s']");
    String beaverRule = "";
    tokensloop:
    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      String beaverToken = token;
      Terminal.TokenExtendedEnum state = Terminal.TokenExtendedEnum.Undefined;
      if (token.endsWith("?")) {
        state = Terminal.TokenExtendedEnum.Optional;
      }
      if (token.endsWith("+")) {
        state = Terminal.TokenExtendedEnum.NonEmpty;
      }
      if (token.contains("*")) {
        state = Terminal.TokenExtendedEnum.PossibleEmpty;
        String[] parts = token.split("\\*");
        if (parts.length > 1) {
          String temp = parts[1];
          String separator = temp.substring(1, temp.length() - 1);
          beaverToken = this.ListHandler(parts[0], separator);
          token = parts[0] + "*";
        }else{
          beaverToken = this.ListHandler(parts[0], "");
        }

      }
      if (state != Terminal.TokenExtendedEnum.Undefined) {
        token = (String) token.subSequence(0, token.length() - 1);
      }
      beaverRule += (" " + beaverToken);
      for (Rule mRule : rules) {
        if (token.equals(mRule.getToken())) {
          if (mRule.isIdentifier()) {
            terminal.addSemanticAction(String.format("String var%d = (String)_symbols[offset + %d].value;", i + 1, i + 1));
            arguments.add(String.format("var%d", i + 1));
            terminal.addSemanticToken(token);
          } else {
//            if (tokens.length == 1) {
              if (mRule.getJavaType() != null) {

                if (mRule.getJavaType().equals("String")) {
                  terminal.addSemanticAction(String.format("String var%d = (String)_symbols[offset + %d].value;", i + 1, i + 1));
                }
                if (mRule.getJavaType().equals("Integer")) {
                  terminal.addSemanticAction(String.format("Integer var%d = Integer.parseInt((String)_symbols[offset + %d].value);", i + 1, i + 1));
                }

                if (mRule.getJavaType().equals("Boolean")) {
                  terminal.addSemanticAction(String.format("Boolean var%d = Boolean.parseBoolean((String)_symbols[offset + %d].value);", i + 1, i + 1));
                }
                arguments.add(String.format("var%d", i + 1));
                terminal.addSemanticToken(token);
              } else {
//                beaverRule = "";
//                continue tokensloop;
              }


//            }
          }

          continue tokensloop;
        }
      }
      if (state == Terminal.TokenExtendedEnum.PossibleEmpty) {
        terminal.addSemanticAction(String.format("List<%s> var%d;\n" +
          "          if(_symbols[offset+%d].value == null){\n" +
          "            var%d = new List<>();\n" +
          "          }else{\n" +
          "            if(_symbols[offset+%d] instanceof List) {\n" +
          "              var%d = (List) _symbols[offset + %d];\n" +
          "            }else {\n" +
          "              var%d = new List<>();\n" +
          "              var%d.add((%s) _symbols[offset+%d]);\n" +
          "            }\n" +
          "          }", token, i + 1, i + 1, i + 1, i + 1,i+1,i+1,i+1,i+1,token,i+1));
        token += "*";
      }
      if (state == Terminal.TokenExtendedEnum.NonEmpty) {
        terminal.addSemanticAction(String.format("List<%s> var%d;\n" +
          "          if(_symbols[offset+%d].value == null){\n" +
          "            var%d = new List<>();\n" +
          "          }else{\n" +
          "            if(_symbols[offset+%d] instanceof List) {\n" +
          "              var%d = (List) _symbols[offset + %d];\n" +
          "            }else {\n" +
          "              var%d = new List<>();\n" +
          "              var%d.add((%s) _symbols[offset+%d]);\n" +
          "            }\n" +
          "          }", token, i + 1, i + 1, i + 1, i + 1,i+1,i+1,i+1,i+1,token,i+1));
        token += "*";
      }
      if (state == Terminal.TokenExtendedEnum.Optional) {
        terminal.addSemanticAction(String.format("Opt var%d = new Opt();\n" +
          "if(_symbols[offset + %d] != null){\n" +
          "\t%s e = (%s)_symbols[offset + %d].value;\n" +
          "\tvar%d = new Opt(e);\n" +
          "}", i + 1, i + 1, token, token, i + 1, i + 1));
        token = "[" + token + "]";
      }
      if (state == Terminal.TokenExtendedEnum.Undefined) {
        terminal.addSemanticAction(String.format("%s var%d = (%s)_symbols[offset + %d].value;", token, i + 1, token, i + 1));
      }

      arguments.add(String.format("var%d", i + 1));
      terminal.addSemanticToken(token);
    }
    terminal.setBeaverRule(beaverRule);
    //add return action
    StringBuilder argumentString = new StringBuilder();
    if (arguments.size() > 0) {
      for (String argument : arguments) {
        argumentString.append(argument).append(",");
      }
      argumentString.deleteCharAt(argumentString.length() - 1);
    }
    terminal.addSemanticAction(String.format("return new %s (%s);", terminal.getIdentifier(), argumentString.toString()));
  }

  //Change token* to tokenList
  // tokenList = token tokenY*
  // tokenY =separtor token
  private String ListHandler(String token, String separator) {
    //tokenY
    Terminal tokenY = new Terminal();
    tokenY.setIdentifier(token + "Y");
    tokenY.setRule(String.format("%s %s", separator, token));
    tokenY.setBeaverRule(String.format("%s %s", separator, token));
    this.addSemanticActionsY(tokenY,separator);
    //tokenList
    Terminal tokenList = new Terminal();
    tokenList.setIdentifier(token + "List");
    tokenList.setRule(String.format("%s %s*", token, tokenY.getIdentifier()));
    tokenList.setBeaverRule(String.format("%s %s*", token, tokenY.getIdentifier()));
    this.addSemanticActionsList(tokenList);
    this.addAuxTerminal(tokenY);
    this.addAuxTerminal(tokenList);
    return tokenList.getIdentifier()+"*";
  }

  private void addSemanticActionsList(Terminal tokenList) {
    String token = tokenList.getIdentifier();
    token = token.substring(0,token.length()-4);
    tokenList.addSemanticAction(String.format("%s var1 = (%s) _symbols[offset + 1];\n" +
        "          List<%s> result = new List<>();\n" +
        "          result.add(var1);\n" +
        "          Symbol var2 = _symbols[offset + 2];\n" +
        "          if (var2.value != null) {\n" +
        "            if (var2 instanceof List) {\n" +
        "              for (%s symbol : (List<%s>) var2) {\n" +
        "                result.add(symbol);\n" +
        "              }\n" +
        "            } else {\n" +
        "              result.add((%s) var2);\n" +
        "            }\n" +
        "          }\n" +
        "          return result;"
    ,token,token,token,token,token,token));
  }

  private void addSemanticActionsY(Terminal tokenY, String separator) {

    String token = tokenY.getIdentifier();
    token = token.substring(0,token.length()-1);
    if("".equals(separator)){
      tokenY.addSemanticAction(String.format("" +
        "%s var = (%s)_symbols[offset+1];\n" +
        "return var;\n",token,token));
    }else{
      tokenY.addSemanticAction(String.format("" +
        "%s var = (%s)_symbols[offset+2];\n" +
        "return var;\n",token,token));
    }

  }

  public void generateSemanticAction() {

    for (Terminal terminal : terminals) {
      this.addSemanticActions(terminal);
    }
  }

  public ArrayList<String> getSemanticAction() {
    ArrayList<String> result = new ArrayList<>();
    for (Terminal terminal : terminals) {
      result.add(this.getSemanticActionString(terminal));
    }
    for (Terminal terminal : auxTerminals) {
      result.add(this.getSemanticActionString(terminal));
    }
    return result;
  }
}
