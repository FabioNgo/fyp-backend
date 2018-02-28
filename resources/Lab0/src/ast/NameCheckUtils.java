package ast;


import java.util.HashSet;
import java.util.Set;

public class NameCheckUtils {
  public static void nameCheck(ASTNode nd){
    if(nd instanceof Program){
      Set<String> module_names = new HashSet<String>();
      for(Module module : ((Program) nd).getModuleList()) {
        if(!module_names.add(module.getvar0())) {
          Errors.error("Multiple modules with name " + module.getvar0(),nd);
        }
        nameCheck(module);
      }
    }
    if(nd instanceof Module){

      for(Declaration decl : ((Module) nd).getvar1List()) {
        nameCheck(decl);
      }
    }
    if(nd instanceof FunctionDeclaration){
      if(!NamesUtils.lookupFunction(((FunctionDeclaration) nd).getvar1(),nd).equals(nd)){
        Errors.error("Multiple declarations for function " + ((FunctionDeclaration) nd).getvar1(),nd);
      }
      nameCheck(((FunctionDeclaration) nd).getvar0());
      for(VarDecl varDecl: ((FunctionDeclaration) nd).getvar2List()){
        nameCheck(varDecl);
      }
      nameCheck(((FunctionDeclaration) nd).getvar3());
    }
    if(nd instanceof FieldDeclaration){
      nameCheck(((FieldDeclaration) nd).getvar0());
    }
    if(nd instanceof VarDecl){
      nameCheck(((VarDecl) nd).getvar0());
      VarDecl varDecl  = NamesUtils.lookupVar(((VarDecl) nd).getvar1(),nd);
      if(varDecl != null && varDecl != nd){
        Errors.error("Multiple declarations for " + ((VarDecl) nd).getvar1() + " in same scope",nd);
      }
    }
    if(nd instanceof BlockStatement){
      for(Statement statement: ((BlockStatement) nd).getvar0List()){
        nameCheck(statement);
      }
    }
    if(nd instanceof BreakStatement){
      if(StatementUtils.getEnclosingLoop(nd) == null){
        Errors.error("Break statement outside loop.",nd);
      }
    }
    if(nd instanceof ExpressionStatement){
      nameCheck(((ExpressionStatement) nd).getvar0());
    }
    if(nd instanceof IfStatement){
      nameCheck(((IfStatement) nd).getvar0());
      nameCheck(((IfStatement) nd).getvar1());
      if(((IfStatement) nd).hasvar2()){
        nameCheck(((IfStatement) nd).getvar2());
      }
    }
    if( nd instanceof ReturnStatement){
      if(((ReturnStatement) nd).hasvar0()){
        nameCheck(((ReturnStatement) nd).getvar0());
      }
    }
    if( nd instanceof LocalVariableDeclaration){
      nameCheck(((LocalVariableDeclaration) nd).getvar0());
    }
    if(nd instanceof WhileStatement){
      nameCheck(((WhileStatement) nd).getvar0());
      nameCheck(((WhileStatement) nd).getvar1());
    }
    if(nd instanceof VarName){
      if(NamesUtils.decl(nd) == null){
        Errors.error("Variable " + ((VarName) nd).getvar0() + " cannot be resolved.",nd);
      }
    }
    if(nd instanceof ArrayIndex){
      nameCheck(((ArrayIndex) nd).getvar0());
      nameCheck(((ArrayIndex) nd).getvar1());
    }
    if(nd instanceof FunctionCall){
      nameCheck(NamesUtils.getCallTarget((FunctionCall) nd));
      for(Expression expression: ((FunctionCall) nd).getvar1List()){
        nameCheck(expression);
      }
    }
    if(nd instanceof Assignment){
      nameCheck(((Assignment) nd).getvar0());
      nameCheck(((Assignment) nd).getvar1());
    }
    if(nd instanceof AddExpr){
      nameCheck(((AddExpr) nd).getvar0());
      nameCheck(((AddExpr) nd).getvar1());
    }
    if(nd instanceof SubExpr){
      nameCheck(((SubExpr) nd).getvar0());
      nameCheck(((SubExpr) nd).getvar1());
    }
    if(nd instanceof MulExpr){
      nameCheck(((MulExpr) nd).getvar0());
      nameCheck(((MulExpr) nd).getvar1());
    }
    if(nd instanceof DivExpr){
      nameCheck(((DivExpr) nd).getvar0());
      nameCheck(((DivExpr) nd).getvar1());
    }
    if(nd instanceof ModExpr){
      nameCheck(((ModExpr) nd).getvar0());
      nameCheck(((ModExpr) nd).getvar1());
    }
    if(nd instanceof LessThanEqualExpr){
      nameCheck(((LessThanEqualExpr) nd).getvar0());
      nameCheck(((LessThanEqualExpr) nd).getvar1());
    }
    if(nd instanceof LessThanExpr){
      nameCheck(((LessThanExpr) nd).getvar0());
      nameCheck(((LessThanExpr) nd).getvar1());
    }
    if(nd instanceof GreaterThanExpr){
      nameCheck(((GreaterThanExpr) nd).getvar0());
      nameCheck(((GreaterThanExpr) nd).getvar1());
    }
    if(nd instanceof GreaterThanEqualExpr){
      nameCheck(((GreaterThanEqualExpr) nd).getvar0());
      nameCheck(((GreaterThanEqualExpr) nd).getvar1());
    }
    if(nd instanceof NotEqualExpr){
      nameCheck(((NotEqualExpr) nd).getvar0());
      nameCheck(((NotEqualExpr) nd).getvar1());
    }
    if(nd instanceof EqualExpr){
      nameCheck(((EqualExpr) nd).getvar0());
      nameCheck(((EqualExpr) nd).getvar1());
    }
    if(nd instanceof NegateExpression){
      nameCheck(((NegateExpression) nd).getvar0());
    }
    if(nd instanceof ArrayLiteral){
      for(Expression expression: ((ArrayLiteral) nd).getvar0List()){
        nameCheck(expression);
      }
    }
//    if(nd instanceof FunctionName){
//      if(NamesUtils.decl(nd) == null){
//        Errors.error("Function " + ((FunctionName) nd).getvar0() + " cannot be resolved.",nd);
//      }
//    }
  }
}
