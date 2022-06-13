package Proyecto_progra;
//Clase Abstracta
public abstract class Salario {

    //Metodo abstracto para calcular sueldos
    public abstract int calcularSueldo( double horas);
    //Metodo que retorna String con texto especifico para Salario
    public String mostrarSueldo(int sueldo, double horas){
        return ("Aun no se puede generar Salario especifico.");
    }
}
