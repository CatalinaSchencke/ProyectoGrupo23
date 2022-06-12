
package Proyecto_progra;

public class Honorario extends Salario {
    

    public int calcularSueldo( double horas){
        if (horas<=1) return 0;
        int sueldo;
 
        sueldo = (int) (7500 * horas);
        return sueldo;
    }
    public String mostrarSueldo(int sueldo, double horas){
    
        return ("Se genero un salario de " + sueldo + " por honorario en base a "+  horas  + " horas trabajadas.");
        
    }
    
}
