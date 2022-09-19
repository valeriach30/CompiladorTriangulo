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
public class LoopUntilEndAST extends Command{
    
    public LoopUntilEndAST(Identifier iAST, Command cAST, UntilEndCommand UntilAST, SourcePosition commandPos) {
        super(commandPos);
        C = cAST;
        I = iAST;
        UntilEnd = UntilAST;
    }
    
    public LoopUntilEndAST(Command cAST, UntilEndCommand UntilAST, SourcePosition commandPos) {
        super(commandPos);
        C = cAST;
        I = null;
        UntilEnd = UntilAST;
    }
    public Object visit(Visitor v, Object o) {
        return v.visitLooopUntilEndCommand(this, o);
    }

    public Command C;
    public Identifier I;
    public UntilEndCommand UntilEnd;
}
