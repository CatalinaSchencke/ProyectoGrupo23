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
                case 1: agregarEnfermera(); break;
                case 2: mostrarEnfermeras(); break;
                case 3: {
                    System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
                    String dato=entrada.readLine();
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
    public void agregarEnfermera() throws IOException{
      BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
      
        System.out.println("Ingrese Nombre de la enfermera");
        String dato;
        int cont=0;
        do{
            dato=entrada.readLine();
            if (this.enfermerasNombre.containsKey(dato) == true){
                System.out.println("El nombre ingresado ya existe en la base de datos, por favor ingresar otro");
                cont++; 
            }
            if (cont==3)return;
        }while(this.enfermerasNombre.containsKey(dato) == true);
        
        Enfermera nurse = new Enfermera();
        nurse.setNombre(dato);

        System.out.println("Ingrese disponibilidad (true / false )");
        dato=entrada.readLine();
        if (dato.equals(true)) nurse.setDisponibilidad(true);
        if (dato.equals(false)) nurse.setDisponibilidad(false);

        System.out.println("Ingrese turno (dia / noche)");
        dato=entrada.readLine();
        nurse.setTurno(dato);
        
        System.out.println("Ingrese el area al que pertenece");
        do{
            dato=entrada.readLine();
            if (buscarArea(dato) == false){
                System.out.println("No se puede ingresar una nueva area a la base de datos, por favor intente nuevamente");
            }
        }while(this.enfermerasNombre.containsKey(dato) == true);
        //agregar a la lista
        this.areasHospital.get(obtenerIndex(dato)).agregarListaEnfermeras(nurse);
        //agregar al mapa
        this.enfermerasNombre.put(nurse.getNombre(), nurse);
      
    }
    
    public void mostrarEnfermeras(){
        
        for(AreasHospital a : this.areasHospital)
            a.mostrarArea();
        
        /*
        for(Enfermera a : this.enfermerasCodigo.values()){
            a.mostrarEnfermera();
        }
        
        for(Enfermera a : this.enfermerasNombre.values()){
            a.mostrarEnfermera();
        }*/
    }
    
    public void cargarDatos(){
        File archivo = new File("C:\\Users\\vicen\\OneDrive\\Documentos\\GitHub\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt");
        int numeroClave=0;
        try {
            BufferedReader entrada = new BufferedReader( new FileReader(archivo));
            String lectura = entrada.readLine();            
            while(lectura != null){
                
                String[] parts=lectura.split(",");
                Enfermera enfermera= new Enfermera();
                
                enfermera.setNombre(parts[1]);
                enfermera.setCodigo(100+numeroClave);
                enfermera.setTurno(parts[2]);
                
                
                this.enfermerasCodigo.put(enfermera.getCodigo(), enfermera);
                this.enfermerasNombre.put(enfermera.getNombre(), enfermera);
                
                if(buscarArea(parts[0])==false || this.areasHospital.size()==13){
                    AreasHospital area= new AreasHospital(parts[0]);
                    this.areasHospital.add(area);
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
    public boolean buscarArea(String nombreArea){
        for (AreasHospital area : this.areasHospital){
            if (area.getNombre().equals(nombreArea)){
                return true;
            }
        }
        return false;
    }
    public int obtenerIndex(String nombreArea){
        int i;
        for (i=0;i<this.areasHospital.size();i++){
            if (this.areasHospital.get(i).getNombre().equals(nombreArea))
                return i;
        } 
        return i;
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
