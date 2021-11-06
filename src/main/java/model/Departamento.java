package model;

import java.util.List;

public class Departamento {
    private long id;
    private String nombre;
    private double presupuesto;
    private Programador jefe;
    private List<Programador> programadores;

    public Departamento(long id, String nombre, double presupuesto, Programador jefe, List<Programador> programadores) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.jefe = jefe;
        this.programadores = programadores;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Programador getJefe() {
        return jefe;
    }

    public void setJefe(Programador jefe) {
        this.jefe = jefe;
    }

    public List<Programador> getProgramadores() {
        return programadores;
    }

    public void setProgramadores(List<Programador> programadores) {
        this.programadores = programadores;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", jefe=" + jefe +
                ", programadores=" + programadores +
                '}';
    }
}
