package Proyecto_progra;

import java.io.*;
import java.util.*;

public class ClaseMenu extends javax.swing.JFrame {
    
    //Instancia de hospital
    Hospital hospital;
        
    /*Cuando se construye esta clase se crea el hospital y a la vez se cargan 
    los datos*/
    public ClaseMenu() {
        this.hospital = new Hospital("Alexander Fleming");
        hospital.cargarDatos();
    }
    
    /*Menu que se muestra por pantalla, corresponde al menu proncipal*/
    public void menuHospital()throws IOException, ErrorIOArchivosException{
        
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
    
    /*Funcion que llama a una ventana para agregar una enfermera a las colecciones
    del hospital*/
    public void agregarEnfermera(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_AgregarEnfermera(hospital).setVisible(true);
            }
        });
    }
    /*Agrega un area en el hospital llamando a la funcion con el mismo nombre 
    desde el menu*/
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
    /*Muestra los nombres de las areas por pantalla, para luego, preguntar
    si quiere mostrar las enfermeras de un area en especifico*/
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
    /*Llama a una ventana donde muestra en una tabla a todas las enfermeras
    del hospital*/
    public void mostrarListadoEnfermeras()throws IOException{
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_MostrarEnfermeras(hospital).setVisible(true);
            }
        });
    }
    /*Esta funcion llama a una del mismo nombre dentro de la clase hospital y marca
    la hora exacta de entrada de una enfermera al trabajo*/
    public void marcarEntrada () throws  IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();
        hospital.marcarEntrada(dato);
    }
    /*Esta funcion llama a una del mismo nombre dentro de la clase hospital y marca
    la hora exacta de salida de una enfermera al trabajo, cabe destacar que para 
    usar esta funcion primero hay que marcar la entrada, lo cual se hace en el 
    menuHospital esta verificacion*/
    public void marcarSalida() throws IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();
        hospital.marcarSalida(dato);
    }
    /*Llama a la funcion del mismo nombre en el hospital para generar su salario,
    y al igual que la funcion de arriba se necesita haber marcado primero una 
    entrada y salida para generar este salario*/
    public void generarSalario () throws IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();    
        String s=hospital.generarSalario(dato);
        System.out.println(s);
    }
    /*Pide el nombre por pantalla para buscar a una enfermera en el hospital y
    retornarla por pantalla*/
    public void buscarEnfermera()throws IOException, ErrorIOArchivosException{
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
    /*Funcion que se usa para mostrar strings que estan concatenados por comas
    por pantalla. Estos strings tienen siempre la forma de "linea1,linea2,..,"*/
    public void mostrarStringsConcatenados(String aux){
        String[] parts=aux.split(",");
        for (int i=0; i<parts.length;i++){
            System.out.println(parts[i]);
            System.out.println(" ");
        }
    } 
    /*Menu secundario al menu principal(menuHospital) que se ejecuta en ventana
    y tiene otras funciones que tambien se ejecutan en ventana que son parte de
    la funcionalidad, estas son: cambiar turno, cambiar disponibilidad y el
    cambio de turno y disponibilidad al mismo tiempo*/
    public void menuTurnoDisponibilidad()throws IOException{
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                new Ventana_MenuDisponibilidad(hospital).setVisible(true);
            }
        });
    }  
    
    /*Funcion que muestra por pantalla a la enfermera mejor pagada del hospital*/
    public void mejorPagada(){
        mostrarStringsConcatenados(hospital.mejorPagada());
        //String aux = hospital.mejorPagada();
        //System.out.println("La Enfermera mejor pagada es: "+ aux);
    }
    /*Segundo menu secundario que tambien sale del menu principal(menuHospital)
    de donde al igual que el anterior tiene funciones asociadas a la funcionalidad
    del codigo*/
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
    /*Esta funcion llama al hospital para eliminar a una enfermera de todo el 
    sistema*/
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
    /*Esta funcion llama al hospital para eliminar a una enfermera del area
    pero no del hospital*/
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
    /*Funcion que llama a hospital para cambiar el nombre de un area*/
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
    /*Funcion que llama a hospital para eliminar un area del hospital*/
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
