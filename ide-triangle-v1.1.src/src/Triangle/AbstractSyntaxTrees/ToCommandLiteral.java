/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Kevin
 */
public class ToCommandLiteral extends Command{
   public ToCommandLiteral (CaseLiteralCommand east, SourcePosition thePosition) {
    super (thePosition);
    CLCT = east;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitToCommandLiteralAST(this, o);
  }

  public CaseLiteralCommand CLCT;    
}
