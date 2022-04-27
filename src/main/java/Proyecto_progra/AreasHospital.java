package Proyecto_progra;
/*Usar lista para guardar a las 4 enfermeras por area*/
import java.util.*;

public class AreasHospital {
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
    public void eliminarListaEnfermeras (String nombre){
        for (int i=0;i<this.enfermerasArea.size();i++){
            if (this.enfermerasArea.get(i).getNombre().equals(nombre)){
                this.enfermerasArea.remove(i);
            }
        }
    }
    public void mostrarArea (){
        System.out.println(this.nombre);
        for (int i = 0; i < this.enfermerasArea.size(); i++) {
            this.enfermerasArea.get(i).mostrarEnfermera();
        }
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
