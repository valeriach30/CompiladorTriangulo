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
public class MultipleThen extends ThenCommand{
    public MultipleThen(MultipleThen cAST, ThenCommand cAST2, SourcePosition thePosition) {
        super (thePosition);
        TC = cAST;
        TC2 = cAST2;
    }
    
    public MultipleThen(ThenCommand cAST, SourcePosition thePosition) {
        super (thePosition);
        TC = cAST;
        TC2 = null;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitMultipleThen(this, o);
    }
    public ThenCommand TC; 
    public ThenCommand TC2;
}
