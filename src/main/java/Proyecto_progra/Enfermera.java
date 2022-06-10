package Proyecto_progra;

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
        if(this.disponibilidad==false){
            s = s.concat(",Disponibilidad Inmediata: No");
        }
        s = s.concat(",Turno: "+this.turno);
        s = s.concat(",Codigo Asignado: "+this.codigo);
        s = s.concat(",Tipo de Contrato: "+this.contrato);
        s = s.concat("\n,");
        return s;
        
    }

    public void calcularSalario(){
        if (contrato.equals("HONORARIO")){
            Salario aux= new Honorario();
            sueldo=aux.calcularSueldo(horasTrabajadas);
        }
        if (contrato.equals("INDEFINIDO")){
            Salario aux= new Contrato();
            sueldo=aux.calcularSueldo(horasTrabajadas);
        }
    }
    public String mostrarSalario(){
        if (contrato.equals("HONORARIO")){
            Salario aux= new Honorario();   
            return aux.mostrarSueldo(horasTrabajadas);
        }
        if (contrato.equals("INDEFINIDO")){
            Salario aux= new Contrato();
            return aux.mostrarSueldo(horasTrabajadas);
        }
        return "No se puede mostar el salario";
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
