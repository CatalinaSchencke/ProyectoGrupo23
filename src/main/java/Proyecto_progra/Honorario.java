
package Proyecto_progra;

//Clase que hereda clase abstracta Salario
public class Honorario extends Salario {
    
    //Implementación de método abstracto para 
    //calcular sueldo según honorario
    //es decir, le pagan cada hora trabajada independientemente
    public int calcularSueldo( double horas){
        if (horas<=1) return 0;
        int sueldo;
 
        sueldo = (int) (7500 * horas);
        return sueldo;
    }
    
    //Sobreescritura de Método
    //muestra el sueldo con texto especifico para clase Honorario
    public String mostrarSueldo(int sueldo, double horas){
    
        return ("Se genero un salario de " + sueldo + " por honorario en base a "+  horas  + " horas trabajadas.");
        
    }
    
}
