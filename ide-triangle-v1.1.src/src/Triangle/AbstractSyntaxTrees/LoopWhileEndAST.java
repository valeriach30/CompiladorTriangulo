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
public class LoopWhileEndAST extends Command{

    public LoopWhileEndAST(Command cAST, WhileEndCommand WhileVar, SourcePosition commandPos) {
        super(commandPos);
        C = cAST;
        WhileV = WhileVar;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitLooopWhileEndCommand(this, o);
    }

    public Command C;
    public WhileEndCommand WhileV;
}
