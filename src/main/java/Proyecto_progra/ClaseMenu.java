package Proyecto_progra;

import java.io.*;
import java.util.*;

public class ClaseMenu {
    
    Hospital hospital;
    
    
    public void menuHospital()throws IOException{
        Hospital hospital = new Hospital("Alexander Fleming");
        hospital.cargarDatos();
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
            System.out.println("0. Salir.");
            System.out.println("Seleccione el numero para operar:");
            
            numero=Integer.parseInt(entrada.readLine());
            switch(numero){
                //case 1: agregarEnfermera(); break;
                case 2: agregarArea(hospital); break;
                case 3: mostrarListadoAreas(hospital);break;
                case 4: mostrarListadoEnfermeras(hospital); break;
                case 5: {
                    marcarEntrada(hospital);
                    aux=1;
                    break;
                }
                case 6: {
                    if (aux==1){
                        marcarSalida(hospital);
                        flagCalculo=true;
                    }
                    break;
                    
                }
                /*case 7: {
                    System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
                    String dato=entrada.readLine();
                    if (isNumeric(dato)==true) buscarEnfermera(Integer.parseInt(dato));
                    else buscarEnfermera(dato);
                }
                case 8: menuTurnoDisponibilidad();break;
                case 9: exportarReporte(); break;
                case 10: menuModificar(); break;*/
                case 11:{
                    //if (flagCalculo==true){
                        generarSalario(hospital);
                        //flagCalculo=false;
                    //}  
                    //else System.out.println("Primero marque la salida de su ultimo turno antes de calcular.");
                    //break;
                } 
                case 0: break;
                default: System.out.println("Opcion no valida."); break;
            }
            
        }
    }
    public void marcarEntrada (Hospital hospital) throws  IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();
        hospital.marcarEntrada(dato);
    }
    
    public void marcarSalida(Hospital hospital) throws IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();
        hospital.marcarSalida(dato);
    }
    public void generarSalario (Hospital hospital) throws IOException{
        System.out.println("Ingrese Codigo o Nombre de la Enfermera");
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato=entrada.readLine();    
        Enfermera enf=hospital.generarSalario(dato);
        System.out.println(enf.mostrarSalario());
    }
    public void mostrarListadoAreas(Hospital hospital)throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String dato;
        ArrayList aux = new ArrayList (hospital.mostrarListadoAreas());
 
        for(int i=0;i<aux.size();i++){
            String nombreAreas = (String) aux.get(i);
            System.out.println(nombreAreas);
        }
           
        System.out.println("Quiere mostrar enfermeras del area (si o no):");
        dato = entrada.readLine();
        if(dato.equals("si")){
            System.out.println("Que area desea ver:");
            dato=entrada.readLine();
            aux = hospital.mostrarEnfermerasArea(dato);
            for(int i=0;i<aux.size();i++){
                String mostrar = ((Enfermera)aux.get(i)).Mostrar();
                mostrarStringsConcatenados(mostrar);
                System.out.println(" ");
            }  
        }
    }
    public void agregarEnfermera(Hospital hospital)throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String dato;
        int cont=0;
        while(true){
            System.out.println("Ingrese Nombre de la enfermera");
            dato=entrada.readLine();
            if (hospital.existeEnfermera(dato)==false)break;
            if(cont==3)return;
            System.out.println("Tiene "+(3-cont)+" intentos mas:");
            cont++;
        }
        String s=dato;
        System.out.println("Ingrese disponibilidad (true / false )");
        dato=entrada.readLine();
        s=s.concat(","+dato);
        System.out.println("Ingrese turno (dia / noche)");
        dato=entrada.readLine();
        s=s.concat(","+dato);
        while(true){
            System.out.println("Ingrese el area al que pertenece(Todo con Mayusculas)");
            dato=entrada.readLine();
            if (hospital.buscarArea(dato)==true)break;
            if (cont==3)return;
            System.out.println("Tiene "+(3-cont)+" intentos mas:");
            cont++;
        }
        s=s.concat(","+dato);
        boolean aux = hospital.agregarEnfermera(s);
        if(aux==true){
            System.out.println("Se agrego exitosamente.");
        }else{
            System.out.println("Hubo un error al ingresar la enfermera.");
        }
    }
    public void mostrarListadoEnfermeras(Hospital hospital)throws IOException{
        ArrayList aux = hospital.mostrarListadoEnfermeras();
        for (int i=0; i<aux.size();i++){
            String s = (String) aux.get(i);
            mostrarStringsConcatenados(s);
            System.out.println(" ");
        }
    }
    
    
    public void mostrarStringsConcatenados(String aux){
        String[] parts=aux.split(",");
        for (int i=0; i<parts.length;i++){
            System.out.println(parts[i]);
        }
    }
    
    public void agregarArea (Hospital hospital) throws IOException {
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
    /*public void menuTurnoDisponibilidad()throws IOException{
        int numero2 = -1;    
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));       
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
                        
                        if(disponibilidad.equals("Si")) disponibilidadB= true;
                        else{                        
                            if(disponibilidad.equals("No")) disponibilidadB= false;                                                          
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
                           
                            if(disponibilidad.equals("Si")) disponibilidadB= true;                                                         
                            else{                           
                                if(disponibilidad.equals("No")) disponibilidadB= false;                                                                    
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
    }*/
}
