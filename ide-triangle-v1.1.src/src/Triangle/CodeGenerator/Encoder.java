/*
 * @(#)Encoder.java                        2.1 2003/10/07
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

package Triangle.CodeGenerator;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import TAM.Instruction;
import TAM.Machine;
import Triangle.ErrorReporter;
import Triangle.StdEnvironment;
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
import Triangle.AbstractSyntaxTrees.CompoundSingleDeclaration;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.Declaration;
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
import Triangle.AbstractSyntaxTrees.RecDeclaration;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.ReturnCommand;
import Triangle.AbstractSyntaxTrees.SelectCommand;
import Triangle.AbstractSyntaxTrees.SequentialCases;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialDeclarationProcFuncs;
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
import Triangle.AbstractSyntaxTrees.Vname;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;
import Triangle.AbstractSyntaxTrees.WhileEndCommand;

public final class Encoder implements Visitor {

  // Commands
  @Override
  public Object visitAssignCommand(AssignCommand ast, Object o) {
    Frame frame = (Frame) o;
    Integer valSize = (Integer) ast.E.visit(this, frame);
    encodeStore(ast.V, new Frame(frame, valSize), valSize);
    return null;
  }

  @Override
  public Object visitCallCommand(CallCommand ast, Object o) {
    Frame frame = (Frame) o;
    Integer argsSize = (Integer) ast.APS.visit(this, frame);
    ast.I.visit(this, new Frame(frame.level, argsSize));
    return null;
  }

  @Override
  public Object visitEmptyCommand(EmptyCommand ast, Object o) {
    return 0;
  }

  @Override
  public Object visitIfCommand(IfCommand ast, Object o) {
    Frame frame = (Frame) o;
    int jumpifAddr, jumpAddr;
    ast.E.visit(this, frame);
    jumpifAddr = nextInstrAddr;
    emit(Machine.JUMPIFop, Machine.falseRep, Machine.CBr, 0);
    ast.C1.visit(this, frame);
    jumpAddr = nextInstrAddr;
    emit(Machine.JUMPop, 0, Machine.CBr, 0);
    patch(jumpifAddr, nextInstrAddr);
    ast.C2.visit(this, frame);
    patch(jumpAddr, nextInstrAddr);
    return null;
  }

  @Override
  public Object visitLetCommand(LetCommand ast, Object o) {
    Frame frame = (Frame) o;
    int extraSize = ((Integer) ast.D.visit(this, frame));
    ast.C.visit(this, new Frame(frame, extraSize));
    if (extraSize > 0)
      emit(Machine.POPop, 0, 0, extraSize);
    return null;
  }

    @Override
  public Object visitSequentialCommand(SequentialCommand ast, Object o) {
    ast.C1.visit(this, o);
    ast.C2.visit(this, o);
    return null;
  }

  @Override
  public Object visitSequentialCases(SequentialCases ast, Object o) {
    ast.C1.visit(this, o);
    ast.C2.visit(this, o);
    return null;
  }

  @Override
  public Object visitWhileCommand(WhileCommand ast, Object o) {
    Frame frame = (Frame) o;
    int jumpAddr, loopAddr;

    jumpAddr = nextInstrAddr;
    emit(Machine.JUMPop, 0, Machine.CBr, 0);
    loopAddr = nextInstrAddr;
    ast.C.visit(this, frame);
    patch(jumpAddr, nextInstrAddr);
    ast.E.visit(this, frame);
    emit(Machine.JUMPIFop, Machine.trueRep, Machine.CBr, loopAddr);
    return null;
  }

  // Se dejo declarado el CaseLiteralCommand para los siguientes proyectos.
  // Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  @Override
  public Object visitCaseLiteralCommand(CaseLiteralCommand ast, Object O) {
    Frame frame = (Frame) O;
    //verifica si es un IL o un CL
    if(ast.CL != null){
        //es caracter
        emit(Machine.LOADLop, 0, 0, ast.CL.spelling.charAt(1));
        //emit();
    }else{
        //es entero
        emit(Machine.LOADLop, 0, 0, Integer.parseInt(ast.IL.spelling));
    }
    return 1;
  }

  // Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  @Override
  public Object visitCaseRangeCommand(CaseRangeCommand ast, Object O) {
    Frame frame = (Frame) O;
    int size,jmpaddr;
    //decide si es rango o no
    if(ast.TC == null){
        //no es rango
        jmpaddr = nextInstrAddr;
        //se duplica el tope de la pila
        emit(Machine.LOADop, 1, Machine.STr, -1);
        //se carga la literal
        size = (int) ast.CLC.visit(this, frame);
        emit(Machine.LOADLop, 0, 0, size);
        //se comparan
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.eqDisplacement);
        //si son diferente se hace el jump a lo que sigue
        emit(Machine.JUMPIFop, Machine.falseRep, Machine.CBr, nextInstrAddr);
    }else{
        //es rango
        jmpaddr = nextInstrAddr;
        //se duplica el tope
        emit(Machine.LOADop, 1, Machine.STr, -1);
        //se carga la primera literal
        size = (int) ast.CLC.visit(this, frame);
        //Se verifica que sea mayor o igual 
        emit(Machine.CALLop, 0, 0, Machine.geDisplacement);
        //salta a lo que sigue si no se cumple
        emit(Machine.JUMPIFop, Machine.falseRep, Machine.CBr, nextInstrAddr);
        //se vuelve a duplicar el tope
        emit(Machine.LOADop, 1, Machine.STr, -1);
        //se carga la segunda literal
        size = size + (int) ast.TC.visit(this, frame);
        //se compara que sea menor o igual
        emit(Machine.CALLop, 0, 0, Machine.leDisplacement);
        //salta a lo que sigue si no se cumple
        emit(Machine.JUMPIFop, Machine.falseRep, Machine.CBr, nextInstrAddr);
    }
    //lo que le sigue a esto es el comando que se visita en el visitCaseCommand
    return 1;
  }

  // Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  @Override
  public Object visitToCommandLiteralAST(ToCommandLiteral ast, Object obj) {
    return null;
  }

  // Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  @Override
  public Object visitBarCommandCaseRange(BarCommandCaseRange ast, Object obj) {
    return null;
  }

  // Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  @Override
  public Object visitCaseLiterals(CaseLiterals ast, Object obj) {
    Frame frame = (Frame) obj;
    int size;
    //Se decide si son varias literales o una sola
    if(ast.SCRCL != null){
        //Es una sola
        size = (int) ast.SCRCL.visit(this, frame);
    }else{
        //son varias
        size = (int) ast.MCRCL.visit(this, frame);
    }
    return size;
  }

  @Override
  public Object visitCaseCommand(CaseCommand ast, Object obj) {
    Frame frame = (Frame) obj;
    // Visita el case literals
    int size = (int) ast.CL.visit(this, frame);
    int jmpAddr = nextInstrAddr;
    //Se visita el comando
    ast.C.visit(this, frame);
    //patch(jmpAddr-1, nextInstrAddr);
      System.out.println(jmpAddr-1);
    return size;
  }

   
    @Override
  public Object visitCasesCommand(CasesCommand ast, Object obj) {
    Frame frame = (Frame) obj;
    int size = 0;
    if(ast.singleCase != null){
        size = (int) ast.singleCase.visit(this, frame);
    }else if(ast.multipleCase != null){
        size = (int) ast.multipleCase.visit(this, frame);
    }
    return size;
  }

  @Override
  public Object visitSelectCommand(SelectCommand ast, Object obj) {
    Frame frame = (Frame) obj;
    /*// se evalua la expresion selectora
    int expression = (Integer) ast.expression.visit(this, frame);
    int jmpAddr = nextInstrAddr;
    //se evaluan los cases
    int cases = (Integer) ast.cases.visit(this, frame);
    //Se hace un patch para el jump del else
    jmpAddr = nextInstrAddr;
    emit(Machine.JUMPop, 0, Machine.CBr, jmpAddr);
    patch(jmpAddr-1, nextInstrAddr);
    //Se revisa si hay un else
    if(ast.command != null){
        ast.command.visit(this, frame);
    }else{
        emit(Machine.CASEerror, 0, 0, 0);
    }
    patch(jmpAddr, nextInstrAddr);
    emit(Machine.POPop, 0, 0, expression);*/
    return null;
  }

  @Override
  public Object visitSingleCaseRange(SingleCaseRange ast, Object obj) {
    Frame frame = (Frame) obj;
    int size;
    //visita el case range
    size = (int) ast.CRCSCR.visit(this, frame);
    return size;
  }

    @Override
  public Object visitSingleThen(SingleThen ast, Object obj) {
    return null;
  }
    @Override
  public Object visitSingleCase(SingleCase ast, Object obj) {
    Frame frame = (Frame) obj;
    return (int) ast.SC.visit(this, frame);
  }

  @Override
  public Object visitMultipleCaseRange(MultipleCaseRange ast, Object obj) {
    Frame frame = (Frame) obj;
    int size;
    //verifica si es el ultimo o hay mas
    if(ast.CRCMCR2 == null){
        //es el ultimo
        size = (int) ast.CRCMCR.visit(this, frame);
    }else{
        size = (int) ast.CRCMCR.visit(this, frame);
        size = size + (int) ast.CRCMCR2.visit(this, frame);
    }
    return 1;
  }

  @Override
  public Object visitMultipleThen(MultipleThen ast, Object obj) {
    return null;
  }

  @Override
  public Object visitMultipleCase(MultipleCase ast, Object obj) {
    Frame frame = (Frame) obj;
    int size;
    //revisa si hay mas
    if(ast.MCC2 == null){
        //no hay
        size = (int) ast.MCC.visit(this, frame);
    }else{
        size = (int) ast.MCC.visit(this, frame);
        size = size + (int) ast.MCC2.visit(this, frame);
    }
    return size;
  }

  // Expressions
  @Override
  public Object visitArrayExpression(ArrayExpression ast, Object o) {
    ast.type.visit(this, null);
    return ast.AA.visit(this, o);
  }

  @Override
  public Object visitBinaryExpression(BinaryExpression ast, Object o) {
    Frame frame = (Frame) o;
    Integer valSize = (Integer) ast.type.visit(this, null);
    int valSize1 = ((Integer) ast.E1.visit(this, frame));
    Frame frame1 = new Frame(frame, valSize1);
    int valSize2 = ((Integer) ast.E2.visit(this, frame1));
    Frame frame2 = new Frame(frame.level, valSize1 + valSize2);
    ast.O.visit(this, frame2);
    return valSize;
  }

  @Override
  public Object visitCallExpression(CallExpression ast, Object o) {
    Frame frame = (Frame) o;
    Integer valSize = (Integer) ast.type.visit(this, null);
    Integer argsSize = (Integer) ast.APS.visit(this, frame);
    ast.I.visit(this, new Frame(frame.level, argsSize));
    return valSize;
  }

  @Override
  public Object visitCharacterExpression(CharacterExpression ast,
      Object o) {
    Frame frame = (Frame) o;
    Integer valSize = (Integer) ast.type.visit(this, null);
    emit(Machine.LOADLop, 0, 0, ast.CL.spelling.charAt(1));
    return valSize;
  }

  public Object visitEmptyExpression(EmptyExpression ast, Object o) {
    return new Integer(0);
  }

  public Object visitIfExpression(IfExpression ast, Object o) {
    Frame frame = (Frame) o;
    Integer valSize;
    int jumpifAddr, jumpAddr;

    ast.type.visit(this, null);
    ast.E1.visit(this, frame);
    jumpifAddr = nextInstrAddr;
    emit(Machine.JUMPIFop, Machine.falseRep, Machine.CBr, 0);
    valSize = (Integer) ast.E2.visit(this, frame);
    jumpAddr = nextInstrAddr;
    emit(Machine.JUMPop, 0, Machine.CBr, 0);
    patch(jumpifAddr, nextInstrAddr);
    valSize = (Integer) ast.E3.visit(this, frame);
    patch(jumpAddr, nextInstrAddr);
    return valSize;
  }

  public Object visitIntegerExpression(IntegerExpression ast, Object o) {
    Frame frame = (Frame) o;
    Integer valSize = (Integer) ast.type.visit(this, null);
    emit(Machine.LOADLop, 0, 0, Integer.parseInt(ast.IL.spelling));
    return valSize;
  }

  public Object visitLetExpression(LetExpression ast, Object o) {
    Frame frame = (Frame) o;
    ast.type.visit(this, null);
    int extraSize = ((Integer) ast.D.visit(this, frame)).intValue();
    Frame frame1 = new Frame(frame, extraSize);
    Integer valSize = (Integer) ast.E.visit(this, frame1);
    if (extraSize > 0)
      emit(Machine.POPop, valSize.intValue(), 0, extraSize);
    return valSize;
  }

  public Object visitRecordExpression(RecordExpression ast, Object o) {
    ast.type.visit(this, null);
    return ast.RA.visit(this, o);
  }

  public Object visitUnaryExpression(UnaryExpression ast, Object o) {
    Frame frame = (Frame) o;
    Integer valSize = (Integer) ast.type.visit(this, null);
    ast.E.visit(this, frame);
    ast.O.visit(this, new Frame(frame.level, valSize.intValue()));
    return valSize;
  }

  public Object visitVnameExpression(VnameExpression ast, Object o) {
    Frame frame = (Frame) o;
    Integer valSize = (Integer) ast.type.visit(this, null);
    encodeFetch(ast.V, frame, valSize);
    return valSize;
  }

  // Declarations
  public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast,
      Object o) {
    return new Integer(0);
  }

  public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
    Frame frame = (Frame) o;
    int extraSize = 0;

    if (ast.E instanceof CharacterExpression) {
      CharacterLiteral CL = ((CharacterExpression) ast.E).CL;
      ast.entity = new KnownValue(Machine.characterSize,
          characterValuation(CL.spelling));
    } else if (ast.E instanceof IntegerExpression) {
      IntegerLiteral IL = ((IntegerExpression) ast.E).IL;
      ast.entity = new KnownValue(Machine.integerSize,
          Integer.parseInt(IL.spelling));
    } else {
      int valSize = ((Integer) ast.E.visit(this, frame)).intValue();
      ast.entity = new UnknownValue(valSize, frame.level, frame.size);
      extraSize = valSize;
    }
    writeTableDetails(ast);
    return new Integer(extraSize);
  }

  public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
    Frame frame = (Frame) o;
    int jumpAddr = nextInstrAddr;
    int argsSize = 0, valSize = 0;

    emit(Machine.JUMPop, 0, Machine.CBr, 0);
    ast.entity = new KnownRoutine(Machine.closureSize, frame.level, nextInstrAddr);
    writeTableDetails(ast);
    if (frame.level == Machine.maxRoutineLevel)
      reporter.reportRestriction("can't nest routines more than 7 deep");
    else {
      Frame frame1 = new Frame(frame.level + 1, 0);
      argsSize = ((Integer) ast.FPS.visit(this, frame1)).intValue();
      Frame frame2 = new Frame(frame.level + 1, Machine.linkDataSize);
      valSize = ((Integer) ast.E.visit(this, frame2)).intValue();
    }
    emit(Machine.RETURNop, valSize, 0, argsSize);
    patch(jumpAddr, nextInstrAddr);
    return new Integer(0);
  }

  public Object visitProcDeclaration(ProcDeclaration ast, Object o) {
    Frame frame = (Frame) o;
    int jumpAddr = nextInstrAddr;
    int argsSize = 0;

    emit(Machine.JUMPop, 0, Machine.CBr, 0);
    ast.entity = new KnownRoutine(Machine.closureSize, frame.level,
        nextInstrAddr);
    writeTableDetails(ast);
    if (frame.level == Machine.maxRoutineLevel)
      reporter.reportRestriction("can't nest routines so deeply");
    else {
      Frame frame1 = new Frame(frame.level + 1, 0);
      argsSize = ((Integer) ast.FPS.visit(this, frame1)).intValue();
      Frame frame2 = new Frame(frame.level + 1, Machine.linkDataSize);
      ast.C.visit(this, frame2);
    }
    emit(Machine.RETURNop, 0, 0, argsSize);
    patch(jumpAddr, nextInstrAddr);
    return new Integer(0);
  }

  public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
    Frame frame = (Frame) o;
    int extraSize1, extraSize2;

    extraSize1 = ((Integer) ast.D1.visit(this, frame)).intValue();
    Frame frame1 = new Frame(frame, extraSize1);
    extraSize2 = ((Integer) ast.D2.visit(this, frame1)).intValue();
    return new Integer(extraSize1 + extraSize2);
  }

  public Object visitLocalDeclaration(LocalDeclaration ast, Object o) {
    Frame frame = (Frame) o;
    int extraSize1, extraSize2;

    extraSize1 = ((Integer) ast.D1.visit(this, frame)).intValue();
    Frame frame1 = new Frame(frame, extraSize1);
    extraSize2 = ((Integer) ast.D2.visit(this, frame1)).intValue();
    return new Integer(extraSize1 + extraSize2);
  }

  public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
    // just to ensure the type's representation is decided
    ast.T.visit(this, null);
    return new Integer(0);
  }

  public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast,
      Object o) {
    return new Integer(0);
  }

  public Object visitVarDeclaration(VarDeclaration ast, Object o) {
    Frame frame = (Frame) o;
    int extraSize;

    extraSize = ((Integer) ast.T.visit(this, null)).intValue();
    emit(Machine.PUSHop, 0, 0, extraSize);
    ast.entity = new KnownAddress(Machine.addressSize, frame.level, frame.size);
    writeTableDetails(ast);
    return new Integer(extraSize);
  }

  // Array Aggregates
  public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast,
      Object o) {
    Frame frame = (Frame) o;
    int elemSize = ((Integer) ast.E.visit(this, frame)).intValue();
    Frame frame1 = new Frame(frame, elemSize);
    int arraySize = ((Integer) ast.AA.visit(this, frame1)).intValue();
    return new Integer(elemSize + arraySize);
  }

  public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {
    return ast.E.visit(this, o);
  }

  // Record Aggregates
  public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast,
      Object o) {
    Frame frame = (Frame) o;
    int fieldSize = ((Integer) ast.E.visit(this, frame)).intValue();
    Frame frame1 = new Frame(frame, fieldSize);
    int recordSize = ((Integer) ast.RA.visit(this, frame1)).intValue();
    return new Integer(fieldSize + recordSize);
  }

  public Object visitSingleRecordAggregate(SingleRecordAggregate ast,
      Object o) {
    return ast.E.visit(this, o);
  }

  // Formal Parameters
  public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
    Frame frame = (Frame) o;
    int valSize = ((Integer) ast.T.visit(this, null)).intValue();
    ast.entity = new UnknownValue(valSize, frame.level, -frame.size - valSize);
    writeTableDetails(ast);
    return new Integer(valSize);
  }

  public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
    Frame frame = (Frame) o;
    int argsSize = Machine.closureSize;
    ast.entity = new UnknownRoutine(Machine.closureSize, frame.level,
        -frame.size - argsSize);
    writeTableDetails(ast);
    return new Integer(argsSize);
  }

  public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
    Frame frame = (Frame) o;
    int argsSize = Machine.closureSize;
    ast.entity = new UnknownRoutine(Machine.closureSize, frame.level,
        -frame.size - argsSize);
    writeTableDetails(ast);
    return new Integer(argsSize);
  }

  public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
    Frame frame = (Frame) o;
    ast.T.visit(this, null);
    ast.entity = new UnknownAddress(Machine.addressSize, frame.level,
        -frame.size - Machine.addressSize);
    writeTableDetails(ast);
    return new Integer(Machine.addressSize);
  }

  public Object visitEmptyFormalParameterSequence(
      EmptyFormalParameterSequence ast, Object o) {
    return new Integer(0);
  }

  public Object visitMultipleFormalParameterSequence(
      MultipleFormalParameterSequence ast, Object o) {
    Frame frame = (Frame) o;
    int argsSize1 = ((Integer) ast.FPS.visit(this, frame)).intValue();
    Frame frame1 = new Frame(frame, argsSize1);
    int argsSize2 = ((Integer) ast.FP.visit(this, frame1)).intValue();
    return new Integer(argsSize1 + argsSize2);
  }

  public Object visitSingleFormalParameterSequence(
      SingleFormalParameterSequence ast, Object o) {
    return ast.FP.visit(this, o);
  }

  // Actual Parameters
  public Object visitConstActualParameter(ConstActualParameter ast, Object o) {
    return ast.E.visit(this, o);
  }

  public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {
    Frame frame = (Frame) o;
    if (ast.I.decl.entity instanceof KnownRoutine) {
      ObjectAddress address = ((KnownRoutine) ast.I.decl.entity).address;
      // static link, code address
      emit(Machine.LOADAop, 0, displayRegister(frame.level, address.level), 0);
      emit(Machine.LOADAop, 0, Machine.CBr, address.displacement);
    } else if (ast.I.decl.entity instanceof UnknownRoutine) {
      ObjectAddress address = ((UnknownRoutine) ast.I.decl.entity).address;
      emit(Machine.LOADop, Machine.closureSize, displayRegister(frame.level,
          address.level), address.displacement);
    } else if (ast.I.decl.entity instanceof PrimitiveRoutine) {
      int displacement = ((PrimitiveRoutine) ast.I.decl.entity).displacement;
      // static link, code address
      emit(Machine.LOADAop, 0, Machine.SBr, 0);
      emit(Machine.LOADAop, 0, Machine.PBr, displacement);
    }
    return new Integer(Machine.closureSize);
  }

  public Object visitProcActualParameter(ProcActualParameter ast, Object o) {
    Frame frame = (Frame) o;
    if (ast.I.decl.entity instanceof KnownRoutine) {
      ObjectAddress address = ((KnownRoutine) ast.I.decl.entity).address;
      // static link, code address
      emit(Machine.LOADAop, 0, displayRegister(frame.level, address.level), 0);
      emit(Machine.LOADAop, 0, Machine.CBr, address.displacement);
    } else if (ast.I.decl.entity instanceof UnknownRoutine) {
      ObjectAddress address = ((UnknownRoutine) ast.I.decl.entity).address;
      emit(Machine.LOADop, Machine.closureSize, displayRegister(frame.level,
          address.level), address.displacement);
    } else if (ast.I.decl.entity instanceof PrimitiveRoutine) {
      int displacement = ((PrimitiveRoutine) ast.I.decl.entity).displacement;
      // static link, code address
      emit(Machine.LOADAop, 0, Machine.SBr, 0);
      emit(Machine.LOADAop, 0, Machine.PBr, displacement);
    }
    return new Integer(Machine.closureSize);
  }

  public Object visitVarActualParameter(VarActualParameter ast, Object o) {
    encodeFetchAddress(ast.V, (Frame) o);
    return new Integer(Machine.addressSize);
  }

  public Object visitEmptyActualParameterSequence(
      EmptyActualParameterSequence ast, Object o) {
    return new Integer(0);
  }

  public Object visitMultipleActualParameterSequence(
      MultipleActualParameterSequence ast, Object o) {
    Frame frame = (Frame) o;
    int argsSize1 = ((Integer) ast.AP.visit(this, frame)).intValue();
    Frame frame1 = new Frame(frame, argsSize1);
    int argsSize2 = ((Integer) ast.APS.visit(this, frame1)).intValue();
    return new Integer(argsSize1 + argsSize2);
  }

  public Object visitSingleActualParameterSequence(
      SingleActualParameterSequence ast, Object o) {
    return ast.AP.visit(this, o);
  }

  // Type Denoters
  public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
    return new Integer(0);
  }

  public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
    int typeSize;
    if (ast.entity == null) {
      int elemSize = ((Integer) ast.T.visit(this, null)).intValue();
      typeSize = Integer.parseInt(ast.IL.spelling) * elemSize;
      ast.entity = new TypeRepresentation(typeSize);
      writeTableDetails(ast);
    } else
      typeSize = ast.entity.size;
    return new Integer(typeSize);
  }

  public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
    if (ast.entity == null) {
      ast.entity = new TypeRepresentation(Machine.booleanSize);
      writeTableDetails(ast);
    }
    return new Integer(Machine.booleanSize);
  }

  public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
    if (ast.entity == null) {
      ast.entity = new TypeRepresentation(Machine.characterSize);
      writeTableDetails(ast);
    }
    return new Integer(Machine.characterSize);
  }

  public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
    return new Integer(0);
  }

  public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast,
      Object o) {
    return new Integer(0);
  }

  public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {
    if (ast.entity == null) {
      ast.entity = new TypeRepresentation(Machine.integerSize);
      writeTableDetails(ast);
    }
    return new Integer(Machine.integerSize);
  }

  public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {
    int typeSize;
    if (ast.entity == null) {
      typeSize = ((Integer) ast.FT.visit(this, new Integer(0))).intValue();
      ast.entity = new TypeRepresentation(typeSize);
      writeTableDetails(ast);
    } else
      typeSize = ast.entity.size;
    return new Integer(typeSize);
  }

  public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast,
      Object o) {
    int offset = ((Integer) o).intValue();
    int fieldSize;

    if (ast.entity == null) {
      fieldSize = ((Integer) ast.T.visit(this, null)).intValue();
      ast.entity = new Field(fieldSize, offset);
      writeTableDetails(ast);
    } else
      fieldSize = ast.entity.size;

    Integer offset1 = new Integer(offset + fieldSize);
    int recSize = ((Integer) ast.FT.visit(this, offset1)).intValue();
    return new Integer(fieldSize + recSize);
  }

  public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast,
      Object o) {
    int offset = ((Integer) o).intValue();
    int fieldSize;

    if (ast.entity == null) {
      fieldSize = ((Integer) ast.T.visit(this, null)).intValue();
      ast.entity = new Field(fieldSize, offset);
      writeTableDetails(ast);
    } else
      fieldSize = ast.entity.size;

    return new Integer(fieldSize);
  }

  // Literals, Identifiers and Operators
  public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {
    return null;
  }

  public Object visitIdentifier(Identifier ast, Object o) {
    Frame frame = (Frame) o;
    if (ast.decl.entity == null) { //para la primera pasada del rec
        emit(Machine.CALLop, 0, Machine.CBr, 0);
    } else if (ast.decl.entity instanceof KnownRoutine) {
      ObjectAddress address = ((KnownRoutine) ast.decl.entity).address;
      emit(Machine.CALLop, displayRegister(frame.level, address.level),
          Machine.CBr, address.displacement);
    } else if (ast.decl.entity instanceof UnknownRoutine) {
      ObjectAddress address = ((UnknownRoutine) ast.decl.entity).address;
      emit(Machine.LOADop, Machine.closureSize, displayRegister(frame.level,
          address.level), address.displacement);
      emit(Machine.CALLIop, 0, 0, 0);
    } else if (ast.decl.entity instanceof PrimitiveRoutine) {
      int displacement = ((PrimitiveRoutine) ast.decl.entity).displacement;
      if (displacement != Machine.idDisplacement)
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, displacement);
    } else if (ast.decl.entity instanceof EqualityRoutine) { // "=" or "\="
      int displacement = ((EqualityRoutine) ast.decl.entity).displacement;
      emit(Machine.LOADLop, 0, 0, frame.size / 2);
      emit(Machine.CALLop, Machine.SBr, Machine.PBr, displacement);
    } 
    return null;
  }

  public Object visitIntegerLiteral(IntegerLiteral ast, Object o) {
    return null;
  }

  public Object visitOperator(Operator ast, Object o) {
    Frame frame = (Frame) o;
    if (ast.decl.entity instanceof KnownRoutine) {
      ObjectAddress address = ((KnownRoutine) ast.decl.entity).address;
      emit(Machine.CALLop, displayRegister(frame.level, address.level),
          Machine.CBr, address.displacement);
    } else if (ast.decl.entity instanceof UnknownRoutine) {
      ObjectAddress address = ((UnknownRoutine) ast.decl.entity).address;
      emit(Machine.LOADop, Machine.closureSize, displayRegister(frame.level,
          address.level), address.displacement);
      emit(Machine.CALLIop, 0, 0, 0);
    } else if (ast.decl.entity instanceof PrimitiveRoutine) {
      int displacement = ((PrimitiveRoutine) ast.decl.entity).displacement;
      if (displacement != Machine.idDisplacement)
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, displacement);
    } else if (ast.decl.entity instanceof EqualityRoutine) { // "=" or "\="
      int displacement = ((EqualityRoutine) ast.decl.entity).displacement;
      emit(Machine.LOADLop, 0, 0, frame.size / 2);
      emit(Machine.CALLop, Machine.SBr, Machine.PBr, displacement);
    }
    return null;
  }

  // Value-or-variable names
  public Object visitDotVname(DotVname ast, Object o) {
    Frame frame = (Frame) o;
    RuntimeEntity baseObject = (RuntimeEntity) ast.V.visit(this, frame);
    ast.offset = ast.V.offset + ((Field) ast.I.decl.entity).fieldOffset;
    // I.decl points to the appropriate record field
    ast.indexed = ast.V.indexed;
    return baseObject;
  }

  public Object visitSimpleVname(SimpleVname ast, Object o) {
    ast.offset = 0;
    ast.indexed = false;
    return ast.I.decl.entity;
  }

  public Object visitSubscriptVname(SubscriptVname ast, Object o) {
    Frame frame = (Frame) o;
    RuntimeEntity baseObject;
    int elemSize, indexSize, index;

    baseObject = (RuntimeEntity) ast.V.visit(this, frame);
    ast.offset = ast.V.offset;
    ast.indexed = ast.V.indexed;
    elemSize = ((Integer) ast.type.visit(this, null)).intValue();
    if (ast.E instanceof IntegerExpression) {
      IntegerLiteral IL = ((IntegerExpression) ast.E).IL;
      ast.offset = ast.offset + Integer.parseInt(IL.spelling) * elemSize;
      index = Integer.parseInt(IL.spelling);
      emit(Machine.LOADLop, 0, 0, ast.V.offset + index*elemSize); //indice
      emit(Machine.LOADLop, 0, 0, ast.V.offset); //cota inferior
      emit(Machine.LOADLop, 0, 0, ast.V.type.entity.size); //cota superior
      emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.INDEXcheck); //chequeo
      emit(Machine.POPop, 0, 0, 3);//limpia la pila
    } else {
      // v-name is indexed by a proper expression, not a literal
      if (ast.indexed)
        frame.size = frame.size + Machine.integerSize;
      index = ((Integer) ast.E.visit(this, frame)).intValue();
      if (elemSize != 1) {
        emit(Machine.LOADLop, 0, 0, elemSize);
        emit(Machine.CALLop, Machine.SBr, Machine.PBr,
            Machine.multDisplacement);
      }
      //La cota superior es el tope de la pila donde esta el resultado de la expresion
      emit(Machine.LOADLop, 0, 0, ast.V.offset); //cota inferior
      emit(Machine.LOADLop, 0, 0, ast.V.type.entity.size); //cotasuperior
      emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.INDEXcheck); //chequeo
      emit(Machine.POPop, 0, 0, 2); //deja la pila como estaba con el resultado de la expresion en el tope
      if (ast.indexed)
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
      else
        ast.indexed = true;
    }
    return baseObject;
  }

  // Programs
  public Object visitProgram(Program ast, Object o) {
    return ast.C.visit(this, o);
  }

  public Encoder(ErrorReporter reporter) {
    this.reporter = reporter;
    nextInstrAddr = Machine.CB;
    elaborateStdEnvironment();
  }

  private ErrorReporter reporter;

  // Generates code to run a program.
  // showingTable is true iff entity description details
  // are to be displayed.
  public final void encodeRun(Program theAST, boolean showingTable) {
    tableDetailsReqd = showingTable;
    // startCodeGeneration();
    theAST.visit(this, new Frame(0, 0));
    emit(Machine.HALTop, 0, 0, 0);
  }

  // Decides run-time representation of a standard constant.
  private final void elaborateStdConst(Declaration constDeclaration,
      int value) {

    if (constDeclaration instanceof ConstDeclaration) {
      ConstDeclaration decl = (ConstDeclaration) constDeclaration;
      int typeSize = ((Integer) decl.E.type.visit(this, null)).intValue();
      decl.entity = new KnownValue(typeSize, value);
      writeTableDetails(constDeclaration);
    }
  }

  // Decides run-time representation of a standard routine.
  private final void elaborateStdPrimRoutine(Declaration routineDeclaration,
      int routineOffset) {
    routineDeclaration.entity = new PrimitiveRoutine(Machine.closureSize, routineOffset);
    writeTableDetails(routineDeclaration);
  }

  private final void elaborateStdEqRoutine(Declaration routineDeclaration,
      int routineOffset) {
    routineDeclaration.entity = new EqualityRoutine(Machine.closureSize, routineOffset);
    writeTableDetails(routineDeclaration);
  }

  private final void elaborateStdRoutine(Declaration routineDeclaration,
      int routineOffset) {
    routineDeclaration.entity = new KnownRoutine(Machine.closureSize, 0, routineOffset);
    writeTableDetails(routineDeclaration);
  }

  private final void elaborateStdEnvironment() {
    tableDetailsReqd = false;
    elaborateStdConst(StdEnvironment.falseDecl, Machine.falseRep);
    elaborateStdConst(StdEnvironment.trueDecl, Machine.trueRep);
    elaborateStdPrimRoutine(StdEnvironment.notDecl, Machine.notDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.andDecl, Machine.andDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.orDecl, Machine.orDisplacement);
    elaborateStdConst(StdEnvironment.maxintDecl, Machine.maxintRep);
    elaborateStdPrimRoutine(StdEnvironment.addDecl, Machine.addDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.subtractDecl, Machine.subDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.multiplyDecl, Machine.multDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.divideDecl, Machine.divDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.moduloDecl, Machine.modDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.lessDecl, Machine.ltDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.notgreaterDecl, Machine.leDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.greaterDecl, Machine.gtDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.notlessDecl, Machine.geDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.chrDecl, Machine.idDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.ordDecl, Machine.idDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.eolDecl, Machine.eolDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.eofDecl, Machine.eofDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.getDecl, Machine.getDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.putDecl, Machine.putDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.getintDecl, Machine.getintDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.putintDecl, Machine.putintDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.geteolDecl, Machine.geteolDisplacement);
    elaborateStdPrimRoutine(StdEnvironment.puteolDecl, Machine.puteolDisplacement);
    elaborateStdEqRoutine(StdEnvironment.equalDecl, Machine.eqDisplacement);
    elaborateStdEqRoutine(StdEnvironment.unequalDecl, Machine.neDisplacement);
  }

  // Saves the object program in the named file.

  public void saveObjectProgram(String objectName) {
    FileOutputStream objectFile = null;
    DataOutputStream objectStream = null;

    int addr;

    try {
      objectFile = new FileOutputStream(objectName);
      objectStream = new DataOutputStream(objectFile);

      addr = Machine.CB;
      for (addr = Machine.CB; addr < nextInstrAddr; addr++)
        Machine.code[addr].write(objectStream);
      objectFile.close();
    } catch (FileNotFoundException s) {
      System.err.println("Error opening object file: " + s);
    } catch (IOException s) {
      System.err.println("Error writing object file: " + s);
    }
  }

  boolean tableDetailsReqd;

  public static void writeTableDetails(AST ast) {
  }

  // OBJECT CODE

  // Implementation notes:
  // Object code is generated directly into the TAM Code Store, starting at CB.
  // The address of the next instruction is held in nextInstrAddr.

  private int nextInstrAddr;

  // Appends an instruction, with the given fields, to the object code.
  private void emit(int op, int n, int r, int d) {
    Instruction nextInstr = new Instruction();
    if (n > 255) {
      reporter.reportRestriction("length of operand can't exceed 255 words");
      n = 255; // to allow code generation to continue
    }
    nextInstr.op = op;
    nextInstr.n = n;
    nextInstr.r = r;
    nextInstr.d = d;
    if (nextInstrAddr == Machine.PB)
      reporter.reportRestriction("too many instructions for code segment");
    else {
      Machine.code[nextInstrAddr] = nextInstr;
      nextInstrAddr = nextInstrAddr + 1;
    }
  }

  // Patches the d-field of the instruction at address addr.
  private void patch(int addr, int d) {
    Machine.code[addr].d = d;
  }

  // DATA REPRESENTATION

  public int characterValuation(String spelling) {
    // Returns the machine representation of the given character literal.
    return spelling.charAt(1);
    // since the character literal is of the form 'x'}
  }

  // REGISTERS

  // Returns the register number appropriate for object code at currentLevel
  // to address a data object at objectLevel.
  private int displayRegister(int currentLevel, int objectLevel) {
    if (objectLevel == 0)
      return Machine.SBr;
    else if (currentLevel - objectLevel <= 6)
      return Machine.LBr + currentLevel - objectLevel; // LBr|L1r|...|L6r
    else {
      reporter.reportRestriction("can't access data more than 6 levels out");
      return Machine.L6r; // to allow code generation to continue
    }
  }

  // Generates code to fetch the value of a named constant or variable
  // and push it on to the stack.
  // currentLevel is the routine level where the vname occurs.
  // frameSize is the anticipated size of the local stack frame when
  // the constant or variable is fetched at run-time.
  // valSize is the size of the constant or variable's value.

  private void encodeStore(Vname V, Frame frame, int valSize) {

    RuntimeEntity baseObject = (RuntimeEntity) V.visit(this, frame);
    // If indexed = true, code will have been generated to load an index value.
    if (valSize > 255) {
      reporter.reportRestriction("can't store values larger than 255 words");
      valSize = 255; // to allow code generation to continue
    }
    if (baseObject instanceof KnownAddress) {
      ObjectAddress address = ((KnownAddress) baseObject).address;
      if (V.indexed) {
        emit(Machine.LOADAop, 0, displayRegister(frame.level, address.level),
            address.displacement + V.offset);
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
        emit(Machine.STOREIop, valSize, 0, 0);
      } else {
        emit(Machine.STOREop, valSize, displayRegister(frame.level,
            address.level), address.displacement + V.offset);
      }
    } else if (baseObject instanceof UnknownAddress) {
      ObjectAddress address = ((UnknownAddress) baseObject).address;
      emit(Machine.LOADop, Machine.addressSize, displayRegister(frame.level,
          address.level), address.displacement);
      if (V.indexed)
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
      if (V.offset != 0) {
        emit(Machine.LOADLop, 0, 0, V.offset);
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
      }
      emit(Machine.STOREIop, valSize, 0, 0);
    }
  }

  // Generates code to fetch the value of a named constant or variable
  // and push it on to the stack.
  // currentLevel is the routine level where the vname occurs.
  // frameSize is the anticipated size of the local stack frame when
  // the constant or variable is fetched at run-time.
  // valSize is the size of the constant or variable's value.

  private void encodeFetch(Vname V, Frame frame, int valSize) {

    RuntimeEntity baseObject = (RuntimeEntity) V.visit(this, frame);
    // If indexed = true, code will have been generated to load an index value.
    if (valSize > 255) {
      reporter.reportRestriction("can't load values larger than 255 words");
      valSize = 255; // to allow code generation to continue
    }
    if (baseObject instanceof KnownValue) {
      // presumably offset = 0 and indexed = false
      int value = ((KnownValue) baseObject).value;
      emit(Machine.LOADLop, 0, 0, value);
    } else if ((baseObject instanceof UnknownValue) ||
        (baseObject instanceof KnownAddress)) {
      ObjectAddress address = (baseObject instanceof UnknownValue) ? ((UnknownValue) baseObject).address
          : ((KnownAddress) baseObject).address;
      if (V.indexed) {
        emit(Machine.LOADAop, 0, displayRegister(frame.level, address.level),
            address.displacement + V.offset);
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
        emit(Machine.LOADIop, valSize, 0, 0);
      } else
        emit(Machine.LOADop, valSize, displayRegister(frame.level,
            address.level), address.displacement + V.offset);
    } else if (baseObject instanceof UnknownAddress) {
      ObjectAddress address = ((UnknownAddress) baseObject).address;
      emit(Machine.LOADop, Machine.addressSize, displayRegister(frame.level,
          address.level), address.displacement);
      if (V.indexed)
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
      if (V.offset != 0) {
        emit(Machine.LOADLop, 0, 0, V.offset);
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
      }
      emit(Machine.LOADIop, valSize, 0, 0);
    }
  }

  // Generates code to compute and push the address of a named variable.
  // vname is the program phrase that names this variable.
  // currentLevel is the routine level where the vname occurs.
  // frameSize is the anticipated size of the local stack frame when
  // the variable is addressed at run-time.

  private void encodeFetchAddress(Vname V, Frame frame) {

    RuntimeEntity baseObject = (RuntimeEntity) V.visit(this, frame);
    // If indexed = true, code will have been generated to load an index value.
    if (baseObject instanceof KnownAddress) {
      ObjectAddress address = ((KnownAddress) baseObject).address;
      emit(Machine.LOADAop, 0, displayRegister(frame.level, address.level),
          address.displacement + V.offset);
      if (V.indexed)
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
    } else if (baseObject instanceof UnknownAddress) {
      ObjectAddress address = ((UnknownAddress) baseObject).address;
      emit(Machine.LOADop, Machine.addressSize, displayRegister(frame.level,
          address.level), address.displacement);
      if (V.indexed)
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
      if (V.offset != 0) {
        emit(Machine.LOADLop, 0, 0, V.offset);
        emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
      }
    }
  }

  @Override
  public Object visitLoopCommandAST1(LoopCommandAST1 aThis, Object o) {
      Frame frame = (Frame) o;
      aThis.WhileVar.visit(this, frame);
      return null;
  }

  @Override
  public Object visitForFromCommand(ForFromCommand aThis, Object o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Object visitDoCommandAST(DoCommand aThis, Object o) {
     Frame frame = (Frame) o;
    aThis.C.visit(this, frame);
    return null;
  }

  @Override
  public Object visitForFromAST1(ForFromAST1 aThis, Object o) {
    Frame frame = (Frame) o;
    // Evaluar E1 y E2
    int last = (Integer) aThis.TC.E.visit(this, frame);
    frame = new Frame(frame, last);
    
    int first = (Integer) aThis.ForFrom.E.visit(this, frame);
    aThis.ForFrom.entity = new UnknownValue(first, frame.level, frame.size);
    frame = new Frame(frame, first);
    
    int jumpAddr, repeatAddr;
    jumpAddr = nextInstrAddr; // Jump a la condicion
    emit(Machine.JUMPop, 0, Machine.SBr, 0);
    repeatAddr = nextInstrAddr;
    
    
    aThis.Do.C.visit(this, frame);
    emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.succDisplacement); // Call succ
    

    int evaluate = nextInstrAddr;
    patch(jumpAddr, evaluate);
    
    emit(Machine.LOADop, 1, Machine.STr, -1);
    emit(Machine.LOADop, 1, Machine.STr, -3);
    emit(Machine.CALLop, 0, Machine.PBr, Machine.leDisplacement);
    emit(Machine.JUMPIFop, Machine.trueRep, Machine.SBr, repeatAddr);

    emit(Machine.POPop, 0, 0, first+last);

    return null;

  }

  @Override
  public Object visitLoopUntilDoAST(LoopUntilDoAST aThis, Object o) {
    Frame frame = (Frame) o;
    aThis.UntilVar.visit(this, frame);
    return null;
  }

  @Override
  public Object visitUntilCommand(UntilCommand aThis, Object o) {
    Frame frame = (Frame) o;
    int jumpAddr, loopAddr;

    jumpAddr = nextInstrAddr;
    emit(Machine.JUMPop, 0, Machine.CBr, 0);
    loopAddr = nextInstrAddr;
    aThis.C.visit(this, frame);
    patch(jumpAddr, nextInstrAddr);
    aThis.E.visit(this, frame);
    emit(Machine.JUMPIFop, Machine.falseRep, Machine.CBr, loopAddr);
    return null;
  }

  @Override
  public Object visitWhileEndCommand(WhileEndCommand aThis, Object o) {
    return null; //No es necesario implementarlo pues se visita la expresion desde el visit de loop while end
  }

  @Override
  public Object visitLoopWhileEndCommand(LoopWhileEndAST aThis, Object o) {
    Frame frame = (Frame) o;
    int loopAddr;

    loopAddr = nextInstrAddr;
    aThis.C.visit(this, frame);
    aThis.WhileV.E.visit(this, frame);
    emit(Machine.JUMPIFop, Machine.trueRep, Machine.CBr, loopAddr);
    return null;
  }

  @Override
  public Object visitUntilEndCommand(UntilEndCommand aThis, Object o) {
    return null; //No es necesario implementarlo pues se visita la expresion desde el visit de loop until end
  }

  @Override
  public Object visitLoopUntilEndCommand(LoopUntilEndAST aThis, Object o) {
    Frame frame = (Frame) o;
    int loopAddr;

    loopAddr = nextInstrAddr;
    aThis.C.visit(this, frame);
    aThis.UntilEnd.E.visit(this, frame);
    emit(Machine.JUMPIFop, Machine.falseRep, Machine.CBr, loopAddr);
    return null;
  }

  @Override
  public Object visitForFromWhile(LoopForFromWhile aThis, Object o) {
    Frame frame = (Frame) o;
    // Evaluar E1 y E2
    int last = (Integer) aThis.E.E.visit(this, frame);
    frame = new Frame(frame, last);
    
    int first = (Integer) aThis.ForFrom.E.visit(this, frame);
    aThis.ForFrom.entity = new UnknownValue(first, frame.level, frame.size);
    frame = new Frame(frame, first);
    
    int comAddr, repeatAddr, jmpAddr;
    jmpAddr = nextInstrAddr;
    emit(Machine.JUMPop, 0, Machine.SBr, 0);
    
    comAddr = nextInstrAddr;
    aThis.whileV.C.visit(this, frame);
    emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.succDisplacement); // Call succ
    
    patch(jmpAddr, nextInstrAddr);
    
    emit(Machine.LOADop, 1, Machine.STr, -1);
    emit(Machine.LOADop, 1, Machine.STr, -3);
    emit(Machine.CALLop, 0, Machine.PBr, Machine.leDisplacement);
    repeatAddr = nextInstrAddr;
    emit(Machine.JUMPIFop, Machine.falseRep, Machine.SBr, repeatAddr);
    
    aThis.whileV.E.visit(this, frame);
    emit(Machine.JUMPIFop, Machine.trueRep, Machine.SBr, comAddr);
    
    patch(repeatAddr, nextInstrAddr);
    
    emit(Machine.POPop, 0, 0, first+last+1);

    return null;
  }

  @Override
  public Object visitForFromUntil(LoopForFromUntil aThis, Object o) {
    Frame frame = (Frame) o;
    // Evaluar E1 y E2
    int last = (Integer) aThis.E.E.visit(this, frame);
    frame = new Frame(frame, last);
    
    int first = (Integer) aThis.ForFrom.E.visit(this, frame);
    aThis.ForFrom.entity = new UnknownValue(first, frame.level, frame.size);
    frame = new Frame(frame, first);
    
    int comAddr, repeatAddr, jmpAddr;
    jmpAddr = nextInstrAddr;
    emit(Machine.JUMPop, 0, Machine.SBr, 0);
    
    comAddr = nextInstrAddr;
    aThis.untilV.C.visit(this, frame);
    emit(Machine.CALLop, Machine.SBr, Machine.PBr, Machine.succDisplacement); // Call succ
    
    patch(jmpAddr, nextInstrAddr);
    
    emit(Machine.LOADop, 1, Machine.STr, -1);
    emit(Machine.LOADop, 1, Machine.STr, -3);
    emit(Machine.CALLop, 0, Machine.PBr, Machine.leDisplacement);
    repeatAddr = nextInstrAddr;
    emit(Machine.JUMPIFop, Machine.falseRep, Machine.SBr, repeatAddr);
    
    aThis.untilV.E.visit(this, frame);
    emit(Machine.JUMPIFop, Machine.falseRep, Machine.SBr, comAddr);
    
    patch(repeatAddr, nextInstrAddr);
    
    emit(Machine.POPop, 0, 0, first+last+1);

    return null;
  }

  @Override
  public Object visitForInCommand(ForInCommand aThis, Object o) {
    return null; //se visita cada parte en el forInDoCommand
  }

  @Override
  public Object visitForInDoCommand(ForInDo aThis, Object o) {
    Frame frame = (Frame) o;
    
    int sizeofArray = (Integer) aThis.forAST.E.visit(this, frame);
    int sizeOfType = (Integer) ((ArrayTypeDenoter) aThis.forAST.E.type).T.visit(this, frame);
    
    aThis.forAST.E.entity =  new UnknownValue(sizeofArray, frame.level, frame.size);
    emit (Machine. PUSHop, 0, 0, sizeOfType);
    aThis.forAST.entity = new KnownAddress(sizeOfType, frame.level, frame.size+sizeofArray);
    emit (Machine.LOADAop, 0, displayRegister(frame.level, //Carga el tamaño de la pila - tamaño del tipo
            ((UnknownValue) aThis.forAST.E.entity).address.level), //tomando en cuenta la posicion en la que empieza
            ((UnknownValue) aThis.forAST.E.entity).address.displacement + sizeofArray-sizeOfType); //el arreglo en la pila
    emit (Machine.LOADAop, 0, displayRegister(frame.level, //Carga la posicion en la que empieza la pila
            ((UnknownValue) aThis.forAST.E.entity).address.level), 
            ((UnknownValue) aThis.forAST.E.entity).address.displacement);
    
    frame = new Frame(frame, sizeOfType+sizeofArray+2);
    int repeatAddr = nextInstrAddr;
    emit (Machine.LOADop, 1, Machine.STr, -1);
    emit(Machine.LOADIop, sizeOfType, 0, 0);
    emit (Machine.STOREop, sizeOfType, Machine.STr, -1*(2 + 2*sizeOfType));
    aThis.C.visit(this, frame);
    emit (Machine. LOADLop, 0, 0, sizeOfType);
    emit (Machine.CALLop, Machine.SBr, Machine.PBr, Machine.addDisplacement);
    emit (Machine.LOADop, 2, Machine.STr, -2);
    emit (Machine.CALLop, Machine.SBr, Machine.PBr, Machine.geDisplacement);
    emit (Machine.JUMPIFop, Machine.trueRep, Machine.CBr, repeatAddr);
    emit (Machine.POPop, 0, 0, (sizeofArray+sizeOfType+2));
    return null;

  }

  @Override
  public Object visitToCommandAST(ToCommand aThis, Object o) {
    return null; // no se visita directamente, si no en los for from
  }

  @Override
  public Object visitVarDeclarationInit(VarDeclarationInit aThis, Object o) {
    Frame frame = (Frame) o;
    int size = (Integer) aThis.E.visit(this, frame);
    aThis.entity = new KnownAddress(Machine.addressSize, frame.level, frame.size);
    writeTableDetails(aThis);
    return size;
  }

  @Override
  public Object visitThenCommandAST(ThenCommand aThis, Object o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Object visitLeaveIdentifier(LeaveIdentifier aThis, Object o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Object visitNextIdentifier(NextIdentifier aThis, Object o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Object visitReturnCommand(ReturnCommand aThis, Object o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Object visitSequentialDeclarationProcFuncs(SequentialDeclarationProcFuncs aThis, Object o) {
    Frame frame = (Frame) o;
    int sizeD1, sizeD2, inicio;
    inicio = nextInstrAddr; 
    sizeD1 = (Integer) aThis.D1.visit(this, frame);
    Frame newFrame = new Frame(frame, sizeD1);
    sizeD2 = (Integer) aThis.D2.visit(this, newFrame);
    return sizeD1+sizeD2;
  }

  @Override
  public Object visitRecDeclaration(RecDeclaration aThis, Object o) {
    Frame frame = (Frame) o;
    int intrAddr, sizeD;
    intrAddr = nextInstrAddr; // se guarda la dirección para realizar de nuevo el visit
    aThis.dAST.visit(this, frame);
    nextInstrAddr = intrAddr; // para realizar de nuevo el visit y rellenar lo necesario
    sizeD = (Integer) aThis.dAST.visit(this, frame);
    return sizeD;
  }

  @Override
  public Object visitCompoundSingleDeclaration(CompoundSingleDeclaration aThis, Object o) {
    return (Integer) aThis.dAST.visit(this, (Frame)o);
  }
}
