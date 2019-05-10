package com.karla.control_venta.Tablas;

public class Abono {
    private String ID_Pago;
    private String ID_Cliente;
    private String ID_Distribuidor;
    private double Cantidad;
    private String Fecha;
    private String UID;

    public Abono() {
    }

    public Abono(String ID_Pago, String ID_Cliente, String ID_Distribuidor, double cantidad, String fecha, String UID) {
        this.ID_Pago = ID_Pago;
        this.ID_Cliente = ID_Cliente;
        this.ID_Distribuidor = ID_Distribuidor;
        Cantidad = cantidad;
        Fecha = fecha;
        this.UID = UID;
    }

    public String getID_Pago() {
        return ID_Pago;
    }

    public void setID_Pago(String ID_Pago) {
        this.ID_Pago = ID_Pago;
    }

    public String getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(String ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getID_Distribuidor() {
        return ID_Distribuidor;
    }

    public void setID_Distribuidor(String ID_Distribuidor) {
        this.ID_Distribuidor = ID_Distribuidor;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double cantidad) {
        Cantidad = cantidad;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
