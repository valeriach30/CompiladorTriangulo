/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Triangle.TreeDrawer;
import Triangle.AbstractSyntaxTrees.Program;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
    * @author Valeria Chinchilla
 */

// Clase para crear el archivo del XML
public class XML {
    public static void crear(Program AST, String nombreArchivo) throws IOException{
        try{
            try(FileWriter escritorXML = new FileWriter(nombreArchivo)){
                escritorXML.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
                XMLcreator layout = new XMLcreator(escritorXML);
                AST.visit(layout, null);
            }
        }
        catch(IOException e){
            System.out.println("No se logro crear el XML, hubieron errores");
        }
    }
}
