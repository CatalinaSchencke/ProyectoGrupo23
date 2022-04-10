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
            System.out.println("2. Agregar Horario.");
            System.out.println("3. Mostrar Areas.");
            System.out.println("4. Mostrar Enfermeras.");
            System.out.println("5. Mostrar Horarios de Enfermeras.");
            System.out.println("6. Buscar Enfermera.");
            System.out.println("7. Cambiar Turno y/o Disponibilidad.");
            System.out.println("0. Salir");
            System.out.println("Seleccione el numero para operar:");
            
            numero=Integer.parseInt(entrada.readLine());
            switch(numero){
                case 1: agregarEnfermera(); break;
                case 2: agregarHorario(); break;
                case 3: mostrarListadoAreas(); break;
                case 4: mostrarListadoEnfermeras(); break;
                case 5: mostrarHorarios(); break;
                case 6: {
                    System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
                    String dato=entrada.readLine();
                    if (isNumeric(dato)==true) buscarEnfermera(Integer.parseInt(dato));
                    else buscarEnfermera(dato);
                }
                case 7:{
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
    
    public void agregarHorario() throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String dato;
        do{
            System.out.println("Ingrese Nombre de la enfermera");
            dato = entrada.readLine();
            
        }while(this.enfermerasNombre.get(dato)==null);
        if (this.enfermerasNombre.get(dato).tieneHorario()==false){
            for (int i=0;i<7;i++){
                int horaEntrada, horaSalida;
                System.out.println("Hora de Entrada Dia (De 1 a 24)"+(i+1));
                horaEntrada=Integer.parseInt(entrada.readLine());
                System.out.println("Hora de Salida Dia (De 1 a 24)"+(i+1));
                horaSalida=Integer.parseInt(entrada.readLine());
                this.enfermerasNombre.get(dato).agregarHorario(horaEntrada,horaSalida);
            }
        }else System.out.println("Ya tiene un Horario.");
        
        
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
        
        Enfermera nurse = new Enfermera(dato);
        
        System.out.println("Ingrese disponibilidad (true / false )");
        dato=entrada.readLine();
        if (dato.equals(true)) nurse.setDisponibilidad(true);
        if (dato.equals(false)) nurse.setDisponibilidad(false);

        System.out.println("Ingrese turno (dia / noche)");
        dato=entrada.readLine();
        nurse.setTurno(dato);
        
        System.out.println("Ingrese el area al que pertenece(Todo con Mayusculas)");
        do{
            dato=entrada.readLine();
            if (buscarArea(dato) == false){
                System.out.println("No se puede ingresar una nueva area a la base de datos, por favor intente nuevamente");
            }
            else break;
        }while(this.enfermerasNombre.containsKey(dato) == false);
        int max=0;
        for (int i : this.enfermerasCodigo.keySet()) if (i > max) max = i;
        nurse.setCodigo(max+1);
        
        //agregar a la lista
        this.areasHospital.get(obtenerIndex(dato)).agregarListaEnfermeras(nurse);
        //agregar al mapa
        this.enfermerasNombre.put(nurse.getNombre(), nurse);
        this.enfermerasCodigo.put(nurse.getCodigo(), nurse);
      
    }
    
    public void mostrarListadoAreas(){
        for(AreasHospital areas : this.areasHospital)
            areas.mostrarArea();
    }
    public void mostrarListadoEnfermeras(){
        for(Enfermera enfermera : this.enfermerasNombre.values())
            enfermera.mostrarEnfermera();
    }
    public void mostrarHorarios(){
        for(Enfermera enfermera : this.enfermerasNombre.values()){
            enfermera.mostrarHorario();
        }
    }
    
    public void cargarDatos(){
        File archivo = new File("C:\\Users\\lucas\\Desktop\\Universidad\\Programacion General\\Java\\Proyecto_Progra\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt");
        int numeroClave=0;
        try {
            BufferedReader entrada = new BufferedReader( new FileReader(archivo));
            String lectura = entrada.readLine();            
            while(lectura != null){
                
                String[] parts=lectura.split(",");
                Enfermera enfermera= new Enfermera(parts[1]);
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
