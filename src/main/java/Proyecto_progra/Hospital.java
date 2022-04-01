package Proyecto_progra;

import java.io.*;
import java.util.*;

public class Hospital{
    private ArrayList<AreasHospital> areasHospital ;
    private String nombre;
    private HashMap<Integer,Enfermera> enfermerasCodigo;
    
    public Hospital(){}
    public Hospital(String nombre) {
        this.areasHospital = new ArrayList<>();
        this.nombre = nombre;
        this.enfermerasCodigo = new HashMap<>();
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
            System.out.println("3. Mostrar Enfermera");
            System.out.println("4. ");
            System.out.println("0. Salir");
            System.out.println("Seleccione el numero para operar:");
            
            numero=Integer.parseInt(entrada.readLine());
            switch(numero){
                case 1:
                case 2:
                case 3: mostrarEnfermeraPorCodigo();
                case 4:
                case 0: break;
                default: System.out.println("Opcion no valida."); break;
            }
            
        }
    }
    public void cargarDatos(){
        this.areasHospital = ManejoArchivos.cargarArchivoList("C:\\Users\\lucas\\Desktop\\Universidad\\Programacion General\\Java\\Proyecto_Progra\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt");
        for (AreasHospital a : this.areasHospital){
            a.mostrarArea();
        }
        

        //this.enfermerasCodigo = ManejoArchivos.cargarArchivoMap("C:\\Users\\lucas\\Desktop\\Universidad\\Programacion General\\Java\\Proyecto_Progra\\ProyectoGrupo23\\src\\main\\java\\Proyecto_progra\\Enfermeras.txt");  
    }
    public void mostrarEnfermeraPorCodigo()throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String ingreso;
        Enfermera enfermera;
        System.out.println("Ingrese su Codigo:");
        ingreso=entrada.readLine();
        if(this.enfermerasCodigo.containsKey(Integer.parseInt(ingreso))==true){
            enfermera=this.enfermerasCodigo.get(Integer.parseInt(ingreso));
            enfermera.mostrarEnfermera();
        }
    }
    
    
     
}
