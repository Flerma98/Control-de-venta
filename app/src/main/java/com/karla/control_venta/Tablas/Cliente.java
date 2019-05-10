package com.karla.control_venta.Tablas;

public class Cliente {
    private String ID;
    private String Apellido_Paterno;
    private String Apellido_Materno;
    private String Nombre;
    private String Telefono;
    private String Correo;
    private String Fecha_Nacimiento;
    private String Ciudad;
    private String Estado;
    private String Estatus;
    private String Sexo;
    private String Referencia;
    private String Direccion_Referencia;
    private String Fecha_Nacimiento_Referencia;
    private String UID;

    public Cliente() {
    }

    public Cliente(String ID, String apellido_Paterno, String apellido_Materno, String nombre, String telefono, String correo, String fecha_Nacimiento, String ciudad, String estado, String estatus, String sexo, String referencia, String direccion_Referencia, String fecha_Nacimiento_Referencia, String UID) {
        this.ID = ID;
        Apellido_Paterno = apellido_Paterno;
        Apellido_Materno = apellido_Materno;
        Nombre = nombre;
        Telefono = telefono;
        Correo = correo;
        Fecha_Nacimiento = fecha_Nacimiento;
        Ciudad = ciudad;
        Estado = estado;
        Estatus = estatus;
        Sexo = sexo;
        Referencia = referencia;
        Direccion_Referencia = direccion_Referencia;
        Fecha_Nacimiento_Referencia = fecha_Nacimiento_Referencia;
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

    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(String fecha_Nacimiento) {
        Fecha_Nacimiento = fecha_Nacimiento;
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

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public String getDireccion_Referencia() {
        return Direccion_Referencia;
    }

    public void setDireccion_Referencia(String direccion_Referencia) {
        Direccion_Referencia = direccion_Referencia;
    }

    public String getFecha_Nacimiento_Referencia() {
        return Fecha_Nacimiento_Referencia;
    }

    public void setFecha_Nacimiento_Referencia(String fecha_Nacimiento_Referencia) {
        Fecha_Nacimiento_Referencia = fecha_Nacimiento_Referencia;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
