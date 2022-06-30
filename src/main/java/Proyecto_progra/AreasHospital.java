package Proyecto_progra;

import Colecciones.ListaEnfermerasArea;

public class AreasHospital implements MostrarDatos{
    //Instancias
    private ListaEnfermerasArea enfermerasArea;
    private String nombre;
    
    //Constructor
    public AreasHospital(){}
    public AreasHospital(String nombre) {
        this.enfermerasArea = new ListaEnfermerasArea();
        this.nombre = nombre;
    }
    
    //getter y setter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Métodos
    
    //Agrega enfermera a Wrapper que implementa ArrayList
    public void agregarListaEnfermeras (Enfermera enfermera){
        enfermerasArea.add(enfermera);
    }
    
    //Retorna enfermera de index específico en Wrapper que implementa ArrayList
    public Enfermera obtenerEnfermera( int index){ 
        return enfermerasArea.get(index);
    }
    
    //Retorna size de las enfermeras actuales en el Área
    public int obtenerSize(){
        return enfermerasArea.size();
    }
    
    //Elimina enfermera según el nombre de esta
    public void eliminarListaEnfermeras (String nombre){
        for (int i=0;i<enfermerasArea.size();i++){
            if (enfermerasArea.get(i).getNombre().equals(nombre)){
                enfermerasArea.remove(i);
            }
        }
    }
    
    //Implementación de Mostrar (Interface) para obtener String 
    //con información de enfermeras del área
    public String Mostrar (){
        String s=nombre+",";
        for (int i = 0; i < enfermerasArea.size(); i++) {
            s=s.concat(enfermerasArea.get(i).Mostrar());
        }
        return s;
    }
    
    //Implementación de Mostrar (Interface) para obtener String 
    //con información de enfermeras del área que tengan disponibilidad
    public String Mostrar (boolean disponibilidad){
        String s=nombre+",";
        for (int i = 0; i < enfermerasArea.size(); i++) {
            if( enfermerasArea.get(i).isDisponibilidad()==disponibilidad){
                s=s.concat(enfermerasArea.get(i).Mostrar());
            }
        }
        return s;
    }

    //Corrobora si se encuentra la enfermera por
    // nombre en el area
    public boolean existeEnfermera(String nombre){
        for (int i=0;i<enfermerasArea.size();i++){
            if (enfermerasArea.get(i).getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }
}
