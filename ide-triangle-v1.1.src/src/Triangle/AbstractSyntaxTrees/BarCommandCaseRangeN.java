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
public class BarCommandCaseRangeN extends Command {

  public BarCommandCaseRangeN (BarCommandCaseRange east, ActualParameterSequence apsAST,
               SourcePosition thePosition) {
    super (thePosition);
    BCCR = east;
    APS = apsAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitBarCommandCaseRangeN(this, o);
  }

  public BarCommandCaseRange BCCR;
  public ActualParameterSequence APS;
}