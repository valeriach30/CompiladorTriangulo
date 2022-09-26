/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
 */
public class CaseRangeCommand extends Command{
    public CaseRangeCommand (CaseLiteralCommand ast, SourcePosition thePosition){
        super(thePosition);
        CLC = ast;
        TC = null;
    }
    public CaseRangeCommand (CaseLiteralCommand iAST,
                            ToCommandLiteral eAST,
                            SourcePosition thePosition){
        super(thePosition);
        CLC = iAST;
        TC = eAST;
    }
    
    public CaseRangeCommand (SourcePosition thePosition){
        super(thePosition);
        CLC = null;
        TC = null;
    }
    
    public Object visit(Visitor v, Object o){
        return v.visitCaseRangeCommand(this, o);
    }
    
    
    public CaseLiteralCommand CLC;
    public ToCommandLiteral TC;
}
