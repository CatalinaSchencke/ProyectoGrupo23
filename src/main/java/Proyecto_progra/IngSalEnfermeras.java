package Proyecto_progra;
import java.time.*;
public class IngSalEnfermeras {
    //Instancias
    private double horaEntrada;
    private double horaSalida;
    private double horasAcumuladas;

    //Constructor
    public IngSalEnfermeras(){
        horaEntrada=0;
        horaSalida=0;
        horasAcumuladas=0;
    }
    
    //Metodos
    //Calcula horas trabajadas y suma a instancia horas acumuladas 
    public double actualizarHorasAcumuladas(){
        horasAcumuladas+=horaSalida-horaEntrada; 
        return horasAcumuladas;
    } 
    
    //Marca entrada con la hora actual
    public void marcarEntrada(){
        double hh = LocalDateTime.now().getHour();
        double mm = LocalDateTime.now().getMinute();
        //se suman los minutos como decimal proporcional
        horaEntrada= hh + (mm/60);
        
    }
    
    //Marca salida con la hora actual
    public void marcarSalida(){
        double hh = LocalDateTime.now().getHour();
        double mm = LocalDateTime.now().getMinute();
        //se suman los minutos como decimal proporcional
        horaSalida= hh + (mm/60);
    }
    
    //Getter y setter
    public double getHoraEntrada() {
        return horaEntrada;
    }
    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
    public double getHoraSalida() {
        return horaSalida;
    }
    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }
    public double getHorasAcumuladas() {
        return horasAcumuladas;
    }
    public void setHorasAcumuladas(double horasAcumuladas) {
        this.horasAcumuladas = horasAcumuladas;
    }
}
