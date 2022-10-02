/*
 * IDE-Triangle v1.0
 * TreeVisitor.java
 */

package Core.Visitors;
import Triangle.AbstractSyntaxTrees.AST;
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
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Implements the Triangle Visitor interface, which is used to
 * visit an entire AST. 
 *
 * Generates DefaultMutableTreeNodes, used to draw a JTree.
 *
 * @author Luis Leopoldo Perez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class TreeVisitor implements Visitor {
      
    /**
     * Creates a new instance of TreeVisitor.
     */
    public TreeVisitor() {
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Commands ">    
    // Commands  
    public Object visitAssignCommand(AssignCommand ast, Object o) {
        return(createBinary("Assign Command", ast.V, ast.E));
    }
    
    public Object visitCallCommand(CallCommand ast, Object o) {
        return(createBinary("Call Command", ast.I, ast.APS));
    }
    
    public Object visitEmptyCommand(EmptyCommand ast, Object o) {
        return(createNullary("Empty Command"));
    }
    
    public Object visitIfCommand(IfCommand ast, Object obj) {
//        if(ast.ST == null){
//            return(createQuaternary("If Command", ast.E, ast.C1, ast.C2, ast.MT));
//        }
//        else{
//            return(createQuaternary("If Command", ast.E, ast.C1, ast.C2, ast.ST));
//        }
        return(createTernary("If command", ast.E, ast.C1, ast.C2));
    }
    
    
    public Object visitLetCommand(LetCommand ast, Object obj) {
        return(createBinary("Let Command", ast.D, ast.C));
    }
    
    public Object visitSequentialCommand(SequentialCommand ast, Object obj) {
        return(createBinary("Sequential Command", ast.C1, ast.C2));
    }
    
    public Object visitSequentialCases(SequentialCases ast, Object obj) {
        return(createBinary("Sequential Cases", ast.C1, ast.C2));
    }
    
    public Object visitWhileCommand(WhileCommand ast, Object obj) {
        return(createBinary("While Do Command", ast.E, ast.C));
    }
    //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
    public Object visitCaseLiteralCommand(CaseLiteralCommand ast, Object obj){
        if(ast.CL == null)
            return(createUnary("Case Literal Command", ast.IL));
        else
            return(createUnary("Case Literal Command", ast.CL));
    }
    //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
    public Object visitCaseCommand(CaseCommand ast, Object obj){
        return(createBinary("Case Command", ast.CL,  ast.C));
    }
    //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
    public Object visitCasesCommand(CasesCommand ast, Object obj){
        if(ast.multipleCase == null)
            return(createUnary("Cases Command", ast.singleCase));
        else
            return(createUnary("Cases Command", ast.multipleCase));
    }
    
    public Object visitSelectCommand(SelectCommand ast, Object obj){
        if(ast.command == null)
            return(createBinary("Select Command", ast.expression, ast.cases));
        else
            return(createTernary("Select Command", ast.expression, ast.cases, ast.command));
    }
    //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
    public Object visitCaseRangeCommand(CaseRangeCommand ast, Object obj){
        if(ast.TC == null){
            return(createUnary("Case Range Command", ast.CLC));
        }
        else{
            return(createBinary("Case Range Command", ast.CLC, ast.TC));
        }
    }
    //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
    public Object visitToCommandLiteralAST(ToCommandLiteral ast, Object obj){
        return(createUnary("Case To Literal", ast.CLCT));
    }

    //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
    public Object visitBarCommandCaseRange(BarCommandCaseRange ast, Object obj){
        return(createUnary("Case Bar Command Case Range", ast.CRCB));
    }
    //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
    public Object visitCaseLiterals(CaseLiterals ast, Object o) {
        if(ast.MCRCL == null)
            return(createUnary("Case Literals", ast.SCRCL));
        else
            return(createUnary("Case Literals", ast.MCRCL));
    }

    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Expressions ">
    // Expressions
    public Object visitArrayExpression(ArrayExpression ast, Object obj) {
        return(createUnary("Array Expression", ast.AA));
    }
    
    public Object visitBinaryExpression(BinaryExpression ast, Object obj) {
        return(createTernary("Binary Expression", ast.E1, ast.O, ast.E2));
    }
    
    public Object visitCallExpression(CallExpression ast, Object obj) {
        return(createBinary("Call Expression", ast.I, ast.APS));
    }
    
    public Object visitCharacterExpression(CharacterExpression ast, Object obj) {
        return(createUnary("Character Expression", ast.CL));
    }
    
    public Object visitEmptyExpression(EmptyExpression ast, Object obj) {
        return(createNullary("Empty Expression"));
    }
    
    public Object visitIfExpression(IfExpression ast, Object obj) {
        return(createTernary("If Expression", ast.E1, ast.E2, ast.E3));
    }
    
    public Object visitIntegerExpression(IntegerExpression ast, Object obj) {
        return(createUnary("Integer Expression", ast.IL));
    }
    
    public Object visitLetExpression(LetExpression ast, Object obj) {
        return(createBinary("Let Expression", ast.D, ast.E));
    }
    
    public Object visitRecordExpression(RecordExpression ast, Object obj) {
        return(createUnary("Record Expression", ast.RA));
    }
    
    public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
        return(createBinary("Unary Expression", ast.O, ast.E));
    }
    
    public Object visitVnameExpression(VnameExpression ast, Object obj) {
        return(createUnary("Vname Expression", ast.V));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Declarations ">
    // Declarations
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
        return(createQuaternary("Binary Operator Declaration", ast.O, ast.ARG1, ast.ARG2, ast.RES));
    }
    
    public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
        return(createBinary("Constant Declaration", ast.I, ast.E));
    }
    
    public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
        return(createQuaternary("Function Declaration", ast.I, ast.FPS, ast.T, ast.E));
    }
    
    public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
        return(createTernary("Procedure Declaration", ast.I, ast.FPS, ast.C));        
    }
    
    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
        return(createBinary("Sequential Declaration", ast.D1, ast.D2));
    }
    
    public Object visitLocalDeclaration(LocalDeclaration ast, Object obj) {
        return(createBinary("Local Declaration", ast.D1, ast.D2));
    }
    
    public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
        return(createBinary("Type Declaration", ast.I, ast.T));
    }
    
    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
        return(createTernary("Unary Operator Declaration", ast.O, ast.ARG, ast.RES));
    }
    
    public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
        return(createBinary("Variable Declaration", ast.I, ast.T));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Aggregates ">
    // Array Aggregates
    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
        return(createBinary("Multiple Array Aggregate", ast.E, ast.AA));
    }
    
    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
        return(createUnary("Single Array Aggregate", ast.E));
    }
    
    // Record Aggregates
    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
        return(createTernary("Multiple Record Aggregate", ast.I, ast.E, ast.RA));
    }
    
    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
        return(createBinary("Single Record Aggregate", ast.I, ast.E));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Parameters ">
    // Formal Parameters   
    public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
        return(createBinary("Constant Formal Parameter", ast.I, ast.T));
    }
    
    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
        return(createTernary("Function Formal Parameter", ast.I, ast.FPS, ast.T));
    }
    
    public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
        return(createBinary("Procedure Formal Parameter", ast.I, ast.FPS));
    }
    
    public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
        return(createBinary("Variable Formal Parameter", ast.I, ast.T));
    }
    
    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
        return(createNullary("Empty Formal Parameter Sequence"));
    }
    
    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
        return(createBinary("Multiple Formal Parameter Sequence", ast.FP, ast.FPS));
    }
    
    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
        return(createUnary("Single Formal Parameter Sequence", ast.FP));
    }
    
    // Actual Parameters
    public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
        return(createUnary("Constant Actual Parameter", ast.E));
    }
    
    public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
        return(createUnary("Function Actual Parameter", ast.I));
    }
    
    public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
        return(createUnary("Procedure Actual Parameter", ast.I));
    }
    
    public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
        return(createUnary("Variable Actual Parameter", ast.V));
    }
    
    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
        return(createNullary("Empty Actual Parameter Sequence"));
    }
    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
        return(createBinary("Multiple Actual Parameter Sequence", ast.AP, ast.APS));
    }
    
    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
        return(createUnary("Single Actual Parameter Sequence", ast.AP));
    }
    
    //Autores: Kevin, Hillary, Gabriel
    public Object visitSingleCaseRange(SingleCaseRange ast, Object obj) {
        return(createUnary("Single Case Range", ast.CRCSCR));
    }
    
    //Autores: Kevin, Hillary, Gabriel
    public Object visitSingleThen(SingleThen ast, Object obj) {
        return(createBinary("Single Then", ast.TC, ast.E));
    }
    
    public Object visitSingleCase(SingleCase ast, Object obj) {
        return(createUnary("Single Case ", ast.SC));
    }
    
    public Object visitMultipleCaseRange(MultipleCaseRange ast, Object obj) {
        if(ast.CRCMCR2 == null)
            return(createUnary("Multiple Case Range", ast.CRCMCR));
        else
            return(createBinary("Multiple Case Range", ast.CRCMCR, ast.CRCMCR2));
    }
    
    public Object visitMultipleThen(MultipleThen ast, Object obj) {
        if(ast.TC2 == null)
            return(createUnary("Multiple Then", ast.TC));
        else
            return(createTernary("Multiple Then", ast.TC, ast.TC2, ast.E));
    }
    
    public Object visitMultipleCase(MultipleCase ast, Object obj) {
        if(ast.MCC2 == null)
            return(createUnary("Multiple Case ", ast.MCC));
        else
            return(createBinary("Multiple Case ", ast.MCC, ast.MCC2));
    }
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" Type Denoters ">
    // Type Denoters
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
        return(createNullary("any"));
    }
    
    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
        return(createBinary("Array Type Denoter", ast.IL, ast.T));
    }
    
    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
        return(createNullary("bool"));
    }
    
    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
        return(createNullary("char"));
    }
    
    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
        return(createNullary("error"));
    }
    
    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
        return(createUnary("Simple Type Denoter", ast.I));
    }
    
    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
        return(createNullary("int"));
    }
    
    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
        return(createUnary("Record Type Denoter", ast.FT));
    }
    
    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
        return(createTernary("Multiple Field Type Denoter", ast.I, ast.T, ast.FT));
    }
    
    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
        return(createBinary("Single Field Type Denoter", ast.I, ast.T));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Literals, Identifiers and Operators ">
    // Literals, Identifiers and Operators
    public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
        return(createNullary(ast.spelling));
    }
    
    public Object visitIdentifier(Identifier ast, Object obj) {
        return(createNullary(ast.spelling));
    }
    
    public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
        return(createNullary(ast.spelling));
    }
    
    public Object visitOperator(Operator ast, Object obj) {
        return(createNullary(ast.spelling));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Values or Variable Names ">
    // Values or Variable Names
    public Object visitDotVname(DotVname ast, Object obj) {
        return(createBinary("Dot Vname", ast.I, ast.V));
    }
    
    public Object visitSimpleVname(SimpleVname ast, Object obj) {
        return(createUnary("Simple Vname", ast.I));
    }
    
    public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
        return(createBinary("Subscript Vname", ast.V, ast.E));
    }
    
    public Object visitProgram(Program ast, Object obj) {
        return(createUnary("Program", ast.C));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Tree Creation Methods ">
    
    /**
     * Creates a nullary tree node (doesn't have any children).
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @return The tree node.
     */
    public DefaultMutableTreeNode createNullary(String caption) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        
        return(t);
    }
    
    /**
     * Creates an unary tree node.
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @param child1 The first children node.
     * @return The tree node.
     */
    public DefaultMutableTreeNode createUnary(String caption, AST child1) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        
        return(t);
    }
    
    /**
     * Creates a binary tree node.
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @param child1 The first children node.
     * @param child2 The second children node.
     * @return The tree node.
     */
    public DefaultMutableTreeNode createBinary(String caption, AST child1, AST child2) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        
        return(t);
    }
    
    /**
     * Creates a ternary tree node.
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @param child1 The first children node.
     * @param child2 The second children node.
     * @param child3 The third children node.
     * @return The tree node.
     */
    public DefaultMutableTreeNode createTernary(String caption, AST child1, AST child2, AST child3) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        t.add((DefaultMutableTreeNode)child3.visit(this, null));
        
        return(t);        
    }
    
    /**
     * Creates a quaternary tree node.
     * @param caption The tree's caption (text to be shown when the tree is drawn).
     * @param child1 The first children node.
     * @param child2 The second children node.
     * @param child3 The third children node.
     * @param child4 The fourth children node.
     * @return The tree node.
     */
    public DefaultMutableTreeNode createQuaternary(String caption, AST child1, AST child2, AST child3, AST child4) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        t.add((DefaultMutableTreeNode)child3.visit(this, null));
        t.add((DefaultMutableTreeNode)child4.visit(this, null));
        
        return(t);             
    }
//Autores: Kevin Rodriguez, Gabriel Fallas
    public DefaultMutableTreeNode createQuinary(String caption, AST child1, AST child2, AST child3, AST child4, AST child5) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        t.add((DefaultMutableTreeNode)child3.visit(this, null));
        t.add((DefaultMutableTreeNode)child4.visit(this, null));
        t.add((DefaultMutableTreeNode)child5.visit(this, null));
        
        return(t);             
    }
    
    public DefaultMutableTreeNode createSixary(String caption, AST child1, AST child2, AST child3, AST child4, AST child5, AST child6) {
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(caption);
        t.add((DefaultMutableTreeNode)child1.visit(this, null));
        t.add((DefaultMutableTreeNode)child2.visit(this, null));
        t.add((DefaultMutableTreeNode)child3.visit(this, null));
        t.add((DefaultMutableTreeNode)child4.visit(this, null));
        t.add((DefaultMutableTreeNode)child5.visit(this, null));
        t.add((DefaultMutableTreeNode)child6.visit(this, null));
        
        return(t);             
    }
    // </editor-fold>

    // -------------------------------------------- NUEVOS --------------------------------------------
    // Autor : Valeria Chinchilla
    @Override
    public Object visitLoopCommandAST1(LoopCommandAST1 ast, Object o) {
        if(ast.I == null){
            return(createUnary("Loop Command", ast.WhileVar));
        }
        else{
            return(createBinary("Loop Command", ast.I, ast.WhileVar));
        }
    }
    
    // Autor : Valeria Chinchilla
    @Override
    public Object visitForFromCommand(ForFromCommand aThis, Object o) {
        return(createBinary("For From Command", aThis.I, aThis.E));
    }
    
    // Autor : Valeria Chinchilla
    @Override
    public Object visitDoCommandAST(DoCommand aThis, Object o) {
        return(createUnary("Do Command", aThis.C));
    }
    
    // Autor : Kevin Rodriguez
    @Override
    public Object visitThenCommandAST(ThenCommand aThis, Object o) {
        return(createUnary("Then Command", aThis.C));
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitForFromAST1(ForFromAST1 aThis, Object o) {
        if(aThis.I == null){
            return(createTernary("Loop Command", aThis.ForFrom, aThis.E, aThis.Do));
        }
        else{
            return(createQuaternary("Loop Command",aThis.I, aThis.ForFrom, aThis.E, aThis.Do));
        }
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitLoopUntilDoAST(LoopUntilDoAST aThis, Object o) {
        if(aThis.I == null){
            return(createUnary("Loop Command", aThis.UntilVar));
        }
        else{
            return(createBinary("Loop Command", aThis.I, aThis.UntilVar));
        }
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitUntilCommand(UntilCommand aThis, Object o) {
        return(createBinary("Until Command", aThis.I, aThis.C));
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitWhileEndCommand(WhileEndCommand aThis, Object o) {
        return(createUnary("While Command", aThis.E));
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitLooopWhileEndCommand(LoopWhileEndAST aThis, Object o) {
        if(aThis.I == null){
            return(createBinary("Loop Command", aThis.C, aThis.WhileV));
        }
        else{
            return(createTernary("Loop Command", aThis.I, aThis.C, aThis.WhileV));
        }
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitUntilEndCommand(UntilEndCommand aThis, Object o) {
        return(createUnary("Until Command", aThis.E));
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitLooopUntilEndCommand(LoopUntilEndAST aThis, Object o) {
        if(aThis.I == null){
            return(createBinary("Loop Command", aThis.C, aThis.UntilEnd));
        }
        else{
            return(createTernary("Loop Command",aThis.I , aThis.C, aThis.UntilEnd));
        }
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitForFromWhile(LoopForFromWhile aThis, Object o) {
        if(aThis.I == null){
            return(createTernary("Loop Command",aThis.ForFrom, aThis.E, aThis.whileV));
        }
        else{
            return(createQuaternary("Loop Command",aThis.I, aThis.ForFrom, aThis.E, aThis.whileV));
        }
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitForFromUntil(LoopForFromUntil aThis, Object o) {
        if(aThis.I == null){
            return(createTernary("Loop Command",aThis.ForFrom, aThis.E, aThis.untilV));
        }
        else{
            return(createQuaternary("Loop Command",aThis.I, aThis.ForFrom, aThis.E, aThis.untilV));
        }
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitForInCommand(ForInCommand aThis, Object o) {
        return(createBinary("For In Command", aThis.I, aThis.E));
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitForInDoCommand(ForInDo aThis, Object o) {
        if(aThis.I == null){
            return(createBinary("Loop Command", aThis.forAST, aThis.C));
        }
        else{
            return(createTernary("Loop Command", aThis.I, aThis.forAST, aThis.C));
        }
    }

    // Autor : Valeria Chinchilla
    @Override
    public Object visitToCommandAST(ToCommand aThis, Object o) {
        return(createUnary("To Command", aThis.E));
    }
    
    // Autor : Valeria Chinchilla
    @Override
    public Object visitVarDeclarationInit(VarDeclarationInit aThis, Object o) {
        return(createBinary("Variable Declaration", aThis.I, aThis.E));
    }

    @Override
    public Object visitLeaveIdentifier(LeaveIdentifier aThis, Object o) {
        if(aThis.I != null){
            return(createUnary("Leave Command", aThis.I));
        }
        return(createNullary("Leave Command"));
    }

    @Override
    public Object visitNextIdentifier(NextIdentifier aThis, Object o) {
        if(aThis.I != null){
            return(createUnary("Next Command", aThis.I));
        }
        return(createNullary("Next Command"));
    }

    public Object visitReturnCommand(ReturnCommand aThis, Object o) {
        return(createNullary("Return Command"));
    }
}
