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
public class SingleActualParameterSequenceCaseLiterals extends ActualParameterSequenceCaseLiterals{
    public SingleActualParameterSequenceCaseLiterals(SourcePosition thePosition, BarCommandCaseRange bAST, CaseRangeCommand cAST) {
        super (thePosition, bAST, cAST);
        BCCRSAPS = bAST;
        CRCSAPS = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSingleActualParameterSequenceCaseLiterals(this, o);
    }
    public BarCommandCaseRange BCCRSAPS;
    public CaseRangeCommand CRCSAPS;
}
