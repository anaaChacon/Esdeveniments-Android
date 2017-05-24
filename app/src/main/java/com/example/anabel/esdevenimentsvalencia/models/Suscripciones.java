package com.example.anabel.esdevenimentsvalencia.models;

/**
 * Created by Anabel on 23/05/2017.
 */

public class Suscripciones {

    private int idUsuario, idEvento;

    public Suscripciones() {
    }

    public Suscripciones(int idUsuario, int idEvento) {
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
}
