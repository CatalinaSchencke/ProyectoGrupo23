package Proyecto_progra;
import java.util.*;

public class Enfermera implements MostrarDatos{
    private String nombre;
    private String contrato;
    private String turno;
    private boolean disponibilidad;
    private int codigo;
    private int sueldo;
    private double horasTrabajadas;
    private IngSalEnfermeras horasDiarias;
   
    
    public Enfermera(){}
    public Enfermera(String nombre) {
        this.nombre = nombre;
        horasDiarias=new IngSalEnfermeras();
    }
    
    public void Mostrar(){
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
        if (contrato.equals("CONTRATADO")){
            Contrato aux= new Contrato();
            sueldo=aux.calcularSueldo(horasTrabajadas);
        }
    }
    
    public void marcarEntrada(){
        horasDiarias.marcarEntrada();
    }
    public void marcarSalida(){
        horasDiarias.marcarSalida();
        horasTrabajadas=horasDiarias.actualizarHorasAcumuladas();
    }
    
    //Getter y Setter
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
