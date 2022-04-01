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
                    int numero2 = -1;
                    while(numero2 != 0){
                        System.out.println("-----------------------------------------");
                        System.out.println("     Seleccione que cambio desea hacer");
                        System.out.println("-----------------------------------------");


                        System.out.println("1. Modificar Turno.");
                        System.out.println("2. Modificar Disponibilidad.");
                        System.out.println("3. Modificar Turno y Disponibilidad.");
                        System.out.println("0. Salir");
                        System.out.println("Seleccione el numero para operar:");
                        
                        
                        
                        numero2=Integer.parseInt(entrada.readLine());
                        switch(numero2){
                            case 1:{
                                if(numero2==1){
                                    System.out.println("Ingrese Turno a modificar (dia/noche)"); 
                                    String turno = entrada.readLine();
                                    if(turno.equals("dia") || turno.equals("noche")){
                                        System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
                                        String dato =entrada.readLine();
                                        Enfermera enfermera;
                                        if (isNumeric(dato)==true) enfermera=retornarEnfermera(Integer.parseInt(dato));
                                        else enfermera=retornarEnfermera(dato);
                                        cambioTurnoEnfermera(enfermera, turno);
                                    }
                                }
                                
                            }
                            case 2:{
                                if(numero2==2){
                                        System.out.println("Ingrese Disponibilidad a modificar (Si/No)"); 
                                    String disponibilidad  = entrada.readLine();
                                    boolean disponibilidadB;

                                    if(disponibilidad.equals("Si")){
                                         disponibilidadB= true;
                                    }
                                    else{
                                        if(disponibilidad.equals("No")){
                                         disponibilidadB= false;
                                        }
                                        else System.out.println("Opcion no valida."); break;
                                    }

                                        System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
                                        String dato =entrada.readLine();
                                        Enfermera enfermera;
                                        if (isNumeric(dato)==true) enfermera=retornarEnfermera(Integer.parseInt(dato));
                                        else enfermera=retornarEnfermera(dato);
                                        cambioTurnoEnfermera(enfermera, disponibilidadB);

                                }
                            }
                            case 3:{
                                if (numero2==3){
                                   System.out.println("Ingrese Turno a modificar (dia/noche)"); 
                                   String turno = entrada.readLine();
                                   if(turno.equals("dia") || turno.equals("noche")){
                                       System.out.println("Ingrese Disponibilidad a modificar (Si/No)"); 
                                       String disponibilidad  = entrada.readLine();
                                       boolean disponibilidadB;

                                       if(disponibilidad.equals("Si")){
                                            disponibilidadB= true;
                                       }
                                       else{
                                           if(disponibilidad.equals("No")){
                                            disponibilidadB= false;
                                           }
                                           else System.out.println("Opcion no valida."); break;
                                       }

                                           System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
                                           String dato =entrada.readLine();
                                           Enfermera enfermera;
                                           if (isNumeric(dato)==true) enfermera=retornarEnfermera(Integer.parseInt(dato));
                                           else enfermera=retornarEnfermera(dato);

                                           cambioTurnoEnfermera(enfermera, turno, disponibilidadB);


                                   }
                                }
                            }
                            case 0: break;
                            default: System.out.println("Opcion no valida."); break;
                        }
                    }
        
                   
                
                
                } 
                case 0: break;
                default: System.out.println("Opcion no valida."); break;
            }
            
        }
    }
    public void cargarDatos(){
        this.areasHospital = ManejoArchivos.cargarArchivoList("C:\\Users\\CATALINA\\Desktop\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt");
        //for(AreasHospital a : this.areasHospital)
          //  a.mostrarArea();
        this.enfermerasCodigo = ManejoArchivos.cargarArchivoMapCodigo("C:\\Users\\CATALINA\\Desktop\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt");  
        /*for(Enfermera a : this.enfermerasCodigo.values()){
            a.mostrarEnfermera();
        }*/
        this.enfermerasNombre = ManejoArchivos.cargarArchivoMapNombre("C:\\Users\\CATALINA\\Desktop\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt"); 
        for(Enfermera a : this.enfermerasNombre.values()){
            a.mostrarEnfermera();
        }
    }
    public void cambioTurnoEnfermera(Enfermera enfermera, String turno){
        System.out.println("Información de la enfermera Inicial"); 
        enfermera.mostrarEnfermera();
        enfermera.setTurno(turno);
        System.out.println("Información de la enfermera Modificada"); 
        enfermera.mostrarEnfermera();
    }
    public void cambioTurnoEnfermera(Enfermera enfermera, boolean disponibilidad){
        System.out.println("Información de la enfermera Inicial"); 
        enfermera.mostrarEnfermera();
        enfermera.setDisponibilidad(disponibilidad);
        System.out.println("Información de la enfermera Modificada"); 
        enfermera.mostrarEnfermera();
    }
    public void cambioTurnoEnfermera(Enfermera enfermera, String turno, boolean disponibilidad ){
        System.out.println("Información de la enfermera Inicial"); 
        enfermera.mostrarEnfermera();
        enfermera.setTurno(turno);
        enfermera.setDisponibilidad(disponibilidad);
        System.out.println("Información de la enfermera Modificada"); 
        enfermera.mostrarEnfermera();
    }
    public Enfermera retornarEnfermera (int codigo){
        
        if(this.enfermerasCodigo.containsKey(codigo)==true){
            Enfermera enfermera;
            enfermera=this.enfermerasCodigo.get(codigo);
            return this.enfermerasCodigo.get(codigo);
        }else {
            System.out.println("No existe ese codigo de enfermera");
        }
        return null ;
    }
     public Enfermera retornarEnfermera (String nombre){
        if(this.enfermerasNombre.containsKey(nombre)==true){
            Enfermera enfermera;
            enfermera=this.enfermerasNombre.get(nombre);
            return this.enfermerasNombre.get(nombre);
        }else {
            System.out.println("No existe ese nombre de enfermera");
        }
        return null ;
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
