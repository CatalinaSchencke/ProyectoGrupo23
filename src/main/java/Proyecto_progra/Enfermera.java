package Proyecto_progra;
import java.util.*;

public class Enfermera {
    private String nombre;
    private ArrayList<IngSalEnfermeras> IngresoSalida;
    private int salarioPorSemana;
    private boolean disponibilidad;
    private String turno;
    private int codigo;
    
    public Enfermera(){}
    public Enfermera(String nombre, int salarioPorSemana, boolean disponibilidad, String turno, int codigo) {
        this.nombre = nombre;
        this.IngresoSalida = new ArrayList<>();
        this.salarioPorSemana = salarioPorSemana; 
        this.disponibilidad = disponibilidad;
        this.turno = turno;
        this.codigo = codigo;
    }
    
    public void mostrarEnfermera(){
        System.out.println(this.nombre);
        System.out.println(this.salarioPorSemana);
        System.out.println(this.disponibilidad);
        System.out.println(this.turno);
        System.out.println(this.codigo);
        /*int i = 0;
        for (IngSalEnfermeras horario : this.IngresoSalida){
            System.out.println(horario.horaEntrada);
            System.out.println(horario.horaEntrada);
            System.out.println("Dia "+i);
            i++;
        }*/
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

    public int getSalarioPorSemana() {
        return salarioPorSemana;
    }

    public void setSalarioPorSemana(int salarioPorSemana) {
        this.salarioPorSemana = salarioPorSemana;
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
