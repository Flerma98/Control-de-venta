package com.karla.control_venta.Tablas;

public class Venta {
    private String IDVenta;
    private String IDCliente;
    private String IDDistribuidor;
    private int Numero_Quincenas;
    private String Fecha;
    private long Numero_Vale;
    private double Valor_Vale;
    private String UID;

    public Venta() {
    }

    public Venta(String IDVenta, String IDCliente, String IDDistribuidor, int numero_Quincenas, String fecha, long numero_Vale, double valor_Vale, String UID) {
        this.IDVenta = IDVenta;
        this.IDCliente = IDCliente;
        this.IDDistribuidor = IDDistribuidor;
        Numero_Quincenas = numero_Quincenas;
        Fecha = fecha;
        Numero_Vale = numero_Vale;
        Valor_Vale = valor_Vale;
        this.UID = UID;
    }

    public String getIDVenta() {
        return IDVenta;
    }

    public void setIDVenta(String IDVenta) {
        this.IDVenta = IDVenta;
    }

    public String getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(String IDCliente) {
        this.IDCliente = IDCliente;
    }

    public String getIDDistribuidor() {
        return IDDistribuidor;
    }

    public void setIDDistribuidor(String IDDistribuidor) {
        this.IDDistribuidor = IDDistribuidor;
    }

    public int getNumero_Quincenas() {
        return Numero_Quincenas;
    }

    public void setNumero_Quincenas(int numero_Quincenas) {
        Numero_Quincenas = numero_Quincenas;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public long getNumero_Vale() {
        return Numero_Vale;
    }

    public void setNumero_Vale(long numero_Vale) {
        Numero_Vale = numero_Vale;
    }

    public double getValor_Vale() {
        return Valor_Vale;
    }

    public void setValor_Vale(double valor_Vale) {
        Valor_Vale = valor_Vale;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
