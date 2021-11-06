package model;

import java.util.List;

public class Programador {
    private long id;
    private String nombre;
    private int experiencia;
    private double salario;
    private long id_departamento;
    private List<Lenguajes> lenguajes;

    public Programador(long id, String nombre, int experiencia, double salario, long id_departamento, List<Lenguajes> lenguajes) {
        this.id = id;
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.salario = salario;
        this.id_departamento = id_departamento;
        this.lenguajes = lenguajes;
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

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public long getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(long id_departamento) {
        this.id_departamento = id_departamento;
    }

    public List<Lenguajes> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<Lenguajes> lenguajes) {
        this.lenguajes = lenguajes;
    }

    @Override
    public String toString() {
        return "Programador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", experiencia=" + experiencia +
                ", salario=" + salario +
                ", id_departamento=" + id_departamento +
                ", lenguajes=" + lenguajes +
                '}';
    }
}
