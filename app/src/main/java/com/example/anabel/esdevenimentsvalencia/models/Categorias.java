package com.example.anabel.esdevenimentsvalencia.models;

/**
 * Created by Anabel on 06/05/2017.
 */

public class Categorias {

    private int id_categoria;
    private String nombre_categoria, foto_categoria;

    public Categorias(){}

    public Categorias(int id_categoria, String nombre_categoria, String foto_categoria) {
        this.id_categoria = id_categoria;
        this.nombre_categoria = nombre_categoria;
        this.foto_categoria = foto_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
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
