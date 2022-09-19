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
public class ToCommand extends Command{
   public ToCommand (Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitToCommandAST(this, o);
  }

  public Expression E;    
}
