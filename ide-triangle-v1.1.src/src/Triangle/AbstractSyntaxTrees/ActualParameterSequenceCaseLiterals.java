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
public abstract class ActualParameterSequenceCaseLiterals extends AST{
    public ActualParameterSequenceCaseLiterals (SourcePosition thePosition, BarCommandCaseRange bAST, CaseRangeCommand cAST) {
        super (thePosition);
        BCCRAPS = bAST;
        CRCAPS = cAST;
    }
    public BarCommandCaseRange BCCRAPS;
    public CaseRangeCommand CRCAPS;
    
}
