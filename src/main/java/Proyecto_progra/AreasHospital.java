package Proyecto_progra;
/*Usar lista para guardar a las 4 enfermeras por area*/
import java.util.*;

public class AreasHospital implements MostrarDatos{
    private ArrayList<Enfermera> enfermerasArea ;
    private String nombre;
    
    public AreasHospital(){}
    public AreasHospital(String nombre) {
        this.enfermerasArea = new ArrayList<>();
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void agregarListaEnfermeras (Enfermera enfermera){
        this.enfermerasArea.add(enfermera);
    }
    
    public Enfermera obtenerEnfermera( int index){ 
        return enfermerasArea.get(index);
    }
    
    public ArrayList obtenerListaEnfermeras(){
        return enfermerasArea;
    }
    
    public int obtenerSize(){
        return enfermerasArea.size();
    }
    public void eliminarListaEnfermeras (String nombre){
        for (int i=0;i<this.enfermerasArea.size();i++){
            if (this.enfermerasArea.get(i).getNombre().equals(nombre)){
                this.enfermerasArea.remove(i);
            }
        }
    }
    public String Mostrar (){
        System.out.println(this.nombre);
        for (int i = 0; i < this.enfermerasArea.size(); i++) {
            this.enfermerasArea.get(i).Mostrar();
        }
        String a="pdi";
        return a;
    }
    
    public String Mostrar (boolean disponibilidad){
        System.out.println(this.nombre);
        for (int i = 0; i < this.enfermerasArea.size(); i++) {
            if( this.enfermerasArea.get(i).isDisponibilidad() ==disponibilidad)this.enfermerasArea.get(i).Mostrar();
        }
        String a="pdi";
        return a;
    }

    public boolean existeEnfermera(String nombre){
        for (int i=0;i<this.enfermerasArea.size();i++){
            if (this.enfermerasArea.get(i).getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }
}
