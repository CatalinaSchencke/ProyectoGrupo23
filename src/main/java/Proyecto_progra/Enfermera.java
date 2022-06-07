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
   
    
    public Enfermera(){
        horasTrabajadas=0;
        sueldo=0;
    }
    public Enfermera(String nombre) {
        horasTrabajadas=0;
        sueldo=0;
        this.nombre = nombre;
        horasDiarias=new IngSalEnfermeras();
    }
    
    public String Mostrar(){
        String s ="Nombre: "+this.nombre;
        if (this.disponibilidad==true){
            s = s.concat(",Disponibilidad Inmediata: Si");
        }
        else{
            s = s.concat(",Disponibilidad Inmediata: Si");
        }
        s = s.concat(",Turno: "+this.turno);
        s = s.concat(",Codigo Asignado: "+this.codigo);
        s = s.concat(",Tipo de Contrato: "+this.contrato);
        s = s.concat(",");
        return s;
        
    }

    public void calcularSalario(){
        if (contrato.equals("HONORARIO")){
            Salario aux= new Honorario();
            sueldo=aux.calcularSueldo(horasTrabajadas);
        }
        if (contrato.equals("INDEFINIDO")){
            Contrato aux= new Contrato();
            sueldo=aux.calcularSueldo(horasTrabajadas);
        }
    }
    public void mostrarSalario(){
        if (contrato.equals("HONORARIO")){
            Honorario aux= new Honorario();
            aux.mostrarSalario(horasTrabajadas);
        }
        if (contrato.equals("INDEFINIDO")){
            Contrato aux= new Contrato();
            aux.mostrarSalario(horasTrabajadas);
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
    public String getContrato() {
        return contrato;
    }
    public void setContrato(String contrato) {
        this.contrato = contrato;
    }
    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }
    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
