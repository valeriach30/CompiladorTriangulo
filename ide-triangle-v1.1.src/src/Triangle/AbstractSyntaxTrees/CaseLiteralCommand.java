/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;
import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
 */
public class CaseLiteralCommand extends Command{
    public CaseLiteralCommand (CharacterLiteral cAST, SourcePosition thePosition) {
        super (thePosition);
        CL = cAST;
    }
    public CaseLiteralCommand (IntegerLiteral cAST, SourcePosition thePosition) {
        super (thePosition);
        IL = cAST;
    }
    
    public Object visit(Visitor V, Object O){
        return V.visitCaseLiteralCommand(this, O);
    }
    public CharacterLiteral CL;
    public IntegerLiteral IL;
  }


