package Proyecto_progra;

public class Enfermera implements MostrarDatos{
    
    //Instancias
    private String nombre;
    private String contrato;
    private String turno;
    private boolean disponibilidad;
    private int codigo;
    private int sueldo;
    private double horasTrabajadas;
    private IngSalEnfermeras horasDiarias;
   
    //Constructores
    public Enfermera(){
        this.horasTrabajadas=0;
        this.sueldo=0;
    }
    public Enfermera(String nombre) {
        this.horasTrabajadas=0;
        this.sueldo=0;
        this.nombre = nombre;
        this.horasDiarias=new IngSalEnfermeras();
    }
    
    //Métodos
    
    //Implementación de Interfase con información
    //de la enfermera, retornando cadena con esta
    public String Mostrar(){
        String s =nombre;
        if (disponibilidad==true){
            s = s.concat(",Si");
        }
        if(disponibilidad==false){
            s = s.concat(",No");
        }
        s = s.concat(","+turno);
        s = s.concat(","+codigo);
        s = s.concat(","+contrato);
        s = s.concat(",");
        return s;
        
    }
    
    /*Funcion que calcula el sueldo dependiendo de que salario tenga y lo guarda*/
    public void calcularSalario(Salario aux){
        sueldo=aux.calcularSueldo(horasTrabajadas);
    }
    /*Funcion que retorna un String concatenado con los datos del sueldo y las
    horas trabajadas*/
    public String mostrarSalario(Salario aux){
        return aux.mostrarSueldo(sueldo,horasTrabajadas);
    }
    /*Funcion que llama a la clase IngSalEnfermeras y que marca la hora de 
    entrada de la enfermera al trabajo*/
    public void marcarEntrada(){
        horasDiarias.marcarEntrada();
    }
    /*Funcion que llama a la clase IngSalEnfermeras y que marca la hora de 
    salida de la enfermera al trabajo*/
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
    public int getSueldo(){
        return sueldo;
    }
    public void setSueldo(){
        this.sueldo=sueldo;
    }
}
