package ast;

/**
 *  <p>A visitor class for AST nodes. Contains one visitor method for each AST node type (including
 *  the abstract ones), each of which must return an object of type {@link A}. The default
 *  implementation of {@link #visitASTNode(ASTNode)} returns {@code null}, all other visitor
 *  methods invoke the visitor method of their node super type.</p>
 *
 *  <p>When you write your own visitor implementations, you only need to override those visitor
 *  methods you are actually interested in; all others will return {@code null} by default.
 *  To apply a visitor to an AST node, use {@link ASTNode#accept(Visitor)}.</p>
 */
public class Visitor<A> {
	public A visitASTNode(ASTNode<?> nd) {
		return null;
	}
	public A visitList(List<?> nd) {
		return visitASTNode(nd);
	}
	public A visitOpt(Opt<?> nd) {
		return visitASTNode(nd);
	}
	public A visitProgram(Program nd) {
		return visitASTNode(nd);
	}
	public A visitModule(Module nd) {
		return visitASTNode(nd);
	}
//	public A visitImport(Import nd) {
//		return visitASTNode(nd);
//	}
	public A visitDeclaration(Declaration nd) {
		return visitASTNode(nd);
	}
//	public A visitAccessibility(Accessibility nd) {
//		return visitASTNode(nd);
//	}
	public A visitFunctionDeclaration(FunctionDeclaration nd) {
		return visitDeclaration(nd);
	}
	public A visitFieldDeclaration(FieldDeclaration nd) {
		return visitDeclaration(nd);
	}
//	public A visitTypeDeclaration(TypeDeclaration nd) {
//		return visitDeclaration(nd);
//	}
	public A visitVarDecl(VarDecl nd) {
		return visitASTNode(nd);
	}
//	public A visitParameter(Parameter nd) {
//		return visitASTNode(nd);
//	}
//	public A visitLocalVarDecl(LocalVarDecl nd) {
//		return visitVarDecl(nd);
//	}
	public A visitTypeName(TypeName nd) {
		return visitASTNode(nd);
	}
	public A visitIntTypeName(IntTypeName nd) {
		return visitTypeName(nd);
	}
	public A visitBooleanTypeName(BooleanTypeName nd) {
		return visitTypeName(nd);
	}
	public A visitVoidTypeName(VoidTypeName nd) {
		return visitTypeName(nd);
	}
	public A visitArrayTypeName(ArrayTypeName nd) {
		return visitTypeName(nd);
	}
//	public A visitUserTypeName(UserTypeName nd) {
//		return visitTypeName(nd);
//	}
//	public A visitJavaTypeName(JavaTypeName nd) {
//		return visitTypeName(nd);
//	}
	public A visitStatement(Statement nd) {
		return visitASTNode(nd);
	}
	public A visitExpressionStatement(ExpressionStatement nd) {
		return visitStatement(nd);
	}

	public A visitLocalVariableDeclaration(LocalVariableDeclaration nd) {
		return visitStatement(nd);
	}
	public A visitBlockStatement(BlockStatement nd) {
		return visitStatement(nd);
	}
	public A visitIfStatement(IfStatement nd) {
		return visitStatement(nd);
	}
	public A visitForStatement(ForStatement nd) {
	  return visitStatement(nd);
  }
	public A visitWhileStatement(WhileStatement nd) {
		return visitStatement(nd);
	}
	public A visitReturnStatement(ReturnStatement nd) {
		return visitStatement(nd);
	}
	public A visitBreakStatement(BreakStatement nd) {
		return visitStatement(nd);
	}
	public A visitExpr(Expression nd) {
		return visitASTNode(nd);
	}
	public A visitLHSExpr(LhsExpression nd) {
		return visitExpr(nd);
	}
	public A visitVarName( VarName nd) {
		return visitLHSExpr(nd);
	}
	public A visitArrayIndex(ArrayIndex nd) {
		return visitLHSExpr(nd);
	}
	public A visitCall(FunctionCall nd) {
		return visitExpr(nd);
	}
	public A visitAssignment(Assignment nd) {
		return visitExpr(nd);
	}
	public A visitBinaryExpr(BinaryExpression nd) {
		return visitExpr(nd);
	}
	public A visitAddExpr(AddExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitSubExpr(SubExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitMulExpr(MulExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitDivExpr(DivExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitModExpr(ModExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitUnaryExpr(UnaryExpression nd) {
		return visitExpr(nd);
	}
	public A visitNegExpr(NegateExpression nd) {
		return visitUnaryExpr(nd);
	}
//	public A visitCompExpr(Compara nd) {
//		return visitBinaryExpr(nd);
//	}
  public A visitElseStatement(ElseStatement nd){
	  return visitStatement(nd);
  }
  public A visitTrueLiteral(TrueLiteral nd){
	  return null;
  }
  public A visitFalseLiteral(FalseLiteral nd){
    return null;
  }
	public A visitEqExpr(EqualExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitNeqExpr(NotEqualExpr nd) {
		return visitBinaryExpr(nd);
	}
//	public A visitArithCompExpr(Arit nd) {
//		return visitCompExpr(nd);
//	}
	public A visitLtExpr(LessThanExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitGtExpr(GreaterThanExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitLeqExpr(LessThanEqualExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitGeqExpr(GreaterThanEqualExpr nd) {
		return visitBinaryExpr(nd);
	}
	public A visitLiteral(Literal nd) {
		return visitExpr(nd);
	}
	public A visitStringLiteral(StringLiteral nd) {
		return visitLiteral(nd);
	}
	public A visitIntLiteral(IntLiteral nd) {
		return visitLiteral(nd);
	}
//	public A visitNegativeIntLiteral(NegativeIntLiteral nd) {
//		return visitLiteral(nd);
//	}
	public A visitBooleanLiteral(BooleanLiteral nd) {
		return visitLiteral(nd);
	}
	public A visitArrayLiteral(ArrayLiteral nd) {
		return visitLiteral(nd);
	}
//	public A visitFunctionName(FunctionN nd) {
//		return visitASTNode(nd);
//	}
	public A visitTypeDescriptor(TypeDescriptor nd) {
		return visitASTNode(nd);
	}
	public A visitIntType(IntType nd) {
		return visitTypeDescriptor(nd);
	}
	public A visitBooleanType(BooleanType nd) {
		return visitTypeDescriptor(nd);
	}
	public A visitVoidType(VoidType nd) {
		return visitTypeDescriptor(nd);
	}
	public A visitArrayType(ArrayType nd) {
		return visitTypeDescriptor(nd);
	}
	public A visitJavaType(JavaType nd) {
		return visitTypeDescriptor(nd);
	}
}
