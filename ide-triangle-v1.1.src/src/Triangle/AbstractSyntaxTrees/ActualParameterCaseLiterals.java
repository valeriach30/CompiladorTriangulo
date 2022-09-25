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
public abstract class ActualParameterCaseLiterals extends AST{
    public ActualParameterCaseLiterals (SourcePosition thePosition, BarCommandCaseRange bAST, CaseRangeCommand cAST) {
        super (thePosition);
        BCCRAP = bAST;
        CRCAP = cAST;
    }
    public BarCommandCaseRange BCCRAP;
    public CaseRangeCommand CRCAP;
}
