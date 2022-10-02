/*
 * @(#)LayoutVisitor.java                        2.1 2003/10/07
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

package Triangle.TreeDrawer;

import java.awt.FontMetrics;

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

public class LayoutVisitor implements Visitor {

  private final int BORDER = 5;
  private final int PARENT_SEP = 30;

  private FontMetrics fontMetrics;

  public LayoutVisitor (FontMetrics fontMetrics) {
    this.fontMetrics = fontMetrics;
  }

  // Commands
 //Se dejo declarado el CaseLiteralCommand para los siguientes proyectos. 
 //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
 public Object visitCaseLiteralCommand(CaseLiteralCommand ast, Object obj) {
    if(ast.CL == null)
        return layoutUnary("CaseLitCom.", ast.IL);
    else
        return layoutUnary("CaseLitCom.", ast.CL);
  }
 
 //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  public Object visitCaseRangeCommand(CaseRangeCommand ast, Object obj) {
      if(ast.TC == null){
          return layoutUnary("CaseRngCom.", ast.CLC);
      }
      else{
          return layoutBinary("CaseRngCom.", ast.CLC, ast.TC);
      }
  }
 
 //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  public Object visitToCommandLiteralAST(ToCommandLiteral ast, Object obj) {
    return layoutUnary("CaseToLitCom.", ast.CLCT);
  }  
 //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  public Object visitCaseCommand(CaseCommand ast, Object obj) {
    return layoutBinary("CaseCommandCom.", ast.CL,  ast.C);
  }  
 //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  public Object visitCasesCommand(CasesCommand ast, Object obj) {
    if(ast.multipleCase == null)
        return layoutUnary("CasesCommandCom.", ast.singleCase);
    else
        return layoutUnary("CasesCommandCom.", ast.multipleCase);
  }  
  
  public Object visitSelectCommand(SelectCommand ast, Object obj) {
    return layoutTernary("SelectCommandCom.", ast.expression, ast.cases, ast.command);
  } 
 //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
 public Object visitBarCommandCaseRange(BarCommandCaseRange ast, Object obj) {
    return layoutUnary("CaseBarCaseRngtCom.", ast.CRCB);
  }  
  //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
 public Object visitCaseLiterals(CaseLiterals ast, Object obj) {
    if(ast.MCRCL == null)
            return(layoutUnary("SingleCaseRange", ast.SCRCL));
        else
            return(layoutUnary("MultipleCaseRange", ast.MCRCL));
  }  
  
  public Object visitAssignCommand(AssignCommand ast, Object obj) {
    return layoutBinary("AssignCom.", ast.V, ast.E);
  }

  public Object visitCallCommand(CallCommand ast, Object obj) {
    return layoutBinary("CallCom.", ast.I, ast.APS);
   }

  public Object visitEmptyCommand(EmptyCommand ast, Object obj) {
    return layoutNullary("EmptyCom.");
  }

  public Object visitIfCommand(IfCommand ast, Object obj) {
//    if(ast.ST == null){
//            return layoutQuaternary("If Command", ast.E, ast.C1, ast.C2, ast.MT);
//    }
//    else{
//        return layoutQuaternary("If Command", ast.E, ast.C1, ast.C2, ast.ST);
//    }
    return(layoutTernary("If command", ast.E, ast.C1, ast.C2));
  }

  public Object visitLetCommand(LetCommand ast, Object obj) {
    return layoutBinary("LetCom.", ast.D, ast.C);
  }

  public Object visitSequentialCommand(SequentialCommand ast, Object obj) {
    return layoutBinary("Seq.Com.", ast.C1, ast.C2);
  }
  
  public Object visitSequentialCases(SequentialCases ast, Object obj) {
    return layoutBinary("Seq.Cas.", ast.C1, ast.C2);
  }

  public Object visitWhileCommand(WhileCommand ast, Object obj) {
    return layoutBinary("WhileCom.", ast.E, ast.C);
  }


  // Expressions
  public Object visitArrayExpression(ArrayExpression ast, Object obj) {
    return layoutUnary("ArrayExpr.", ast.AA);
  }

  public Object visitBinaryExpression(BinaryExpression ast, Object obj) {
    return layoutTernary("Bin.Expr.", ast.E1, ast.O, ast.E2);
  }

  public Object visitCallExpression(CallExpression ast, Object obj) {
    return layoutBinary("CallExpr.", ast.I, ast.APS);
  }

  public Object visitCharacterExpression(CharacterExpression ast, Object obj) {
    return layoutUnary("Char.Expr.", ast.CL);
  }

  public Object visitEmptyExpression(EmptyExpression ast, Object obj) {
    return layoutNullary("EmptyExpr.");
  }

  public Object visitIfExpression(IfExpression ast, Object obj) {
    return layoutTernary("IfExpr.", ast.E1, ast.E2, ast.E3);
  }

  public Object visitIntegerExpression(IntegerExpression ast, Object obj) {
    return layoutUnary("Int.Expr.", ast.IL);
  }

  public Object visitLetExpression(LetExpression ast, Object obj) {
    return layoutBinary("LetExpr.", ast.D, ast.E);
  }

  public Object visitRecordExpression(RecordExpression ast, Object obj) {
    return layoutUnary("Rec.Expr.", ast.RA);
  }

  public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
    return layoutBinary("UnaryExpr.", ast.O, ast.E);
  }

  public Object visitVnameExpression(VnameExpression ast, Object obj) {
    return layoutUnary("VnameExpr.", ast.V);
  }


  // Declarations
  public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
    return layoutQuaternary("Bin.Op.Decl.", ast.O, ast.ARG1, ast.ARG2, ast.RES);
  }

  public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
    return layoutBinary("ConstDecl.", ast.I, ast.E);
  }

  public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
    return layoutQuaternary("FuncDecl.", ast.I, ast.FPS, ast.T, ast.E);
  }

  public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
    return layoutTernary("ProcDecl.", ast.I, ast.FPS, ast.C);
  }

  public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
    return layoutBinary("Seq.Decl.", ast.D1, ast.D2);
  }
  
  public Object visitLocalDeclaration(LocalDeclaration ast, Object obj) {
    return layoutBinary("Loc.Decl.", ast.D1, ast.D2);
  }

  public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
    return layoutBinary("TypeDecl.", ast.I, ast.T);
  }

  public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
    return layoutTernary("UnaryOp.Decl.", ast.O, ast.ARG, ast.RES);
  }

  public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
    return layoutBinary("VarDecl.", ast.I, ast.T);
  }


  // Array Aggregates
  public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
    return layoutBinary("Mult.ArrayAgg.", ast.E, ast.AA);
  }

  public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
    return layoutUnary("Sing.ArrayAgg.", ast.E);
  }


  // Record Aggregates
  public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
    return layoutTernary("Mult.Rec.Agg.", ast.I, ast.E, ast.RA);
  }

  public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
    return layoutBinary("Sing.Rec.Agg.", ast.I, ast.E);
  }


  // Formal Parameters
  public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
    return layoutBinary("ConstF.P.", ast.I, ast.T);
  }

  public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
    return layoutTernary("FuncF.P.", ast.I, ast.FPS, ast.T);
  }

  public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
    return layoutBinary("ProcF.P.", ast.I, ast.FPS);
  }

  public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
    return layoutBinary("VarF.P.", ast.I, ast.T);
  }


  public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
    return layoutNullary("EmptyF.P.S.");
  }

  public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
    return layoutBinary("Mult.F.P.S.", ast.FP, ast.FPS);
  }

  public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
    return layoutUnary("Sing.F.P.S.", ast.FP);
  }


  // Actual Parameters
  public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
    return layoutUnary("ConstA.P.", ast.E);
  }

  public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
    return layoutUnary("FuncA.P.", ast.I);
  }

  public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
    return layoutUnary("ProcA.P.", ast.I);
  }

  public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
    return layoutUnary("VarA.P.", ast.V);
  }


  public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
    return layoutNullary("EmptyA.P.S.");
  }

  public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
    return layoutBinary("Mult.A.P.S.", ast.AP, ast.APS);
  }

  public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
    return layoutUnary("Sing.A.P.S.", ast.AP);
  }
  
  public Object visitSingleCaseRange(SingleCaseRange ast, Object obj) {
    return layoutUnary("Sing.C.R.C.L", ast.CRCSCR);
  }
  
  public Object visitSingleThen(SingleThen ast, Object obj) {
    return layoutBinary("Sing.Then", ast.TC, ast.E);
  }
  
  public Object visitSingleCase(SingleCase ast, Object obj) {
    return layoutUnary("Sing.C.", ast.SC);
  }
  
  public Object visitMultipleCaseRange(MultipleCaseRange ast, Object obj) {
    if(ast.CRCMCR2 == null)
        return layoutUnary("First.Multiple.C.R.C.L", ast.CRCMCR);
    else
        return layoutBinary("Multiple.C.R.C.L", ast.CRCMCR, ast.CRCMCR2);
  }
  
  public Object visitMultipleThen(MultipleThen ast, Object obj) {
    if(ast.TC2 == null)
        return layoutUnary("First.Multiple.Then", ast.TC);
    else
        return layoutTernary("Multiple.Then", ast.TC, ast.TC2, ast.E);
  }

   public Object visitMultipleCase(MultipleCase ast, Object obj) {
    if(ast.MCC2 == null)
        return layoutUnary("First.Multiple.C.C", ast.MCC);
    else
        return layoutBinary("Multiple.C.C", ast.MCC, ast.MCC2);
  }

  // Type Denoters
  public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
    return layoutNullary("any");
  }

  public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
    return layoutBinary("ArrayTypeD.", ast.IL, ast.T);
  }

  public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
    return layoutNullary("bool");
  }

  public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
    return layoutNullary("char");
  }

  public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
    return layoutNullary("error");
  }

  public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
    return layoutUnary("Sim.TypeD.", ast.I);
  }

  public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
    return layoutNullary("int");
  }

  public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
    return layoutUnary("Rec.TypeD.", ast.FT);
  }


  public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
    return layoutTernary("Mult.F.TypeD.", ast.I, ast.T, ast.FT);
  }

  public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
    return layoutBinary("Sing.F.TypeD.", ast.I, ast.T);
  }


  // Literals, Identifiers and Operators
  public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
    return layoutNullary(ast.spelling);
  }

  public Object visitIdentifier(Identifier ast, Object obj) {
    return layoutNullary(ast.spelling);
 }

  public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
    return layoutNullary(ast.spelling);
  }

  public Object visitOperator(Operator ast, Object obj) {
    return layoutNullary(ast.spelling);
  }


  // Value-or-variable names
  public Object visitDotVname(DotVname ast, Object obj) {
    return layoutBinary("DotVname", ast.I, ast.V);
  }

  public Object visitSimpleVname(SimpleVname ast, Object obj) {
    return layoutUnary("Sim.Vname", ast.I);
  }

  public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
    return layoutBinary("Sub.Vname",
        ast.V, ast.E);
  }


  // Programs
  public Object visitProgram(Program ast, Object obj) {
    return layoutUnary("Program", ast.C);
  }

  private DrawingTree layoutCaption (String name) {
    int w = fontMetrics.stringWidth(name) + 4;
    int h = fontMetrics.getHeight() + 4;
    return new DrawingTree(name, w, h);
  }

  private DrawingTree layoutNullary (String name) {
    DrawingTree dt = layoutCaption(name);
    dt.contour.upper_tail = new Polyline(0, dt.height + 2 * BORDER, null);
    dt.contour.upper_head = dt.contour.upper_tail;
    dt.contour.lower_tail = new Polyline(-dt.width - 2 * BORDER, 0, null);
    dt.contour.lower_head = new Polyline(0, dt.height + 2 * BORDER, dt.contour.lower_tail);
    return dt;
  }

  private DrawingTree layoutUnary (String name, AST child1) {
    DrawingTree dt = layoutCaption(name);
    DrawingTree d1 = (DrawingTree) child1.visit(this, null);
    dt.setChildren(new DrawingTree[] {d1});
    attachParent(dt, join(dt));
    return dt;
  }

  private DrawingTree layoutBinary (String name, AST child1, AST child2) {
    DrawingTree dt = layoutCaption(name);
    DrawingTree d1 = (DrawingTree) child1.visit(this, null);
    DrawingTree d2 = (DrawingTree) child2.visit(this, null);
    dt.setChildren(new DrawingTree[] {d1, d2});
    attachParent(dt, join(dt));
    return dt;
  }

  private DrawingTree layoutTernary (String name, AST child1, AST child2,
                                     AST child3) {
    DrawingTree dt = layoutCaption(name);
    DrawingTree d1 = (DrawingTree) child1.visit(this, null);
    DrawingTree d2 = (DrawingTree) child2.visit(this, null);
    DrawingTree d3 = (DrawingTree) child3.visit(this, null);
    dt.setChildren(new DrawingTree[] {d1, d2, d3});
    attachParent(dt, join(dt));
    return dt;
  }

  private DrawingTree layoutQuaternary (String name, AST child1, AST child2,
                                        AST child3, AST child4) {
    DrawingTree dt = layoutCaption(name);
    DrawingTree d1 = (DrawingTree) child1.visit(this, null);
    DrawingTree d2 = (DrawingTree) child2.visit(this, null);
    DrawingTree d3 = (DrawingTree) child3.visit(this, null);
    DrawingTree d4 = (DrawingTree) child4.visit(this, null);
    dt.setChildren(new DrawingTree[] {d1, d2, d3, d4});
    attachParent(dt, join(dt));
    return dt;
  }
  
//Autores: Kevin Rodriguez, Gabriel Fallas
  private DrawingTree layoutQuinary (String name, AST child1, AST child2,
                                        AST child3, AST child4, AST child5) {
    DrawingTree dt = layoutCaption(name);
    DrawingTree d1 = (DrawingTree) child1.visit(this, null);
    DrawingTree d2 = (DrawingTree) child2.visit(this, null);
    DrawingTree d3 = (DrawingTree) child3.visit(this, null);
    DrawingTree d4 = (DrawingTree) child4.visit(this, null);
    DrawingTree d5 = (DrawingTree) child5.visit(this, null);
    dt.setChildren(new DrawingTree[] {d1, d2, d3, d4, d5});
    attachParent(dt, join(dt));
    return dt;
  }
  
  private DrawingTree layoutSixary (String name, AST child1, AST child2,
                                        AST child3, AST child4, AST child5, AST child6) {
    DrawingTree dt = layoutCaption(name);
    DrawingTree d1 = (DrawingTree) child1.visit(this, null);
    DrawingTree d2 = (DrawingTree) child2.visit(this, null);
    DrawingTree d3 = (DrawingTree) child3.visit(this, null);
    DrawingTree d4 = (DrawingTree) child4.visit(this, null);
    DrawingTree d5 = (DrawingTree) child5.visit(this, null);
    DrawingTree d6 = (DrawingTree) child6.visit(this, null);
    dt.setChildren(new DrawingTree[] {d1, d2, d3, d4, d5, d6});
    attachParent(dt, join(dt));
    return dt;
  }

  private void attachParent(DrawingTree dt, int w) {
    int y = PARENT_SEP;
    int x2 = (w - dt.width) / 2 - BORDER;
    int x1 = x2 + dt.width + 2 * BORDER - w;

    dt.children[0].offset.y = y + dt.height;
    dt.children[0].offset.x = x1;
    dt.contour.upper_head = new Polyline(0, dt.height,
                                new Polyline(x1, y, dt.contour.upper_head));
    dt.contour.lower_head = new Polyline(0, dt.height,
                                new Polyline(x2, y, dt.contour.lower_head));
  }

  private int join (DrawingTree dt) {
    int w, sum;

    dt.contour = dt.children[0].contour;
    sum = w = dt.children[0].width + 2 * BORDER;

    for (int i = 1; i < dt.children.length; i++) {
      int d = merge(dt.contour, dt.children[i].contour);
      dt.children[i].offset.x = d + w;
      dt.children[i].offset.y = 0;
      w = dt.children[i].width + 2 * BORDER;
      sum += d + w;
    }
    return sum;
  }

  private int merge(Polygon c1, Polygon c2) {
    int x, y, total, d;
    Polyline lower, upper, b;

    x = y = total = 0;
    upper = c1.lower_head;
    lower = c2.upper_head;

    while (lower != null && upper != null) {
        d = offset(x, y, lower.dx, lower.dy, upper.dx, upper.dy);
	x += d;
	total += d;

	if (y + lower.dy <= upper.dy) {
	  x += lower.dx;
	  y += lower.dy;
	  lower = lower.link;
	} else {
	  x -= upper.dx;
	  y -= upper.dy;
	  upper = upper.link;
	}
      }

      if (lower != null) {
        b = bridge(c1.upper_tail, 0, 0, lower, x, y);
        c1.upper_tail = (b.link != null) ? c2.upper_tail : b;
        c1.lower_tail = c2.lower_tail;
      } else {
        b = bridge(c2.lower_tail, x, y, upper, 0, 0);
        if (b.link == null) {
          c1.lower_tail = b;
        }
      }

      c1.lower_head = c2.lower_head;

      return total;
    }

  private int offset (int p1, int p2, int a1, int a2, int b1, int b2) {
    int d, s, t;

    if (b2 <= p2 || p2 + a2 <= 0) {
      return 0;
    }

    t = b2 * a1 - a2 * b1;
    if (t > 0) {
      if (p2 < 0) {
        s = p2 * a1;
        d = s / a2 - p1;
      } else if (p2 > 0) {
        s = p2 * b1;
        d = s / b2 - p1;
      } else {
        d = -p1;
      }
    } else if (b2 < p2 + a2) {
      s = (b2 - p2) * a1;
      d = b1 - (p1 + s / a2);
    } else if (b2 > p2 + a2) {
      s = (a2 + p2) * b1;
      d = s / b2 - (p1 + a1);
    } else {
      d = b1 - (p1 + a1);
    }

    if (d > 0) {
      return d;
    } else {
      return 0;
    }
  }

  private Polyline bridge (Polyline line1, int x1, int y1,
                           Polyline line2, int x2, int y2) {
    int dy, dx, s;
    Polyline r;

    dy = y2 + line2.dy - y1;
    if (line2.dy == 0) {
      dx = line2.dx;
    } else {
      s = dy * line2.dx;
      dx = s / line2.dy;
    }

    r = new Polyline(dx, dy, line2.link);
    line1.link = new Polyline(x2 + line2.dx - dx - x1, 0, r);

    return r;
  }

    @Override
    public Object visitLoopCommandAST1(LoopCommandAST1 aThis, Object o) {
       if(aThis.I == null){
            return(layoutUnary("Loop Command", aThis.WhileVar));
        }
        else{
            return(layoutBinary("Loop Command", aThis.I, aThis.WhileVar));
        }
    }

    @Override
    public Object visitForFromCommand(ForFromCommand aThis, Object o) {
        return(layoutBinary("For From Command", aThis.I, aThis.E));
    }

    @Override
    public Object visitDoCommandAST(DoCommand aThis, Object o) {
        return(layoutUnary("Do Command", aThis.C));
    }
    
    public Object visitThenCommandAST(ThenCommand aThis, Object o) {
        return(layoutUnary("Then Command", aThis.C));
    }

    @Override
    public Object visitForFromAST1(ForFromAST1 aThis, Object o) {
        if(aThis.I == null){
            return(layoutTernary("Loop Command", aThis.ForFrom, aThis.E, aThis.Do));
        }
        else{
            return(layoutQuaternary("Loop Command",aThis.I, aThis.ForFrom, aThis.E, aThis.Do));
        }
    }

    @Override
    public Object visitLoopUntilDoAST(LoopUntilDoAST aThis, Object o) {
        if(aThis.I == null){
            return(layoutUnary("Loop Command", aThis.UntilVar));
        }
        else{
            return(layoutBinary("Loop Command", aThis.I, aThis.UntilVar));
        }
    }

    @Override
    public Object visitUntilCommand(UntilCommand aThis, Object o) {
        return(layoutBinary("Until Command", aThis.I, aThis.C));
    }

    @Override
    public Object visitWhileEndCommand(WhileEndCommand aThis, Object o) {
        return(layoutUnary("While Command", aThis.E));
    }

    @Override
    public Object visitLooopWhileEndCommand(LoopWhileEndAST aThis, Object o) {
        if(aThis.I == null){
            return(layoutBinary("Loop Command", aThis.C, aThis.WhileV));
        }
        else{
            return(layoutTernary("Loop Command", aThis.I, aThis.C, aThis.WhileV));
        }
    }

    @Override
    public Object visitUntilEndCommand(UntilEndCommand aThis, Object o) {
        return(layoutUnary("Until Command", aThis.E));
    }

    @Override
    public Object visitLooopUntilEndCommand(LoopUntilEndAST aThis, Object o) {
        if(aThis.I == null){
            return(layoutBinary("Loop Command", aThis.C, aThis.UntilEnd));
        }
        else{
            return(layoutTernary("Loop Command",aThis.I , aThis.C, aThis.UntilEnd));
        }
    }

    @Override
    public Object visitForFromWhile(LoopForFromWhile aThis, Object o) {
        if(aThis.I == null){
            return(layoutTernary("Loop Command",aThis.ForFrom, aThis.E, aThis.whileV));
        }
        else{
            return(layoutQuaternary("Loop Command",aThis.I, aThis.ForFrom, aThis.E, aThis.whileV));
        }
    }

    @Override
    public Object visitForFromUntil(LoopForFromUntil aThis, Object o) {
        if(aThis.I == null){
            return(layoutTernary("Loop Command",aThis.ForFrom, aThis.E, aThis.untilV));
        }
        else{
            return(layoutQuaternary("Loop Command",aThis.I, aThis.ForFrom, aThis.E, aThis.untilV));
        }
    }

    @Override
    public Object visitForInCommand(ForInCommand aThis, Object o) {
        return(layoutBinary("For In Command", aThis.I, aThis.E));
    }

    @Override
    public Object visitForInDoCommand(ForInDo aThis, Object o) {
        if(aThis.I == null){
            return(layoutBinary("Loop Command", aThis.forAST, aThis.C));
        }
        else{
            return(layoutTernary("Loop Command", aThis.I, aThis.forAST, aThis.C));
        }
    }

    @Override
    public Object visitToCommandAST(ToCommand aThis, Object o) {
        return(layoutUnary("To Command", aThis.E));
    }

    @Override
    public Object visitVarDeclarationInit(VarDeclarationInit aThis, Object o) {
        return(layoutBinary("Variable Declaration", aThis.I, aThis.E));
    }

    @Override
    public Object visitLeaveIdentifier(LeaveIdentifier aThis, Object o) {
        if(aThis.I != null){
            return(layoutUnary("Leave Command", aThis.I));
        }
        return(layoutNullary("Leave Command"));
    }

    @Override
    public Object visitNextIdentifier(NextIdentifier aThis, Object o) {
                if(aThis.I != null){
            return(layoutUnary("Next Command", aThis.I));
        }
        return(layoutNullary("Next Command"));
    }

    @Override
    public Object visitReturnCommand(ReturnCommand aThis, Object o) {
        return(layoutNullary("Return Command"));
    }

}