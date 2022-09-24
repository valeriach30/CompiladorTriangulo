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
    private void nuevaLinea(String linea){
        try{
            FileWriter escritorHTML = new FileWriter(nombreArchivo, true);
            escritorHTML.write(linea);
            escritorHTML.write("\n");
            escritorHTML.close();
        }
        catch (IOException error){
            System.out.println("Error al agregar nueva linea al HTML");
            System.out.println(linea);
            System.out.println(error.getMessage());
        }
    }
    
    // Escribe el programa fuente 
    public void programaFuente(Token currentToken){
        switch(currentToken.kind){
            case Token.LET:{
                nuevaLinea("<p><b>LET</b></p>");
                break;
            }
            default:
                nuevaLinea("cualquiercosa");
                break;
        }
    }
    
    // Crea el escritor del HTML
    public void crear(){
        try{
            try(FileWriter escritor = new FileWriter(nombreArchivo, true)){
                this.escritorHTML = escritor;
                escritorHTML.write("<p> Archivo <p>");
            }
        }
        catch(IOException e){
            System.out.println("No se logro crear el HTML, hubieron errores");
        }
    }
}
