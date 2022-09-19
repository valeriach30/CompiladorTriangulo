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
public class LoopWhileEndAST extends Command{

    public LoopWhileEndAST(Identifier iAST, Command cAST, WhileEndCommand WhileVar, SourcePosition commandPos) {
        super(commandPos);
        C = cAST;
        I = iAST;
        WhileV = WhileVar;
    }
    
    public LoopWhileEndAST(Command cAST, WhileEndCommand WhileVar, SourcePosition commandPos) {
        super(commandPos);
        C = cAST;
        I = null;
        WhileV = WhileVar;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitLooopWhileEndCommand(this, o);
    }

    public Command C;
    public Identifier I;
    public WhileEndCommand WhileV;
}
