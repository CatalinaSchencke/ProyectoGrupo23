
package Excepciones;

import java.io.*;

public class ErrorIOArchivosException extends IOException{

    public ErrorIOArchivosException() {
        super("No se pudo anexar en el archivo correctamente.");
    }
    
}
