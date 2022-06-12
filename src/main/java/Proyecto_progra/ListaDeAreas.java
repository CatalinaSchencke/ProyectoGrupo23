package Proyecto_progra;

import java.util.ArrayList;

public class ListaDeAreas {
    private ArrayList<AreasHospital> list ;
    
    public ListaDeAreas() {
        this.list = new ArrayList<>();
    }
    
    public AreasHospital get (int index){
        return list.get(index);
    }
    public void remove (int index){
        list.remove(index);
    }
    public int size (){
        return list.size();
    }
    public void add (AreasHospital area){
        list.add(area);
    }
    
}
