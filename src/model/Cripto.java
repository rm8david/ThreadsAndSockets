package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Cripto implements Serializable {
    private static final long serialVersionUID = 1234L;
    private String nombre;
    private String tipo;
    private String simbolo;
    private String descripcion;
    private double precio;
    private long marketCap;
    private String fechaLanzamiento;

    public Cripto() {
    }

    public Cripto(String nombre, String tipo, String simbolo, String descripcion, double precio, long marketCap, String fechaLanzamiento) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.simbolo = simbolo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marketCap = marketCap;
        this.fechaLanzamiento = fechaLanzamiento;
    }


    @Override
    public String toString() {
        return "Cripto{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", simbolo='" + simbolo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio + " USD"+
                ", marketCap=" + marketCap + " USD"+
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}

