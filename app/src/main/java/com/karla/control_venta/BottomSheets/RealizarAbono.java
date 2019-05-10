package com.karla.control_venta.BottomSheets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Cliente;

public class RealizarAbono extends BottomSheetDialogFragment {

    public static Cliente cliente;

    TextView txtCliente, txtEstatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.realizar_abono, container, false);
        txtCliente= v.findViewById(R.id.txt_abono_nombrecliente);
        txtEstatus= v.findViewById(R.id.txt_abono_estatus);

        txtCliente.setText("Cliente: " + cliente.getNombre() + " " + cliente.getApellido_Paterno() + " " + cliente.getApellido_Materno());
        txtEstatus.setText("Estatus: " + cliente.getEstatus());
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
