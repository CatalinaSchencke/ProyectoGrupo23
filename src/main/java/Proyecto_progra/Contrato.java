package Proyecto_progra;


public class Contrato extends Salario {
    
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
    public String mostrarSueldo(int sueldo, double horas){
        return ("Se genero un salario de " + sueldo + " por contrato en base a "+  horas  + " horas trabajadas.");
    }
}
