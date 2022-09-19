/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Valeria Chinchilla
 */
public class LoopForFromUntil extends Command{

    public LoopForFromUntil(Identifier iAST, ForFromCommand ForFromVar, ToCommand eAST, UntilCommand untilAST, SourcePosition thePosition) {
        super (thePosition);
        I = iAST;
        ForFrom = ForFromVar;    
        E = eAST;
        untilV = untilAST;
    }
    
    public LoopForFromUntil(ForFromCommand ForFromVar, ToCommand eAST, UntilCommand untilAST, SourcePosition thePosition) {
        super (thePosition);
        I = null;
        ForFrom = ForFromVar;    
        E = eAST;
        untilV = untilAST;
    }
    
    @Override
    public Object visit(Visitor v, Object o) {
      return v.visitForFromUntil(this, o);
    }

    public ForFromCommand ForFrom;   
    public ToCommand E;
    public UntilCommand untilV;
    public Identifier I;        
}
