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
public class ForFromAST1 extends Command{

    public ForFromAST1(Identifier iAST, ForFromCommand ForFromVar, Expression eAST, DoCommand Dovar, SourcePosition thePosition) {
        super (thePosition);
        I = iAST;
        ForFrom = ForFromVar;    
        E = eAST;
        Do = Dovar;
    }
    public ForFromAST1(ForFromCommand ForFromVar, Expression eAST, DoCommand Dovar, SourcePosition thePosition) {
        super (thePosition);
        I = null;
        ForFrom = ForFromVar;    
        E = eAST;
        Do = Dovar;
    }
    
    public Object visit(Visitor v, Object o) {
      return v.visitForFromAST1(this, o);
    }

    public ForFromCommand ForFrom;   
    public Expression E;
    public DoCommand Do;
    public Identifier I;
}
