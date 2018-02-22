package ast;

public class StatementUtils {
  public static LoopStatement getEnclosingLoop(ASTNode nd) {
    if(nd instanceof List){
      return getEnclosingLoop(nd.parent);
    }
    if(!(nd instanceof Statement)){
      return null;
    }else{
      if(nd instanceof LoopStatement){
        return (LoopStatement) nd;
      }else{
        return getEnclosingLoop(nd.getParent());
      }
    }
  }
}
