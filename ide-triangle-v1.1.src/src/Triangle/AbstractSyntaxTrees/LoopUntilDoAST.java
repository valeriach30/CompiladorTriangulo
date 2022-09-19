/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Valeria Chinchilla
 */
public class LoopUntilDoAST extends Command {
  public LoopUntilDoAST (Identifier iAST, UntilCommand UntilVarAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    UntilVar = UntilVarAST;
  }
  public LoopUntilDoAST (UntilCommand UntilVarAST, SourcePosition thePosition) {
    super (thePosition);
    I = null;
    UntilVar = UntilVarAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitLoopUntilDoAST(this, o);
  }

  public Identifier I;
  public UntilCommand UntilVar;
}
