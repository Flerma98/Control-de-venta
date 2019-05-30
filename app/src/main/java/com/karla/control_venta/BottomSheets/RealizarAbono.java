package com.karla.control_venta.BottomSheets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karla.control_venta.Administrador;
import com.karla.control_venta.Invitado;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Abono;
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
import java.util.StringTokenizer;

public class RealizarAbono extends BottomSheetDialogFragment {

    public static Cliente cliente;
    public static Venta venta;
    public static Distribuidor distribuidor;

    TextView txtCliente, txtEstatus, txtAdeudo;
    EditText txtAbono;
    Button btnAbonar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.realizar_abono, container, false);
        txtCliente= v.findViewById(R.id.txt_abono_nombrecliente);
        txtEstatus= v.findViewById(R.id.txt_abono_estatus);
        txtAdeudo= v.findViewById(R.id.txt_abono_adeudo);
        txtAbono= v.findViewById(R.id.txt_abono_Cantidad);
        btnAbonar= v.findViewById(R.id.btn_abono_Abonar);

        txtCliente.setText("Cliente: " + cliente.getNombre() + " " + cliente.getApellido_Paterno() + " " + cliente.getApellido_Materno());
        txtEstatus.setText("Estatus: " + cliente.getEstatus());
        txtAdeudo.setText("Adeudo de: $ " + cliente.getDinero());

        btnAbonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtAbono.getText().toString().isEmpty()){
                    if(Double.parseDouble(txtAbono.getText().toString())<=cliente.getDinero()) {
                        Abono abono = new Abono();
                        int ID = 1;
                        try {
                            ArrayList<Abono> abonos = Invitado.lista_Abono;
                            Collections.sort(abonos, new Comparator<Abono>() {
                                @Override
                                public int compare(Abono p1, Abono p2) {
                                    return new Integer(p1.getID_Pago()).compareTo(new Integer(p2.getID_Pago()));
                                }
                            });
                            ID = Integer.parseInt(abonos.get(abonos.size() - 1).getID_Pago()) + 1;
                        } catch (Exception e) {
                        }
                        abono.setID_Pago(String.valueOf(ID));
                        abono.setID_Cliente(cliente.getID());
                        abono.setID_Distribuidor(venta.getIDDistribuidor());
                        abono.setCantidad(Double.parseDouble(txtAbono.getText().toString()));
                        abono.setFecha(getFechaHora());
                        abono.setUID(String.valueOf(ID));

                        distribuidor.setSaldo(distribuidor.getSaldo() - Double.parseDouble(txtAbono.getText().toString()));

                        Invitado.ref_Abonos.child(String.valueOf(ID)).setValue(abono);
                        double resto = cliente.getDinero() - Double.parseDouble(txtAbono.getText().toString());
                        if(resto==0){
                            distribuidor.setClientes(distribuidor.getClientes()-1);
                            cliente.setEstatus("No Debe");
                        }else{
                            cliente.setEstatus("Abonado");
                        }
                        cliente.setDinero(resto);
                        Invitado.ref_Usuarios.child(cliente.getUID()).setValue(cliente);
                        Invitado.ref_Distribuidor.child(distribuidor.getUID()).setValue(distribuidor);
                        dismiss();
                    }else{
                        Toast.makeText(getContext(), "Introdusca cantidad menor o igual a la de abono", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Introdusca cantidad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
