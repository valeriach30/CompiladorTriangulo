/*
 * @(#)Parser.java                        2.1 2003/10/07
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
//PROBANDO GITHUB
package Triangle.SyntacticAnalyzer;

import Triangle.ErrorReporter;
import Triangle.AbstractSyntaxTrees.ActualParameter;
import Triangle.AbstractSyntaxTrees.ActualParameterSequence;
import Triangle.AbstractSyntaxTrees.ArrayAggregate;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.CaseCommand;
import Triangle.AbstractSyntaxTrees.CaseLiteralCommand;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.CaseRangeCommand;
import Triangle.AbstractSyntaxTrees.CasesCommand;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.Command;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.Declaration;
import Triangle.AbstractSyntaxTrees.DoCommand;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.Expression;
import Triangle.AbstractSyntaxTrees.FieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.ForFromAST1;
import Triangle.AbstractSyntaxTrees.ForFromCommand;
import Triangle.AbstractSyntaxTrees.ForInCommand;
import Triangle.AbstractSyntaxTrees.ForInDo;
import Triangle.AbstractSyntaxTrees.FormalParameter;
import Triangle.AbstractSyntaxTrees.FormalParameterSequence;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
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
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleCase;
import Triangle.AbstractSyntaxTrees.MultipleCaseRange;
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
import Triangle.AbstractSyntaxTrees.RecordAggregate;
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
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.TypeDenoter;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UntilCommand;
import Triangle.AbstractSyntaxTrees.UntilEndCommand;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Vname;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;
import Triangle.AbstractSyntaxTrees.WhileEndCommand;
import Triangle.AbstractSyntaxTrees.ToCommand;
import Triangle.AbstractSyntaxTrees.ToCommandLiteral;
import Triangle.AbstractSyntaxTrees.VarDeclarationInit;

public class Parser {

  private Scanner lexicalAnalyser;
  private ErrorReporter errorReporter;
  private Token currentToken;
  private SourcePosition previousTokenPosition;

  public Parser(Scanner lexer, ErrorReporter reporter) {
    lexicalAnalyser = lexer;
    errorReporter = reporter;
    previousTokenPosition = new SourcePosition();
  }

// accept checks whether the current token matches tokenExpected.
// If so, fetches the next token.
// If not, reports a syntactic error.

  void accept (int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      previousTokenPosition = currentToken.position;
      currentToken = lexicalAnalyser.scan();
    } else {
      syntacticError("\"%\" expected here", Token.spell(tokenExpected));
    }
  }

  void acceptIt() {
    previousTokenPosition = currentToken.position;
    currentToken = lexicalAnalyser.scan();
  }

// start records the position of the start of a phrase.
// This is defined to be the position of the first
// character of the first token of the phrase.

  void start(SourcePosition position) {
    position.start = currentToken.position.start;
  }

// finish records the position of the end of a phrase.
// This is defined to be the position of the last
// character of the last token of the phrase.

  void finish(SourcePosition position) {
    position.finish = previousTokenPosition.finish;
  }

  void syntacticError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePosition pos = currentToken.position;
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw(new SyntaxError());
  }

///////////////////////////////////////////////////////////////////////////////
//
// PROGRAMS
//
///////////////////////////////////////////////////////////////////////////////

  public Program parseProgram() {

    Program programAST = null;

    previousTokenPosition.start = 0;
    previousTokenPosition.finish = 0;
    currentToken = lexicalAnalyser.scan();

    try {
      Command cAST = parseCommand();
      programAST = new Program(cAST, previousTokenPosition);
      if (currentToken.kind != Token.EOT) {
        syntacticError("\"%\" not expected after end of program",
          currentToken.spelling);
      }
    }
    catch (SyntaxError s) { return null; }
    return programAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// LITERALS
//
///////////////////////////////////////////////////////////////////////////////

// parseIntegerLiteral parses an integer-literal, and constructs
// a leaf AST to represent it.

  IntegerLiteral parseIntegerLiteral() throws SyntaxError {
    IntegerLiteral IL = null;

    if (currentToken.kind == Token.INTLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      IL = new IntegerLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      IL = null;
      syntacticError("integer literal expected here", "");
    }
    return IL;
  }

// parseCharacterLiteral parses a character-literal, and constructs a leaf
// AST to represent it.

  CharacterLiteral parseCharacterLiteral() throws SyntaxError {
    CharacterLiteral CL = null;

    if (currentToken.kind == Token.CHARLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      CL = new CharacterLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      CL = null;
      syntacticError("character literal expected here", "");
    }
    return CL;
  }
  //Autores: Gabriel Fallas, Kevin Rodriguez y Hilary Castro.
  CaseLiteralCommand parseCaseLiteral() throws SyntaxError{
    CaseLiteralCommand caseLiteralAST = null;
    SourcePosition commandPos = new SourcePosition();
    start(commandPos);
    if(currentToken.kind == Token.INTLITERAL){
        IntegerLiteral c2AST = parseIntegerLiteral();
        finish(commandPos);
        caseLiteralAST = new CaseLiteralCommand(c2AST, commandPos);
    }
    else if(currentToken.kind == Token.CHARLITERAL){
        CharacterLiteral c2AST = parseCharacterLiteral();
        finish(commandPos);
        caseLiteralAST = new CaseLiteralCommand(c2AST, commandPos);
    }
    else{
       caseLiteralAST = null; 
       syntacticError("character literal or integer literal expected here", "");
    }
    return caseLiteralAST;
  }
  
  //Autores: Gabriel Fallas, Kevin Rodriguez, Hillary Castro
  CaseRangeCommand parseCaseRangeCommand() throws SyntaxError{
      CaseRangeCommand caseRangeCommandAST = null;
      ToCommandLiteral ToCommandLiteralAST = null;
      SourcePosition commandPos = new SourcePosition();
      start(commandPos); 
      CaseLiteralCommand c2AST = parseCaseLiteral();
      if(currentToken.kind == Token.TO){
        acceptIt();
        CaseLiteralCommand c3AST = parseCaseLiteral();
        ToCommandLiteralAST = new ToCommandLiteral(c3AST, commandPos);
        finish(commandPos);
        caseRangeCommandAST = new CaseRangeCommand(c2AST, ToCommandLiteralAST, commandPos);
      }
      /*else if(currentToken.kind == Token.BAR){
         acceptIt();
         CaseLiteralCommand c3AST = parseCaseLiteral();
         finish(commandPos);
        caseRangeCommandAST = new CaseRangeCommand(c2AST, commandPos);
      }*/
      else if(currentToken.kind == Token.THEN || currentToken.kind == Token.BAR){
         finish(commandPos);
        caseRangeCommandAST = new CaseRangeCommand(c2AST, commandPos);
      }
      else{
        caseRangeCommandAST = null; 
        syntacticError("character literal or integer literal expected here", "");  
      }
      return caseRangeCommandAST;
    }
   //Autores: Gabriel Fallas, Kevin Rodriguez, Hillary Castro
  CaseLiterals parseCaseLiteralsCommand() throws SyntaxError{
      CaseLiterals clAST = null;
      SourcePosition actualsPos = new SourcePosition();
      start(actualsPos);
      CaseRangeCommand c2AST = parseCaseRangeCommand();
      if(currentToken.kind == Token.THEN){
          SingleCaseRange scrAST = new SingleCaseRange(c2AST, actualsPos);
          finish(actualsPos);
          clAST = new CaseLiterals(scrAST, actualsPos);
      }
      else if((currentToken.kind != Token.THEN) && (currentToken.kind != Token.BAR)){
          clAST = null;
          syntacticError("| or then expected here", "");
      }
      else{
          MultipleCaseRange mcrAST = new MultipleCaseRange(c2AST, actualsPos);
          while (currentToken.kind == Token.BAR){
            acceptIt();
            CaseRangeCommand c3AST = parseCaseRangeCommand();
            finish(actualsPos);
            mcrAST = new MultipleCaseRange(mcrAST, c3AST, actualsPos);
        }
        clAST = new CaseLiterals(mcrAST, actualsPos); 
      }
      return clAST;
  }
  //Autores: Kevin Rodriguez, Hilary Castro, Gabriel Fallas
  CaseCommand parseCaseCommand() throws SyntaxError{
      CaseCommand c1AST = null;
      SourcePosition actualsPos = new SourcePosition();
      start(actualsPos);
      if(currentToken.kind == Token.WHEN){
        acceptIt();
        CaseLiterals c2AST = parseCaseLiteralsCommand();
        accept(Token.THEN);
        Command c3AST = parseCommand();
        finish(actualsPos);
        c1AST = new CaseCommand(c2AST, c3AST, actualsPos);
        }
      else{
          c1AST = null;
          syntacticError("A Case expected here", "");
      }
      return c1AST;
  }
  CasesCommand parseCasesCommand() throws SyntaxError{
      CasesCommand commandAST = null; // in case there's a syntactic error
      SourcePosition position = new SourcePosition();
      start(position);
      CaseCommand cAST1 = parseCaseCommand();
      if(currentToken.kind == Token.WHEN){
        MultipleCase mCase = new MultipleCase(cAST1, position);
        while(currentToken.kind == Token.WHEN){
            CaseCommand cAST2 = parseCaseCommand();
            finish(position);
            mCase = new MultipleCase(mCase, cAST2,position);
        }
        commandAST = new CasesCommand(mCase, position);
      }
      else if(currentToken.kind == Token.END || currentToken.kind == Token.ELSE){
          finish(position);
          SingleCase sCase = new SingleCase(cAST1, position);
          commandAST = new CasesCommand(sCase, position);
      }
      else{
          commandAST = null;
          syntacticError("when or end expected here", "");
      }
      return commandAST;
  }
  
// parseIdentifier parses an identifier, and constructs a leaf AST to
// represent it.

  Identifier parseIdentifier() throws SyntaxError {
    Identifier I = null;

    if (currentToken.kind == Token.IDENTIFIER) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      I = new Identifier(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      I = null;
      syntacticError("identifier expected here", "");
    }
    return I;
  }


  Identifier parseIdentifierOptional() throws SyntaxError {
    Identifier I = null;

    if (currentToken.kind == Token.IDENTIFIER) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      I = new Identifier(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      I = null;
    }
    return I;
  }
  
// parseOperator parses an operator, and constructs a leaf AST to
// represent it.

  Operator parseOperator() throws SyntaxError {
    Operator O = null;

    if (currentToken.kind == Token.OPERATOR) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      O = new Operator(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      O = null;
      syntacticError("operator expected here", "");
    }
    return O;
  }

///////////////////////////////////////////////////////////////////////////////
//
// COMMANDS
//
///////////////////////////////////////////////////////////////////////////////

 ThenCommand parseThenCommand(SourcePosition commandPos) throws SyntaxError {
    ThenCommand commandAST = null;
    start(commandPos);
    Expression eAST = parseExpression();
    Command cAST = parseCommand();
    finish(commandPos);
    commandAST = new ThenCommand(cAST, commandPos);
    return commandAST;
  }
// parseCommand parses the command, and constructs an AST
// to represent its phrase structure.

  Command parseCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();

    start(commandPos);
    commandAST = parseSingleCommand();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Command c2AST = parseSingleCommand();
      finish(commandPos);
      commandAST = new SequentialCommand(commandAST, c2AST, commandPos);
    }
    return commandAST;
  }
  
  Command parseSingleCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();
    start(commandPos);
    switch (currentToken.kind) {
    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(commandPos);
          commandAST = new CallCommand(iAST, apsAST, commandPos);

        } else {

          Vname vAST = parseRestOfVname(iAST);
          if(currentToken.kind == Token.BECOMES){
            acceptIt();
            Expression eAST = parseExpression();
            finish(commandPos);
            commandAST = new AssignCommand(vAST, eAST, commandPos);
          }
          else{
              syntacticError(":= expected after a variable name", "");
          }
          
        }   
      }
      break;
      
    // -------------------------------- ELIMINAR --------------------------------
      
    // Eliminar begin
    
    /*
    case Token.BEGIN:
      acceptIt();
      commandAST = parseCommand();
      accept(Token.END);
      break;
    */  
      
    // Eliminar let
      
    /*
    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Command cAST = parseSingleCommand();
        finish(commandPos);
        commandAST = new LetCommand(dAST, cAST, commandPos);
      }
      break;
    */  
      
    // -------------------------------- NIL --------------------------------
    // Autor : Valeria Chinchilla
    case Token.NIL:
      {
        acceptIt();
        finish(commandPos);
        commandAST = new EmptyCommand(commandPos);
      }
      break;
      
    // -------------------------------- LET --------------------------------
    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Command cAST = parseCommand();
        accept(Token.END);
        finish(commandPos);
        commandAST = new LetCommand(dAST, cAST, commandPos);
      }
      break;  
    
    // -------------------------------- SELECT ---------------------------------
    // "select" Expression "from" Cases ["else" Command] "end"
    case Token.SELECT: {
        acceptIt();
        // Determinar la expresion
        Expression eAST = parseExpression();
        accept(Token.FROM);
        // Determinar los Cases
        CasesCommand casesCommand = parseCasesCommand();
        // Determinar si hay else 0 o 1 vez
        if(currentToken.kind == Token.ELSE){
            acceptIt();
            Command command = parseCommand();
            accept(Token.END);
            finish(commandPos);
            commandAST = new SelectCommand(eAST, casesCommand,
                                              command,commandPos);
        }
        else if(currentToken.kind != Token.ELSE){
            accept(Token.END);
            finish(commandPos);
            commandAST = new SelectCommand(eAST, casesCommand,
                                              commandPos);
        }
        else{
            syntacticError("Token expected here",
                    "");
        }
        break;
    }
    
    // ---------------------------------- IF -----------------------------------
    case Token.IF: {
        acceptIt(); //Se acepta el if
        Expression eAST = parseExpression(); //Se acepta el expression.
        accept(Token.THEN);
        Command c1AST = parseCommand();
        Command c2AST = parseBarThen(); 
        finish(commandPos);
        commandAST = new IfCommand(eAST, c1AST, c2AST, commandPos);
      }
      break;
    
    // -------------------------------- LOOP -----------------------------------
    // Autor : Valeria Chinchilla
    case Token.LOOP: 
      {
        acceptIt();

        // Agarrar el identificador 
        Identifier iAST = parseIdentifierOptional();
        
        // Determinar que sigue despues del loop 
        switch(currentToken.kind){
            
            // --------------------------------> Caso 1 <--------------------------------
            // "loop" [ Identifier ] "while" Expression "do" Command "end"
            
            case Token.WHILE: {
                // Crear el primer arbol
                acceptIt();
                WhileCommand WhileAST = whileDo(commandPos);
                // Crear el arbol final
                commandAST = new LoopCommandAST1(iAST, WhileAST, commandPos);
                break;
            }
            
            
            
            // --------------------------------> Caso 2 <--------------------------------
            // "loop" [ Identifier ] "until" Expression "do" Command "end"

            case Token.UNTIL: {
                acceptIt();
                // Crear el primer arbol
                UntilCommand UntilAST = UntilDo(commandPos);
                // Crear el arbol final
                commandAST = new LoopUntilDoAST(iAST, UntilAST, commandPos);
                break;
            }
            
            
            // ------------------------------> Caso 3 y 4 <------------------------------
            case Token.DO: {
                acceptIt();
                
                // Aceptar el command
                Command cAST = parseCommand();
                
                // Crear AST del Do
                DoCommand DoAST;
                DoAST = new DoCommand(cAST, commandPos);
                
                // Determinar que sigue despues del command
                switch(currentToken.kind){
                    
                    // ------------------ Caso 3 ------------------
                    
                    // "loop" [ Identifier ] "do" Command "while" Expression "end"
                    
                    case Token.WHILE:{
                        acceptIt();
                        // Crear el arbol del while
                        WhileEndCommand WhileAST = whileEnd(commandPos); 
                        // Crear el arbol final
                        commandAST = new LoopWhileEndAST(iAST, DoAST, WhileAST, commandPos);
                        break;
                    }
                    
                    // ------------------ Caso 4 ------------------
                    
                    // "loop" [ Identifier ] "do" Command "until" Expression "end"
                    
                    case Token.UNTIL:{
                        acceptIt();
                        // Crear el arbol del until
                        UntilEndCommand UntilAST = UntilEnd(commandPos);
                        // Crear el arbol final
                        commandAST = new LoopUntilEndAST(iAST, DoAST, UntilAST, commandPos);
                        break;
                    }
                    default :{
                        syntacticError("Expected 'while' or 'until' after the command", currentToken.spelling);
                        break;
                    }
                }
                break;
            }
            
            
            // ----------------------------> Resto de casos <-----------------------------
            case Token.FOR: {
                acceptIt();
                // Agarrar el identificador
                Identifier iAST2 = parseIdentifier();
                
                switch(currentToken.kind){
                    case Token.FROM: {
                        
                        acceptIt();
                        // Se obtiene el primer arbol ("for" Identifier "from" Expression)
                        ForFromCommand ForFromAST = ParseForFromCommand(commandPos, iAST2);
                        
                        // Aceptar el token to
                        accept(Token.TO);
                        
                        // Guardar la expresion
                        Expression eAST = parseExpression();
                        
                        // Crear el AST del To
                        ToCommand ToAST = new ToCommand(eAST, commandPos);
                        
                        // Construir la ultima parte del arbol
                        switch(currentToken.kind){
                            
                            // ------------------ Caso 5 ------------------
                        
                            /* "loop" [ Identifier ] "for" Identifier "from" Expression "to" Expression
                               "do" Command "end"
                            */
                            case Token.DO:{
                                acceptIt();
                                DoCommand DoAST2 = ParseDoCommand(commandPos);
                                commandAST = new ForFromAST1(iAST, ForFromAST, 
                                             ToAST, DoAST2, commandPos);
                                break;
                            }
                            
                            // ------------------ Caso 6 ------------------
                            
                            /*  "loop" [ Identifier ] "for" Identifier "from" Expression "to" Expression
                                "while" Expression "do" Command "end"
                            */
                            case Token.WHILE:{
                                acceptIt();
                                // Crear el ast del while
                                WhileCommand WhileAST = whileDo(commandPos);
                                // Crear el arbol final
                                commandAST = new LoopForFromWhile(iAST, ForFromAST,
                                             ToAST, WhileAST, commandPos);
                                break;
                            }
                            
                            // ------------------ Caso 7 ------------------
                            
                            /*  "loop" [ Identifier ] "for" Identifier "from" Expression "to" Expression
                                "until" Expression "do" Command "end"
                            */
                            
                            case Token.UNTIL:{
                                acceptIt();
                                // Crear el ast del until
                                UntilCommand UntilAST = UntilDo(commandPos);
                                // Crear el arbol final
                                commandAST = new LoopForFromUntil(iAST, ForFromAST, 
                                             ToAST, UntilAST, commandPos);
                                break;
                            }
                            default :{
                                syntacticError("Expected 'while', 'do' or 'until' after the expression", currentToken.spelling);
                                break;
                            }
                        }
                    break;
                    }
                    case Token.IN: {
                        // "loop" [ Identifier ] "for" Identifier "in" Expression "do" Command "end"
                        
                        acceptIt();
                        
                        // Se obtiene el primer arbol ("for" Identifier "in" Expression)
                        ForInCommand ForInAST = ParseForInCommand(commandPos, iAST2);
                        
                        // Obtener el segundo ast
                        
                        // Aceptar el token do
                        accept(Token.DO);
                        
                        // Guardar el comando
                        Command cAST = parseCommand();
                        
                        // Crear AST del Do
                        DoCommand DoAST;
                        DoAST = new DoCommand(cAST, commandPos);
                        
                        // End
                        if(currentToken.kind == Token.END){
                            acceptIt();
                            finish(commandPos);
                            commandAST = new ForInDo (iAST, ForInAST, DoAST, commandPos);
                        }
                        // Error
                        else{
                            syntacticError("Expected 'end' after the command", currentToken.spelling);
                        }
                        break;
                    }
                    default: 
                        syntacticError("Expected 'from' or 'in' after the identifier", currentToken.spelling);
                        acceptIt();
                        break;
                }
            break;
            }
            default:
                syntacticError("Expected 'while', 'do', 'until' or 'for' after loop", currentToken.spelling);
                break;
        }
      }
      break;
      
    // -------------------------------- ELIMINAR --------------------------------
    
    // Eliminar if

    /*
    case Token.IF:
      {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.THEN);
        Command c1AST = parseSingleCommand();
        accept(Token.ELSE);
        Command c2AST = parseSingleCommand();
        finish(commandPos);
        commandAST = new IfCommand(eAST, c1AST, c2AST, commandPos);
      }
      break;
    */
      
    // Eliminar while
     
    /*
    case Token.WHILE:
      {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.DO);
        Command cAST = parseSingleCommand();
        finish(commandPos);
        commandAST = new WhileCommand(eAST, cAST, commandPos);
      }
      break;

    case Token.SEMICOLON:
    case Token.END:
    case Token.ELSE:
    case Token.IN:
    
      
    case Token.EOT:

      finish(commandPos);
      commandAST = new EmptyCommand(commandPos);
      break;
    */
    case Token.LEAVE: {
        acceptIt();
        // Agarrar el identificador 
        Identifier iAST = parseIdentifierOptional();
        // Crear AST
        commandAST = new LeaveIdentifier(iAST, commandPos);
        break;
    }  
    case Token.NEXT: {
        acceptIt();
        // Agarrar el identificador 
        Identifier iAST = parseIdentifierOptional();
        // Crear AST
        commandAST = new NextIdentifier(iAST, commandPos);
        break;
    } 
    case Token.RETURN: {
        acceptIt();
        // Crear AST
        commandAST = new ReturnCommand(commandPos);
        break;
    } 
    default:
      syntacticError("\"%\" cannot start a command",
        currentToken.spelling);
      break;

    }

    return commandAST;
    
   //Autores: Gabriel Fallas, Kevin Rodriguez y Hilary Castro.
    
  }

///////////////////////////////////////////////////////////////////////////////
//
// EXPRESSIONS
//
///////////////////////////////////////////////////////////////////////////////

  Expression parseExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();

    start (expressionPos);

    switch (currentToken.kind) {

    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Expression eAST = parseExpression();
        finish(expressionPos);
        expressionAST = new LetExpression(dAST, eAST, expressionPos);
      }
      break;

    case Token.IF:
      {
        acceptIt();
        Expression e1AST = parseExpression();
        accept(Token.THEN);
        Expression e2AST = parseExpression();
        accept(Token.ELSE);
        Expression e3AST = parseExpression();
        finish(expressionPos);
        expressionAST = new IfExpression(e1AST, e2AST, e3AST, expressionPos);
      }
      break;

    default:
      expressionAST = parseSecondaryExpression();
      break;
    }
    return expressionAST;
  }

  Expression parseSecondaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    expressionAST = parsePrimaryExpression();
    while (currentToken.kind == Token.OPERATOR) {
      Operator opAST = parseOperator();
      Expression e2AST = parsePrimaryExpression();
      expressionAST = new BinaryExpression (expressionAST, opAST, e2AST,
        expressionPos);
    }
    return expressionAST;
  }

  Expression parsePrimaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    switch (currentToken.kind) {

    case Token.INTLITERAL:
      {
        IntegerLiteral ilAST = parseIntegerLiteral();
        finish(expressionPos);
        expressionAST = new IntegerExpression(ilAST, expressionPos);
      }
      break;

    case Token.CHARLITERAL:
      {
        CharacterLiteral clAST= parseCharacterLiteral();
        finish(expressionPos);
        expressionAST = new CharacterExpression(clAST, expressionPos);
      }
      break;

    case Token.LBRACKET:
      {
        acceptIt();
        ArrayAggregate aaAST = parseArrayAggregate();
        accept(Token.RBRACKET);
        finish(expressionPos);
        expressionAST = new ArrayExpression(aaAST, expressionPos);
      }
      break;

    case Token.LCURLY:
      {
        acceptIt();
        RecordAggregate raAST = parseRecordAggregate();
        accept(Token.RCURLY);
        finish(expressionPos);
        expressionAST = new RecordExpression(raAST, expressionPos);
      }
      break;

    case Token.IDENTIFIER:
      {
        Identifier iAST= parseIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(expressionPos);
          expressionAST = new CallExpression(iAST, apsAST, expressionPos);

        } else {
          Vname vAST = parseRestOfVname(iAST);
          finish(expressionPos);
          expressionAST = new VnameExpression(vAST, expressionPos);
        }
      }
      break;

    case Token.OPERATOR:
      {
        Operator opAST = parseOperator();
        Expression eAST = parsePrimaryExpression();
        finish(expressionPos);
        expressionAST = new UnaryExpression(opAST, eAST, expressionPos);
      }
      break;

    case Token.LPAREN:
      acceptIt();
      expressionAST = parseExpression();
      accept(Token.RPAREN);
      break;

    default:
      syntacticError("\"%\" cannot start an expression",
        currentToken.spelling);
      break;

    }
    return expressionAST;
  }

  RecordAggregate parseRecordAggregate() throws SyntaxError {
    RecordAggregate aggregateAST = null; // in case there's a syntactic error
    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Identifier iAST = parseIdentifier();
    accept(Token.IS);
    Expression eAST = parseExpression();

    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      RecordAggregate aAST = parseRecordAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleRecordAggregate(iAST, eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleRecordAggregate(iAST, eAST, aggregatePos);
    }
    return aggregateAST;
  }

  ArrayAggregate parseArrayAggregate() throws SyntaxError {
    ArrayAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Expression eAST = parseExpression();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ArrayAggregate aAST = parseArrayAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleArrayAggregate(eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleArrayAggregate(eAST, aggregatePos);
    }
    return aggregateAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// VALUE-OR-VARIABLE NAMES
//
///////////////////////////////////////////////////////////////////////////////

  Vname parseVname () throws SyntaxError {
    Vname vnameAST = null; // in case there's a syntactic error
    Identifier iAST = parseIdentifier();
    vnameAST = parseRestOfVname(iAST);
    return vnameAST;
  }

  Vname parseRestOfVname(Identifier identifierAST) throws SyntaxError {
    SourcePosition vnamePos = new SourcePosition();
    vnamePos = identifierAST.position;
    Vname vAST = new SimpleVname(identifierAST, vnamePos);

    while (currentToken.kind == Token.DOT ||
           currentToken.kind == Token.LBRACKET) {

      if (currentToken.kind == Token.DOT) {
        acceptIt();
        Identifier iAST = parseIdentifier();
        vAST = new DotVname(vAST, iAST, vnamePos);
      } else {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.RBRACKET);
        finish(vnamePos);
        vAST = new SubscriptVname(vAST, eAST, vnamePos);
      }
    }
    return vAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// DECLARATIONS
//
///////////////////////////////////////////////////////////////////////////////

  //Autores: Gabriel Fallas, Hilary Castro, Kevin Rodriguez.
  //Se modifica parseCompoundDeclaration por parseSingleDeclaration
  Declaration parseDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error
    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    declarationAST = parseCompoundDeclaration();
    while (currentToken.kind == Token.SEMICOLON) {  
      acceptIt();
      Declaration d2AST = parseCompoundDeclaration();
      finish(declarationPos);
      //Un sequential declaration son dos single declaration.
      declarationAST = new SequentialDeclaration(declarationAST, d2AST,
        declarationPos);
    }
    return declarationAST;
  }
  //Autores: Gabriel Fallas, Hilary Castro, Kevin Rodriguez.
  Declaration parseCompoundDeclaration() throws SyntaxError{
      //Aqui se hace un single declaration a partir del declaration
      //que se necesita.
      Declaration declarationAST = null;
      SourcePosition position = new SourcePosition();
      start(position);
      switch(currentToken.kind){
          case Token.CONST:
          case Token.VAR:
          case Token.PROC:
          case Token.FUNC:
          case Token.TYPE:
              declarationAST = parseSingleDeclaration();
              finish(position);
              break;
          case Token.REC:
              acceptIt();
              declarationAST = parseProcFuncs();
              accept(Token.END);
              finish(position);
              break;
          case Token.LOCAL:
              acceptIt();
              Declaration dAST = parseDeclaration();
              accept(Token.IN);
              Declaration dAST2 = parseDeclaration();
              accept(Token.END);
              finish(position);
              declarationAST = new LocalDeclaration(dAST, dAST2, position);
              break;
          default:
              syntacticError("\"%\" cannot start a declaration.",
                             currentToken.spelling);
              break;
      }
      return declarationAST;
   }
  //Autores: Gabriel Fallas, Hilary Castro, Kevin Rodriguez.
  Declaration parseProcFunc() throws SyntaxError{
      Declaration procFuncAST = null;
      SourcePosition position = new SourcePosition();
      start(position);
      switch(currentToken.kind){
          case Token.PROC:
              acceptIt();
              Identifier iAST = parseIdentifier();
              accept(Token.LPAREN);
              FormalParameterSequence formalAST = parseFormalParameterSequence();
              accept(Token.RPAREN);
              accept(Token.IS);
              Command cAST = parseCommand();
              accept(Token.END);
              finish(position);
              procFuncAST = new ProcDeclaration(iAST, formalAST, cAST, position);
              break;
          case Token.FUNC:
              acceptIt();
              Identifier identifierAST = parseIdentifier();
              accept(Token.LPAREN);
              FormalParameterSequence fpsAST = parseFormalParameterSequence();
              accept(Token.RPAREN);
              accept(Token.COLON);
              TypeDenoter tAST = parseTypeDenoter();
              accept(Token.IS);
              Expression eAST = parseExpression();
              finish(position);
              procFuncAST = new FuncDeclaration(identifierAST, fpsAST, tAST, eAST, position);
              break;
          default:
              syntacticError("expected here a proc or func",
                             currentToken.spelling);
              break;
      }
      return procFuncAST;
  }
  //Autores: Gabriel Fallas, Hilary Castro, Kevin Rodriguez.
  Declaration parseProcFuncs() throws SyntaxError{
      Declaration declarationAST = null; // in case there's a syntactic error
      SourcePosition position = new SourcePosition();
      start(position);
      declarationAST = parseProcFunc();
      if(currentToken.kind == Token.BAR){
          while(currentToken.kind == Token.BAR){
              acceptIt();
              Declaration dAST2 = parseProcFunc();
              finish(position);
              declarationAST = new SequentialDeclaration(declarationAST,
                               dAST2, position);
          }
      }else{
          syntacticError("| expected here.","");
      }
      return declarationAST;
  }

  Declaration parseSingleDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);

    switch (currentToken.kind) {

    case Token.CONST:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(declarationPos);
        declarationAST = new ConstDeclaration(iAST, eAST, declarationPos);
      }
      break;
    
    case Token.VAR: // Autor : Valeria Chinchilla
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        // Determinar que token sigue
        switch(currentToken.kind){
            case Token.COLON: {
                accept(Token.COLON);
                TypeDenoter tAST = parseTypeDenoter();
                finish(declarationPos);
                declarationAST = new VarDeclaration(iAST, tAST, declarationPos);
                break;
            }
            case Token.INIT:{
                accept(Token.INIT);
                Expression eAST = parseExpression();
                finish(declarationPos);
                declarationAST = new VarDeclarationInit(iAST, eAST, declarationPos);
                break;
            }
            default:{
                syntacticError("Expected ':' or 'init' after the identifier", currentToken.spelling);
            }
        }
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.IS);
        
        // Se cambia de single command a command
        Command cAST = parseCommand();
        
        // Determina que haya un end al final
        
        if(currentToken.kind == Token.END){
            acceptIt();
            finish(declarationPos);
            declarationAST = new ProcDeclaration(iAST, fpsAST, cAST, declarationPos);
        }
        // Error
        else{
            syntacticError("Expected end after the command", currentToken.spelling);
        }
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(declarationPos);
        declarationAST = new FuncDeclaration(iAST, fpsAST, tAST, eAST,
          declarationPos);
      }
      break;

    case Token.TYPE:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        TypeDenoter tAST = parseTypeDenoter();
        finish(declarationPos);
        declarationAST = new TypeDeclaration(iAST, tAST, declarationPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a declaration",
        currentToken.spelling);
      break;

    }
    return declarationAST;
  }
  

///////////////////////////////////////////////////////////////////////////////
//
// PARAMETERS
//
///////////////////////////////////////////////////////////////////////////////

  FormalParameterSequence parseFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST;

    SourcePosition formalsPos = new SourcePosition();

    start(formalsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(formalsPos);
      formalsAST = new EmptyFormalParameterSequence(formalsPos);

    } else {
      formalsAST = parseProperFormalParameterSequence();
    }
    return formalsAST;
  }

  FormalParameterSequence parseProperFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST = null; // in case there's a syntactic error;

    SourcePosition formalsPos = new SourcePosition();
    start(formalsPos);
    FormalParameter fpAST = parseFormalParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FormalParameterSequence fpsAST = parseProperFormalParameterSequence();
      finish(formalsPos);
      formalsAST = new MultipleFormalParameterSequence(fpAST, fpsAST,
        formalsPos);

    } else {
      finish(formalsPos);
      formalsAST = new SingleFormalParameterSequence(fpAST, formalsPos);
    }
    return formalsAST;
  }

  FormalParameter parseFormalParameter() throws SyntaxError {
    FormalParameter formalAST = null; // in case there's a syntactic error;

    SourcePosition formalPos = new SourcePosition();
    start(formalPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new ConstFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new VarFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        finish(formalPos);
        formalAST = new ProcFormalParameter(iAST, fpsAST, formalPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new FuncFormalParameter(iAST, fpsAST, tAST, formalPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a formal parameter",
        currentToken.spelling);
      break;

    }
    return formalAST;
  }


  ActualParameterSequence parseActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST;

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(actualsPos);
      actualsAST = new EmptyActualParameterSequence(actualsPos);

    } else {
      actualsAST = parseProperActualParameterSequence();
    }
    return actualsAST;
  }

  ActualParameterSequence parseProperActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST = null; // in case there's a syntactic error

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    ActualParameter apAST = parseActualParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ActualParameterSequence apsAST = parseProperActualParameterSequence();
      finish(actualsPos);
      actualsAST = new MultipleActualParameterSequence(apAST, apsAST,
        actualsPos);
    } else {
      finish(actualsPos);
      actualsAST = new SingleActualParameterSequence(apAST, actualsPos);
    }
    return actualsAST;
  }

  ActualParameter parseActualParameter() throws SyntaxError {
    ActualParameter actualAST = null; // in case there's a syntactic error

    SourcePosition actualPos = new SourcePosition();

    start(actualPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
    case Token.INTLITERAL:
    case Token.CHARLITERAL:
    case Token.OPERATOR:
    case Token.LET:
    case Token.IF:
    case Token.LPAREN:
    case Token.LBRACKET:
    case Token.LCURLY:
      {
        Expression eAST = parseExpression();
        finish(actualPos);
        actualAST = new ConstActualParameter(eAST, actualPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Vname vAST = parseVname();
        finish(actualPos);
        actualAST = new VarActualParameter(vAST, actualPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new ProcActualParameter(iAST, actualPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new FuncActualParameter(iAST, actualPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start an actual parameter",
        currentToken.spelling);
      break;

    }
    return actualAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// TYPE-DENOTERS
//
///////////////////////////////////////////////////////////////////////////////

  TypeDenoter parseTypeDenoter() throws SyntaxError {
    TypeDenoter typeAST = null; // in case there's a syntactic error
    SourcePosition typePos = new SourcePosition();

    start(typePos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        finish(typePos);
        typeAST = new SimpleTypeDenoter(iAST, typePos);
      }
      break;

    case Token.ARRAY:
      {
        acceptIt();
        IntegerLiteral ilAST = parseIntegerLiteral();
        accept(Token.OF);
        TypeDenoter tAST = parseTypeDenoter();
        finish(typePos);
        typeAST = new ArrayTypeDenoter(ilAST, tAST, typePos);
      }
      break;

    case Token.RECORD:
      {
        acceptIt();
        FieldTypeDenoter fAST = parseFieldTypeDenoter();
        accept(Token.END);
        finish(typePos);
        typeAST = new RecordTypeDenoter(fAST, typePos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a type denoter",
        currentToken.spelling);
      break;

    }
    return typeAST;
  }

  FieldTypeDenoter parseFieldTypeDenoter() throws SyntaxError {
    FieldTypeDenoter fieldAST = null; // in case there's a syntactic error

    SourcePosition fieldPos = new SourcePosition();

    start(fieldPos);
    Identifier iAST = parseIdentifier();
    accept(Token.COLON);
    TypeDenoter tAST = parseTypeDenoter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FieldTypeDenoter fAST = parseFieldTypeDenoter();
      finish(fieldPos);
      fieldAST = new MultipleFieldTypeDenoter(iAST, tAST, fAST, fieldPos);
    } else {
      finish(fieldPos);
      fieldAST = new SingleFieldTypeDenoter(iAST, tAST, fieldPos);
    }
    return fieldAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// FUNCIONES DE APOYO PARA SINGLE COMMAND
//
///////////////////////////////////////////////////////////////////////////////
    
    // Autor : Valeria Chinchilla
    private WhileCommand whileDo(SourcePosition commandPos) throws SyntaxError{
        
        start(commandPos);
        WhileCommand commandAST = null;
        
        // Obtener la expresion
        Expression eAST = parseExpression();
        
        // Aceptar el command
        accept(Token.DO);
        Command cAST = parseCommand();
        
        // Crear AST del Do
        DoCommand DoAST;
        DoAST = new DoCommand(cAST, commandPos);
        
        // End
        if(currentToken.kind == Token.END){
            acceptIt();
            finish(commandPos);
            commandAST = new WhileCommand (eAST, DoAST, commandPos);
        }
        
        // Error
        else{
            syntacticError("Expected END here", currentToken.spelling);
        }
        
        // Retornar el arbol
        return commandAST;
    }
    
    // Autor : Valeria Chinchilla
    private ForFromCommand ParseForFromCommand(SourcePosition commandPos, Identifier iAST2) throws SyntaxError {
        start(commandPos);
        ForFromCommand commandAST = null;
        Expression eAST = parseExpression();
        finish(commandPos);
        commandAST = new ForFromCommand(iAST2, eAST, commandPos);
        return commandAST;
    }
    
    // Autor : Valeria Chinchilla
    private DoCommand ParseDoCommand(SourcePosition commandPos) throws SyntaxError {
        start(commandPos);
        DoCommand commandAST = null;
        Command cAST = parseCommand();
        accept(Token.END);
        finish(commandPos);
        commandAST = new DoCommand(cAST, commandPos);
        return commandAST;
    }

    // Autor : Valeria Chinchilla
    private UntilCommand UntilDo(SourcePosition commandPos) throws SyntaxError {
        start(commandPos);
        UntilCommand commandAST = null;
        
        // Obtener la expresion
        Expression eAST = parseExpression();
        
        // Aceptar el command
        accept(Token.DO);
        Command cAST = parseCommand();
        
        // Crear AST del Do
        DoCommand DoAST;
        DoAST = new DoCommand(cAST, commandPos);
        
        // End
        if(currentToken.kind == Token.END){
            acceptIt();
            finish(commandPos);
            commandAST = new UntilCommand(eAST, DoAST, commandPos);
        }
        
        // Error
        else{
            syntacticError("Expected END here", currentToken.spelling);
        }
        
        // Retornar el arbol
        return commandAST;
    }

    // Autor : Valeria Chinchilla
    private WhileEndCommand whileEnd(SourcePosition commandPos) throws SyntaxError {
        start(commandPos);
        WhileEndCommand commandAST = null;
        
        // Obtener la expresion
        Expression eAST = parseExpression();
        
        // End
        if(currentToken.kind == Token.END){
            acceptIt();
            finish(commandPos);
            commandAST = new WhileEndCommand (eAST, commandPos);
        }
        
        // Error
        else{
            syntacticError("Expected END here", currentToken.spelling);
        }
        
        // Retornar el arbol
        return commandAST;
    }
    
    // Autor : Valeria Chinchilla
    private UntilEndCommand UntilEnd(SourcePosition commandPos) throws SyntaxError {
        start(commandPos);
        UntilEndCommand commandAST = null;
        
        // Obtener la expresion
        Expression eAST = parseExpression();
        
        // End
        if(currentToken.kind == Token.END){
            acceptIt();
            finish(commandPos);
            commandAST = new UntilEndCommand (eAST, commandPos);
        }
        
        // Error
        else{
            syntacticError("Expected END here", currentToken.spelling);
        }
        
        // Retornar el arbol
        return commandAST;}
    
    // Autor : Valeria Chinchilla
    private ForInCommand ParseForInCommand(SourcePosition commandPos, Identifier iAST2) throws SyntaxError {
        start(commandPos);
        ForInCommand commandAST = null;
        Expression eAST = parseExpression();
        finish(commandPos);
        commandAST = new ForInCommand(iAST2, eAST, commandPos);
        return commandAST;
    }

  Command parseBarThen() throws SyntaxError {

    Command commandAST = null; 
    SourcePosition commandPos = new SourcePosition();
    start(commandPos);

    switch (currentToken.kind) {
      case Token.ELSE: {
        acceptIt();
        Command cAST = parseCommand();
        accept(Token.END);
        commandAST = cAST;
      }
        break;
      case Token.BAR: {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.THEN);
        Command cAST = parseCommand();
        Command c2AST = parseBarThen();
        finish(commandPos);
        commandAST = new IfCommand(eAST, cAST, c2AST, commandPos); 
      }
        break;

    default:
        syntacticError("| or else expected here", "");
        break;
    }
    return commandAST;
  }
}
