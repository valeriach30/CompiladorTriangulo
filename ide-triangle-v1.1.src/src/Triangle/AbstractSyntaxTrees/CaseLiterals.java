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
    public CaseLiterals (CaseRangeCommand cAST, SourcePosition thePosition) {
        super (thePosition);
        CRCCL = cAST;
        APS = null;
        BCCRCL = null;
    }
    public CaseLiterals (CaseRangeCommand cAST, BarCommandCaseRange eAST, ActualParameterSequence apsAST, SourcePosition thePosition) {
        super (thePosition);
        CRCCL = cAST;
        BCCRCL = eAST;
        APS = apsAST;
    }
    
    public Object visit(Visitor V, Object O){
        return V.visitCaseLiterals(this, O);
    }
    public BarCommandCaseRange BCCRCL;
    public CaseRangeCommand CRCCL;
    public ActualParameterSequence APS;
  }
