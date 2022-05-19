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
public abstract class Salario {

    public abstract int calcularSueldo( double horas);
    public void mostrarSalario( double horas){
        System.out.println("Aun no se puede generar Salario especifico.");
    }
}
