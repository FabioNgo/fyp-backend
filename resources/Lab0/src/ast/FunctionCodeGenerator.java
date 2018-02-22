package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import soot.Local;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.jimple.ParameterRef;
import ast.FunctionDeclaration;
//import ast.Parameter;
import ast.VarDecl;

/**
 * This class is in charge of generating Jimple code for a single function.
 * In particular, it keeps track of the Soot local variables corresponding to
 * source-level locals.
 */
public class FunctionCodeGenerator {
	/** A reference to the instantiating {@link ModuleCodeGenerator}. */
	private final ModuleCodeGenerator mcg;

	/** The body of the generated method. */
	private JimpleBody body;

	/** Keep track of the names of all Soot-level local variables in the generated method. */
	private final HashSet<String> generatedNames = new HashSet<String>();

	/** Map from source-level local variables to Soot-level local variables. */
	private final HashMap<VarDecl, Local> sootLocalMap = new HashMap<VarDecl, Local>();

	public FunctionCodeGenerator(ModuleCodeGenerator mcg) {
		this.mcg = mcg;
	}

	/** Returns a reference to the instantiating {@link ModuleCodeGenerator}. */
	public ModuleCodeGenerator getModuleCodeGenerator() {
		return mcg;
	}

	/** Returns a reference to the body of the generated method. */
	public JimpleBody getBody() {
		return body;
	}

	/** Generates a fresh name starting with {@code prefix}. */
	private String mkTempName(String prefix) {
		String tmpname = prefix;
		if(!generatedNames.add(prefix))
			for(int i=0;!generatedNames.add(tmpname=prefix+i);++i);
		return tmpname;
	}

	/** Generates a new Soot-level local variable with a fresh name and returns it. */
	public Local mkTemp(Type type) {
		Local var = Jimple.v().newLocal(mkTempName("tmp"), type);
		body.getLocals().add(var);
		return var;
	}

	/** Looks up the Soot-level local variable corresponding to the given source-level local variable.
	 * If it doesn't exist yet, it is created. */
	public Local getSootLocal(VarDecl decl) {
		Local local = sootLocalMap.get(decl);
		if(local == null) {
			String varName = decl.getvar1();
			Type varType = SootTypeUtil.getSootType(SootTypeUtil.type(decl));
			local = Jimple.v().newLocal(mkTempName(varName), varType);
			body.getLocals().add(local);
			sootLocalMap.put(decl, local);
		}
		return local;
	}

	/** Generates Jimple code for the given function declaration. */
	public SootMethod generate(FunctionDeclaration fn, SootClass klass) {
		// compute parameter types
		ArrayList<Type> parmtps = new ArrayList<Type>();
		for(VarDecl parm : fn.getvar2List()) {
      parmtps.add(SootTypeUtil.getSootType(SootTypeUtil.type(parm)));
    }
		// create SootMethod
		String fnName = fn.getvar1();
		Type fnReturnType = SootTypeUtil.getSootType(SootTypeUtil.type(fn.getvar0()));
		int fnModifiers = ModuleCodeGenerator.getModifiers(fn);
		SootMethod method = new SootMethod(fnName, parmtps, fnReturnType, fnModifiers);
		klass.addMethod(method);


		// create body
		body = Jimple.v().newBody(method);
		method.setActiveBody(body);

		// create identity statements
		int nparm = fn.getNumvar2();
		for(int i=0;i<nparm;++i) {
			VarDecl parm = fn.getvar2(i);
			Local sootLocal = getSootLocal(parm);
			Type parmType = SootTypeUtil.getSootType(SootTypeUtil.type(parm));
			ParameterRef soot_parm = Jimple.v().newParameterRef(parmType, i);
			body.getUnits().add(Jimple.v().newIdentityStmt(sootLocal, soot_parm));
		}

		// generate code for statements

		new StmtCodeGenerator(this).visitBlockStatement(fn.getvar3());
    System.out.println(body);
		// tack on extra 'return;' if body may terminate normally


		return method;
	}
}
