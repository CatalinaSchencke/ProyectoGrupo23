package Proyecto_progra;

//Clase que hereda clase abstracta Salario
public class Contrato extends Salario {
    
    
    //Implementación de método abstracto para 
    //calcular sueldo según contrato indefinido
    //es decir, le pagan un sueldo por 45 horas
    //y las horas extras a estas aparte
    public int calcularSueldo( double horas){
        if (horas==0) return 0;
        horas=46;
        int sueldo;
        double horasExtra;
        if (horas<=45) return 700000;
        horasExtra= Math.abs(45-horas);
        sueldo= (int) (7500*horasExtra) + 700000;
        return sueldo;    
    }
    
    //Sobreescritura de Método
    //muestra el sueldo con texto especifico para clase Contrato
    public String mostrarSueldo(int sueldo, double horas){
        return ("Se genero un salario de " + sueldo + " por contrato en base a "+  horas  + " horas trabajadas.");
    }
}
