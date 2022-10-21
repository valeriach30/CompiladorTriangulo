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
public class ForInCommand extends Declaration{
  public ForInCommand (Identifier iAST, Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    E = eAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitForInCommand(this, o);
  }
  public Identifier I;
  public Expression E;    
}
