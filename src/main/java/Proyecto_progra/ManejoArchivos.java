
package Proyecto_progra;

import java.io.*;

public class ManejoArchivos {
    
    //Crea archivo sin ingresar contenido
    public static void crearArchivo(String nombreArchivo) throws  FileNotFoundException{
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
        } catch (ArchivoException ex) {
            throw new ArchivoException();
        }
    }
    
    //Sobrescribe contenido dentro de un archivo
    public static void sobreEscribirArchivo(String nombreArchivo, String contenido) throws  FileNotFoundException{
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(contenido);
            salida.close();
        }  catch (ArchivoException ex) {
            throw new ArchivoException();
        }
    }
    
    //Anexa lineas de contenido en un archivo
    public static void anexarArchivo(String nombreArchivo, String contenido) throws FileNotFoundException, IOException{
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(contenido);
            salida.close();
        } catch (ArchivoException ex) {
            throw new ArchivoException();
        } catch (ErrorIOArchivosException ex) {
            throw new ErrorIOArchivosException();
        }
    }
    

}