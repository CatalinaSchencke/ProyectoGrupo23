package Proyecto_progra;

import java.io.*;
import java.util.*;

public class Hospital{
    private ArrayList<AreasHospital> areasHospital ;
    private String nombre;
    private HashMap<Integer,Enfermera> enfermerasCodigo;
    private HashMap<String,Enfermera> enfermerasNombre;
    
    public Hospital(){}
    public Hospital(String nombre) {
        this.areasHospital = new ArrayList<>();
        this.nombre = nombre;
        this.enfermerasCodigo = new HashMap<>();
        this.enfermerasNombre = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void menuHospital()throws IOException{
        cargarDatos();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));       
        int numero = -1;
        while(numero != 0){
            System.out.println("-----------------------------------------");
            System.out.println("          Bienvenido al Hospital");
            System.out.println("-----------------------------------------");
            
            
            System.out.println("1. Agregar Enfermera.");
            System.out.println("2. Mostrar Lista de Enfermeras.");
            System.out.println("3. Buscar Enfermera.");
            System.out.println("4. Cambiar Turno y/o Disponibilidad ");
            System.out.println("0. Salir");
            System.out.println("Seleccione el numero para operar:");
            
            numero=Integer.parseInt(entrada.readLine());
            switch(numero){
                case 1:
                case 2:
                case 3: {
                    System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
                    String dato =entrada.readLine();
                    if (isNumeric(dato)==true) buscarEnfermera(Integer.parseInt(dato));
                    else buscarEnfermera(dato);
                }
                case 4:{
                System.out.println("Ingrese numero Turno o Nombre de la enfermera:");    
                System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
                    String dato =entrada.readLine();
                    if (isNumeric(dato)==true) buscarEnfermera(Integer.parseInt(dato));
                    else buscarEnfermera(dato);
                
                } 
                case 0: break;
                default: System.out.println("Opcion no valida."); break;
            }
            
        }
    }
    public void cargarDatos(){
        this.areasHospital = ManejoArchivos.cargarArchivoList("C:\\Users\\vicen\\OneDrive\\Documentos\\GitHub\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt");
        //for(AreasHospital a : this.areasHospital)
          //  a.mostrarArea();
        this.enfermerasCodigo = ManejoArchivos.cargarArchivoMapCodigo("C:\\Users\\vicen\\OneDrive\\Documentos\\GitHub\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt");  
        /*for(Enfermera a : this.enfermerasCodigo.values()){
            a.mostrarEnfermera();
        }*/
        this.enfermerasNombre = ManejoArchivos.cargarArchivoMapNombre("C:\\Users\\vicen\\OneDrive\\Documentos\\GitHub\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt"); 
        for(Enfermera a : this.enfermerasNombre.values()){
            a.mostrarEnfermera();
        }
    }
    public void cambioTurnoEnfermera(String turno){
    
    
    }
    public void cambioTurnoEnfermera(boolean disponibilidad){
    
    
    }
    public void cambioTurnoEnfermera(String turno, boolean disponibilidad ){
    
    
    }
    
    public void buscarEnfermera(int codigo){
        Enfermera enfermera;
        if(this.enfermerasCodigo.containsKey(codigo)==true){
            enfermera=this.enfermerasCodigo.get(codigo);
            enfermera.mostrarEnfermera();
        }else System.out.println("No existe ese codigo de enfermera");
    }
    
    public void buscarEnfermera(String nombre){
        Enfermera enfermera;
        
        if(this.enfermerasNombre.containsKey(nombre)==true){
            enfermera=this.enfermerasNombre.get(nombre);
            enfermera.mostrarEnfermera();
        }else System.out.println("Nombre de enfermera no encontrado");
    }
    
    private static boolean isNumeric (String cadena){
        boolean result;
        try{
            Integer.parseInt(cadena);
            result = true;
        }catch (NumberFormatException nfe){
            result = false;
        }
        return result;
    }
    
     
}
