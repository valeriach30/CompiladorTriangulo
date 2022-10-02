/*
 * IDE-Triangle v1.0
 * TableDetails.java
 */

package Core.Visitors;

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
import Triangle.CodeGenerator.Field;
import Triangle.CodeGenerator.KnownAddress;
import Triangle.CodeGenerator.KnownRoutine;
import Triangle.CodeGenerator.KnownValue;
import Triangle.CodeGenerator.UnknownAddress;
import Triangle.CodeGenerator.UnknownRoutine;
import Triangle.CodeGenerator.UnknownValue;
import javax.swing.table.DefaultTableModel;

/**
 * Implements the Triangle Visitor interface, which is used to
 * visit an entire AST. 
 *
 * Generates a DefaultTableModel, used to draw a Jable.
 *
 * @author Luis Leopoldo Perez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class TableVisitor implements Visitor {
    
    /** Creates a new instance of TableDetails */
    public TableVisitor() {        
    }

  // <editor-fold defaultstate="collapsed" desc=" Commands ">
  // Commands
  public Object visitAssignCommand(AssignCommand ast, Object o) { 
      ast.V.visit(this, null);
      ast.E.visit(this, null);
      return(null);
  }
  
  public Object visitCallCommand(CallCommand ast, Object o) { 
      ast.I.visit(this, null);
      ast.APS.visit(this, null);
      return(null);
  }
  public Object visitCaseCommand(CaseCommand ast, Object o) { 
      ast.CL.visit(this, null);
      ast.C.visit(this, null);
      return(null);
  }
  public Object visitCasesCommand(CasesCommand ast, Object o) { 
      ast.singleCase.visit(this, null);
      ast.multipleCase.visit(this, null);
      return(null);
  }
  
  public Object visitSelectCommand(SelectCommand ast, Object o) { 
      ast.cases.visit(this, null);
      ast.command.visit(this, null);
      ast.expression.visit(this, null);
      return(null);
  }
  
  public Object visitEmptyCommand(EmptyCommand ast, Object o) { 
      return(null);
  }
  
  public Object visitIfCommand(IfCommand ast, Object o) { 
      ast.E.visit(this, null);
      ast.C1.visit(this, null);
      ast.C2.visit(this, null);
//      ast.ST.visit(this, null);
//      ast.MT.visit(this, null);
      
      return(null);
  }
  
  public Object visitLetCommand(LetCommand ast, Object o) {     
      ast.D.visit(this, null);
      ast.C.visit(this, null);
      
      return(null);
  }
  
  public Object visitSequentialCommand(SequentialCommand ast, Object o) { 
      ast.C1.visit(this, null);
      ast.C2.visit(this, null);
      
      return(null);
  }
  
  public Object visitSequentialCases(SequentialCases ast, Object o) { 
      ast.C1.visit(this, null);
      ast.C2.visit(this, null);
      
      return(null);
  }
  
  public Object visitWhileCommand(WhileCommand ast, Object o) { 
      ast.E.visit(this, null);
      ast.C.visit(this, null);
      
      return(null);
  }
//Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
public Object visitCaseLiteralCommand(CaseLiteralCommand ast, Object o) { 
    ast.CL.visit(this, null);
    ast.IL.visit(this, null);
      
    return(null);
}

//Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
public Object visitCaseRangeCommand(CaseRangeCommand ast, Object o) { 
    ast.CLC.visit(this, null);
    ast.TC.visit(this, null);
    return(null);
 }
//Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
public Object visitToCommandLiteralAST(ToCommandLiteral ast, Object o){
    ast.CLCT.visit(this, null);
    return(null);
}

//Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
public Object visitBarCommandCaseRange(BarCommandCaseRange ast, Object obj){
    ast.CRCB.visit(this, null);
    return(null);
}

//Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
 public Object visitCaseLiterals(CaseLiterals ast, Object o) { 
      ast.MCRCL.visit(this, null);
      ast.SCRCL.visit(this, null);
      return(null);
  }
 
 public Object visitThenCommandAST(ThenCommand aThis, Object o) { // Autor : Kevin Rodriguez
        aThis.C.visit(this, null);
      
        return(null);
    }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Expressions ">
  // Expressions
  public Object visitArrayExpression(ArrayExpression ast, Object o) { 
      ast.AA.visit(this, null);
      
      return(null);
  }
  
  public Object visitBinaryExpression(BinaryExpression ast, Object o) { 
      ast.E1.visit(this, null);
      ast.E2.visit(this, null);
      ast.O.visit(this, null);
      
      return(null);
  }
  
  public Object visitCallExpression(CallExpression ast, Object o) { 
      ast.I.visit(this, null);
      ast.APS.visit(this, null);
      
      return(null);
  }
  
  public Object visitCharacterExpression(CharacterExpression ast, Object o) { 
      ast.CL.visit(this, null);
      
      return(null);
  }
  
  public Object visitEmptyExpression(EmptyExpression ast, Object o) {       
      return(null);
  }
  
  public Object visitIfExpression(IfExpression ast, Object o) {       
      ast.E1.visit(this, null);
      ast.E2.visit(this, null);
      ast.E3.visit(this, null);
      
      return(null);
  }
  
  public Object visitIntegerExpression(IntegerExpression ast, Object o) { 
      return(null);
  }
  
  public Object visitLetExpression(LetExpression ast, Object o) { 
      ast.D.visit(this, null);
      ast.E.visit(this, null);

      return(null);
  }
  
  public Object visitRecordExpression(RecordExpression ast, Object o) {   
      ast.RA.visit(this, null);
      
      return(null);
  }
  
  public Object visitUnaryExpression(UnaryExpression ast, Object o) {    
      ast.E.visit(this, null);
      ast.O.visit(this, null);
      
      return(null);
  }
  
  public Object visitVnameExpression(VnameExpression ast, Object o) { 
      ast.V.visit(this, null);
      
      return(null);
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" Declarations ">
  // Declarations
  public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) {        
      return(null);
  }
  
  public Object visitConstDeclaration(ConstDeclaration ast, Object o) {   
      String name = ast.I.spelling;
      String type = "N/A";
      try {
        int size = (ast.entity!=null?ast.entity.size:0);
        int level = -1;
        int displacement = -1;
        int value = -1;
      
        if (ast.entity instanceof KnownValue) {
              type = "KnownValue";
              value = ((KnownValue)ast.entity).value;
          }
          else if (ast.entity instanceof UnknownValue) {
              type = "UnknownValue";
              level = ((UnknownValue)ast.entity).address.level;
              displacement = ((UnknownValue)ast.entity).address.displacement;
          }
          addIdentifier(name, type, size, level, displacement, value);
      } catch (NullPointerException e) { }
      
      ast.E.visit(this, null);
      ast.I.visit(this, null);

      return(null);
  }
  
  public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {    
      try {
      addIdentifier(ast.I.spelling, 
              "KnownRoutine", 
              (ast.entity!=null?ast.entity.size:0), 
              ((KnownRoutine)ast.entity).address.level, 
              ((KnownRoutine)ast.entity).address.displacement, 
              -1);      
      } catch (NullPointerException e) { }
      ast.T.visit(this, null);            
      ast.FPS.visit(this, null);
      ast.E.visit(this, null);
            
      return(null);
  }
  
  public Object visitProcDeclaration(ProcDeclaration ast, Object o) { 
      try {
      addIdentifier(ast.I.spelling, "KnownRoutine", 
              (ast.entity!=null?ast.entity.size:0), 
              ((KnownRoutine)ast.entity).address.level, 
              ((KnownRoutine)ast.entity).address.displacement, 
              -1);
      } catch (NullPointerException e) { }
      
      ast.FPS.visit(this, null);
      ast.C.visit(this, null);
            
      return(null);
  }
  
  public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {   
      ast.D1.visit(this, null);
      ast.D2.visit(this, null);
      
      return(null);
  }
  public Object visitLocalDeclaration(LocalDeclaration ast, Object o) {   
      ast.D1.visit(this, null);
      ast.D2.visit(this, null);
      
      return(null);
  }
  
  public Object visitTypeDeclaration(TypeDeclaration ast, Object o) { 
      ast.T.visit(this, null);
      
      return(null);
  }
  
  public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {        
      return(null);
  }
  
  public Object visitVarDeclaration(VarDeclaration ast, Object o) {      
      try {
      addIdentifier(ast.I.spelling, 
              "KnownAddress", 
              (ast.entity!=null?ast.entity.size:0), 
              ((KnownAddress)ast.entity).address.level, 
              ((KnownAddress)ast.entity).address.displacement, 
              -1);
      } catch (NullPointerException e) { }
      
      ast.T.visit(this, null);
      return(null);
  }
  
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Aggregates ">
  // Array Aggregates
  public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) { 
      ast.AA.visit(this, null);
      ast.E.visit(this, null);
      
      return(null);
  }
  
  public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) { 
      ast.E.visit(this, null);
      
      return(null);
  }

  // Record Aggregates
  public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) { 
      ast.E.visit(this, null);
      ast.I.visit(this, null);
      ast.RA.visit(this, null);
      
      return(null);
  }
  
  public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) { 
      ast.E.visit(this, null);
      ast.I.visit(this, null);
      
      return(null);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Parameters ">
  // Formal Parameters
  public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {       
      try {
      addIdentifier(ast.I.spelling, 
              "UnknownValue", 
              (ast.entity!=null?ast.entity.size:0), 
              ((UnknownValue)ast.entity).address.level, 
              ((UnknownValue)ast.entity).address.displacement, 
              -1);
      } catch (NullPointerException e) { }
      
      ast.I.visit(this, null);
      ast.T.visit(this, null);
      
      return(null);
  }
  
  public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {       
      try {
      addIdentifier(ast.I.spelling, 
              "UnknownRoutine",
              (ast.entity!=null?ast.entity.size:0), 
              ((UnknownRoutine)ast.entity).address.level, 
              ((UnknownRoutine)ast.entity).address.displacement,
              -1);
      } catch (NullPointerException e) { }
      ast.FPS.visit(this, null);      
      ast.T.visit(this, null);     

      return(null);
  }
  
  public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {       
      try {
      addIdentifier(ast.I.spelling, 
              "UnknownRoutine",
              (ast.entity!=null?ast.entity.size:0), 
              ((UnknownRoutine)ast.entity).address.level, 
              ((UnknownRoutine)ast.entity).address.displacement,
              -1);      
      } catch (NullPointerException e) { }
      ast.FPS.visit(this, null);      
      
      return(null);
  }
  
  public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {       
      try {
      addIdentifier(ast.I.spelling, 
              "UnknownAddress",
              ast.T.entity.size,
              ((UnknownAddress)ast.entity).address.level, 
              ((UnknownAddress)ast.entity).address.displacement,
              -1);
      } catch (NullPointerException e) { }
      ast.T.visit(this, null);
      
      return(null);
  }

  public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) { 
      return(null);
  }
  
  public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) { 
      ast.FP.visit(this, null);
      ast.FPS.visit(this, null);
      
      return(null);
  }
  
  public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) { 
      ast.FP.visit(this, null);
      
      return(null);
  }

  // Actual Parameters
  public Object visitConstActualParameter(ConstActualParameter ast, Object o) { 
      ast.E.visit(this, null);
      
      return(null);
  }
  
  public Object visitFuncActualParameter(FuncActualParameter ast, Object o) { 
      ast.I.visit(this, null);
      
      return(null);
  }
  
  public Object visitProcActualParameter(ProcActualParameter ast, Object o) { 
      ast.I.visit(this, null);
      
      return(null);
  }
  
  public Object visitVarActualParameter(VarActualParameter ast, Object o) { 
      ast.V.visit(this, null);
      
      return(null);
  }

  public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {       
      return(null);
  }
  
  public Object visitSingleCaseRange(SingleCaseRange ast, Object o) { 
      ast.CRCSCR.visit(this, null);
      
      return(null);
  }
  
  public Object visitSingleThen(SingleThen ast, Object o) { 
      ast.TC.visit(this, null);
      ast.E.visit(this, null);
      
      return(null);
  }
  
  public Object visitSingleCase(SingleCase ast, Object o) { 
      ast.SC.visit(this, null);
      
      return(null);
  }
  
  public Object visitMultipleCaseRange(MultipleCaseRange ast, Object o) { 
      ast.CRCMCR.visit(this, null);
      ast.CRCMCR2.visit(this, null);
      
      return(null);
  }
  
  public Object visitMultipleThen(MultipleThen ast, Object o) { 
      ast.TC.visit(this, null);
      ast.TC2.visit(this, null);
      ast.E.visit(this, null);
      
      return(null);
  }
  
  public Object visitMultipleCase(MultipleCase ast, Object o) { 
      ast.MCC.visit(this, null);
      ast.MCC2.visit(this, null);
      
      return(null);
  }
  
  public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) { 
      ast.AP.visit(this, null);
      ast.APS.visit(this, null);
      
      return(null);
  }
  
  
  
  public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {   
      ast.AP.visit(this, null);
      
      return(null);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Type Denoters ">
  // Type Denoters
  public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {      
      return(null);
  }
  
  public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) { 
      ast.IL.visit(this, null);
      ast.T.visit(this, null);
      
      return(null);
  }
  
  public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {       
      return(null);
  }
  
  public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) { 
      return(null);
  }
  
  public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) { 
      return(null);
  }
  
  public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) { 
      ast.I.visit(this, null);
      
      return(null);
  }
  
  public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) { 
      return(null);
  }
  
  public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {   
      ast.FT.visit(this, null);
      return(null);
  }

  public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) { 
      try {
      addIdentifier(ast.I.spelling, 
              "Field", 
              (ast.entity!=null?ast.entity.size:0),
              -1, ((Field)ast.entity).fieldOffset, -1);      
    } catch (NullPointerException e) { }
      ast.FT.visit(this, null);
      ast.I.visit(this, null);
      ast.T.visit(this, null);


      return(null);
  }
  
  public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) { 
      try {
      addIdentifier(ast.I.spelling, 
              "Field", 
              (ast.entity!=null?ast.entity.size:0),
              -1, ((Field)ast.entity).fieldOffset, -1);
      } catch (NullPointerException e) { }
      ast.I.visit(this, null);
      ast.T.visit(this, null);
  
      return(null);
  }
  
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Literals, Identifiers and Operators ">
  // Literals, Identifiers and Operators
  public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {   
      return(null);
  }
  
  public Object visitIdentifier(Identifier ast, Object o) {             
      return(null);
  }
  
  public Object visitIntegerLiteral(IntegerLiteral ast, Object o) { 
      return(null);
  }
  
  public Object visitOperator(Operator ast, Object o) { 
      ast.decl.visit(this, null);
  
      return(null);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Values or Variable Names ">
  // Value-or-variable names
  public Object visitDotVname(DotVname ast, Object o) { 
      ast.I.visit(this, null);
      ast.V.visit(this, null);
  
      return(null);
  }
  
  public Object visitSimpleVname(SimpleVname ast, Object o) { 
      ast.I.visit(this, null);
  
      return(null);
  }
  
  public Object visitSubscriptVname(SubscriptVname ast, Object o) { 
      ast.E.visit(this, null);
      ast.V.visit(this, null);
  
      return(null);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Table Creation Methods ">
  // Programs
  public Object visitProgram(Program ast, Object o) { 
      ast.C.visit(this, null);
      
      return(null);
  }
  
    /**
     * Adds an identifier to the table.
     */
    private void addIdentifier(String name, String type, int size, int level, int displacement, int value) {
        boolean exists = false;
        
        for (int i=0;(i<model.getRowCount() && !exists);i++)
            if (((String)model.getValueAt(i, 0)).compareTo(name) == 0)
                exists = true;
        
        if (!exists) {
            model.addRow(new String[] {name, 
                    type, 
                    String.valueOf(size), 
                    (level<0?" ":String.valueOf(level)), 
                    (displacement<0?" ":String.valueOf(displacement)), 
                    (value<0?" ":String.valueOf(value))});
        }
    }
    
    
    /**
     * Returns the filled table model.
     */
    public DefaultTableModel getTable(Program ast) {
        model = new DefaultTableModel((new String[] {"Name", "Type", "Size", "Level", "Displacement", "Value"}), 0);
        visitProgram(ast, null);
        
        return(model);
    }
    
    // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    private DefaultTableModel model;
    // </editor-fold>

    @Override
    public Object visitLoopCommandAST1(LoopCommandAST1 aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.WhileVar.visit(this, null);
        return(null);
    }

    @Override
    public Object visitForFromCommand(ForFromCommand aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.E.visit(this, null);      
        return(null);
    }

    @Override
    public Object visitDoCommandAST(DoCommand aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.C.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitForFromAST1(ForFromAST1 aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.E.visit(this, null);
        aThis.ForFrom.visit(this, null);
        aThis.Do.visit(this, null);
        
        return(null);
    }

    @Override
    public Object visitLoopUntilDoAST(LoopUntilDoAST aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.UntilVar.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitUntilCommand(UntilCommand aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.C.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitWhileEndCommand(WhileEndCommand aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.E.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitLooopWhileEndCommand(LoopWhileEndAST aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.WhileV.visit(this, null);
        aThis.C.visit(this, null);
      
        return(null);}

    @Override
    public Object visitUntilEndCommand(UntilEndCommand aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.E.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitLooopUntilEndCommand(LoopUntilEndAST aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.C.visit(this, null);
        aThis.UntilEnd.visit(this, null);
      
        return(null);    
    }

    @Override
    public Object visitForFromWhile(LoopForFromWhile aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.E.visit(this, null);
        aThis.ForFrom.visit(this, null);
        aThis.whileV.visit(this, null);
        
        return(null);
    }

    @Override
    public Object visitForFromUntil(LoopForFromUntil aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.E.visit(this, null);
        aThis.ForFrom.visit(this, null);
        aThis.untilV.visit(this, null);
        
        return(null);
    }

    @Override
    public Object visitForInCommand(ForInCommand aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.E.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitForInDoCommand(ForInDo aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.I.visit(this, null);
        aThis.forAST.visit(this, null);
        aThis.C.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitToCommandAST(ToCommand aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.E.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitVarDeclarationInit(VarDeclarationInit aThis, Object o) { // Autor : Valeria Chinchilla
        aThis.E.visit(this, null);
        aThis.I.visit(this, null);
      
        return(null);
    }

    @Override
    public Object visitLeaveIdentifier(LeaveIdentifier aThis, Object o) {
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        return (null);
    }

    @Override
    public Object visitNextIdentifier(NextIdentifier aThis, Object o) {
        if(aThis.I != null){
            aThis.I.visit(this, null);
        }
        return (null);        
    }

    public Object visitReturnCommand(ReturnCommand aThis, Object o) {
        return (null);
    }
}
