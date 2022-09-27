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
public class MultipleCase extends CaseCommand{
    public MultipleCase(MultipleCase cAST, CaseCommand cAST2, SourcePosition thePosition) {
        super (thePosition);
        MCC = cAST;
        MCC2 = cAST2;
    }
    
    public MultipleCase(CaseCommand cAST, SourcePosition thePosition) {
        super (thePosition);
        MCC = cAST;
        MCC2 = null;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitMultipleCase(this, o);
    }
    public CaseCommand MCC;
    public CaseCommand MCC2;
    
}
