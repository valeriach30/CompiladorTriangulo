/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author vchin
 */
public class RecProcFuncDeclaration extends Declaration{
                                         //Declaration d1AST
  public RecProcFuncDeclaration (ProcFuncs pfAST,SourcePosition thePosition) {
    super (thePosition);
    D1 = pfAST;
  }


  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitRecProcFuncDeclaration(this, o);
  }

  public ProcFuncs D1;

}
