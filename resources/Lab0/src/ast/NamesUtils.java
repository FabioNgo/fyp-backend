package ast;

import jas.Var;
import soot.JastAddJ.VarDeclStmt;

public class NamesUtils {
  public static String getQualifiedName(ASTNode node){
    if(node instanceof Module){
      return ((Module) node).getvar0();
    }
    return "";
  }
  public static VarDecl lookupVar(String name, ASTNode node){
//    if(node.getParent() instanceof List){

//    }
    if(node.getParent() instanceof BlockStatement){
      BlockStatement blockStatement = (BlockStatement) node.getParent();
      for(Statement statement: blockStatement.getvar0List()){
        if(statement.equals(node)){
          break;
        }
        if(statement instanceof LocalVariableDeclaration){
          VarDecl varDecl = ((LocalVariableDeclaration) statement).getvar0();
          if(varDecl.getvar1().equals(name)){
            return varDecl;
          }
        }
      }
      return lookupVar(name,blockStatement);
    }
    if(node.getParent() instanceof FunctionDeclaration){
      FunctionDeclaration functionDeclaration = (FunctionDeclaration) node.getParent();
      for( VarDecl varDecl : functionDeclaration.getvar2List()) {
        if (varDecl.getvar1().equals(name))
          return varDecl;
      }
      return lookupVar(name,functionDeclaration);
    }
    if(node.getParent() instanceof Module){
        Module module = (Module) node.getParent();
      FieldDeclaration decl = lookupLocalField(name,module);
      if(decl != null) {
        return decl.getvar0();
      }
      // otherwise try lookup imported fields
      return null;
    }
    if(node instanceof VarName){
      return lookupVar(name,node.getParent());
    }
    if(node instanceof BlockStatement){
      return lookupVar(name,node.getParent());
    }
    if(node instanceof FunctionDeclaration){
      return lookupVar(name,node.getParent());
    }
    if(node instanceof VarDecl){
      return lookupVar(name,node.getParent());
    }
    return lookupVar(name,node.getParent());
  }

  private static FieldDeclaration lookupLocalField(String name,ASTNode node) {
    if(node instanceof Module){
      for(Declaration decl : ((Module) node).getvar1List()) {
        if(decl instanceof FieldDeclaration) {
          VarDecl vd = ((FieldDeclaration)decl).getvar0();
          if(vd.getvar1().equals(name))
            return (FieldDeclaration)decl;
        }
      }
      return null;
    }
    return null;
  }

  public static FunctionDeclaration  lookupFunction (String name, ASTNode nd){
    if(nd.getParent() instanceof Module){
      FunctionDeclaration fn = lookupLocalFunction(name,nd);
      if(fn != null) {
        return fn;
      }
      return null;
    }
//    if(nd instanceof FunctionName){
//      return lookupFunction(name,nd.getParent());
//    }
    if(nd instanceof FunctionDeclaration){
      return lookupFunction(name,nd.getParent());
    }
    return lookupFunction(name,nd.getParent());
  }
  public  static FunctionDeclaration getCallTarget(FunctionCall nd){
      return lookupFunction(nd.getvar0(),nd);

  }
  public static FunctionDeclaration  lookupLocalFunction (String name, ASTNode nd){
    if(nd.getParent() instanceof Module){
      Module module = (Module) nd.getParent();
      for(Declaration decl : module.getvar1List() ) {
        if (decl instanceof FunctionDeclaration && ((FunctionDeclaration) decl).getvar1().equals(name)) {
          return (FunctionDeclaration) decl;
        }
      }
      return null;
    }
    return null;
  }

  public static VarDecl decl(ASTNode nd){
    if(nd instanceof VarName){
      return lookupVar(((VarName) nd).getvar0(),nd);
    }
    return null;
  }
  public static boolean isLocal(VarDecl varDecl){
    VarDecl varDecl1 = lookupVar(varDecl.getvar1(),varDecl.getParent());
    if(varDecl1.getParent() instanceof Module){
      return false;
    }
    return true;
  }
}
