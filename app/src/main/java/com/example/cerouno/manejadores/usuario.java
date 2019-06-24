package com.example.cerouno.manejadores;

public class usuario {
    private int id;
    private String nombre,hash;

    public usuario(String nombre, String hash) {
        this.nombre = nombre;
        this.hash = hash;
        this.id=0;
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

    public String getHash() {
        return hash;
    }

    public usuario setHash(String hash) {
        this.hash = hash;
        return this;
    }


}
