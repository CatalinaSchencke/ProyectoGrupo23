package com.mycompany.proyecto_progra;
/*Usar lista para guardar a las 4 enfermeras por area*/
import java.util.*;

public class AreasHospital {
    private ArrayList<Enfermera> enfermerasArea ;
    private String nombre;
    
    public AreasHospital(String nombre) {
        this.enfermerasArea = new ArrayList<>();
        this.nombre = nombre;
    }
    public void agregarListaEnfermeras (Enfermera enfermera){
        this.enfermerasArea.add(enfermera);
    }
    public void mostrarArea (){
        System.out.println(this.nombre);
        for (int i = 0; i < this.enfermerasArea.size(); i++) {
            this.enfermerasArea.get(i).mostrarEnfermera();
        }
    }

    public List<Enfermera> getEnfermerasArea() {
        return enfermerasArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

}
