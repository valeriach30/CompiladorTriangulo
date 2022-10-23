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
public abstract class ProcFuncs extends Declaration{
    
  public ProcFuncs(SourcePosition thePosition) {
    super(thePosition);
  }

  public abstract Object visitRec(Visitor v, Object o);
}
