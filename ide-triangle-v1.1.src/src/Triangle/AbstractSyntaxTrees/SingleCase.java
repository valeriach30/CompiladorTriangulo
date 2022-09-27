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
public class SingleCase extends CaseCommand{
    public SingleCase(CaseCommand cAST, SourcePosition thePosition) {
        super (thePosition);
        SC = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSingleCase(this, o);
    }
    public CaseCommand SC; //Case range command (single case range)
    
}
