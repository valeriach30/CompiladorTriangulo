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
    public CasesCommand (SingleCase eAST,
                         SourcePosition thePosition) {
        super (thePosition);
        SAPSCM = eAST;
        MAPSCM = null;
    }
    public CasesCommand (MultipleCase eAST,
                         SourcePosition thePosition) {
        super (thePosition);
        SAPSCM = null;
        MAPSCM = eAST;
    }

    public Object visit(Visitor V, Object O){
        return V.visitCasesCommand(this, O);
    }
    public SingleCase SAPSCM;
    public MultipleCase MAPSCM;
}
