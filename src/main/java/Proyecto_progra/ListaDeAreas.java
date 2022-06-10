/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto_progra;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
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
