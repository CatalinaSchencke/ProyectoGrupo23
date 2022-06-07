package Proyecto_progra;

public abstract class Salario {

    public abstract int calcularSueldo( double horas);
    public void mostrarSueldo( double horas){
        System.out.println("Aun no se puede generar Salario especifico.");
    }
}
