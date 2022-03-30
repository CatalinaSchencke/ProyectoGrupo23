package com.mycompany.proyecto_progra;
import java.io.*;
import java.util.*;

public class Hospital{
    private List<AreasHospital> areasHospital ;
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
            Enfermera enf = new Enfermera();
            System.out.println("-----------------------------------------");
            System.out.println("          Bienvenido al Hospital");
            System.out.println("-----------------------------------------");
            
            
            System.out.println("1. Agregar Enfermera.");
            System.out.println("2. Mostrar Lista de Enfermeras.");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("0. Salir");
            System.out.println("Seleccione el numero para operar:");
            
            numero=Integer.parseInt(entrada.readLine());
            switch(numero){
                case 1:
                case 2:
                case 3:
                case 4:
                case 0: break;
                default: System.out.println("Opcion no valida."); break;
            }
            
        }
    }
    public void cargarDatos(){
        
    }
}
