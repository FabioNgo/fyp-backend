package ast;

import java.util.ArrayList;

import soot.Local;
import soot.Scene;
import soot.SootClass;
import soot.SootMethodRef;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.*;
import soot.util.Chain;
import ast.*;

/**
 * This class is in charge of creating Jimple code for a given expression (and its nested
 * expressions, if applicable).
 */
public class ExprCodeGenerator extends Visitor<Value> {
  /**
   * The {@link FunctionCodeGenerator} that instantiated this object.
   */
  private final FunctionCodeGenerator fcg;

  /**
   * We cache the statement list of the enclosing function for convenience.
   */
  private final Chain<Unit> units;

  private ExprCodeGenerator(FunctionCodeGenerator fcg) {
    this.fcg = fcg;
    this.units = fcg.getBody().getUnits();
  }

  /**
   * Ensures that the given value can be used as an operand; that is, if the
   * value is not a {@link Local} or a {@link Constant}, this method allocates
   * a new temporary variable and stores the value into that temporary.
   */
  private Value wrap(Value v) {
    if (v == null || v instanceof Local || v instanceof Constant) {
      return v;
    } else {
      Local temp = fcg.mkTemp(v.getType());
      units.add(Jimple.v().newAssignStmt(temp, v));
      return temp;
    }
  }

  /**
   * Convenience method to generate code for an expression and wrap it.
   */
  public static Value generate(Expression expr, FunctionCodeGenerator fcg) {
    ExprCodeGenerator gen = new ExprCodeGenerator(fcg);
    return gen.wrap(gen.visitExpr(expr));
  }

  @Override
  public Value visitExpr(Expression nd) {
    if(nd instanceof Assignment){
      return visitAssignment((Assignment) nd);
    }
    if(nd instanceof IntLiteral){
      return visitIntLiteral((IntLiteral) nd);
    }
    if(nd instanceof StringLiteral){
      return visitStringLiteral((StringLiteral) nd);
    }
    if(nd instanceof TrueLiteral){
      return visitTrueLiteral((TrueLiteral) nd);
    }
    if(nd instanceof FalseLiteral){
      return visitFalseLiteral((FalseLiteral) nd);
    }
    if(nd instanceof ArrayLiteral){
      return visitArrayLiteral((ArrayLiteral) nd);
    }
    if(nd instanceof ArrayIndex){
      return visitArrayIndex((ArrayIndex) nd);
    }
    if(nd instanceof VarName){
      return visitVarName((VarName) nd);
    }
    if(nd instanceof AddExpr){
      return visitAddExpr((AddExpr) nd);
    }
    if(nd instanceof SubExpr){
      return visitSubExpr((SubExpr) nd);
    }
    if(nd instanceof MulExpr){
      return visitMulExpr((MulExpr) nd);
    }
    if(nd instanceof DivExpr){
      return visitDivExpr((DivExpr) nd);
    }
    if(nd instanceof ModExpr){
      return visitModExpr((ModExpr) nd);
    }
    if(nd instanceof EqualExpr){
      return visitEqExpr((EqualExpr) nd);
    }
    if(nd instanceof NotEqualExpr){
      return visitNeqExpr((NotEqualExpr) nd);
    }
    if(nd instanceof LessThanExpr){
      return visitLtExpr((LessThanExpr) nd);
    }
    if(nd instanceof LessThanEqualExpr){
      return visitLeqExpr((LessThanEqualExpr) nd);
    }
    if(nd instanceof GreaterThanExpr){
      return visitGtExpr((GreaterThanExpr) nd);
    }
    if(nd instanceof GreaterThanEqualExpr){
      return visitGeqExpr((GreaterThanEqualExpr) nd);
    }
    if(nd instanceof NegateExpression){
      return visitNegExpr((NegateExpression) nd);
    }
    if(nd instanceof FunctionCall){
      return visitCall((FunctionCall) nd);
    }
    return null;
  }

  /**
   * Generate code for an assignment.
   */
  @Override
  public Value visitAssignment(Assignment nd) {
    // note that the left hand side should _not_ be wrapped!
    Value lhs = visitLHSExpr(nd.getvar0()),
      rhs = wrap(visitExpr(nd.getvar1()));
    units.add(Jimple.v().newAssignStmt(lhs, rhs));
    return rhs;
  }

  /**
   * Generate code for an integer literal.
   */
  @Override
  public Value visitIntLiteral(IntLiteral nd) {
    /* TODO: return something meaningful here */

    return IntConstant.v(nd.getvar0());
  }

  /**
   * Generate code for a string literal.
   */
  @Override
  public Value visitStringLiteral(StringLiteral nd) {
    /* TODO: return something meaningful here */
    return StringConstant.v(nd.getvar0());
  }

  /**
   * Generate code for a Boolean literal.
   */
  @Override
  public Value visitTrueLiteral(TrueLiteral nd) {
    return IntConstant.v(1);
  }

  @Override
  public Value visitFalseLiteral(FalseLiteral nd) {
    return IntConstant.v(0);
  }

  /**
   * Generate code for an array literal.
   */
  @Override
  public Value visitArrayLiteral(ArrayLiteral nd) {
    Type elttp = SootTypeUtil.getSootType(SootTypeUtil.type(nd.getvar0(0)));
    // create a new array with the appropriate number of elements
    Value array = wrap(Jimple.v().newNewArrayExpr(elttp, IntConstant.v(nd.getNumvar0())));
    for (int i = 0; i < nd.getNumvar0(); ++i) {
      // generate code to store the individual expressions into the elements of the array
      Value elt = wrap(visitExpr(nd.getvar0(i)));
      units.add(Jimple.v().newAssignStmt(Jimple.v().newArrayRef(array, IntConstant.v(i)), elt));
    }
    return array;
  }

  /**
   * Generate code for an array index expression.
   */
  @Override
  public Value visitArrayIndex(ArrayIndex nd) {
    /* TODO: generate code for array index */
    Value base = visitExpr(nd.getvar0()), index = wrap(visitExpr(nd.getvar1()));
    return Jimple.v().newArrayRef(base, index);
  }

  /**
   * Generate code for a variable name.
   */
  @Override
  public Value visitVarName(VarName nd) {
    VarDecl decl = NamesUtils.decl(nd);
    // determine whether this name refers to a local or to a field
    if (NamesUtils.isLocal(decl)) {
      return fcg.getSootLocal(decl);
    } else {
      SootClass declaringClass = fcg.getModuleCodeGenerator().getProgramCodeGenerator().getSootClass(ModuleUtils.getModule(decl));
      Type fieldType = SootTypeUtil.getSootType(SootTypeUtil.type(decl));
      return Jimple.v().newStaticFieldRef(Scene.v().makeFieldRef(declaringClass, decl.getvar1(), fieldType, true));
    }
  }

  /**
   * Generate code for a binary expression.
   */

  @Override
  public Value visitAddExpr(AddExpr nd) {
    final Value value = wrap(Jimple.v().newAddExpr(visitExpr(nd.getvar0()), visitExpr(nd.getvar1())));
    return value;
  }

  @Override
  public Value visitSubExpr(SubExpr nd) {
    final Value value = wrap(Jimple.v().newSubExpr(visitExpr(nd.getvar0()), visitExpr(nd.getvar1())));
    return value;
  }

  @Override
  public Value visitMulExpr(MulExpr nd) {
    final Value value = wrap(Jimple.v().newMulExpr(visitExpr(nd.getvar0()), visitExpr(nd.getvar1())));
    return value;
  }

  @Override
  public Value visitDivExpr(DivExpr nd) {
    final Value value = wrap(Jimple.v().newDivExpr(visitExpr(nd.getvar0()), visitExpr(nd.getvar1())));
    return value;
  }

  @Override
  public Value visitModExpr(ModExpr nd) {
    final Value value = wrap(Jimple.v().newRemExpr(visitExpr(nd.getvar0()), visitExpr(nd.getvar1())));
    return value;
  }

  @Override
  public Value visitEqExpr(EqualExpr nd) {
    final Value left = wrap(visitExpr(nd.getvar0()));
    final Value right = wrap(visitExpr(nd.getvar1()));
    return Jimple.v().newEqExpr(left, right);
  }

  @Override
  public Value visitNeqExpr(NotEqualExpr nd) {
    final Value left = wrap(visitExpr(nd.getvar0()));
    final Value right = wrap(visitExpr(nd.getvar1()));
    Value res = Jimple.v().newNeExpr(left, right);
    Local resvar = fcg.mkTemp(SootTypeUtil.getSootType(SootTypeUtil.type(nd)));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(1)));
    NopStmt join = Jimple.v().newNopStmt();
    units.add(Jimple.v().newIfStmt(res, join));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(0)));
    units.add(join);
    return resvar;
  }

  @Override
  public Value visitLtExpr(LessThanExpr nd) {
    final Value left = wrap(visitExpr(nd.getvar0()));
    final Value right = wrap(visitExpr(nd.getvar1()));
    Value res =  Jimple.v().newLtExpr(left, right);
    Local resvar = fcg.mkTemp(SootTypeUtil.getSootType(SootTypeUtil.type(nd)));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(1)));
    NopStmt join = Jimple.v().newNopStmt();
    units.add(Jimple.v().newIfStmt(res, join));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(0)));
    units.add(join);
    return resvar;
  }

  @Override
  public Value visitGtExpr(GreaterThanExpr nd) {
    final Value left = wrap(visitExpr(nd.getvar0()));
    final Value right = wrap(visitExpr(nd.getvar1()));
    Value res = Jimple.v().newGtExpr(left, right);
    Local resvar = fcg.mkTemp(SootTypeUtil.getSootType(SootTypeUtil.type(nd)));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(1)));
    NopStmt join = Jimple.v().newNopStmt();
    units.add(Jimple.v().newIfStmt(res, join));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(0)));
    units.add(join);
    return resvar;
  }

  @Override
  public Value visitLeqExpr(LessThanEqualExpr nd) {
    final Value left = wrap(visitExpr(nd.getvar0()));
    final Value right = wrap(visitExpr(nd.getvar1()));
    Value res = Jimple.v().newLeExpr(left, right);
    Local resvar = fcg.mkTemp(SootTypeUtil.getSootType(SootTypeUtil.type(nd)));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(1)));
    NopStmt join = Jimple.v().newNopStmt();
    units.add(Jimple.v().newIfStmt(res, join));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(0)));
    units.add(join);
    return resvar;
  }

  @Override
  public Value visitGeqExpr(GreaterThanEqualExpr nd) {
    final Value left = wrap(visitExpr(nd.getvar0()));
    final Value right = wrap(visitExpr(nd.getvar1()));
    Value res = Jimple.v().newGeExpr(left, right);
    Local resvar = fcg.mkTemp(SootTypeUtil.getSootType(SootTypeUtil.type(nd)));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(1)));
    NopStmt join = Jimple.v().newNopStmt();
    units.add(Jimple.v().newIfStmt(res, join));
    units.add(Jimple.v().newAssignStmt(resvar, IntConstant.v(0)));
    units.add(join);
    return resvar;
  }

  /**
   * Generate code for a negation expression.
   */
  @Override
  public Value visitNegExpr(NegateExpression nd) {
    /* TODO: generate code for negation expression */
    return Jimple.v().newNegExpr(wrap(visitExpr(nd.getvar0())));
  }

  /**
   * Generate code for a function call.
   */
  @Override
  public Value visitCall(FunctionCall nd) {
    String calleeName = nd.getvar0();
    FunctionDeclaration calleeDecl = NamesUtils.getCallTarget(nd);
    Module calleeModule = ModuleUtils.getModule(calleeDecl);
    ArrayList<Type> parmTypes = new ArrayList<Type>(calleeDecl.getNumvar2());
    for (VarDecl parm : calleeDecl.getvar2List()) {
      parmTypes.add(SootTypeUtil.getSootType(SootTypeUtil.type(parm)));
    }
    Type rettp = SootTypeUtil.getSootType(SootTypeUtil.type(calleeDecl));

    // compute reference to callee
    SootClass calleeSootClass = fcg.getModuleCodeGenerator().getProgramCodeGenerator().getSootClass(calleeModule);
    SootMethodRef callee = Scene.v().makeMethodRef(calleeSootClass, calleeName, parmTypes, rettp, true);

    // prepare arguments
    Value[] args = new Value[nd.getNumvar1()];
    for (int i = 0; i < args.length; ++i)
      args[i] = wrap(visitExpr(nd.getvar1(i)));

    // assemble invoke expression
    StaticInvokeExpr invk = Jimple.v().newStaticInvokeExpr(callee, args);

    // decide what to do with the result
    if (rettp == soot.VoidType.v()) {
      units.add(Jimple.v().newInvokeStmt(invk));
      return null;
    } else {
      Local res = fcg.mkTemp(rettp);
      units.add(Jimple.v().newAssignStmt(res, invk));
      return res;
    }
  }
}
