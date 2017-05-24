package com.example.anabel.esdevenimentsvalencia.models;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Anabel on 06/05/2017.
 */

public class Eventos {

    private String nombre, descripcion, info_secundaria, foto_miniatura, foto_principal;
    private String fecha_inicio, fecha_fin;
    private String hora_inicio, hora_fin;
    private int idCategoria, idLugar, idOrganizador, id_evento;

    public Eventos(String nombre, String descripcion, String info_secundaria, String foto_miniatura, String foto_principal, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin, int idCategoria, int idLugar, int idOrganizador, int id_evento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.info_secundaria = info_secundaria;
        this.foto_miniatura = foto_miniatura;
        this.foto_principal = foto_principal;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.idCategoria = idCategoria;
        this.idLugar = idLugar;
        this.idOrganizador = idOrganizador;
        this.id_evento = id_evento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInfo_secundaria() {
        return info_secundaria;
    }

    public void setInfo_secundaria(String info_secundaria) {
        this.info_secundaria = info_secundaria;
    }

    public String getFoto_miniatura() {
        return foto_miniatura;
    }

    public void setFoto_miniatura(String foto_miniatura) {
        this.foto_miniatura = foto_miniatura;
    }

    public String getFoto_principal() {
        return foto_principal;
    }

    public void setFoto_principal(String foto_principal) {
        this.foto_principal = foto_principal;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(int idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }
}
