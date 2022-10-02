/*
 * @(#)Visitor.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.AbstractSyntaxTrees;

public interface Visitor {

  // Commands
  public abstract Object visitAssignCommand(AssignCommand ast, Object o);
  public abstract Object visitCallCommand(CallCommand ast, Object o);
  public abstract Object visitEmptyCommand(EmptyCommand ast, Object o);
  public abstract Object visitIfCommand(IfCommand ast, Object o);
  public abstract Object visitLetCommand(LetCommand ast, Object o);
  public abstract Object visitSequentialCommand(SequentialCommand ast, Object o);
  public abstract Object visitWhileCommand(WhileCommand ast, Object o);
  public abstract Object visitLoopCommandAST1(LoopCommandAST1 aThis, Object o);
  public abstract Object visitForFromCommand(ForFromCommand aThis, Object o);
  public abstract Object visitDoCommandAST(DoCommand aThis, Object o);
  public abstract Object visitForFromAST1(ForFromAST1 aThis, Object o);
  public abstract Object visitLoopUntilDoAST(LoopUntilDoAST aThis, Object o);
  public abstract Object visitUntilCommand(UntilCommand aThis, Object o);
  public abstract Object visitWhileEndCommand(WhileEndCommand aThis, Object o);
  public abstract Object visitLooopWhileEndCommand(LoopWhileEndAST aThis, Object o);
  public abstract Object visitUntilEndCommand(UntilEndCommand aThis, Object o);
  public abstract Object visitLooopUntilEndCommand(LoopUntilEndAST aThis, Object o);
  public abstract Object visitForFromWhile(LoopForFromWhile aThis, Object o);
  public abstract Object visitForFromUntil(LoopForFromUntil aThis, Object o);
  public abstract Object visitForInCommand(ForInCommand aThis, Object o);
  public abstract Object visitForInDoCommand(ForInDo aThis, Object o);
  public abstract Object visitToCommandAST(ToCommand aThis, Object o);
  public abstract Object visitCaseLiteralCommand(CaseLiteralCommand aThis, Object o);
  public abstract Object visitCaseRangeCommand(CaseRangeCommand aThis, Object o);
  public abstract Object visitToCommandLiteralAST(ToCommandLiteral aThis, Object o);
  public abstract Object visitCaseLiterals(CaseLiterals aThis, Object o);
  public abstract Object visitCaseCommand(CaseCommand aThis, Object o);
  public abstract Object visitCasesCommand(CasesCommand aThis, Object o);
  
  // Expressions
  public abstract Object visitArrayExpression(ArrayExpression ast, Object o);
  public abstract Object visitBinaryExpression(BinaryExpression ast, Object o);
  public abstract Object visitCallExpression(CallExpression ast, Object o);
  public abstract Object visitCharacterExpression(CharacterExpression ast, Object o);
  public abstract Object visitEmptyExpression(EmptyExpression ast, Object o);
  public abstract Object visitIfExpression(IfExpression ast, Object o);
  public abstract Object visitIntegerExpression(IntegerExpression ast, Object o);
  public abstract Object visitLetExpression(LetExpression ast, Object o);
  public abstract Object visitRecordExpression(RecordExpression ast, Object o);
  public abstract Object visitUnaryExpression(UnaryExpression ast, Object o);
  public abstract Object visitVnameExpression(VnameExpression ast, Object o);
  
  // Declarations
  public abstract Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o);
  public abstract Object visitConstDeclaration(ConstDeclaration ast, Object o);
  public abstract Object visitFuncDeclaration(FuncDeclaration ast, Object o);
  public abstract Object visitProcDeclaration(ProcDeclaration ast, Object o);
  public abstract Object visitSequentialDeclaration(SequentialDeclaration ast, Object o);
  public abstract Object visitTypeDeclaration(TypeDeclaration ast, Object o);
  public abstract Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o);
  public abstract Object visitVarDeclaration(VarDeclaration ast, Object o);
  public abstract Object visitLocalDeclaration(LocalDeclaration aThis, Object o);

  // Array Aggregates
  public abstract Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o);
  public abstract Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o);

  // Record Aggregates
  public abstract Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o);
  public abstract Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o);

  // Formal Parameters
  public abstract Object visitConstFormalParameter(ConstFormalParameter ast, Object o);
  public abstract Object visitFuncFormalParameter(FuncFormalParameter ast, Object o);
  public abstract Object visitProcFormalParameter(ProcFormalParameter ast, Object o);
  public abstract Object visitVarFormalParameter(VarFormalParameter ast, Object o);

  public abstract Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o);
  public abstract Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o);
  public abstract Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o);

  // Actual Parameters
  public abstract Object visitConstActualParameter(ConstActualParameter ast, Object o);
  public abstract Object visitFuncActualParameter(FuncActualParameter ast, Object o);
  public abstract Object visitProcActualParameter(ProcActualParameter ast, Object o);
  public abstract Object visitVarActualParameter(VarActualParameter ast, Object o);

  public abstract Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o);
  public abstract Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o);
  public abstract Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o);

  // Type Denoters
  public abstract Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o);
  public abstract Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o);
  public abstract Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o);
  public abstract Object visitCharTypeDenoter(CharTypeDenoter ast, Object o);
  public abstract Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o);
  public abstract Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o);
  public abstract Object visitIntTypeDenoter(IntTypeDenoter ast, Object o);
  public abstract Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o);

  public abstract Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o);
  public abstract Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o);

  // Literals, Identifiers and Operators
  public abstract Object visitCharacterLiteral(CharacterLiteral ast, Object o);
  public abstract Object visitIdentifier(Identifier ast, Object o);
  public abstract Object visitIntegerLiteral(IntegerLiteral ast, Object o);
  public abstract Object visitOperator(Operator ast, Object o);

  // Value-or-variable names
  public abstract Object visitDotVname(DotVname ast, Object o);
  public abstract Object visitSimpleVname(SimpleVname ast, Object o);
  public abstract Object visitSubscriptVname(SubscriptVname ast, Object o);

  // Programs
  public abstract Object visitProgram(Program ast, Object o);

    public Object visitVarDeclarationInit(VarDeclarationInit aThis, Object o);

    public Object visitBarCommandCaseRange(BarCommandCaseRange aThis, Object o);

    public Object visitSingleCaseRange(SingleCaseRange aThis, Object o);

    public Object visitMultipleCaseRange(MultipleCaseRange aThis, Object o);

    public Object visitSingleCase(SingleCase aThis, Object o);

    public Object visitMultipleCase(MultipleCase aThis, Object o);

    public Object visitSequentialCases(SequentialCases aThis, Object o);

    public Object visitSelectCommand(SelectCommand aThis, Object O);

    public Object visitThenCommandAST(ThenCommand aThis, Object o);

    public Object visitSingleThen(SingleThen aThis, Object o);

    public Object visitMultipleThen(MultipleThen aThis, Object o);

    public Object visitLeaveIdentifier(LeaveIdentifier aThis, Object o);

    public Object visitNextIdentifier(NextIdentifier aThis, Object o);

    public Object visitReturnCommand(ReturnCommand aThis, Object o);

}
