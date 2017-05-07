package com.example.anabel.esdevenimentsvalencia.models;

/**
 * Created by Anabel on 06/05/2017.
 */

public class Categorias {

    private String nombre, foto_categoria;

    public Categorias(){}

    public Categorias(String nombre, String foto_categoria) {
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
}
