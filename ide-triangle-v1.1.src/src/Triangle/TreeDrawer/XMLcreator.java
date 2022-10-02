/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.TreeDrawer;

import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BarCommandCaseRange;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.CaseCommand;
import Triangle.AbstractSyntaxTrees.CaseLiteralCommand;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.CaseRangeCommand;
import Triangle.AbstractSyntaxTrees.CasesCommand;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DoCommand;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.ForFromAST1;
import Triangle.AbstractSyntaxTrees.ForFromCommand;
import Triangle.AbstractSyntaxTrees.ForInCommand;
import Triangle.AbstractSyntaxTrees.ForInDo;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LeaveIdentifier;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.LocalDeclaration;
import Triangle.AbstractSyntaxTrees.LoopCommandAST1;
import Triangle.AbstractSyntaxTrees.LoopForFromUntil;
import Triangle.AbstractSyntaxTrees.LoopForFromWhile;
import Triangle.AbstractSyntaxTrees.LoopUntilDoAST;
import Triangle.AbstractSyntaxTrees.LoopUntilEndAST;
import Triangle.AbstractSyntaxTrees.LoopWhileEndAST;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleCaseRange;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleCase;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.MultipleThen;
import Triangle.AbstractSyntaxTrees.NextIdentifier;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.ReturnCommand;
import Triangle.AbstractSyntaxTrees.SelectCommand;
import Triangle.AbstractSyntaxTrees.SequentialCases;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleCaseRange;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleCase;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SingleThen;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.ThenCommand;
import Triangle.AbstractSyntaxTrees.ToCommand;
import Triangle.AbstractSyntaxTrees.ToCommandLiteral;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.UntilCommand;
import Triangle.AbstractSyntaxTrees.UntilEndCommand;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarDeclarationInit;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;
import Triangle.AbstractSyntaxTrees.WhileEndCommand;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Valeria Chinchilla
 */
public class XMLcreator implements Visitor {

    private final FileWriter escritorXML;

    public XMLcreator(FileWriter escritor) {
        this.escritorXML = escritor;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    //
    //
    // FUNCIONES DE APOYO
    //
    // Autores: Valeria Chinchilla
    ///////////////////////////////////////////////////////////////////////////////
    
    // Funcion que escribe una nueva linea en el archivo XML
    private void nuevaLinea(String linea){
        try{
            escritorXML.write(linea);
            escritorXML.write("\n");
        }
        catch (IOException error){
            System.out.println("Error al agregar nueva linea al XML");
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    //
    //
    // VISITORS
    //
    // Autores: Valeria Chinchilla
    ///////////////////////////////////////////////////////////////////////////////

    
    @Override
    public Object visitAssignCommand(AssignCommand ast, Object o) {
        nuevaLinea("<AssignCommand>");
        
        // Visitar
        ast.V.visit(this, null);
        ast.E.visit(this, null);
        
        nuevaLinea("</AssignCommand>");
        return (null);
    }

    @Override
    public Object visitCallCommand(CallCommand ast, Object o) {
        nuevaLinea("<CallCommand>");

        ast.I.visit(this, null);
        ast.APS.visit(this, null);
        
        nuevaLinea("</CallCommand>");
        return(null);
    }

    @Override
    public Object visitEmptyCommand(EmptyCommand ast, Object o) {
        nuevaLinea("<EmptyCommand>");
        nuevaLinea("</EmptyCommand>");
        return(null);
    }

    public Object visitIfCommand(IfCommand ast, Object o) {
        nuevaLinea("<IfCommand>");
        
        ast.E.visit(this, null);
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);
      
        nuevaLinea("</IfCommand>");
        return(null);
    }

    @Override
    public Object visitLetCommand(LetCommand ast, Object o) {
        nuevaLinea("<LetCommand>");
        
        ast.D.visit(this, null);
        ast.C.visit(this, null);
      
        nuevaLinea("</LetCommand>");
        return(null);
    }

    @Override
    public Object visitSequentialCommand(SequentialCommand ast, Object o) {
        nuevaLinea("<SequentialCommand>");
        
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);
      
        nuevaLinea("</SequentialCommand>");
        return(null);
    }

    @Override
    public Object visitWhileCommand(WhileCommand ast, Object o) {
        nuevaLinea("<WhileCommand>");
        
        ast.E.visit(this, null);
        ast.C.visit(this, null);
      
        nuevaLinea("</WhileCommand>");
        return(null);
    }

    @Override
    public Object visitLoopCommandAST1(LoopCommandAST1 aThis, Object o) {
        nuevaLinea("<LoopCommand>");
        
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        aThis.WhileVar.visit(this, null);
        
        nuevaLinea("</LoopCommand>");
        return(null);
    }

    @Override
    public Object visitForFromCommand(ForFromCommand aThis, Object o) {
        nuevaLinea("<ForFromCommand>");
        
        aThis.I.visit(this, null);
        aThis.E.visit(this, null);
      
        nuevaLinea("</ForFromCommand>");
        return(null);
    }

    @Override
    public Object visitDoCommandAST(DoCommand aThis, Object o) {
        nuevaLinea("<DoCommand>");
        
        aThis.C.visit(this, null);
        
        nuevaLinea("</DoCommand>");
        return(null);
    }

    @Override
    public Object visitForFromAST1(ForFromAST1 aThis, Object o) {
        nuevaLinea("<LoopCommand>");
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        aThis.E.visit(this, null);
        aThis.ForFrom.visit(this, null);
        aThis.Do.visit(this, null);
        
        nuevaLinea("</LoopCommand>");
        return(null);
    }

    @Override
    public Object visitLoopUntilDoAST(LoopUntilDoAST aThis, Object o) {
        nuevaLinea("<LoopCommand>");
        
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        aThis.UntilVar.visit(this, null);
      
        nuevaLinea("</LoopCommand>");
        return(null);
    }

    @Override
    public Object visitUntilCommand(UntilCommand aThis, Object o) {
        nuevaLinea("<UntilCommand>");
        
        aThis.I.visit(this, null);
        aThis.C.visit(this, null);
      
        nuevaLinea("</UntilCommand>");
        return(null);
    }

    @Override
    public Object visitWhileEndCommand(WhileEndCommand aThis, Object o) {
        nuevaLinea("<WhileEndCommand>");
      
        aThis.E.visit(this, null);
      
        nuevaLinea("</WhileEndCommand>");
        return(null);
    }

    @Override
    public Object visitLooopWhileEndCommand(LoopWhileEndAST aThis, Object o) {
        nuevaLinea("<LoopCommand>");
        
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        aThis.WhileV.visit(this, null);
        aThis.C.visit(this, null);
      
        nuevaLinea("</LoopCommand>");
        return(null);
    }

    @Override
    public Object visitUntilEndCommand(UntilEndCommand aThis, Object o) {
        nuevaLinea("<UntilCommand>");
        
        aThis.E.visit(this, null);
      
        nuevaLinea("</UntilCommand>");
        return(null);
    }

    @Override
    public Object visitLooopUntilEndCommand(LoopUntilEndAST aThis, Object o) {
        nuevaLinea("<LoopCommand>");
        
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        aThis.C.visit(this, null);
        aThis.UntilEnd.visit(this, null);
      
        nuevaLinea("</LoopCommand>");
        return(null);
    }

    @Override
    public Object visitForFromWhile(LoopForFromWhile aThis, Object o) {
        nuevaLinea("<LoopCommand>");
        
        if(aThis.I != null){
           aThis.I.visit(this, null);
        }
        aThis.E.visit(this, null);
        aThis.ForFrom.visit(this, null);
        aThis.whileV.visit(this, null);
        
        nuevaLinea("</LoopCommand>");
        return(null);
    }

    @Override
    public Object visitForFromUntil(LoopForFromUntil aThis, Object o) {
        nuevaLinea("<LoopCommand>");
        
        if(aThis.I != null){
           aThis.I.visit(this, null);
        }
        aThis.E.visit(this, null);
        aThis.ForFrom.visit(this, null);
        aThis.untilV.visit(this, null);
        
        nuevaLinea("</LoopCommand>");
        return(null);
    }

    @Override
    public Object visitForInCommand(ForInCommand aThis, Object o) {
        nuevaLinea("<ForInCommand>");
        
        aThis.I.visit(this, null);
        aThis.E.visit(this, null);
      
        nuevaLinea("</ForInCommand>");
        return(null);
    }

    @Override
    public Object visitForInDoCommand(ForInDo aThis, Object o) {
        nuevaLinea("<LoopCommand>");
        
        if(aThis.I != null){
           aThis.I.visit(this, null);
        }
        aThis.forAST.visit(this, null);
        aThis.C.visit(this, null);
      
        nuevaLinea("</LoopCommand>");
        return(null);
    }

    @Override
    public Object visitToCommandAST(ToCommand aThis, Object o) {
        nuevaLinea("<ToCommand>");
      
        aThis.E.visit(this, null);
      
        nuevaLinea("</ToCommand>");
        return(null);
    }

    @Override
    public Object visitCaseLiteralCommand(CaseLiteralCommand aThis, Object O) {
        nuevaLinea("<CaseLiteralCommand>");
        
        if(aThis.CL != null)
            aThis.CL.visit(this, null);
        else
            aThis.IL.visit(this, null);
    
        nuevaLinea("</CaseLiteralCommand>");
        return(null);
    }

    @Override
    public Object visitCaseRangeCommand(CaseRangeCommand aThis, Object O) {
        nuevaLinea("<CaseRangeCommand>");
        
        aThis.CLC.visit(this, null);
        if(aThis.TC != null)
            aThis.TC.visit(this, null);
    
        nuevaLinea("</CaseRangeCommand>");
        return(null);
    }

    @Override
    public Object visitArrayExpression(ArrayExpression ast, Object o) {
        nuevaLinea("<ArrayExpression>");
        
        ast.AA.visit(this, null);
      
        nuevaLinea("</ArrayExpression>");
        return(null);
    }

    @Override
    public Object visitBinaryExpression(BinaryExpression ast, Object o) {
        nuevaLinea("<BinaryExpression>");
        
        ast.E1.visit(this, null);
        ast.O.visit(this, null);
        ast.E2.visit(this, null);
        
      
        nuevaLinea("</BinaryExpression>");
        return(null);
    }

    @Override
    public Object visitCallExpression(CallExpression ast, Object o) {
        nuevaLinea("<CallExpression>");
        
        ast.I.visit(this, null);
        ast.APS.visit(this, null);
      
        nuevaLinea("</CallExpression>");
        return(null);
    }

    @Override
    public Object visitCharacterExpression(CharacterExpression ast, Object o) {
        nuevaLinea("<CharacterExpression>");
        
        ast.CL.visit(this, null);
      
        nuevaLinea("</CharacterExpression>");
        return(null);
    }

    @Override
    public Object visitEmptyExpression(EmptyExpression ast, Object o) {
        nuevaLinea("<EmptyExpression>");
        nuevaLinea("</EmptyExpression>");
        return(null);
    }

    @Override
    public Object visitIfExpression(IfExpression ast, Object o) {
        nuevaLinea("<IfExpression>");
        
        ast.E1.visit(this, null);
        ast.E2.visit(this, null);
        ast.E3.visit(this, null);
      
        nuevaLinea("</IfExpression>");
        return(null);
    }

    @Override
    public Object visitIntegerExpression(IntegerExpression ast, Object o) { 
        nuevaLinea("<IntegerExpression>");
        
        ast.IL.visit(this, null);
        
        nuevaLinea("</IntegerExpression>");
        return(null);
    }

    @Override
    public Object visitLetExpression(LetExpression ast, Object o) {
        nuevaLinea("<LetExpression>");
        
        ast.D.visit(this, null);
        ast.E.visit(this, null);

        nuevaLinea("</LetExpression>");
        return(null);
    }

    @Override
    public Object visitRecordExpression(RecordExpression ast, Object o) {
        nuevaLinea("<RecordExpression>");
      
        ast.RA.visit(this, null);
      
        nuevaLinea("</RecordExpression>");
        return(null);
    }

    @Override
    public Object visitUnaryExpression(UnaryExpression ast, Object o) {
        nuevaLinea("<UnaryExpression>");
        
        ast.E.visit(this, null);
        ast.O.visit(this, null);
      
        nuevaLinea("</UnaryExpression>");
        return(null);
    }

    @Override
    public Object visitVnameExpression(VnameExpression ast, Object o) {
        nuevaLinea("<VnameExpression>");
      
        ast.V.visit(this, null);
      
        nuevaLinea("</VnameExpression>");
        return(null);
    }

    @Override
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) { 
        nuevaLinea("<BinaryOperatorDeclaration>");
      
        nuevaLinea("</BinaryOperatorDeclaration>");
        return(null);
    }

    @Override
    public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
        nuevaLinea("<ConstDeclaration>");
        
        ast.E.visit(this, null);
        ast.I.visit(this, null);

        nuevaLinea("</ConstDeclaration>");
        return(null);
    }

    @Override
    public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
        nuevaLinea("<FuncDeclaration>");
        
        ast.T.visit(this, null);            
        ast.FPS.visit(this, null);
        ast.E.visit(this, null);
      
        nuevaLinea("</FuncDeclaration>");
        return(null);
    }

    @Override
    public Object visitProcDeclaration(ProcDeclaration ast, Object o) {
        nuevaLinea("<ProcDeclaration>");
        
        ast.FPS.visit(this, null);
        ast.C.visit(this, null);
      
        nuevaLinea("</ProcDeclaration>");
        return(null);
    }

    @Override
    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
        nuevaLinea("<SequentialDeclaration>");
        
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);
      
        nuevaLinea("</SequentialDeclaration>");
        return(null);
    }
    
    @Override
    public Object visitLocalDeclaration(LocalDeclaration ast, Object o) {
        nuevaLinea("<LocalDeclaration>");
        
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);
      
        nuevaLinea("</LocalDeclaration>");
        return(null);
    }

    @Override
    public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
        nuevaLinea("<TypeDeclaration>");
      
        ast.T.visit(this, null);
      
        nuevaLinea("</TypeDeclaration>");
        return(null);
    }

    @Override
    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {
        nuevaLinea("<UnaryOperatorDeclaration>");
        nuevaLinea("</UnaryOperatorDeclaration>");
        return(null);
    }

    @Override
    public Object visitVarDeclaration(VarDeclaration ast, Object o) {
        nuevaLinea("<VarDeclaration>");
        ast.T.visit(this, null);
        nuevaLinea("</VarDeclaration>");
        return(null);
    }

    @Override
    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) {
        nuevaLinea("<MultipleArrayAggregate>");
        
        ast.AA.visit(this, null);
        ast.E.visit(this, null);
      
        nuevaLinea("</MultipleArrayAggregate>");
        return(null);
    }

    @Override
    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {
        nuevaLinea("<SingleArrayAggregate>");
      
        ast.E.visit(this, null);
      
        nuevaLinea("</SingleArrayAggregate>");
        return(null);
    }

    @Override
    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) {
        nuevaLinea("<MultipleRecordAggregate>");
        
        ast.E.visit(this, null);
        ast.I.visit(this, null);
        ast.RA.visit(this, null);
      
        nuevaLinea("</MultipleRecordAggregate>");
        return(null);
    }

    @Override
    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) {
        nuevaLinea("<SingleRecordAggregate>");
        
        ast.E.visit(this, null);
        ast.I.visit(this, null);        
        
        nuevaLinea("</SingleRecordAggregate>");
        return(null);
    }

    @Override
    public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
        nuevaLinea("<ConstFormalParameter>");
        
        ast.I.visit(this, null);
        ast.T.visit(this, null);
      
        nuevaLinea("</ConstFormalParameter>");
        return(null);
    }

    @Override
    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
        nuevaLinea("<FuncFormalParameter>");
        
        ast.FPS.visit(this, null);      
        ast.T.visit(this, null);     

        nuevaLinea("</FuncFormalParameter>");
        return(null);
    }

    @Override
    public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
        nuevaLinea("<ProcFormalParameter>");
        ast.FPS.visit(this, null);      
        nuevaLinea("</ProcFormalParameter>");
        return(null);
    }

    @Override
    public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
        nuevaLinea("<VarFormalParameter>");
        
        ast.T.visit(this, null);
      
        nuevaLinea("</VarFormalParameter>");
        return(null);
    }

    @Override
    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) {
        nuevaLinea("<EmptyFormalParameterSequence>");
        nuevaLinea("</EmptyFormalParameterSequence>");
        return(null);
    }

    @Override
    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) {
        nuevaLinea("<MultipleFormalParameterSequence>");
        
        ast.FP.visit(this, null);
        ast.FPS.visit(this, null);
      
        nuevaLinea("</MultipleFormalParameterSequence>");
        return(null);
    }

    @Override
    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) {
        nuevaLinea("<SingleFormalParameterSequence>");
      
        ast.FP.visit(this, null);
      
        nuevaLinea("</SingleFormalParameterSequence>");
        return(null);
    }

    @Override
    public Object visitConstActualParameter(ConstActualParameter ast, Object o) {
        nuevaLinea("<ConstActualParameter>");
        
        ast.E.visit(this, null);
      
        nuevaLinea("</ConstActualParameter>");
        return(null);
    }

    @Override
    public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {
        nuevaLinea("<FuncActualParameter>");
        
        ast.I.visit(this, null);
      
        nuevaLinea("</FuncActualParameter>");
        return(null);
    }

    @Override
    public Object visitProcActualParameter(ProcActualParameter ast, Object o) {
        nuevaLinea("<ProcActualParameter>");
      
        ast.I.visit(this, null);
      
        nuevaLinea("</ProcActualParameter>");
        return(null);
    }

    @Override
    public Object visitVarActualParameter(VarActualParameter ast, Object o) {
        nuevaLinea("<VarActualParameter>");
      
        ast.V.visit(this, null);
      
        nuevaLinea("</VarActualParameter>");
        return(null);
    }

    @Override
    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {
        nuevaLinea("<EmptyActualParameterSequence>");
        nuevaLinea("</EmptyActualParameterSequence>");
        return(null);
    }

    @Override
    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) {
        nuevaLinea("<MultipleActualParameterSequence>");
        
        ast.AP.visit(this, null);
        ast.APS.visit(this, null);
      
        nuevaLinea("</MultipleActualParameterSequence>");
        return(null);
    }

    @Override
    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {
        nuevaLinea("<SingleActualParameterSequence>");
      
        ast.AP.visit(this, null);
      
        nuevaLinea("</SingleActualParameterSequence>");
        return(null);
    }

    @Override
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
        nuevaLinea("<AnyTypeDenoter>");
        nuevaLinea("</AnyTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
        nuevaLinea("<ArrayTypeDenoter>");
        
        ast.IL.visit(this, null);
        ast.T.visit(this, null);
      
        nuevaLinea("</ArrayTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
        nuevaLinea("<BoolTypeDenoter>");
        nuevaLinea("</BoolTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
        nuevaLinea("<CharTypeDenoter>");
        nuevaLinea("</CharTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
        nuevaLinea("<ErrorTypeDenoter>");
        nuevaLinea("</ErrorTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {
        nuevaLinea("<SimpleTypeDenoter>");
        
        ast.I.visit(this, null);
      
        nuevaLinea("</SimpleTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {
        nuevaLinea("<IntTypeDenoter>");
        nuevaLinea("</IntTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {
        nuevaLinea("<RecordTypeDenoter>");
      
        ast.FT.visit(this, null);
      
        nuevaLinea("</RecordTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) {
        nuevaLinea("<MultipleFieldTypeDenoter>");
        
        ast.FT.visit(this, null);
        ast.I.visit(this, null);
        ast.T.visit(this, null);

        nuevaLinea("</MultipleFieldTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) {
        nuevaLinea("<SingleFieldTypeDenoter>");
    
        ast.I.visit(this, null);
        ast.T.visit(this, null);
  
        nuevaLinea("</SingleFieldTypeDenoter>");
        return(null);
    }

    @Override
    public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {
        nuevaLinea("<CharacterLiteral value=" + ast.spelling + ">");
        nuevaLinea("</CharacterLiteral>");
        return(null);
    }

    @Override
    public Object visitIdentifier(Identifier ast, Object o) {
        nuevaLinea("<Identifier value='" + ast.spelling + "'>");
        nuevaLinea("</Identifier>");
        return(null);
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral ast, Object o) {
        nuevaLinea("<IntegerLiteral value= '" + ast.spelling + "'>");
        nuevaLinea("</IntegerLiteral>");
        return(null);
    }

    @Override
    public Object visitOperator(Operator ast, Object o) {
        if("<".equals(ast.spelling))
            nuevaLinea("<Operator value='&lt;'>");
        else if(">".equals(ast.spelling))
            nuevaLinea("<Operator value='&gt;'>");
        else
            nuevaLinea("<Operator value= '" + ast.spelling + "'>");
        if(ast.decl != null){
            ast.decl.visit(this, null);
        }
        nuevaLinea("</Operator>");
        return(null);
    }

    @Override
    public Object visitDotVname(DotVname ast, Object o) {
        nuevaLinea("<DotVname>");
    
        ast.I.visit(this, null);
        ast.V.visit(this, null);
  
        nuevaLinea("</DotVname>");
        return(null);
    }

    @Override
    public Object visitSimpleVname(SimpleVname ast, Object o) {
        nuevaLinea("<SimpleVname>");
  
        ast.I.visit(this, null);
  
        nuevaLinea("</SimpleVname>");
        return(null);
    }

    @Override
    public Object visitSubscriptVname(SubscriptVname ast, Object o) {
        nuevaLinea("<SubscriptVname>");
        
        ast.E.visit(this, null);
        ast.V.visit(this, null);
  
        nuevaLinea("</SubscriptVname>");
        return(null);
    }

    @Override
    public Object visitProgram(Program ast, Object o) {
        nuevaLinea("<Program>");
      
        ast.C.visit(this, null);
      
        nuevaLinea("</Program>");
        return(null);
    }

    @Override
    public Object visitVarDeclarationInit(VarDeclarationInit aThis, Object o) {
        nuevaLinea("<VarDeclarationInit>");
        
        aThis.E.visit(this, null);
        aThis.I.visit(this, null);
      
        nuevaLinea("</VarDeclarationInit>");
        return(null);
    }

    @Override
    public Object visitToCommandLiteralAST(ToCommandLiteral aThis, Object O) {
        nuevaLinea("<VarDeclarationInit>");
        
        aThis.CLCT.visit(this, null);
      
        nuevaLinea("</VarDeclarationInit>");
        return(null);
    }

    @Override
    public Object visitBarCommandCaseRange(BarCommandCaseRange aThis, Object o) {
        nuevaLinea("<VarDeclarationInit>");
        
        aThis.CRCB.visit(this, null);
      
        nuevaLinea("</VarDeclarationInit>");
        return(null);
    }

    @Override
    public Object visitCaseLiterals(CaseLiterals aThis, Object O) {
        nuevaLinea("<CaseLiterals>");
        
        if(aThis.SCRCL != null)
            aThis.SCRCL.visit(this, null);
        else
            aThis.MCRCL.visit(this, null);
        
        nuevaLinea("</CaseLiterals>");
        return(null);
    }

    @Override
    public Object visitCaseCommand(CaseCommand aThis, Object o) {
        nuevaLinea("<CaseCommand>");
        
        if(aThis.C != null){
            aThis.CL.visit(this, null);
            aThis.C.visit(this, null);
        }
        
        nuevaLinea("</CaseCommand>");
        return(null);
    }

    @Override
    public Object visitCasesCommand(CasesCommand aThis, Object o) {
        nuevaLinea("<CasesCommand>");
        
        if(aThis.multipleCase == null)
            aThis.singleCase.visit(this, null);
        else
            aThis.multipleCase.visit(this, null);
        
        nuevaLinea("</CasesCommand>");
        return(null);
    }

    @Override
    public Object visitSingleCaseRange(SingleCaseRange aThis, Object o) {
        nuevaLinea("<SingleCaseRange>");
        
        aThis.CRCSCR.visit(this, null);
        
        nuevaLinea("</SingleCaseRange>");
        return(null);
    }

    @Override
    public Object visitMultipleCaseRange(MultipleCaseRange aThis, Object o) {
        nuevaLinea("<MultipleCaseRange>");
        
        aThis.CRCMCR.visit(this, null);
        if(aThis.CRCMCR2 != null)
            aThis.CRCMCR2.visit(this, null);
        
        nuevaLinea("</MultipleCaseRange>");
        return(null);
    }

    @Override
    public Object visitSingleCase(SingleCase aThis, Object o) {
        nuevaLinea("<SingleCase>");
        
        aThis.SC.visit(this, null);
        
        nuevaLinea("</SingleCase>");
        return(null);
    }

    @Override
    public Object visitMultipleCase(MultipleCase aThis, Object o) {
        nuevaLinea("<MultipleCase>");
        
        aThis.MCC.visit(this, null);
        if(aThis.MCC2 != null)
            aThis.MCC2.visit(this, null);
        
        nuevaLinea("</MultipleCase>");
        return(null);
    }

    @Override
    public Object visitSequentialCases(SequentialCases aThis, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object visitSelectCommand(SelectCommand aThis, Object O) {
        nuevaLinea("<SelectCommand>");
        
        aThis.expression.visit(this, null);
        aThis.cases.visit(this, null);
        if(aThis.command != null)
            aThis.command.visit(this, null);
        
        nuevaLinea("</SelectCommand>");
        return(null);
    }

    @Override
    public Object visitThenCommandAST(ThenCommand aThis, Object o) {
        nuevaLinea("<ThenCommand>");
        if(aThis.C != null){
            aThis.C.visit(this, null);
        }
        nuevaLinea("</ThenCommand>");
        return (null);
    }

    @Override
    public Object visitSingleThen(SingleThen aThis, Object o) {
        nuevaLinea("<SingleThen>");
        aThis.E.visit(this, null);
        aThis.TC.visit(this, null);
        nuevaLinea("</SingleThen>");
        return (null);
    }

    @Override
    public Object visitMultipleThen(MultipleThen aThis, Object o) {
        nuevaLinea("<MultipleThen>");
        aThis.TC.visit(this, null);
        aThis.TC2.visit(this, null);
        aThis.E.visit(this, null);
        nuevaLinea("</MultipleThen>");
        return (null);
    }

    @Override
    public Object visitLeaveIdentifier(LeaveIdentifier aThis, Object o) {
        nuevaLinea("<Leave>");
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        nuevaLinea("</Leave>");
        return(null);
    }

    @Override
    public Object visitNextIdentifier(NextIdentifier aThis, Object o) {
        nuevaLinea("<Next>");
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        nuevaLinea("</Next>");
        return(null);
    }

    @Override
    public Object visitReturnCommand(ReturnCommand aThis, Object o) {
        nuevaLinea("<Return>");
        nuevaLinea("</Return>");
        return (null);
    }

    
}
