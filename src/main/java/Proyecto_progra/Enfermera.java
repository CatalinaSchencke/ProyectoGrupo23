package Proyecto_progra;
import java.util.*;

public class Enfermera {
    private String nombre;
    private boolean disponibilidad;
    private String turno;
    private int codigo;
    private int sueldo;
    private String contrato;
    private int horasTrabajadas;
    public Enfermera(){}
    public Enfermera(String nombre) {
        this.nombre = nombre;   
    }
    
    public void mostrarEnfermera(){
        System.out.println("Nombre: "+this.nombre);
        if (this.disponibilidad==true){
            System.out.println("Disponibilidad Inmediata: Si");
        }
        else{
            System.out.println("Disponibilidad Inmediata: No");
        }
        System.out.println("Turno: "+this.turno);
        System.out.println("Codigo Asignado: "+this.codigo);
        System.out.println(" ");
    }

    public void calcularSalario(){
        if (contrato.equals("HONORARIO")){
            Honorario aux= new Honorario();
            sueldo=aux.calcularSueldo(horasTrabajadas);
        }
        if (contrato.equals("INDEFINIDO")){
            Contrato aux= new Contrato();
            sueldo=aux.calcularSueldo(horasTrabajadas);
        }
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
