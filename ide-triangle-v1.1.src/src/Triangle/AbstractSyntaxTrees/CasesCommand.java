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
    public CasesCommand (CaseCommand cAST, SourcePosition thePosition, EmptyActualParameterSequence eAST) {
        super (thePosition);
        caseComand = cAST;
        EAPSCM = eAST;
        SAPSCM = null;
        MAPSCM = null;
    }
    public CasesCommand (CaseCommand cAST, SingleActualParameterSequence eAST,
                         SourcePosition thePosition) {
        super (thePosition);
        caseComand = cAST;
        EAPSCM = null;
        SAPSCM = eAST;
        MAPSCM = null;
    }
    public CasesCommand (CaseCommand cAST, MultipleActualParameterSequence eAST,
                         SourcePosition thePosition) {
        super (thePosition);
        caseComand = cAST;
        EAPSCM = null;
        SAPSCM = null;
        MAPSCM = eAST;
    }

    public Object visit(Visitor V, Object O){
        return V.visitCasesCommand(this, O);
    }
    public CaseCommand caseComand;
    public EmptyActualParameterSequence EAPSCM;
    public SingleActualParameterSequence SAPSCM;
    public MultipleActualParameterSequence MAPSCM;
}
