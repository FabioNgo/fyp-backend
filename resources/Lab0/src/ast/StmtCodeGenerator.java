package ast;

import java.util.HashMap;

import soot.Unit;
import soot.Value;
import soot.jimple.IntConstant;
import soot.jimple.Jimple;
import soot.jimple.NopStmt;
import soot.util.Chain;
/**
 * This class is in charge of creating Jimple code for a given statement (and its nested
 * statements, if applicable).
 */
public class StmtCodeGenerator extends Visitor<Void> {
	/** Cache Jimple singleton for convenience. */
	private final Jimple j = Jimple.v();

	/** The {@link FunctionCodeGenerator} that created this object. */
	private final FunctionCodeGenerator fcg;

	/** The statement list of the enclosing function body. */
	private final Chain<Unit> units;

	/** A map from while statements to their break target. */
	private final HashMap<LoopStatement, Unit> breakTargets = new HashMap<LoopStatement, Unit>();

	public StmtCodeGenerator(FunctionCodeGenerator fcg) {
		this.fcg = fcg;
		this.units = fcg.getBody().getUnits();
	}

	/** Generates code for an expression statement. */
	@Override
	public Void visitExpressionStatement(ExpressionStatement nd) {
		/* TODO: generate code for expression statement (hint: use ExprCodeGenerator.generate) */
		ExprCodeGenerator.generate(nd.getvar0(), fcg);
		return null;
	}

	/** Generates code for a break statement. */
	@Override
	public Void visitBreakStatement(BreakStatement nd) {
		/* TODO: generate code for break statement (hint: use ASTNode.getEnclosingLoop and breakTargets;
		 *       use units.add() to insert the statement into the surrounding method) */
		ASTNode temp = nd;
		LoopStatement loop;
		while(true){
		  temp = temp.getParent();
		  if(temp instanceof WhileStatement || temp instanceof ForStatement){
		    loop = (LoopStatement) temp;
		    break;
      }
    }
		Unit breakTarget = breakTargets.get(loop);
		units.add(j.newGotoStmt(breakTarget));
		return null;
	}

	/** Generates code for a block of statements. */
	@Override
	public Void visitBlockStatement(BlockStatement nd) {
		for(Statement stmt : nd.getvar0List())
			visitStatement(stmt);
		return null;
	}

  @Override
  public Void visitStatement(Statement nd) {
    if(nd instanceof ReturnStatement){
      return visitReturnStatement((ReturnStatement) nd);
    }
    if(nd instanceof ExpressionStatement){
      return visitExpressionStatement((ExpressionStatement) nd);
    }
    if(nd instanceof BlockStatement){
      return visitBlockStatement((BlockStatement) nd);
    }
    if(nd instanceof BreakStatement){
      return visitBreakStatement((BreakStatement) nd);
    }
    if(nd instanceof IfStatement){
      return visitIfStatement((IfStatement) nd);
    }
    if(nd instanceof WhileStatement){
      return visitWhileStatement((WhileStatement) nd);
    }
    if(nd instanceof ForStatement){
      return visitForStatement((ForStatement) nd);
    }
    if(nd instanceof ElseStatement){
      return visitElseStatement((ElseStatement) nd);
    }
    return null;
  }

  /** Generates code for a return statement. */
	@Override
	public Void visitReturnStatement(ReturnStatement nd) {
		Unit stmt;
		if(nd.hasvar0())
			stmt = j.newReturnStmt(ExprCodeGenerator.generate(nd.getvar0(), fcg));
		else
			stmt = j.newReturnVoidStmt();
		units.add(stmt);
		return null;
	}

	/** Generates code for an if statement. */
	@Override
	public Void visitIfStatement(IfStatement nd) {
		Value cond = ExprCodeGenerator.generate(nd.getvar0(), fcg);
		NopStmt join = j.newNopStmt();
		units.add(j.newIfStmt(j.newEqExpr(cond, IntConstant.v(0)), join));
		visitStatement(nd.getvar1());
		if(nd.hasvar2()) {
			NopStmt els = join;
			join = j.newNopStmt();
			units.add(j.newGotoStmt(join));
			units.add(els);
			visitElseStatement(nd.getvar2());
		}
		units.add(join);
		return null;
	}

  @Override
  public Void visitForStatement(ForStatement nd) {
	  for(Assignment assignment: nd.getvar0List()){
	    visitAssignment(assignment);
    }
    NopStmt label0 = j.newNopStmt();
    units.add(label0);
    NopStmt label1 = j.newNopStmt();
    breakTargets.put(nd, label1);
    Value cond = ExprCodeGenerator.generate(nd.getvar1(0), fcg);
    units.add(j.newIfStmt(j.newEqExpr(cond, IntConstant.v(0)), label1));
    for(Expression expression: nd.getvar2List()){
      ExprCodeGenerator.generate(expression, fcg);
    }
    units.add(j.newGotoStmt(label0));
    units.add(label1);
    return null;
  }

  /** Generates code for a while statement. */
	@Override
	public Void visitWhileStatement(WhileStatement nd) {
		/* TODO: generate code for while statement as discussed in lecture; add the NOP statement you
		 *       generate as the break target to the breakTargets map
		 */
		NopStmt label0 = j.newNopStmt();
		units.add(label0);
		NopStmt label1 = j.newNopStmt();
		breakTargets.put(nd, label1);
		Value cond = ExprCodeGenerator.generate(nd.getvar0(), fcg);
		units.add(j.newIfStmt(j.newEqExpr(cond, IntConstant.v(0)), label1));
		visitStatement(nd.getvar1());
		units.add(j.newGotoStmt(label0));
		units.add(label1);
		return null;
	}

  @Override
  public Void visitElseStatement(ElseStatement nd) {
    return visitStatement(nd.getvar0());
  }
}
