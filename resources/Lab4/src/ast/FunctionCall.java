/* This file was generated with JastAdd2 (http://jastadd.org) version R20121122 (r889) */
package ast;

/**
 * @production FunctionCall : {@link Expression} ::= <span class="component">&lt;var0:String&gt;</span> <span class="component">var1:{@link Expression}*</span>;
 * @ast node
 * @declaredat C:/Users/fabiongo/WebstormProjects/fyp/backend/working/Lab0/140393/src/ast/grammar.ast:46
 */
public class FunctionCall extends Expression implements Cloneable {
  /**
   * @apilevel low-level
   */
  public void flushCache() {
    super.flushCache();
  }
  /**
   * @apilevel internal
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /**
   * @apilevel internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public FunctionCall clone() throws CloneNotSupportedException {
    FunctionCall node = (FunctionCall)super.clone();
    return node;
  }
  /**
   * @apilevel internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public FunctionCall copy() {
      try {
        FunctionCall node = (FunctionCall)clone();
        if(children != null) node.children = (ASTNode[])children.clone();
        return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   */
  @SuppressWarnings({"unchecked", "cast"})
  public FunctionCall fullCopy() {
    try {
      FunctionCall tree = (FunctionCall) clone();
      tree.setParent(null);// make dangling
      if (children != null) {
        tree.children = new ASTNode[children.length];
        for (int i = 0; i < children.length; ++i) {
          if (children[i] == null) {
            tree.children[i] = null;
          } else {
            tree.children[i] = ((ASTNode) children[i]).fullCopy();
            ((ASTNode) tree.children[i]).setParent(tree);
          }
        }
      }
      return tree;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " +
        getClass().getName());
    }
  }
  /**
   * @ast method 
   * 
   */
  public FunctionCall() {
    super();


  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @ast method 
   * 
   */
  public void init$Children() {
    children = new ASTNode[1];
    setChild(new List(), 0);
  }
  /**
   * @ast method 
   * 
   */
  public FunctionCall(String p0, List<Expression> p1) {
    setvar0(p0);
    setChild(p1, 0);
  }
  /**
   * @ast method 
   * 
   */
  public FunctionCall(beaver.Symbol p0, List<Expression> p1) {
    setvar0(p0);
    setChild(p1, 0);
  }
  /**
   * @apilevel low-level
   * @ast method 
   * 
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * Replaces the lexeme var0.
   * @param value The new value for the lexeme var0.
   * @apilevel high-level
   * @ast method 
   * 
   */
  public void setvar0(String value) {
    tokenString_var0 = value;
  }
  /**
   * @apilevel internal
   * @ast method 
   * 
   */
  
  /**
   * @apilevel internal
   */
  protected String tokenString_var0;
  /**
   * @ast method 
   * 
   */
  
  public int var0start;
  /**
   * @ast method 
   * 
   */
  
  public int var0end;
  /**
   * JastAdd-internal setter for lexeme var0 using the Beaver parser.
   * @apilevel internal
   * @ast method 
   * 
   */
  public void setvar0(beaver.Symbol symbol) {
    if(symbol.value != null && !(symbol.value instanceof String))
      throw new UnsupportedOperationException("setvar0 is only valid for String lexemes");
    tokenString_var0 = (String)symbol.value;
    var0start = symbol.getStart();
    var0end = symbol.getEnd();
  }
  /**
   * Retrieves the value for the lexeme var0.
   * @return The value for the lexeme var0.
   * @apilevel high-level
   * @ast method 
   * 
   */
  public String getvar0() {
    return tokenString_var0 != null ? tokenString_var0 : "";
  }
  /**
   * Replaces the var1 list.
   * @param list The new list node to be used as the var1 list.
   * @apilevel high-level
   * @ast method 
   * 
   */
  public void setvar1List(List<Expression> list) {
    setChild(list, 0);
  }
  /**
   * Retrieves the number of children in the var1 list.
   * @return Number of children in the var1 list.
   * @apilevel high-level
   * @ast method 
   * 
   */
  public int getNumvar1() {
    return getvar1List().getNumChild();
  }
  /**
   * Retrieves the number of children in the var1 list.
   * Calling this method will not trigger rewrites..
   * @return Number of children in the var1 list.
   * @apilevel low-level
   * @ast method 
   * 
   */
  public int getNumvar1NoTransform() {
    return getvar1ListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the var1 list..
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the var1 list.
   * @apilevel high-level
   * @ast method 
   * 
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expression getvar1(int i) {
    return (Expression)getvar1List().getChild(i);
  }
  /**
   * Append an element to the var1 list.
   * @param node The element to append to the var1 list.
   * @apilevel high-level
   * @ast method 
   * 
   */
  public void addvar1(Expression node) {
    List<Expression> list = (parent == null || state == null) ? getvar1ListNoTransform() : getvar1List();
    list.addChild(node);
  }
  /**
   * @apilevel low-level
   * @ast method 
   * 
   */
  public void addvar1NoTransform(Expression node) {
    List<Expression> list = getvar1ListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the var1 list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   * @ast method 
   * 
   */
  public void setvar1(Expression node, int i) {
    List<Expression> list = getvar1List();
    list.setChild(node, i);
  }
  /**
   * Retrieves the var1 list.
   * @return The node representing the var1 list.
   * @apilevel high-level
   * @ast method 
   * 
   */
  public List<Expression> getvar1s() {
    return getvar1List();
  }
  /**
   * Retrieves the var1 list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the var1 list.
   * @apilevel low-level
   * @ast method 
   * 
   */
  public List<Expression> getvar1sNoTransform() {
    return getvar1ListNoTransform();
  }
  /**
   * Retrieves the var1 list.
   * @return The node representing the var1 list.
   * @apilevel high-level
   * @ast method 
   * 
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Expression> getvar1List() {
    List<Expression> list = (List<Expression>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * Retrieves the var1 list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the var1 list.
   * @apilevel low-level
   * @ast method 
   * 
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Expression> getvar1ListNoTransform() {
    return (List<Expression>)getChildNoTransform(0);
  }
}
