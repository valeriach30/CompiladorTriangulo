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
public class CaseLiterals extends Command{
    public CaseLiterals (CaseRangeCommand cAST, SourcePosition thePosition, EmptyActualParameterSequenceCaseLiterals eAST) {
        super (thePosition);
        CRCCL = cAST;
        EAPSCL = eAST;
        SAPSCL = null;
        MAPSCL = null;
    }
    public CaseLiterals (CaseRangeCommand cAST, SourcePosition thePosition, SingleActualParameterSequenceCaseLiterals eAST) {
        super (thePosition);
        CRCCL = cAST;
        EAPSCL = null;
        SAPSCL = eAST;
        MAPSCL = null;
    }
    public CaseLiterals (CaseRangeCommand cAST, SourcePosition thePosition, MultipleActualParameterSequenceCaseLiterals apsAST) {
        super (thePosition);
        CRCCL = cAST;
        EAPSCL = null;
        SAPSCL = null;
        MAPSCL = apsAST;
    }
    
    public Object visit(Visitor V, Object O){
        return V.visitCaseLiterals(this, O);
    }
    public CaseRangeCommand CRCCL;
    public EmptyActualParameterSequenceCaseLiterals EAPSCL;
    public SingleActualParameterSequenceCaseLiterals SAPSCL;
    public MultipleActualParameterSequenceCaseLiterals MAPSCL;
  }
