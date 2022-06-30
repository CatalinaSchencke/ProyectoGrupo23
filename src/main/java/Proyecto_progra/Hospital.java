package Proyecto_progra;

import java.io.*;
import java.util.*;

public class Hospital{
    /*Atributos de hospital, donde ListaDeAreas es una clase wrapper al igual
    que ListaEnfermerasHospital*/
    private String nombre;
    private ListaDeAreas areasHospital;
    private ListaEnfermerasHospital enfermerasHospital;
    //Constructor, getter y setters
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
    
    /*Funciones asociadas a eliminar de las colecciones*/
    
    /*Elimina a una enfermera de un area segun los datos que recibe de tipo
    String de forma: "nombreArea,nombreEnfermera", esto de la coleccion
    ListaDeAreas*/
    public void eliminarEnfermeraArea(String datos){
        String[] parts=datos.split(",");
        areasHospital.get(obtenerIndex(parts[0])).eliminarListaEnfermeras(parts[1]);
    }
    /*Elimina a la enfermera del area en base al nombre de la coleccion
    ListaEnfermerasHospital*/
    public void eliminarEnfermeraHospital(String nombre){
        enfermerasHospital.remove(nombre);
        for (int i=0;i<areasHospital.size();i++){
            if(areasHospital.get(i).existeEnfermera(nombre)){
                areasHospital.get(i).eliminarListaEnfermeras(nombre);
                
            }
        }
        
    }
    /*Elimina a un area de la coleccion ListaDeAreas completa segun el nombre*/
    public void eliminarArea(String nombreArea){
        areasHospital.remove(obtenerIndex(nombreArea));
    }
    
    /*Funciones asociadas a la agregacion de datos en las 
    colecciones*/
    
    /*Agrega un area nueva en la coleccion ListaDeAreas y retorna boolean con
    valor true si se pudo y false si ocurrio algun error*/
    public boolean agregarArea(String nombreArea){
        if (buscarArea(nombreArea)==true){
            return false;
        }
        AreasHospital aux2 = new AreasHospital(nombreArea);
        areasHospital.add(aux2);
        return true; 
    }
    /*Agrega una enfermera nueva en la coleccion ListaDeAreas,
    ListaEnfermerasHospital y retorna boolean con valor true si se pudo y false 
    si ocurrio algun error, para esto recibe un dato String concatenado con los
    valores rellenados por la Clase menu*/
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
    /*Funcion que se ocupa en distintas funciones solo de esta clase que agrega
    una enfermera, que se le pase por parametro, en todas las colecciones de 
    la clase hospital*/
    public void modificarEnMapasListas(Enfermera enf){
        enfermerasHospital.put(enf);
        for (int i=0;i<areasHospital.size();i++){
            if(areasHospital.get(i).existeEnfermera(enf.getNombre())){
                areasHospital.get(i).eliminarListaEnfermeras(enf.getNombre());
                areasHospital.get(i).agregarListaEnfermeras(enf);
            }
        }
    }
    
    /*Funciones asociadas a mostrar las colecciones*/
    
    /*Retorna un ArrayList con los Strings concatenados con los nombres de todas
    las areas del hospital*/
    public ArrayList mostrarListadoAreas(){
        ArrayList aux  = new ArrayList<>();
        
        for(int i = 0; i<areasHospital.size();i++){
            aux.add(areasHospital.get(i).Mostrar());
        }
        return aux; 
    }
    /*Retorna un String concatenado con todos los datos de las enfermeras del 
    area y recibe por parametro el nombre del Area*/
    public String mostrarEnfermerasArea(String nombreArea){
        String s = null;
        for (int i=0;i<areasHospital.size();i++){
            if (areasHospital.get(i).getNombre().equals(nombreArea)){
                s = areasHospital.get(i).Mostrar();
                return s;
            }
        }
        return s;
    }
    /*Retorna un ArrayList que en cada nodo contiene un String con los datos
    concatenados de cada enfermera, por lo que cada enfermera es un nodo del 
    ArrayList*/
    public ArrayList mostrarListadoEnfermeras(){
        ArrayList aux = new ArrayList();
        
        for(Enfermera enfermera : enfermerasHospital.values()){
            aux.add(enfermera.Mostrar());
        }
        return aux;
    }
    
    /*Funciones asociadas al salario*/
    
    /*Marca la entrada de una enfermera, recibiendo como dato el nombre de esta*/
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
    /*Marca la Salida de una enfermera, recibiendo como dato el nombre de esta*/
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
    
    /*Funcion que carga los datos desde un txt externo para inicializar 
    el hospital*/
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
    /*Funcion que ocupa a la clase ManejoArchivos para exportar un archivo de 
    tipo txt con datos como el nombre, turno y codigo de cada enfermera del
    hospital*/
    public void exportarReporte() throws FileNotFoundException, IOException{
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
    
    /*Funciones asociadas a la modificacion de datos en las 
    colecciones*/
    
    /*Funcion que cambia el nombre del area y que recibe por parametro el nombre
    del Area*/
    public void cambiarNombreArea(String datos){
        String[] parts=datos.split(",");
        areasHospital.get(obtenerIndex(parts[0])).setNombre(parts[1]);  
    }
    /*Funcion que cambia en las colecciones de hospital el turno de la enfermera
    y que retorna un String concatenado con los datos antes de modificar y 
    despues de esta*/
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
    /*Funcion que cambia en las colecciones de hospital la disponibilidad de la 
    enfermera y que retorna un String concatenado con los datos antes de 
    modificar y despues de esta*/
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
    /*Funcion que cumple lo mismo que las dos funciones antes que esta, pero 
    modifica ambas al mismo tiempo*/
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
    
    /*Funciones asociadas a buscar en las colecciones de datos del hospital*/
    
    /*Funcion que busca a una enfermera en la ListaEnfermerasHospital por codigo 
    y retorna un String con los datos de la enfermera en caso de encontrarlo*/
    public String buscarEnfermera(int codigo){
        Enfermera enfermera;
        if(enfermerasHospital.containsKey(codigo)==true){
            enfermera=enfermerasHospital.get(codigo);
            String s = enfermera.Mostrar();
            return s;
        }else return ("No existe ese codigo de enfermera,");
    }
    /*Funcion que busca a una enfermera en la ListaEnfermerasHospital por nombre 
    y retorna un String con los datos de la enfermera en caso de encontrarlo*/
    public String buscarEnfermera(String nombre){
        Enfermera enfermera;
        
        if(enfermerasHospital.containsKey(nombre)==true){
            enfermera=enfermerasHospital.get(nombre);
            String s = enfermera.Mostrar();
            return s;
            
        }else {
            return ("Nombre de enfermera no encontrado,");
        }
        
    }
    /*Funcion que determina si existe o no una enfermera en las colecciones de 
    hospital en base al nombre y que retorna un boolean con valor true si existe
    y false si es que no*/
    public boolean existeEnfermera(String nombre){
        if (enfermerasHospital.containsKey(nombre)==true){
            return true;
        }
        return false;
    }
    /*Funcion que determina si existe o no un area en la coleccion de 
    ListaAreasHospital en base al nombre de esta y que retorna un boolean 
    con valor true si existe y false si es que no*/
    public boolean buscarArea(String nombreArea){
        for (int i=0;i<areasHospital.size();i++){
            if (areasHospital.get(i).getNombre().equals(nombreArea)){
                return true;
            }
        }
        return false;
    }
    /*Funcion que busca entre todas las enfermeras del hospital a la que 
    tenga el mejor sueldo hasta el momento*/
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
 

    /*Metodos asociados a las funciones para mejor modularizacion*/
    
    /*Funcion que retorna a un objeto enfermera en base a cierto codigo*/
    public Enfermera retornarEnfermera (int codigo){
        if(enfermerasHospital.containsKey(codigo)==true)return enfermerasHospital.get(codigo);
        return null ;
    }
    /*Funcion que retorna a un objeto enfermera en base a cierto nombre*/
    public Enfermera retornarEnfermera (String nombre){
        if(enfermerasHospital.containsKey(nombre)==true)return enfermerasHospital.get(nombre);
        return null ;
    }
    /*Revisa si la cadena que recibe por parametro es un numero o no, por lo que
    retornara un valor boolean true en caso de que si sea un numero y no en caso
    contrario*/
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
    /*Funcion que obtiene el index de la lista en donde se ubica en base
    al nombre del area*/
    public int obtenerIndex(String nombreArea){
        int i;
        for (i=0;i<areasHospital.size();i++){
            if (areasHospital.get(i).getNombre().equals(nombreArea))
                return i;
        } 
        return i;
    }
}
