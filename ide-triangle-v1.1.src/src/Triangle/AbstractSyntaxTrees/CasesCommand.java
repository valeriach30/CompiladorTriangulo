/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author gabri
 */
public class CasesCommand extends Command{
    public CasesCommand (SingleCase singleCaseAST, SourcePosition thePosition) {
        super (thePosition);
        singleCase = singleCaseAST;
        multipleCase = null;
    }
    
    public CasesCommand (MultipleCase multipleCaseAST, SourcePosition thePosition) {
        super (thePosition);
        multipleCase = multipleCaseAST;
        singleCase = null;
    }

    public Object visit(Visitor V, Object O){
        return V.visitCasesCommand(this, O);
    }
    public SingleCase singleCase;
    public MultipleCase multipleCase;
}
