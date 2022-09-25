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
public class EmptyActualParameterSequenceCaseLiterals extends ActualParameterSequenceCaseLiterals{
    public EmptyActualParameterSequenceCaseLiterals(SourcePosition thePosition, BarCommandCaseRange bAST, CaseRangeCommand cAST) {
        super (thePosition, bAST, cAST);
        BCCREAPS = bAST;
        CRCEAPS = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitEmptyActualParameterSequenceCaseLiterals(this, o);
    }
    public BarCommandCaseRange BCCREAPS;
    public CaseRangeCommand CRCEAPS;
}
