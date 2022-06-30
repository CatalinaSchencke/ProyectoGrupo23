/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

import java.io.*;

public class ArchivoException extends FileNotFoundException {

    public ArchivoException() {
        super("El archivo no se ha encontrado.");
    }

    
    
}
