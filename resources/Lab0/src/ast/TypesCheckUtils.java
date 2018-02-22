package ast;

import static sun.management.Agent.error;

public class TypesCheckUtils {
  public static boolean isArrayType(TypeDescriptor typeDescriptor) {
    if (typeDescriptor instanceof ArrayType) {
      return true;
    }
    return false;
  }

  public static boolean isBoolean(TypeDescriptor typeDescriptor) {
    if (typeDescriptor instanceof BooleanType) {
      return true;
    }
    return false;
  }

  public static boolean isNumeric(TypeDescriptor typeDescriptor) {
    if (typeDescriptor instanceof IntType) {
      return true;
    }
    return false;
  }

  public static boolean isVoid(TypeDescriptor typeDescriptor) {
    if (typeDescriptor instanceof VoidType) {
      return true;
    }
    return false;
  }

  public static void typecheck(ASTNode nd) {
    if (nd instanceof Program) {
      for (Module module : ((Program) nd).getModuleList()) {
        typecheck(module);
      }
    }
    if (nd instanceof Module) {
      for (Declaration declaration : ((Module) nd).getvar1List()) {
        typecheck(declaration);
      }
    }

    if (nd instanceof FunctionDeclaration) {
      typecheck(((FunctionDeclaration) nd).getvar0());
      for (VarDecl varDecl : ((FunctionDeclaration) nd).getvar2List()) {
        typecheck(varDecl);
      }
      typecheck(((FunctionDeclaration) nd).getvar3());
    }
    if (nd instanceof FieldDeclaration) {
      typecheck(((FieldDeclaration) nd).getvar0());
    }
    if (nd instanceof VarDecl) {
      typecheck(((VarDecl) nd).getvar0());
      if (isVoid(SootTypeUtil.type(((VarDecl) nd).getvar0()))) {
        error("Cannot declare variable of type void.");
      }
    }
    if (nd instanceof LocalVariableDeclaration) {
      typecheck(((LocalVariableDeclaration) nd).getvar0());
    }
    if (nd instanceof BlockStatement) {
      for (Statement statement : ((BlockStatement) nd).getvar0List()) {
        typecheck(statement);
      }
    }
    if (nd instanceof ExpressionStatement) {
      typecheck(((ExpressionStatement) nd).getvar0());
    }
    if (nd instanceof IfStatement) {
      typecheck(((IfStatement) nd).getvar0());
      typecheck(((IfStatement) nd).getvar1());
      if (((IfStatement) nd).hasvar2()) {
        typecheck(((IfStatement) nd).getvar2());
      }
      if (!isBoolean(SootTypeUtil.type(((IfStatement) nd).getvar0()))) {
        error("If condition is not of type boolean.");
      }
    }
    if (nd instanceof ReturnStatement) {
      if (((ReturnStatement) nd).hasvar0()) {
        FunctionDeclaration functionDeclaration = (FunctionDeclaration) nd.getParent();
        if (!isVoid(SootTypeUtil.type(functionDeclaration.getvar1()))) {
          error("Return expression missing.");
        } else {
          typecheck(((ReturnStatement) nd).getvar0());
          if (SootTypeUtil.type(((ReturnStatement) nd).getvar0()) != SootTypeUtil.type(functionDeclaration.getvar1())) {
            error("Return statement returns expression of wrong type.");
          }
        }
      }
    }
    if (nd instanceof WhileStatement) {
      typecheck(((WhileStatement) nd).getvar0());
      typecheck(((WhileStatement) nd).getvar1());
      if (isBoolean(SootTypeUtil.type(((WhileStatement) nd).getvar0()))) {
        error("Loop condition is not of type boolean.");
      }
    }
    if (nd instanceof ArrayIndex) {
      typecheck(((ArrayIndex) nd).getvar0());
      typecheck(((ArrayIndex) nd).getvar1());
      if (isArrayType(SootTypeUtil.type(((ArrayIndex) nd).getvar0()))) {
        error("Base expression of array index should be of array type.");
      }
      if (isNumeric(SootTypeUtil.type(((ArrayIndex) nd).getvar1()))) {
        error("Index expression of array index should be of integer type.");
      }
    }
    if (nd instanceof FunctionCall) {
      FunctionDeclaration functionDeclaration = NamesUtils.getCallTarget((FunctionCall) nd);
      for (Expression expression : ((FunctionCall) nd).getvar1List()) {
        typecheck(expression);
      }
      if (((FunctionCall) nd).getNumvar1() != functionDeclaration.getNumvar2()) {
        error("Number of arguments (" + ((FunctionCall) nd).getNumvar1() + ") and number of " +
          "parameters (" + functionDeclaration.getNumvar2() + ") do not match.");
      } else {
        for (int i = 0; i < ((FunctionCall) nd).getNumvar1(); i++) {
          if (SootTypeUtil.type(((FunctionCall) nd).getvar1(i)) != SootTypeUtil.type(functionDeclaration.getvar2(i))) {
            error("The " + i + "th argument has the wrong type.");
          }
        }
      }
    }
    if (nd instanceof Assignment) {
      typecheck(((Assignment) nd).getvar0());
      typecheck(((Assignment) nd).getvar1());
      if (SootTypeUtil.type(((Assignment) nd).getvar0()) != SootTypeUtil.type(((Assignment) nd).getvar1())) {
        error("The two sides of the assignment have different types.");
      }
    }
    if (nd instanceof AddExpr) {
      typecheck(((AddExpr) nd).getvar0());
      typecheck(((AddExpr) nd).getvar1());
      if (!isNumeric(SootTypeUtil.type(((AddExpr) nd).getvar0())) || !isNumeric(SootTypeUtil.type(((AddExpr) nd).getvar1()))) {
        error("Both operands of a binary arithmetic operator must have numeric type.");
      }
    }
    if (nd instanceof SubExpr) {
      typecheck(((SubExpr) nd).getvar0());
      typecheck(((SubExpr) nd).getvar1());
      if (!isNumeric(SootTypeUtil.type(((SubExpr) nd).getvar0())) || !isNumeric(SootTypeUtil.type(((SubExpr) nd).getvar1()))) {
        error("Both operands of a binary arithmetic operator must have numeric type.");
      }
    }
    if (nd instanceof MulExpr) {
      typecheck(((MulExpr) nd).getvar0());
      typecheck(((MulExpr) nd).getvar1());
      if (!isNumeric(SootTypeUtil.type(((MulExpr) nd).getvar0())) || !isNumeric(SootTypeUtil.type(((MulExpr) nd).getvar1()))) {
        error("Both operands of a binary arithmetic operator must have numeric type.");
      }
    }
    if (nd instanceof DivExpr) {
      typecheck(((DivExpr) nd).getvar0());
      typecheck(((DivExpr) nd).getvar1());
      if (!isNumeric(SootTypeUtil.type(((DivExpr) nd).getvar0())) || !isNumeric(SootTypeUtil.type(((DivExpr) nd).getvar1()))) {
        error("Both operands of a binary arithmetic operator must have numeric type.");
      }
    }
    if (nd instanceof ModExpr) {
      typecheck(((ModExpr) nd).getvar0());
      typecheck(((ModExpr) nd).getvar1());
      if (!isNumeric(SootTypeUtil.type(((ModExpr) nd).getvar0())) || !isNumeric(SootTypeUtil.type(((ModExpr) nd).getvar1()))) {
        error("Both operands of a binary arithmetic operator must have numeric type.");
      }
    }
    if (nd instanceof LessThanExpr) {
      typecheck(((LessThanExpr) nd).getvar0());
      typecheck(((LessThanExpr) nd).getvar1());
      if (!SootTypeUtil.typeEqual(SootTypeUtil.type(((LessThanExpr) nd).getvar0()), SootTypeUtil.type(((LessThanExpr) nd).getvar1()))) {
        error("Both operands of a comparison operator must be of the same type.");
      }
      if (isVoid(SootTypeUtil.type(((LessThanExpr) nd).getvar0()))) {
        error("Operands in comparison may not be of type void.");
      }
      if (isNumeric(SootTypeUtil.type(((LessThanExpr) nd).getvar0()))) {
        error("Operands of arithmetic comparison must be numeric.");
      }
    }
    if (nd instanceof LessThanEqualExpr) {
      typecheck(((LessThanEqualExpr) nd).getvar0());
      typecheck(((LessThanEqualExpr) nd).getvar1());
      if (!SootTypeUtil.typeEqual(SootTypeUtil.type(((LessThanEqualExpr) nd).getvar0()), SootTypeUtil.type(((LessThanEqualExpr) nd).getvar1()))) {
        error("Both operands of a comparison operator must be of the same type.");
      }
      if (isVoid(SootTypeUtil.type(((LessThanEqualExpr) nd).getvar0()))) {
        error("Operands in comparison may not be of type void.");
      }
      if (isNumeric(SootTypeUtil.type(((LessThanEqualExpr) nd).getvar0()))) {
        error("Operands of arithmetic comparison must be numeric.");
      }
    }
    if (nd instanceof GreaterThanEqualExpr) {
      typecheck(((GreaterThanEqualExpr) nd).getvar0());
      typecheck(((GreaterThanEqualExpr) nd).getvar1());
      if (!SootTypeUtil.typeEqual(SootTypeUtil.type(((GreaterThanEqualExpr) nd).getvar0()), SootTypeUtil.type(((GreaterThanEqualExpr) nd).getvar1()))) {
        error("Both operands of a comparison operator must be of the same type.");
      }
      if (isVoid(SootTypeUtil.type(((GreaterThanEqualExpr) nd).getvar0()))) {
        error("Operands in comparison may not be of type void.");
      }
      if (isNumeric(SootTypeUtil.type(((GreaterThanEqualExpr) nd).getvar0()))) {
        error("Operands of arithmetic comparison must be numeric.");
      }
    }
    if (nd instanceof GreaterThanExpr) {
      typecheck(((GreaterThanExpr) nd).getvar0());
      typecheck(((GreaterThanExpr) nd).getvar1());
      if (!SootTypeUtil.typeEqual(SootTypeUtil.type(((GreaterThanExpr) nd).getvar0()), SootTypeUtil.type(((GreaterThanExpr) nd).getvar1()))) {
        error("Both operands of a comparison operator must be of the same type.");
      }
      if (isVoid(SootTypeUtil.type(((GreaterThanExpr) nd).getvar0()))) {
        error("Operands in comparison may not be of type void.");
      }
      if (isNumeric(SootTypeUtil.type(((GreaterThanEqualExpr) nd).getvar0()))) {
        error("Operands of arithmetic comparison must be numeric.");
      }
    }
    if (nd instanceof EqualExpr) {
      typecheck(((EqualExpr) nd).getvar0());
      typecheck(((EqualExpr) nd).getvar1());
      if (!SootTypeUtil.typeEqual(SootTypeUtil.type(((EqualExpr) nd).getvar0()),SootTypeUtil.type(((EqualExpr) nd).getvar1()))) {
        error("Both operands of a comparison operator must be of the same type.");
      }
      if (isVoid(SootTypeUtil.type(((EqualExpr) nd).getvar0()))) {
        error("Operands in comparison may not be of type void.");
      }
    }
    if (nd instanceof NotEqualExpr) {
      typecheck(((NotEqualExpr) nd).getvar0());
      typecheck(((NotEqualExpr) nd).getvar1());
      if (!SootTypeUtil.typeEqual(SootTypeUtil.type(((NotEqualExpr) nd).getvar0()),SootTypeUtil.type(((NotEqualExpr) nd).getvar1()))) {
        error("Both operands of a comparison operator must be of the same type.");
      }
      if (isVoid(SootTypeUtil.type(((NotEqualExpr) nd).getvar0()))) {
        error("Operands in comparison may not be of type void.");
      }
    }
    if (nd instanceof NegateExpression) {
      typecheck(((NegateExpression) nd).getvar0());
      if (!isNumeric(SootTypeUtil.type(((NegateExpression) nd).getvar0()))) {
        error("The operand of a unary arithmetic operator must have numeric type.");
      }
    }
    if(nd instanceof ArrayLiteral){
      if(((ArrayLiteral) nd).getNumvar0() == 0) {
        error("Array literals must contain at least one element.");
      } else {
        if(isVoid(SootTypeUtil.type(((ArrayLiteral) nd).getvar0(0))))
          error("Array literal elements may not be of type void.");
        for(int i=0;i<((ArrayLiteral) nd).getNumvar0();++i) {
          typecheck(((ArrayLiteral) nd).getvar0(i));
          if(i > 0 && SootTypeUtil.typeEqual(SootTypeUtil.type(((ArrayLiteral) nd).getvar0(i)),SootTypeUtil.type(((ArrayLiteral) nd).getvar0(0)) )) {
            error("Every element in an array literal must have the same type.");
          }
        }
      }
    }
    if(nd instanceof PrimitiveArrayTypeName){
      if(isVoid(SootTypeUtil.type(((PrimitiveArrayTypeName) nd).getvar0()))){
        error("Component type of array cannot be void.");
      }
    }
    if(nd instanceof UserArrayTypeName){
      if(isVoid(SootTypeUtil.type(((UserArrayTypeName) nd).getvar0()))){
        error("Component type of array cannot be void.");
      }
    }
  }

}
