package com.apk.appinfocovid19.Model;

public class Donacion {
    String tipoDonacion;
    String calidadAlimentos;
    int cantidad;
    String direccion;
    String nombreDonante;


    public String getTipoDonacion() {
        return tipoDonacion;
    }

    public void setTipoDonacion(String tipoDonacion) {
        this.tipoDonacion = tipoDonacion;
    }

    public String getCalidadAlimentos() {
        return calidadAlimentos;
    }

    public void setCalidadAlimentos(String calidadAlimentos) {
        this.calidadAlimentos = calidadAlimentos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreDonante() {
        return nombreDonante;
    }

    public void setNombreDonante(String nombreDonante) {
        this.nombreDonante = nombreDonante;
    }
}

