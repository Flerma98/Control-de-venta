package com.karla.control_venta.BottomSheets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karla.control_venta.Administrador;
import com.karla.control_venta.Invitado;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Distribuidor;
import com.karla.control_venta.Tablas.Venta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class RealizarVenta extends BottomSheetDialogFragment {

    public static Cliente cliente;
        Distribuidor distribuidor;

    TextView txtCliente;
    EditText txtCantidad, txtDistribuidor;
    Spinner spnSemanas;
    Button btnVender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.realizar_venta, container, false);

        txtCliente= v.findViewById(R.id.txt_venta_nombrecliente);
        txtDistribuidor= v.findViewById(R.id.txt_venta_Distribuidor);
        txtCantidad= v.findViewById(R.id.txt_venta_Cantidad);
        spnSemanas= v.findViewById(R.id.spn_venta_semanas);
        btnVender= v.findViewById(R.id.btn_abono_Abonar);

        ArrayList<String> quincenas= new ArrayList<>();
        quincenas.add("-Seleccionar Quincenas-");
        quincenas.add("4 Quincenas");
        quincenas.add("6 Quincenas");
        quincenas.add("8 Quincenas");
        quincenas.add("10 Quincenas");
        quincenas.add("12 Quincenas");

        spnSemanas.setAdapter(new ArrayAdapter<>(getContext(), R.layout.spinner_item, quincenas));

        txtCliente.setText("Cliente: " + cliente.getNombre() + " " + cliente.getApellido_Paterno() + " " + cliente.getApellido_Materno());

        btnVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtDistribuidor.getText().toString().isEmpty() && !txtCantidad.getText().toString().isEmpty() && !spnSemanas.getSelectedItem().toString().equals("-Seleccionar Quincenas-")){
                    for(int t=0; t<Invitado.lista_Distribuidores.size(); t++){
                        if(Invitado.lista_Distribuidores.get(t).getID().equals(txtDistribuidor.getText().toString())){
                            distribuidor= Invitado.lista_Distribuidores.get(t);
                        }
                    }
                    if(distribuidor!=null) {
                        Venta venta = new Venta();
                        int ID = 1;
                        try {
                            ArrayList<Venta> ventas = Invitado.lista_Ventas;
                            Collections.sort(ventas, new Comparator<Venta>() {
                                @Override
                                public int compare(Venta p1, Venta p2) {
                                    return new Integer(p1.getIDVenta()).compareTo(new Integer(p2.getIDVenta()));
                                }
                            });
                            ID = Integer.parseInt(ventas.get(ventas.size() - 1).getIDVenta()) + 1;
                        } catch (Exception e) {
                        }
                        venta.setIDVenta(String.valueOf(ID));
                        venta.setIDCliente(cliente.getID());
                        venta.setIDDistribuidor(txtDistribuidor.getText().toString());
                        int quincenas = 0;
                        double total= Double.parseDouble(txtCantidad.getText().toString());
                        switch (spnSemanas.getSelectedItem().toString()) {
                            case "4 Quincenas":
                                quincenas = 4;
                                total= total + (total*0.10);
                                break;
                            case "6 Quincenas":
                                quincenas = 6;
                                total= total + (total*0.20);
                                break;
                            case "8 Quincenas":
                                quincenas = 8;
                                total= total + (total*0.30);
                                break;
                            case "10 Quincenas":
                                quincenas = 10;
                                total= total + (total*0.40);
                                break;
                            case "12 Quincenas":
                                quincenas = 12;
                                total= total + (total*0.50);
                                break;
                        }
                        distribuidor.setSaldo(distribuidor.getSaldo() + total);
                        distribuidor.setLimite_Credito(distribuidor.getLimite_Credito() + total);
                        distribuidor.setClientes(distribuidor.getClientes() + 1);
                        venta.setNumero_Quincenas(quincenas);
                        venta.setFecha(getFechaHora());
                        venta.setNumero_Vale(String.valueOf(ID));
                        venta.setValor_Vale(total);
                        venta.setUID(String.valueOf(ID));
                        Invitado.ref_Ventas.child(String.valueOf(ID)).setValue(venta);
                        cliente.setEstatus("Compra");
                        cliente.setDinero(total);
                        Invitado.ref_Usuarios.child(cliente.getUID()).setValue(cliente);
                        distribuidor.setEstatus("Tiene Adeudo");
                        Invitado.ref_Distribuidor.child(distribuidor.getUID()).setValue(distribuidor);
                        dismiss();
                    }else{
                        Toast.makeText(getContext(), "El distribuidor no existe", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public static String getHora() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        return formattedDate;
    }

    public static String getFecha(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public static String getFechaHora(){
        return getHora() + " - " + getFecha();
    }
}
