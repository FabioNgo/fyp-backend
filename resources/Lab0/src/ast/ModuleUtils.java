package ast;

public class ModuleUtils {
  public static Module getModule(ASTNode node){
    if(node instanceof Module){
      return (Module) node;
    }else{
      return getModule(node.getParent());
    }
  }
}
