package Proyecto_progra;

public class AreasHospital implements MostrarDatos{
    private ListaEnfermerasArea enfermerasArea;
    private String nombre;
    
    public AreasHospital(){}
    public AreasHospital(String nombre) {
        this.enfermerasArea = new ListaEnfermerasArea();
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void agregarListaEnfermeras (Enfermera enfermera){
        enfermerasArea.add(enfermera);
    }
    
    public Enfermera obtenerEnfermera( int index){ 
        return enfermerasArea.get(index);
    }
    
    public int obtenerSize(){
        return enfermerasArea.size();
    }
    public void eliminarListaEnfermeras (String nombre){
        for (int i=0;i<enfermerasArea.size();i++){
            if (enfermerasArea.get(i).getNombre().equals(nombre)){
                enfermerasArea.remove(i);
            }
        }
    }
    public String Mostrar (){
        String s=nombre+",";
        for (int i = 0; i < enfermerasArea.size(); i++) {
            s=s.concat(enfermerasArea.get(i).Mostrar());
        }
        return s;
    }
    
    public String Mostrar (boolean disponibilidad){
        String s=nombre+",";
        for (int i = 0; i < enfermerasArea.size(); i++) {
            if( enfermerasArea.get(i).isDisponibilidad()==disponibilidad){
                s=s.concat(enfermerasArea.get(i).Mostrar());
            }
        }
        return s;
    }

    public boolean existeEnfermera(String nombre){
        for (int i=0;i<enfermerasArea.size();i++){
            if (enfermerasArea.get(i).getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }
}
