package Proyecto_progra;

public class IngSalEnfermeras {
    private int horaEntrada;
    private int horaSalida;
    
    public IngSalEnfermeras(){}
    public IngSalEnfermeras(int horaEntrada, int horaSalida) {
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    
    
}
