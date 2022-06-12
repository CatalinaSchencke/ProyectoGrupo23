package Proyecto_progra;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class ListaEnfermerasHospital {
    private HashMap<Integer,Enfermera> enfermerasCodigo;
    private HashMap<String,Enfermera> enfermerasNombre;
    public ListaEnfermerasHospital(){
        this.enfermerasCodigo = new HashMap<>();
        this.enfermerasNombre = new HashMap<>();
    }
    
    public void put (Enfermera enfermera){
        enfermerasCodigo.put(enfermera.getCodigo(), enfermera);
        enfermerasNombre.put(enfermera.getNombre(), enfermera);
    }
    public Enfermera get (int codigo){
        return enfermerasCodigo.get(codigo);
    }
    public Enfermera get (String nombre){
        return enfermerasNombre.get(nombre);
    }
    public boolean containsKey (int codigo){
        return enfermerasCodigo.containsKey(codigo);
    }
    public boolean containsKey (String nombre){
        return enfermerasNombre.containsKey(nombre);
    }
    public Enfermera remove (String nombre){
        Enfermera enfermera = enfermerasNombre.remove(nombre);
        return enfermerasCodigo.remove(enfermera.getCodigo());
    }
    public Set<Integer> keySet(){
        return enfermerasCodigo.keySet();
    }
    public Collection<Enfermera> values (){
        return enfermerasNombre.values();
    }
    
}
