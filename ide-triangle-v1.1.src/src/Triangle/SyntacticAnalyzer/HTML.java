/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.SyntacticAnalyzer;

import Triangle.TreeDrawer.XMLcreator;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Valeria Chinchilla
 */
public class HTML {
    String nombreArchivo;
    FileWriter escritorHTML;
    
    // Constructor
    public HTML(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    // Agrega una nueva linea
    public void nuevaLinea(String linea){
        try{
            FileWriter escritorHTML = new FileWriter(nombreArchivo, true);
            escritorHTML.write(linea);
            escritorHTML.close();
        }
        catch (IOException error){
            System.out.println("Error al agregar nueva linea al HTML");
        }
    }
    
    // Escribe el programa fuente 
    public void programaFuente(Token currentToken){
        switch(currentToken.kind){
            case Token.ARRAY:
            case Token.CONST:
            case Token.DO:
            case Token.ELSE:
            case Token.END:
            case Token.FOR:
            case Token.FROM:
            case Token.FUNC:
            case Token.IF:
            case Token.IN:
            case Token.INIT:
            case Token.LEAVE:
            case Token.LET:
            case Token.LOCAL:
            case Token.LOOP:
            case Token.NEXT:
            case Token.NIL:
            case Token.OF:
            case Token.PROC:
            case Token.REC:
            case Token.RECORD:
            case Token.RETURN:
            case Token.SELECT:
            case Token.THEN:
            case Token.TO:
            case Token.TYPE:
            case Token.UNTIL:
            case Token.VAR:
            case Token.WHEN:
            case Token.WHILE:
            {
                nuevaLinea(" <font style='padding-left:1em'><b><tt> "+ currentToken.spelling +" </tt></b></font> ");
                break;
            }
            
            case Token.INTLITERAL:
            case Token.CHARLITERAL:
            {
                nuevaLinea(" <font style='padding-left:1em' color=\"#5050DB\"><tt> "+ currentToken.spelling +" </tt></font> ");
                break;
            }
            
            case Token.IDENTIFIER:
            case Token.OPERATOR:
            case Token.DOT:    
            case Token.COLON:    
            case Token.SEMICOLON:    
            case Token.COMMA:    
            case Token.BECOMES:    
            case Token.IS:    
            case Token.BAR:    
            case Token.LPAREN:    
            case Token.RPAREN:    
            case Token.LBRACKET:    
            case Token.RBRACKET:    
            case Token.LCURLY:    
            case Token.RCURLY:    
            {
                nuevaLinea(" <font style='padding-left:1em' color:'#000000'><tt> "+ currentToken.spelling +" </tt></font> ");
                break;
            }
            
            case Token.EOT: {
                break;
            }
            default:
                break;
        }
    }
    
}
