/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;


/**
 *
 * @author Vale
 */
public class UntilCommand extends Command{

  public UntilCommand (Expression iAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitUntilCommand(this, o);
  }

  public Expression I;
  public Command C;
    
}
