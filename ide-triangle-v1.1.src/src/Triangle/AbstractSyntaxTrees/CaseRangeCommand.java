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
        CLC2 = null;
        TC = null;
    }
    public CaseRangeCommand (CaseLiteralCommand iAST, ToCommand eAST, CaseLiteralCommand uAST, SourcePosition thePosition){
        super(thePosition);
        CLC = iAST;
        CLC2 = uAST;
        TC = eAST;
    }
    
    public Object visit(Visitor v, Object o){
        return v.visitCaseRangeCommand(this, o);
    }
    
    
    public CaseLiteralCommand CLC;
    public CaseLiteralCommand CLC2;
    public ToCommand TC;
}
