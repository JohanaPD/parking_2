package com.clasesParking;

public class Plaza {
    //Para saber el número de la plaza
    private int id;
    private boolean ocupada;
    private Coche coche;

    public Plaza(int id, boolean ocupada) {
        this.id = id;
        this.ocupada = ocupada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public void liberarPlaza(){
        this.ocupada=false;
        this.coche=null;
    }
    public void ocupar(Coche coche){
        this.ocupada=true;
        this.coche=coche;
            }

    @Override
    public String toString() {
        return "Plaza{" +
                "id=" + id +
                ", ocupada=" + ocupada +
                ", coche=" + coche.getMatricula() +
                '}';
    }
}
