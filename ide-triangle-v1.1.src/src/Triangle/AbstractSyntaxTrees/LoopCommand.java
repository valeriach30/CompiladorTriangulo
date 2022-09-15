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

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LoopCommand extends Command {
 
    
  public LoopCommand (Identifier iAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitLoopCommand(this, o);
  }

  public Identifier I;

}
