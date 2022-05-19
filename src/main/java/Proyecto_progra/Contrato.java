package Proyecto_progra;


public class Contrato extends Salario {
    
    public int calcularSueldo( double horas){
        if (horas==0) return 0;
        int sueldo;
        double horasExtra;
        if (horas<=45) return 700000;
        horasExtra= 45-horas;
        sueldo= (int) (7500*horasExtra) + 700000;
        return sueldo;
    }
    public void mostrarSalario( double horas){
        System.out.println("Se genero un salario de " + calcularSueldo(horas) + " por contrato en base a "+  horas  + " horas trabajadas.");
    }
}
