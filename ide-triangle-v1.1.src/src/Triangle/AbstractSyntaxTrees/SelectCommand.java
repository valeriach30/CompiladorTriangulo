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
public class SelectCommand extends Command{
    public SelectCommand (Expression expressionAST, CasesCommand cAST,
                         SourcePosition thePosition) {
        super (thePosition);
        expression = expressionAST;
        cases = cAST;
        command = null;
    }
    public SelectCommand (Expression expressionAST, CasesCommand cAST, Command c2AST,
                         SourcePosition thePosition) {
        super (thePosition);
        expression = expressionAST;
        cases = cAST;
        command = c2AST;
    }
    

    public Object visit(Visitor V, Object O){
        return V.visitSelectCommand(this, O);
    }
    public Expression expression;
    public CasesCommand cases;
    public Command command;
    
}
