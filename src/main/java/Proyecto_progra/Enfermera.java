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
    public Enfermera(String nombre) {
        this.nombre = nombre;
        this.IngresoSalida = new ArrayList<>();
        
    }
    
    public void mostrarEnfermera(){
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Salario Actual: "+this.salarioPorSemana);
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
    public void mostrarHorario(){
        int i = 0;
        System.out.println(this.nombre);
        if (tieneHorario()==false) System.out.println("No existe un Horario");
        else{
            for (IngSalEnfermeras horario : this.IngresoSalida){
                System.out.println("Dia "+i);
                System.out.println(horario.getHoraSalida());
                System.out.println(horario.getHoraEntrada());
            
            i++;
            }
        }
    }
    public void agregarHorario(int horaEntrada, int horaSalida){
        IngSalEnfermeras dia = new IngSalEnfermeras(horaEntrada,horaSalida);
        this.IngresoSalida.add(dia);
    }
    public boolean tieneHorario(){
        if (this.IngresoSalida.size()==0) return false;
        else return true;
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
