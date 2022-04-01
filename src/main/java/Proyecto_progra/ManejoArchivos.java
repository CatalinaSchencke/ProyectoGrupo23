package Proyecto_progra;

import java.io.*;
import java.util.*;

public class ManejoArchivos {
    public static void crearArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se creo el archivo exitosamente");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void sobreEscribirArchivo(String nombreArchivo, String contenido){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(contenido);
            salida.close();
            System.out.println("Se sobreescrito exitosamente");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void anexarArchivo(String nombreArchivo, String contenido){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(contenido);
            salida.close();
            System.out.println("Se escrito exitosamente");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void leerArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try {
            BufferedReader entrada = new BufferedReader( new FileReader(archivo));
            String lectura = entrada.readLine();            
            while(lectura != null){
                System.out.println("lectura = " + lectura);
                String[] parts=lectura.split(",");
                for(String a: parts){
                    System.out.println("parte = " + a);  
                }
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }  
    //ACA
    public static ArrayList<AreasHospital> cargarArchivoList(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        ArrayList<AreasHospital> listaAr=new ArrayList();
        ArrayList<Enfermera> listaEnf = new ArrayList();
        int numeroClave = 0;
        try {
            BufferedReader entrada = new BufferedReader( new FileReader(archivo));
            String lectura = entrada.readLine();
            while(lectura != null){
                String[] parts=lectura.split(",");
                Enfermera enfermera= new Enfermera();
                for(int i=0;i<parts.length;i++){
                    switch(i){
                        case 0:{
                            if(buscarArea(parts[i],listaAr)==false || listaAr.size()==13){
                                AreasHospital area= new AreasHospital(parts[i]);
                                listaAr.add(area);
                            }
                        }
                        case 1:{
                            enfermera.setNombre(parts[i]);
                            enfermera.setCodigo(100+numeroClave);
                        }
                        case 2:{
                            
                        }
                    }
                }
                int index = obtenerIndex(listaAr, parts[0]);
                listaAr.get(index).agregarListaEnfermeras(enfermera);
                numeroClave++;
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        /*for (AreasHospital a : listaAr){
            a.mostrarArea();
        }*/
        return listaAr;
    }
    public static HashMap cargarArchivoMapCodigo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        HashMap<Integer,Enfermera> codigos = new HashMap();
        int numeroClave=0;
        try {
            BufferedReader entrada = new BufferedReader( new FileReader(archivo));
            String lectura = entrada.readLine();            
            while(lectura != null){
                
                String[] parts=lectura.split(",");
                Enfermera enfermera= new Enfermera();
                
                enfermera.setNombre(parts[1]);
                enfermera.setCodigo(100+numeroClave);
                
                numeroClave++;
                lectura = entrada.readLine();
                codigos.put(enfermera.getCodigo(), enfermera);
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        return codigos;
    }
    
    public static HashMap cargarArchivoMapNombre(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        HashMap<String,Enfermera> codigos = new HashMap();
        int numeroClave=0;
        try {
            BufferedReader entrada = new BufferedReader( new FileReader(archivo));
            String lectura = entrada.readLine();            
            while(lectura != null){
                
                String[] parts=lectura.split(",");
                Enfermera enfermera= new Enfermera();
                
                enfermera.setNombre(parts[1]);
                enfermera.setCodigo(100+numeroClave);
                
                numeroClave++;
                lectura = entrada.readLine();
                codigos.put(enfermera.getNombre(), enfermera);
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        return codigos;
    }
    
    public static boolean buscarArea(String nombreArea, ArrayList<AreasHospital> lista){
        for (AreasHospital area : lista){
            if (area.getNombre().equals(nombreArea)){
                return true;
            }
        }
        return false;
    }
    public static int obtenerIndex(ArrayList<AreasHospital>listaAr,String nombreArea){
        int i;
        for (i=0;i<listaAr.size();i++){
            AreasHospital aux = listaAr.get(i);
            if (aux.getNombre().equals(nombreArea)){
                return i;
            }
        } 
        return i;
    }
    
}
