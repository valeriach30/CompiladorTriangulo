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
public class MultipleActualParameterSequenceCaseLiterals extends ActualParameterSequenceCaseLiterals{
    public MultipleActualParameterSequenceCaseLiterals(SourcePosition thePosition, BarCommandCaseRange bAST, CaseRangeCommand cAST) {
        super (thePosition, bAST, cAST);
        BCCRMAPS = bAST;
        CRCMAPS = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitMultipleActualParameterSequenceCaseLiterals(this, o);
    }
    public BarCommandCaseRange BCCRMAPS;
    public CaseRangeCommand CRCMAPS;
}
