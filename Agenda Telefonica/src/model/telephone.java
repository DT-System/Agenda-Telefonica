package model;

public class telephone {

    private int id;
    private String nombre;
    private String apellido;
    private int telefono;
    private int celular;
    private String observaciones;

    public telephone() {
        super();
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.telefono = 0;
        this.celular = 0;
        this.observaciones = "";
    }

    public telephone(int id, String nombre, String apellido, int telefono, int celular, String observaciones) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.celular = celular;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;

    }

    public String getNombre() {
        return nombre;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;

    }

    public String getApellido() {
        return apellido;

    }

    public void setApellido(String apellido) {
        this.apellido = apellido;

    }

    public int getTelefono() {
        return telefono;

    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;

    }

    public int getCelular() {
        return celular;

    }

    public void setCelular(int celular) {
        this.celular = celular;

    }

    public String getObservaciones() {
        return observaciones;

    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;

    }

}
