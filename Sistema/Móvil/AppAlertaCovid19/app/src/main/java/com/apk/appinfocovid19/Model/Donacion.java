package com.apk.appinfocovid19.Model;

import android.renderscript.ScriptIntrinsicYuvToRGB;

public class Donacion {
    String tipoDonacion;
    String calidadAlimentos;
    int cantidad;
    String direccion;
    String nombreDonante;
    String token;
    String latitud;
    String longitud;
    String estado_recepcion;
   String noombre_producto;
    String celular;
    String key_donacion;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getEstado_recepcion() {
        return estado_recepcion;
    }

    public void setEstado_recepcion(String estado_recepcion) {
        this.estado_recepcion = estado_recepcion;
    }

    public String getKey_donacion() {
        return key_donacion;
    }

    public void setKey_donacion(String key_donacion) {
        this.key_donacion = key_donacion;
    }

    public String getNoombre_producto() {
        return noombre_producto;
    }

    public void setNoombre_producto(String noombre_producto) {
        this.noombre_producto = noombre_producto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}

