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
                case 1: agregarEnfermera(hospital); break;
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
                case 7: buscarEnfermera(hospital);break;
                case 8: menuTurnoDisponibilidad(hospital);break;
                case 9: hospital.exportarReporte(); break;
                case 10: menuModificar(hospital); break;
                case 11:{
                    if (flagCalculo==true){
                        generarSalario(hospital);
                        flagCalculo=false;
                    }  
                    else System.out.println("Primero marque la salida de su ultimo turno antes de calcular.");
                    break;
                } 
                case 0: break;
                default: System.out.println("Opcion no valida."); break;
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
    public void mostrarListadoAreas(Hospital hospital)throws IOException{
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
    public void mostrarListadoEnfermeras(Hospital hospital)throws IOException{
        ArrayList aux = hospital.mostrarListadoEnfermeras();
        for (int i=0; i<aux.size();i++){
            String s = (String) aux.get(i);
            mostrarStringsConcatenados(s);
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
    public void buscarEnfermera(Hospital hospital)throws IOException{
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
        }
    } 
    
    public void menuTurnoDisponibilidad(Hospital hospital)throws IOException{
        int numero = -1;    
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));       
        while(numero != 0){        
            System.out.println("-----------------------------------------");            
            System.out.println("     Seleccione que cambio desea hacer");
            System.out.println("-----------------------------------------");           
            System.out.println("1. Modificar Turno.");
            System.out.println("2. Modificar Disponibilidad.");            
            System.out.println("3. Modificar Turno y Disponibilidad.");
            System.out.println("0. Salir");            
            System.out.println("Seleccione el numero para operar:");
         
            numero=Integer.parseInt(entrada.readLine());
            switch(numero){           
                case 1:modificarTurno(hospital);break;            
                case 2:modificarDisponibilidad(hospital);break;               
                //case 3:modificarTurnoyDisponibilidad(hospital);break;            
                case 0: break;
                default: System.out.println("Opcion no valida."); break;   
            }            
        }
    }  
    public void modificarTurno(Hospital hospital)throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String s = null, turno;
        System.out.println("Ingrese Turno a modificar (dia/noche)");                                        
        turno = entrada.readLine();                                      
        if(turno.equals("dia") || turno.equals("noche")){                       
            System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");                            
            String dato=entrada.readLine();                           
            s=hospital.cambioTurnoEnfermera(dato, turno);                              
        }
        mostrarStringsConcatenados(s);
    }
    public void modificarDisponibilidad(Hospital hospital)throws IOException{                
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String s = null, aux;
        System.out.println("Ingrese Disponibilidad a modificar (Si/No)");                         
        aux = entrada.readLine();
        if(aux.equals("Si")){
            System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");
            aux = entrada.readLine();
            s=hospital.cambioTurnoEnfermera(aux, true);
        }
        if(aux.equals("No")){
            System.out.println("Codigo o nombre enfermera");
            aux = entrada.readLine();
            s=hospital.cambioTurnoEnfermera(aux, false);
        }
        mostrarStringsConcatenados(s);
                
                
    }
    public void modificarTurnoyDisponibilidad(Hospital hospital)throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); 
        String turno, disponibilidad, dato, s;
        System.out.println("Ingrese Turno a modificar (dia/noche)");                  
        turno=entrada.readLine();                      
        if(turno.equals("dia") || turno.equals("noche")){                       
            System.out.println("Ingrese Disponibilidad a modificar (Si/No)");                            
            disponibilidad  = entrada.readLine();                          
            boolean disponibilidadB;
            if(disponibilidad.equals("Si")) disponibilidadB= true;                                                                               
            if(disponibilidad.equals("No")) disponibilidadB= false;                                                                    
                                           
            System.out.println("Ingrese numero Codigo o Nombre de la enfermera:");                           
            dato =entrada.readLine();                           
            s=hospital.cambioTurnoEnfermera(dato,turno,true);
            mostrarStringsConcatenados(s);
        }                                  
    }
    
    public void menuModificar( Hospital hospital)throws IOException{
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
                case 1: eliminarEnfermeraArea(hospital);break;
                case 2: eliminarEnfermeraHospital(hospital);break;
                case 3: cambiarNombreArea(hospital);break;
                case 4: eliminarArea(hospital);break;                
                case 0: break;
                default: System.out.println("Opcion no valida."); break;   
            }
        }
    }
    public void eliminarEnfermeraHospital (Hospital hospital) throws IOException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));  
        String aux;
        do{
            System.out.println("Ingrese nombre de enfermera a eliminar");
            aux = entrada.readLine();
            if (hospital.existeEnfermera(aux)==true)break;
        }while(true==true);
        hospital.eliminarEnfermeraHospital(aux);
        //System.out.println("Eliminado correctamente.");
    }
    public void eliminarEnfermeraArea(Hospital hospital)throws IOException{
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
    public void cambiarNombreArea(Hospital hospital)throws IOException{
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
    public void eliminarArea(Hospital hospital)throws IOException{
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
