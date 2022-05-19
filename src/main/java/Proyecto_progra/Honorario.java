/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto_progra;

/**
 *
 * @author vicen
 */
public class Honorario extends Salario {
    

    public int calcularSueldo( double horas){
        if (horas<=1) return 0;
        int sueldo;
 
        sueldo = (int) (7500 * horas);
        return sueldo;
    }
    public void mostrarSalario( double horas){
        System.out.println("Se genero un salario de " + calcularSueldo(horas) + " por honorario en base a "+  horas  + " horas trabajadas.");
    }
    
}
