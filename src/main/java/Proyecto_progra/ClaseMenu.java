package Proyecto_progra;

import java.io.*;
import java.util.*;

public class ClaseMenu extends javax.swing.JFrame {
    
    
    Hospital hospital;
    
    public ClaseMenu() {
        this.hospital = new Hospital("Alexander Fleming");
        hospital.cargarDatos();
    }
    
    
    public void menuHospital()throws IOException{
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));       
        int numero = -1;
        int aux = 0;
        boolean flagCalculo = false;
        
        while(numero != 0){
            System.out.println("-----------------------------------------");
            System.out.println("          Bienvenido al Hospital");
            System.out.println("-----------------------------------------");
            
            
            System.out.println("1. Agregar Enfermera.");
            System.out.println("2. Agregar Area.");
            System.out.println("3. Mostrar Areas.");
            System.out.println("4. Mostrar Enfermeras.");
            System.out.println("5. Marcar Entrada.");
            System.out.println("6. Marcar Salida.");
            System.out.println("7. Buscar Enfermera.");
            System.out.println("8. Cambiar Turno y/o Disponibilidad.");
            System.out.println("9. Generar Reporte.");
            System.out.println("10. Modificacion y Eliminacion.");
            System.out.println("11. Generar Salario.");
            System.out.println("12. Mostrar enfermera mejor pagada. (Para el uso de esta funcion requiere previamente que se haya generado un salario)");
            System.out.println("0. Salir.");
            System.out.println("Seleccione el numero para operar:");
            
            numero=Integer.parseInt(entrada.readLine());
            switch(numero){
                case 1: agregarEnfermera(); break;
                case 2: agregarArea(); break;
                case 3: mostrarListadoAreas();break;
                case 4: mostrarListadoEnfermeras(); break;
                case 5: {
                    marcarEntrada();
                    aux=1;
                    break;
                }
                case 6: {
                    if (aux==1){
                        marcarSalida();
                        flagCalculo=true;
                    }
                    break;
                    
                }
                case 7: buscarEnfermera();break;
                case 8: menuTurnoDisponibilidad();break;
                case 9: hospital.exportarReporte(); break;
                case 10: menuModificar(); break;
                case 11:{
                    if (flagCalculo==true){
                        generarSalario();
                        flagCalculo=false;
                    }  
                    else System.out.println("Primero marque la salida de su ultimo turno antes de calcular.");
                    break;
                }
                case 12: mejorPagada(); break;
                case 0: break;
                default: System.out.println("Opcion no valida."); break;
            }
            
        }
    }
    
    public void agregarEnfermera(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_AgregarEnfermera(hospital).setVisible(true);
            }
        });
    }
    public void agregarArea () throws IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String dato;
        
        System.out.println("Ingrese Nombre del Area Nueva:");
        dato = entrada.readLine();
        boolean aux=hospital.agregarArea(dato);
        if (aux==true){
            System.out.println("Area : "+ dato+ " agregada exitosamente ");
        }else{
            System.out.println("Hubo un error al ingresar el Area.");
        }
    
    }
    public void mostrarListadoAreas()throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato, s;
        ArrayList aux = new ArrayList (hospital.mostrarListadoAreas());
        
        for(int i=0;i<aux.size();i++){
            String[] parts=((String)aux.get(i)).split(",");
            System.out.println(parts[0]);
        }
        
        System.out.println("Quiere mostrar enfermeras del area (si o no):");
        dato = entrada.readLine();
        if(dato.equals("si")){
            System.out.println("Que area desea ver:");
            dato=entrada.readLine();
            s = hospital.mostrarEnfermerasArea(dato);
            mostrarStringsConcatenados(s);
              
        }
    }
    public void mostrarListadoEnfermeras()throws IOException{
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_MostrarEnfermeras(hospital).setVisible(true);
            }
        });
    }
    public void marcarEntrada () throws  IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();
        hospital.marcarEntrada(dato);
    }
    public void marcarSalida() throws IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();
        hospital.marcarSalida(dato);
    }
    public void generarSalario () throws IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();    
        String s=hospital.generarSalario(dato);
        System.out.println(s);
    }
    public void buscarEnfermera()throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
        String dato=entrada.readLine();
        if (hospital.isNumeric(dato)==true){
            mostrarStringsConcatenados(hospital.buscarEnfermera(Integer.parseInt(dato))); 

        }
        else {
            mostrarStringsConcatenados(hospital.buscarEnfermera(dato));
        }

    }

    public void mostrarStringsConcatenados(String aux){
        String[] parts=aux.split(",");
        for (int i=0; i<parts.length;i++){
            System.out.println(parts[i]);
            System.out.println(" ");
        }
    } 
    
    public void menuTurnoDisponibilidad()throws IOException{
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                new Ventana_MenuDisponibilidad(hospital).setVisible(true);
            }
        });
    }  
    
    
    public void mejorPagada(){
        mostrarStringsConcatenados(hospital.mejorPagada());
        //String aux = hospital.mejorPagada();
        //System.out.println("La Enfermera mejor pagada es: "+ aux);
    }
    public void menuModificar()throws IOException{
        int numero2 = -1;    
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));       
        while(numero2 != 0){        
            System.out.println("-----------------------------------------");            
            System.out.println("     Seleccione que cambio desea hacer");
            System.out.println("-----------------------------------------");           
            System.out.println("1. Eliminar Enfermera de Area");
            System.out.println("2. Eliminar Enfermera de Hospital");            
            System.out.println("3. Cambiar Nombre de Area");
            System.out.println("4. Eliminar Area");
            System.out.println("0. Salir");            
            System.out.println("Seleccione el numero para operar:");
      
            numero2=Integer.parseInt(entrada.readLine());
            switch(numero2){           
                case 1: eliminarEnfermeraArea();break;
                case 2: eliminarEnfermeraHospital();break;
                case 3: cambiarNombreArea();break;
                case 4: eliminarArea();break;                
                case 0: break;
                default: System.out.println("Opcion no valida."); break;   
            }
        }
    }
    public void eliminarEnfermeraHospital () throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  
        String aux;
        do{
            System.out.println("Ingrese nombre de enfermera a eliminar");
            aux = entrada.readLine();
            if (hospital.existeEnfermera(aux)==true)break;
        }while(true==true);
        hospital.eliminarEnfermeraHospital(aux);
    }
    public void eliminarEnfermeraArea()throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  
        String dato, s;
        System.out.println("Ingrese Area de enfermera a eliminar:");
        dato=entrada.readLine();
        if (hospital.buscarArea(dato)==false)return;
        s = dato+",";
        System.out.println("Ingrese Nombre de la enfermera");
        dato=entrada.readLine();
        if (hospital.existeEnfermera(dato)==false)return;
        s = s.concat(dato+",");
        hospital.eliminarEnfermeraArea(s);
    }
    public void cambiarNombreArea()throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato, s;
        System.out.println("Nombre del Area a cambiar:");
        dato=entrada.readLine();
        if (hospital.buscarArea(dato)==false)return;
        s = dato+",";
        System.out.println("Nuevo nombre:");
        dato = entrada.readLine();
        s = s.concat(dato+",");
        hospital.cambiarNombreArea(s);
        
    }
    public void eliminarArea()throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  
        String dato;
        System.out.println("Ingrese nombre de area");
        dato = entrada.readLine();
        if (hospital.buscarArea(dato)==false){
            System.out.println("El area no existe, retornando a menu...");
            return;
        }
        
        hospital.eliminarArea(dato);
    }
}
