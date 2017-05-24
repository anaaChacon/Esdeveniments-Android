package com.example.anabel.esdevenimentsvalencia.models;

/**
 * Created by Anabel on 14/05/2017.
 */

public class Lugares {

    private int id_lugar;
    private String nombreLugar, direccion, imagen, informacion;
    private Double coor_latitud, coor_longitud;

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public int getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(int id_lugar) {
        this.id_lugar = id_lugar;
    }

    public Double getCoor_latitud() {
        return coor_latitud;
    }

    public void setCoor_latitud(Double coor_latitud) {
        this.coor_latitud = coor_latitud;
    }

    public Double getCoor_longitud() {
        return coor_longitud;
    }

    public void setCoor_longitud(Double coor_longitud) {
        this.coor_longitud = coor_longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
}
