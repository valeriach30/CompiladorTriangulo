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
public class SingleCaseRange extends CaseRangeCommand{
    public SingleCaseRange(CaseRangeCommand cAST, SourcePosition thePosition) {
        super (thePosition);
        CRCSCR = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSingleCaseRange(this, o);
    }
    public CaseRangeCommand CRCSCR; //Case range command (single case range)
}
