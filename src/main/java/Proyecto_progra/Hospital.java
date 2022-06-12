package Proyecto_progra;

import java.io.*;
import java.util.*;

public class Hospital{
    private String nombre;
    private ListaDeAreas areasHospital;
    private ListaEnfermerasHospital enfermerasHospital;
    
    public Hospital(){}
    public Hospital(String nombre) {
        this.nombre = nombre;
        this.areasHospital = new ListaDeAreas();
        this.enfermerasHospital = new ListaEnfermerasHospital();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /*Eliminar de las colecciones*/
    public void eliminarEnfermeraArea(String datos){
        String[] parts=datos.split(",");
        areasHospital.get(obtenerIndex(parts[0])).eliminarListaEnfermeras(parts[1]);
        
    }
    public void eliminarEnfermeraHospital(String nombre){
        enfermerasHospital.remove(nombre);
        for (int i=0;i<areasHospital.size();i++){
            if(areasHospital.get(i).existeEnfermera(nombre)){
                areasHospital.get(i).eliminarListaEnfermeras(nombre);
                
            }
        }
        
    }
    public void eliminarArea(String nombreArea){
        areasHospital.remove(obtenerIndex(nombreArea));
    }
    /*Agregar a las colecciones*/
    public boolean agregarArea(String dato){
        if (buscarArea(dato)==true){
            return false;
        }
        AreasHospital aux2 = new AreasHospital(dato);
        areasHospital.add(aux2);
        return true; 
    }
    public boolean agregarEnfermera(String dato){
        String[] parts=dato.split(",");
        System.out.println(dato);
        Enfermera enfermera = new Enfermera(parts[0]);

        if (parts[1].equals("true")) enfermera.setDisponibilidad(true);
        else if (parts[1].equals("false")) enfermera.setDisponibilidad(false);
        else return false;
        enfermera.setTurno(parts[3]);
       
        int max=0;
        for (int i : enfermerasHospital.keySet()) {
            if (i > max) max = i;
        }
        enfermera.setCodigo(max+1);
        
        enfermera.setContrato("INDEFINIDO");
        
        //agregar a la lista
        areasHospital.get(obtenerIndex(parts[3])).agregarListaEnfermeras(enfermera);
        //agregar al mapa
        enfermerasHospital.put(enfermera);

        return true;
    }
    public void modificarEnMapasListas(Enfermera enf){
        enfermerasHospital.put(enf);
        for (int i=0;i<areasHospital.size();i++){
            if(areasHospital.get(i).existeEnfermera(enf.getNombre())){
                areasHospital.get(i).eliminarListaEnfermeras(enf.getNombre());
                areasHospital.get(i).agregarListaEnfermeras(enf);
            }
        }
    }
    /*Mostrar las colecciones*/
    public ArrayList mostrarListadoAreas(){
        ArrayList aux  = new ArrayList<>();
        
        for(int i = 0; i<areasHospital.size();i++){
            aux.add(areasHospital.get(i).Mostrar());
        }
        return aux; 
    }
    public String mostrarEnfermerasArea(String aux){
        String s = null;
        for (int i=0;i<areasHospital.size();i++){
            if (areasHospital.get(i).getNombre().equals(aux)){
                s = areasHospital.get(i).Mostrar();
                return s;
            }
        }
        return s;
    }
    public ArrayList mostrarListadoEnfermeras(){
        ArrayList aux = new ArrayList();
        
        for(Enfermera enfermera : enfermerasHospital.values()){
            aux.add(enfermera.Mostrar());
        }
        return aux;
    }
    //Marcar Entrada y Salida
    public void marcarEntrada( String dato){
        if (isNumeric(dato)){
            int datoInt = Integer.parseInt(dato);
            Enfermera enf= retornarEnfermera(datoInt);
            enf.marcarEntrada();
            modificarEnMapasListas(enf);   
        }
        else {
            Enfermera enf = retornarEnfermera(dato);
            enf.marcarEntrada();
            modificarEnMapasListas(enf);    
        } 
    }
    public void marcarSalida(String dato) {
        if (isNumeric(dato)){
            int datoInt = Integer.parseInt(dato);
            Enfermera enf= retornarEnfermera(datoInt);
            enf.marcarSalida();
            modificarEnMapasListas(enf);
        }
        else {
            Enfermera enf = retornarEnfermera(dato);
            enf.marcarSalida();
            modificarEnMapasListas(enf);
        }
    }
    public String generarSalario( String dato ){
        String s=null;
        if (isNumeric(dato)){
            int datoInt = Integer.parseInt(dato);
            Enfermera enf = retornarEnfermera(datoInt);
            if (enf.getContrato().equals("HONORARIO")){
                Salario aux=new Honorario();
                enf.calcularSalario(aux);
                s=enf.mostrarSalario(aux);
            }
            if (enf.getContrato().equals("INDEFINIDO")){
                Salario aux= new Contrato();
                enf.calcularSalario(aux);
                s=enf.mostrarSalario(aux);
            }
            
            modificarEnMapasListas(enf);
            return s;
                
        }
        else {
            Enfermera enf = retornarEnfermera(dato);
            if (enf.getContrato().equals("HONORARIO")){
                Salario aux= new Honorario();
                enf.calcularSalario(aux);
                s=enf.mostrarSalario(aux);
            }
            if (enf.getContrato().equals("INDEFINIDO")){
                Salario aux= new Contrato();
                enf.calcularSalario(aux);
                s=enf.mostrarSalario(aux);
            }
            
            modificarEnMapasListas(enf);
            return s;
        }
    }
    /*Uso de datos de Archivos*/
    public void cargarDatos(){
        File archivo = new File("Enfermeras.txt");
        int numeroClave=0;
        try {
            BufferedReader entrada = new BufferedReader( new FileReader(archivo));
            String lectura = entrada.readLine();            
            while(lectura != null){
                
                String[] parts=lectura.split(",");
                Enfermera enfermera= new Enfermera(parts[1]);
                enfermera.setCodigo(100+numeroClave);
                enfermera.setTurno(parts[2]);
                enfermera.setContrato(parts[3]);
                if(numeroClave%2==0)enfermera.setDisponibilidad(true);
                else enfermera.setDisponibilidad(false);
                enfermerasHospital.put(enfermera);
                if(buscarArea(parts[0])==false || areasHospital.size()==13){
                    AreasHospital area= new AreasHospital(parts[0]);
                    areasHospital.add(area);
                    int index = obtenerIndex(parts[0]);
                    areasHospital.get(index).agregarListaEnfermeras(enfermera);
                }else{
                    int index = obtenerIndex(parts[0]);
                    areasHospital.get(index).agregarListaEnfermeras(enfermera);
                }
       
                numeroClave++;  
                lectura = entrada.readLine();
            }
            
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }   
    }
    public void exportarReporte(){
        ManejoArchivos.crearArchivo("Reporte.txt");
        
        for(int i=0; i<areasHospital.size(); i++){
            AreasHospital aux = areasHospital.get(i);
            ManejoArchivos.anexarArchivo("Reporte.txt", "-------" + aux.getNombre() + "-------");
            for (int k=0; k<aux.obtenerSize(); k++){
                Enfermera ef= aux.obtenerEnfermera(k);
                ManejoArchivos.anexarArchivo("Reporte.txt", ef.getNombre()+ ", " + ef.getTurno() + ", " + ef.getCodigo());
            }  
            ManejoArchivos.anexarArchivo("Reporte.txt", " ");
        }
         
    }
    /*Modificar las colecciones*/
    public void cambiarNombreArea(String datos){
        String[] parts=datos.split(",");
        areasHospital.get(obtenerIndex(parts[0])).setNombre(parts[1]);  
    }
    public String cambioTurnoEnfermera(String dato, String turno){
        Enfermera enfermera;
        if (isNumeric(dato)==true){
            enfermera = retornarEnfermera(Integer.parseInt(dato));
        }else{
            enfermera = retornarEnfermera(dato);
        }
        String s = "Información de la enfermera Inicial,";
        s=s.concat(enfermera.Mostrar());
        enfermera.setTurno(turno);
        s=s.concat("Información de la enfermera Modificada,");
        s=s.concat(enfermera.Mostrar());
        modificarEnMapasListas(enfermera);
        return s;
    }
    public String cambioTurnoEnfermera(String dato, boolean disponibilidad){
        Enfermera enfermera;
        if (isNumeric(dato)==true){
            enfermera = retornarEnfermera(Integer.parseInt(dato));
        }else{
            enfermera = retornarEnfermera(dato);
        }
        String s = "Información de la enfermera Inicial,";
        s=s.concat(enfermera.Mostrar());
        enfermera.setDisponibilidad(disponibilidad);
        s=s.concat("Información de la enfermera Modificada,");
        s=s.concat(enfermera.Mostrar());
        modificarEnMapasListas(enfermera);
        return s;
    }
    public String cambioTurnoEnfermera(String dato, String turno, boolean disponibilidad ){
        Enfermera enfermera;
        if (isNumeric(dato)==true){
            enfermera = retornarEnfermera(Integer.parseInt(dato));
        }else{
            enfermera = retornarEnfermera(dato);
        }
        String s = "Información de la enfermera Inicial,";
        s=s.concat(enfermera.Mostrar());
        enfermera.setDisponibilidad(disponibilidad);
        enfermera.setTurno(turno);
        s=s.concat("Información de la enfermera Modificada,");
        s=s.concat(enfermera.Mostrar());
        modificarEnMapasListas(enfermera);
        return s;
    }
    /*Buscar en las colecciones*/
    public String buscarEnfermera(int codigo){
        Enfermera enfermera;
        if(enfermerasHospital.containsKey(codigo)==true){
            enfermera=enfermerasHospital.get(codigo);
            String s = enfermera.Mostrar();
            return s;
        }else return ("No existe ese codigo de enfermera,");
    }
    public String buscarEnfermera(String nombre){
        Enfermera enfermera;
        
        if(enfermerasHospital.containsKey(nombre)==true){
            enfermera=enfermerasHospital.get(nombre);
            String s = enfermera.Mostrar();
            return s;
        }else return ("Nombre de enfermera no encontrado,");
    }
    public boolean existeEnfermera(String nombre){
        if (enfermerasHospital.containsKey(nombre)==true){
            return true;
        }
        return false;
    }
    public boolean buscarArea(String nombreArea){
        for (int i=0;i<areasHospital.size();i++){
            if (areasHospital.get(i).getNombre().equals(nombreArea)){
                return true;
            }
        }
        return false;
    }
    public String mejorPagada(){
        int aux2=0; 
        String aux1=null;
        for(int i=0; i<areasHospital.size(); i++){
            AreasHospital aux = areasHospital.get(i);
            //System.out.println("No existe ese codigo de enfermera 33");
            for (int k=0; k<aux.obtenerSize(); k++){
                Enfermera ef= aux.obtenerEnfermera(k);
                //System.out.println("No existe ese codigo de enfermera 1");
                if (ef.getSueldo()>aux2){
                    //System.out.println("No existe ese codigo de enfermera 1" + ef.getSueldo());
                    aux1=ef.getNombre();
                    aux2=ef.getSueldo();
                    
                }
            }      
        }
        String s;
        s="Nombre: ";
        s=s.concat(aux1+"\n");
        s=s.concat("Sueldo: ");
        s=s.concat(Integer.toString(aux2)+",");
        
        return s; //System.out.println("La/El enfermera(o) mejor pagado es: " + aux1 + " " + "Su sueldo es: " + aux2);
        
    }
 

    /*Metodos asociados a las funciones*/
    public Enfermera retornarEnfermera (int codigo){
        if(enfermerasHospital.containsKey(codigo)==true)return enfermerasHospital.get(codigo);
        else System.out.println("No existe ese codigo de enfermera");
        
        return null ;
    }
    public Enfermera retornarEnfermera (String nombre){
        if(enfermerasHospital.containsKey(nombre)==true)return enfermerasHospital.get(nombre);
        else System.out.println("No existe ese nombre de enfermera");
        
        return null ;
    }
    public static boolean isNumeric (String cadena){
        boolean result;
        try{
            Integer.parseInt(cadena);
            result = true;
        }catch (NumberFormatException nfe){
            result = false;
        }
        return result;
    }
    public int obtenerIndex(String nombreArea){
        int i;
        for (i=0;i<areasHospital.size();i++){
            if (areasHospital.get(i).getNombre().equals(nombreArea))
                return i;
        } 
        return i;
    }
}
