
package Proyecto_progra;

public class Honorario extends Salario {
    

    public int calcularSueldo( double horas){
        if (horas<=1) return 0;
        int sueldo;
 
        sueldo = (int) (7500 * horas);
        return sueldo;
    }
    public void mostrarSueldo( double horas){
        System.out.println("Se genero un salario de " + calcularSueldo(horas) + " por honorario en base a "+  horas  + " horas trabajadas.");
    }
    
}
