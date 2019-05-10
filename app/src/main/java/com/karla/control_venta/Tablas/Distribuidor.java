package com.karla.control_venta.Tablas;

public class Distribuidor {
    private String ID;
    private String Apellido_Paterno;
    private String Apellido_Materno;
    private String Nombre;
    private String Telefono;
    private String Correo;
    private String Direccion;
    private String Ciudad;
    private String Estado;
    private String Estatus;
    private String Sexo;
    private String UID;

    public Distribuidor() {
    }

    public Distribuidor(String ID, String apellido_Paterno, String apellido_Materno, String nombre, String telefono, String correo, String direccion, String ciudad, String estado, String estatus, String sexo, String UID) {
        this.ID = ID;
        Apellido_Paterno = apellido_Paterno;
        Apellido_Materno = apellido_Materno;
        Nombre = nombre;
        Telefono = telefono;
        Correo = correo;
        Direccion = direccion;
        Ciudad = ciudad;
        Estado = estado;
        Estatus = estatus;
        Sexo = sexo;
        this.UID = UID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getApellido_Paterno() {
        return Apellido_Paterno;
    }

    public void setApellido_Paterno(String apellido_Paterno) {
        Apellido_Paterno = apellido_Paterno;
    }

    public String getApellido_Materno() {
        return Apellido_Materno;
    }

    public void setApellido_Materno(String apellido_Materno) {
        Apellido_Materno = apellido_Materno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String estatus) {
        Estatus = estatus;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
