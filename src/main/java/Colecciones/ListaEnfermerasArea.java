package Colecciones;

import Proyecto_progra.Enfermera;
import java.util.ArrayList;

public class ListaEnfermerasArea {
    private ArrayList<Enfermera> list ;
    
    public ListaEnfermerasArea() {
        this.list = new ArrayList<>();
    }
    
    public Enfermera get (int index){
        return list.get(index);
    }
    public void remove (int index){
        list.remove(index);
    }
    public int size (){
        return list.size();
    }
    public void add (Enfermera enfermera){
        list.add(enfermera);
    }
    
}
