package Proyecto_progra;
import java.time.*;
public class IngSalEnfermeras {
    private double horaEntrada;
    private double horaSalida;
    private double horasAcumuladas;

    public IngSalEnfermeras(){
        horaEntrada=0;
        horaSalida=0;
        horasAcumuladas=0;
    }
    public double actualizarHorasAcumuladas(){
        horasAcumuladas+=horaSalida-horaEntrada; 
        return horasAcumuladas;
    } 
    public void marcarEntrada(){
        double hh = LocalDateTime.now().getHour();
        double mm = LocalDateTime.now().getMinute();
        horaEntrada= hh + (mm/60);  
        horaEntrada=9;
    }
    public void marcarSalida(){
        double hh = LocalDateTime.now().getHour();
        double mm = LocalDateTime.now().getMinute();
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
