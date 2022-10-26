/*
    Se crea una clase auxiliar para poder ingresar
    los id de los loop al scope utilizando los
    metodos de IdentificationTable.java
*/

package Triangle.AbstractSyntaxTrees;
import Triangle.SyntacticAnalyzer.SourcePosition;

public class loopDeclaration extends Declaration{

    public loopDeclaration(SourcePosition thePosition) {
        super(thePosition);
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return null;
    }
    
}
