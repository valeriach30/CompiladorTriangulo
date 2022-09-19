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
public class ForInDo extends Command{

    public ForInDo(Identifier iAST, ForInCommand ForInAST, Command cAST, SourcePosition commandPos) {
        super (commandPos);
        forAST = ForInAST;
        I = iAST;
        C = cAST;    
    }
    
    public ForInDo(ForInCommand ForInAST, Command cAST, SourcePosition commandPos) {
        super (commandPos);
        forAST = ForInAST;
        I = null;
        C = cAST;    
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitForInDoCommand(this, o);
    }
    
    public Identifier I;
    public ForInCommand forAST;
    public Command C;    
}
