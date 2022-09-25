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
    public CaseLiterals (CaseRangeCommand cAST, SourcePosition thePosition, EmptyActualParameterSequence eAST) {
        super (thePosition);
        CRCCL = cAST;
        BCCRCL = null;
        EAPSCL = eAST;
        SAPSCL = null;
        MAPSCL = null;
    }
    public CaseLiterals (CaseRangeCommand cAST, SourcePosition thePosition, SingleActualParameterSequence eAST) {
        super (thePosition);
        CRCCL = cAST;
        BCCRCL = null;
        EAPSCL = null;
        SAPSCL = eAST;
        MAPSCL = null;
    }
    public CaseLiterals (CaseRangeCommand cAST, BarCommandCaseRange eAST, MultipleActualParameterSequence apsAST, SourcePosition thePosition) {
        super (thePosition);
        CRCCL = cAST;
        BCCRCL = eAST;
        EAPSCL = null;
        SAPSCL = null;
        MAPSCL = apsAST;
    }
    
    public Object visit(Visitor V, Object O){
        return V.visitCaseLiterals(this, O);
    }
    public BarCommandCaseRange BCCRCL;
    public CaseRangeCommand CRCCL;
    public EmptyActualParameterSequence EAPSCL;
    public SingleActualParameterSequence SAPSCL;
    public MultipleActualParameterSequence MAPSCL;
  }
