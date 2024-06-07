package model;

import java.time.LocalDate;

public class Movimiento {
    private String cantidad;
    private String categoria;

    public Movimiento(String cantidad, String categoria) {
        this.cantidad = cantidad;
        this.categoria = categoria;
    }


    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}