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
public class LoopCommandAST1 extends Command{
  public LoopCommandAST1 (Identifier iAST, WhileCommand WhileVarAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    WhileVar = WhileVarAST;
  }
  
  // Constructor que se utiliza cuando el iAST es nulo
  public LoopCommandAST1 (WhileCommand WhileVarAST, SourcePosition thePosition) {
    super (thePosition);
    I = null;
    WhileVar = WhileVarAST;
  }
  
  public Object visit(Visitor v, Object o) {
    return v.visitLoopCommandAST1(this, o);
  }

  public Identifier I;
  public WhileCommand WhileVar;
}
