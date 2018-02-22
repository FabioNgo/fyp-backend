package ast;


import soot.RefType;
import soot.Type;

import java.util.HashMap;

/**
 * Utility class for converting from source-level types to Soot types.
 */
public class SootTypeUtil {
  public static boolean typeEqual(TypeDescriptor t1, TypeDescriptor t2){
    return t1.getClass().equals(t2.getClass());
  }
  private static final HashMap<String, TypeDescriptor> javaTypeDescriptors = new HashMap<>();
  /**
   * Determines the Soot-level type for the given type descriptor.
   */
  public static Type getSootType(TypeDescriptor typeDescriptor) {
    Visitor visitor = new Visitor() {
      @Override
      public Type visitIntType(ast.IntType nd) {
        return soot.IntType.v();
      }

      @Override
      public Type visitBooleanType(BooleanType nd) {
        return soot.BooleanType.v();
      }

      @Override
      public Type visitVoidType(VoidType nd) {
        return soot.VoidType.v();
      }

      @Override
      public Type visitArrayType(ArrayType nd) {
        return makeArrayType(getSootType(nd.getElementType()));
      }

      @Override
      public Object visitTypeDescriptor(TypeDescriptor nd) {
        if(nd instanceof IntType){
          return visitIntType((IntType) nd);
        }
        if(nd instanceof BooleanType){
          return visitBooleanType((BooleanType) nd);
        }
        if(nd instanceof VoidType){
          return visitVoidType((VoidType) nd);
        }
        if(nd instanceof ArrayType){
          return visitArrayType((ArrayType) nd);
        }
        return null;
      }

      @Override
      public Type visitJavaType(JavaType nd) {
        return RefType.v(nd.getName());
      }
    };
    return (Type) visitor.visitTypeDescriptor(typeDescriptor);
  }

  private static Type makeArrayType(Type sootType) {
    return soot.ArrayType.v(sootType,1);
  }

  public static TypeDescriptor type(String identifier) {
    return forJavaType(identifier);
  }
  public static ArrayType newArrayType (TypeDescriptor typeDescriptor){
    return new ArrayType(typeDescriptor);
  }
  public static TypeDescriptor type(ASTNode astNode) {
    if(astNode instanceof Assignment){
      Expression expr = ((Assignment) astNode).getvar0();
      return type(expr);
    }

    if(astNode instanceof ArithmeticExpression){
      return new IntType();
    }
    if(astNode instanceof ComparisionExpression){
      return new BooleanType();
    }
    if(astNode instanceof NegateExpression){
      return new IntType();
    }
    if(astNode instanceof StringLiteral){
      return forJavaType("java.lang.String");
    }
    if(astNode instanceof IntLiteral){
      return new IntType();
    }
    if(astNode instanceof BooleanLiteral){
      return new BooleanType();
    }
    if(astNode instanceof ArrayLiteral){
      return newArrayType(type(((ArrayLiteral) astNode).getvar0(0)));
    }
    if(astNode instanceof VarDecl){
      return type(((VarDecl) astNode).getvar0());
    }
    if(astNode instanceof IntTypeName){
      return new IntType();
    }
    if(astNode instanceof BooleanTypeName){
      return new BooleanType();
    }
    if(astNode instanceof VoidTypeName){
      return new VoidType();
    }
    if(astNode instanceof PrimitiveArrayTypeName){
      TypeDescriptor desc = type(((PrimitiveArrayTypeName) astNode).getvar0());
      return new ArrayType(desc);
    }
    if(astNode instanceof UserArrayTypeName){
      TypeDescriptor desc = type(((UserArrayTypeName) astNode).getvar0());
      return new ArrayType(desc);
    }
    if(astNode instanceof ArrayIndex){
      return ((ArrayType)type(((ArrayIndex) astNode).getvar0())).getElementType();
    }


    return null;

  }

  public static final TypeDescriptor forJavaType(String name) {
    TypeDescriptor desc = javaTypeDescriptors.get(name);
    if(desc == null)
      javaTypeDescriptors.put(name, desc = new JavaType(name));
    return desc;
  }
}
