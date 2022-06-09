package Proyecto_progra;

import java.io.*;
import java.util.*;

public class Hospital{
    private String nombre;
    private ArrayList<AreasHospital> areasHospital ;
    private HashMap<Integer,Enfermera> enfermerasCodigo;
    private HashMap<String,Enfermera> enfermerasNombre;
    
    public Hospital(){}
    public Hospital(String nombre) {
        this.nombre = nombre;
        this.areasHospital = new ArrayList<>();
        this.enfermerasCodigo = new HashMap<>();
        this.enfermerasNombre = new HashMap<>();
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
    public void eliminarEnfermeraHospital(String aux){

        Enfermera ee = enfermerasNombre.remove(aux);
        enfermerasCodigo.remove(ee.getCodigo());
        for (int i=0;i<areasHospital.size();i++){
            if(areasHospital.get(i).existeEnfermera(aux)){
                areasHospital.get(i).eliminarListaEnfermeras(aux);
                
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
        this.areasHospital.add(aux2);
        return true; 
    }
    public boolean agregarEnfermera(String dato){
        String[] parts=dato.split(",");
        System.out.println(dato);
        Enfermera nurse = new Enfermera(parts[0]);

        if (parts[1].equals("true")) nurse.setDisponibilidad(true);
        if (parts[1].equals("false")) nurse.setDisponibilidad(false);

        nurse.setTurno(parts[3]);
       
        int max=0;
        for (int i : this.enfermerasCodigo.keySet()) if (i > max) max = i;
        nurse.setCodigo(max+1);
        
        nurse.setContrato("INDEFINIDO");
        
        //agregar a la lista
        this.areasHospital.get(obtenerIndex(parts[3])).agregarListaEnfermeras(nurse);
        //agregar al mapa
        this.enfermerasNombre.put(nurse.getNombre(), nurse);
        this.enfermerasCodigo.put(nurse.getCodigo(), nurse);

        return true;
    }
    public void modificarEnMapasListas(Enfermera enf){
        enfermerasCodigo.put(enf.getCodigo(), enf);
        enfermerasNombre.put(enf.getNombre(), enf);
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
        
        for(AreasHospital areas : this.areasHospital)
            aux.add(areas.getNombre());
        
        
        return aux; 
    }
    public ArrayList mostrarEnfermerasArea(String aux){
        ArrayList areaEnf = new ArrayList();
        for (int i=0;i<areasHospital.size();i++){
            if (areasHospital.get(i).getNombre().equals(aux)){
                areaEnf = areasHospital.get(i).obtenerListaEnfermeras();
                return areaEnf;
            }
        }
        return areaEnf;
    }
    public ArrayList mostrarListadoEnfermeras(){
        ArrayList aux = new ArrayList();
        
        for(Enfermera enfermera : this.enfermerasNombre.values()){
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
    public Enfermera generarSalario( String dato ){
        if (isNumeric(dato)){
            int datoInt = Integer.parseInt(dato);
            Enfermera enf = retornarEnfermera(datoInt);
            modificarEnMapasListas(enf);
            return enf;
            //enf.mostrarSalario();
            
        }
        else {
            Enfermera enf = retornarEnfermera(dato);
            enf.mostrarSalario();
            modificarEnMapasListas(enf);
            return enf;
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
                
                this.enfermerasCodigo.put(enfermera.getCodigo(), enfermera);
                this.enfermerasNombre.put(enfermera.getNombre(), enfermera);
                
                if(buscarArea(parts[0])==false || this.areasHospital.size()==13){
                    AreasHospital area= new AreasHospital(parts[0]);
                    this.areasHospital.add(area);
                    int index = obtenerIndex(parts[0]);
                    this.areasHospital.get(index).agregarListaEnfermeras(enfermera);
                }else{
                    int index = obtenerIndex(parts[0]);
                    this.areasHospital.get(index).agregarListaEnfermeras(enfermera);
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
        s=s.concat(enfermera.Mostrar()+",");
        enfermera.setTurno(turno);
        s=s.concat("Información de la enfermera Modificada,");
        s=s.concat(enfermera.Mostrar()+",");
        modificarEnMapasListas(enfermera);
        return s;
    }
    public void cambioTurnoEnfermera(Enfermera enfermera, boolean disponibilidad){
        System.out.println("Información de la enfermera Inicial"); 
        enfermera.Mostrar();
        enfermera.setDisponibilidad(disponibilidad);
        System.out.println("Información de la enfermera Modificada"); 
        enfermera.Mostrar();
    }
    public void cambioTurnoEnfermera(Enfermera enfermera, String turno, boolean disponibilidad ){
        System.out.println("Información de la enfermera Inicial"); 
        enfermera.Mostrar();
        enfermera.setTurno(turno);
        enfermera.setDisponibilidad(disponibilidad);
        System.out.println("Información de la enfermera Modificada"); 
        enfermera.Mostrar();
    }
    /*Buscar en las colecciones*/
    public String buscarEnfermera(int codigo){
        Enfermera enfermera;
        if(enfermerasCodigo.containsKey(codigo)==true){
            enfermera=enfermerasCodigo.get(codigo);
            String s = enfermera.Mostrar();
            return s;
        }else return ("No existe ese codigo de enfermera,");
    }
    public String buscarEnfermera(String nombre){
        Enfermera enfermera;
        
        if(enfermerasNombre.containsKey(nombre)==true){
            enfermera=enfermerasNombre.get(nombre);
            String s = enfermera.Mostrar();
            return s;
        }else return ("Nombre de enfermera no encontrado,");
    }
    public boolean existeEnfermera(String nombre){
        if (enfermerasNombre.containsKey(nombre)==true){
            return true;
        }
        return false;
    }
    public boolean buscarArea(String nombreArea){
        for (AreasHospital area : this.areasHospital){
            if (area.getNombre().equals(nombreArea)){
                return true;
            }
        }
        return false;
    }
    /*Metodos asociados a las funciones*/
    public Enfermera retornarEnfermera (int codigo){
        if(enfermerasCodigo.containsKey(codigo)==true)return enfermerasCodigo.get(codigo);
        else System.out.println("No existe ese codigo de enfermera");
        
        return null ;
    }
    public Enfermera retornarEnfermera (String nombre){
        if(enfermerasNombre.containsKey(nombre)==true)return enfermerasNombre.get(nombre);
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
        for (i=0;i<this.areasHospital.size();i++){
            if (this.areasHospital.get(i).getNombre().equals(nombreArea))
                return i;
        } 
        return i;
    }
}
