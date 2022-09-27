/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

/**
 *
 * @author gabri
 */

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseCommand extends Command {

  public CaseCommand (CaseLiterals caseLiteralAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    CL = caseLiteralAST;
    C = cAST;
  }
  
  public CaseCommand (SourcePosition thePosition) {
    super (thePosition);
    CL = null;
    C = null;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitCaseCommand(this, o);
  }

  public CaseLiterals CL;
  public Command C;
}
