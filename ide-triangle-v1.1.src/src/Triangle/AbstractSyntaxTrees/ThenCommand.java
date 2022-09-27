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
public class ThenCommand extends Command{
    public ThenCommand (ThenCommand cAST, SourcePosition thePosition) {
    super (thePosition);
    C = cAST;
  }
    public ThenCommand (SourcePosition thePosition){
        super(thePosition);
        C = null;
    }

  public Object visit(Visitor v, Object o) {
    return v.visitThenCommandAST(this, o);
  }

  public Command C;
    
}
