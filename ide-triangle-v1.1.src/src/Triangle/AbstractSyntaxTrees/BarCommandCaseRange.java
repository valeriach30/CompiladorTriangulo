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
public class BarCommandCaseRange extends Command{
   public BarCommandCaseRange (CaseRangeCommand east, SourcePosition thePosition) {
    super (thePosition);
    CRCB = east;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitBarCommandCaseRange(this, o);
  }

  public CaseRangeCommand CRCB;    
}
    
