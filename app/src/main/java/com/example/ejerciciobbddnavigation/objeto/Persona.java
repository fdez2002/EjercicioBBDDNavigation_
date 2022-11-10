package com.example.ejerciciobbddnavigation.objeto;

public class Persona {
    private String dni;
    private String nombre;
    private String apellidos;
    private String sexo;

    public Persona() {
    }

    public Persona(String dni, String nombre, String apellidos, String sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
