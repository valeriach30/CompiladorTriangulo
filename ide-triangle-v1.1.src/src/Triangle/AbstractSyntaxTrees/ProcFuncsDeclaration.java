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
public class ProcFuncsDeclaration  extends ProcFuncs{

    public ProcFuncsDeclaration (ProcFuncs PF1, ProcFuncs PF2,SourcePosition thePosition) {
    super (thePosition);
    //D1 = d1AST;
    //D2 = d2AST;
    D1 = PF1;
    D2 = PF2;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitProcFuncsDeclaration(this, o);
  }

  //public Declaration D1, D2;
  public ProcFuncs D1, D2;
  

  @Override
  public Object visitRec(Visitor v, Object o) {
    return v.visitProcFuncsDeclarationRec(this, o);
  }
}
