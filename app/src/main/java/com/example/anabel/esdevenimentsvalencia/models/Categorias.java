package com.example.anabel.esdevenimentsvalencia.models;

/**
 * Created by Anabel on 06/05/2017.
 */

public class Categorias {

    private int id_categoria;
    private String nombre, foto_categoria;

    public Categorias(){}

    public Categorias(int id_categoria, String nombre, String foto_categoria) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.foto_categoria = foto_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto_categoria() {
        return foto_categoria;
    }

    public void setFoto_categoria(String foto_categoria) {
        this.foto_categoria = foto_categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
