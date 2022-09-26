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
    public CaseLiterals (SingleCaseRange cAST, SourcePosition thePosition) {
        super (thePosition);
        SCRCL = cAST;
        MCRCL = null;
    }
    public CaseLiterals (MultipleCaseRange cAST, SourcePosition thePosition) {
        super (thePosition);
        MCRCL = cAST;
        SCRCL = null;
    }
    
    @Override
    public Object visit(Visitor V, Object O){
        return V.visitCaseLiterals(this, O);
    }
    public SingleCaseRange SCRCL; //Single case range (case literals) 
    public MultipleCaseRange MCRCL; //Multiple case range (case literals)
  }
