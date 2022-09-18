/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Vale
 */
public class LoopForFromWhile extends Command{

    public LoopForFromWhile(Identifier iAST, ForFromCommand ForFromVar, Expression eAST, WhileCommand whileAST, SourcePosition thePosition) {
        super (thePosition);
        I = iAST;
        ForFrom = ForFromVar;    
        E = eAST;
        whileV = whileAST;
    }
    public LoopForFromWhile(ForFromCommand ForFromVar, Expression eAST, WhileCommand whileAST, SourcePosition thePosition) {
        super (thePosition);
        I = null;
        ForFrom = ForFromVar;    
        E = eAST;
        whileV = whileAST;
    }
    @Override
    public Object visit(Visitor v, Object o) {
      return v.visitForFromWhile(this, o);
    }

    public ForFromCommand ForFrom;   
    public Expression E;
    public WhileCommand whileV;
    public Identifier I;    
}
