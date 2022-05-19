package Proyecto_progra;

public abstract class Salario {

    public abstract int calcularSueldo( double horas);
    public void mostrarSalario( double horas){
        System.out.println("Aun no se puede generar Salario especifico.");
    }
}
