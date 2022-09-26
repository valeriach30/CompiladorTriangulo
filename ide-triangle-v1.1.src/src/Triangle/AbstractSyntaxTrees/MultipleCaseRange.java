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
public class MultipleCaseRange extends CaseRangeCommand{
    public MultipleCaseRange(MultipleCaseRange cAST, CaseRangeCommand cAST2, SourcePosition thePosition) {
        super (thePosition);
        CRCMCR = cAST;
        CRCMCR2 = cAST2;
    }
    
    public MultipleCaseRange(CaseRangeCommand cAST, SourcePosition thePosition) {
        super (thePosition);
        CRCMCR = cAST;
        CRCMCR2 = null;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitMultipleCaseRange(this, o);
    }
    public CaseRangeCommand CRCMCR; //Case range command (multiple case range)
    public CaseRangeCommand CRCMCR2;
}
