/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author gabri
 */
public class LocalDeclaration extends Declaration{

    public LocalDeclaration(Declaration d1AST, Declaration d2AST, 
                            SourcePosition thePosition) {
        super (thePosition);
        D1 = d1AST;
        D2 = d2AST;
    }

    public Object visit(Visitor v, Object o) {
      return v.visitLocalDeclaration(this, o);
    }

    public Declaration D1, D2;
}
