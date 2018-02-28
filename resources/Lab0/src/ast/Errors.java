package ast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Errors {
  private static ArrayList<CompilerError> errors = new ArrayList<>();
  public static void reset(){
    errors = new ArrayList<>();
  }
  public static class CompilerError {
    private final String msg;
    private final int line, column;

    public CompilerError(String msg, int line, int column) {
      this.msg = msg;
      this.line = line;
      this.column = column;
    }

    @Override
    public String toString() {
      return "Line " + line + ", column " + column + ": " + msg;
    }
  }

  /** Enter a new error into the list. */
  public static void error(String msg, int line, int column) {
    errors.add(new CompilerError(msg, line, column));
  }

  /** Report an error from some node within the AST. */
  public static void error(String msg, ASTNode nd) {
    Program program = (ast.Program) ModuleUtils.getModule(nd).getParent().getParent();
    error(msg, nd.getLine(nd.getStart()), nd.getColumn(nd.getStart()));
  }

  /** Provide access to the list of compiler errors. */
  public static Iterable<CompilerError> getErrors() {
    return errors;
  }

  /** Check whether any errors have been reported. */
  public static boolean hasErrors() {
    return !errors.isEmpty();
  }
}
