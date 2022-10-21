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
public class ForFromAST1 extends Command{

    public ForFromAST1(Identifier iAST, ForFromCommand ForFromVar, ToCommand toCommand, DoCommand Dovar, SourcePosition thePosition) {
        super (thePosition);
        I = iAST;
        ForFrom = ForFromVar;    
        TC = toCommand;
        Do = Dovar;
    }
    public ForFromAST1(ForFromCommand ForFromVar, ToCommand toCommand, DoCommand Dovar, SourcePosition thePosition) {
        super (thePosition);
        I = null;
        ForFrom = ForFromVar;    
        TC = toCommand;
        Do = Dovar;
    }
    
    public Object visit(Visitor v, Object o) {
      return v.visitForFromAST1(this, o);
    }

    public ForFromCommand ForFrom;   
    public ToCommand TC;
    public DoCommand Do;
    public Identifier I;
}
