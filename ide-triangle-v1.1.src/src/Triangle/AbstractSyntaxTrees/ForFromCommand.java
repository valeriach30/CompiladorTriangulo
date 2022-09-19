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
public class ForFromCommand extends Command{
  public ForFromCommand (Identifier iAST, Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    E = eAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitForFromCommand(this, o);
  }
  public Identifier I;
  public Expression E;
}
