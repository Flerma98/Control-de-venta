package com.karla.control_venta.BottomSheets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karla.control_venta.Adaptadores.Adaptador_Clientes_Morosos;
import com.karla.control_venta.Administrador;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Distribuidor;

import java.util.ArrayList;

public class Reporte_Clientes_Morosos extends BottomSheetDialogFragment {
    RecyclerView rv;
    Adaptador_Clientes_Morosos adapter;
    ArrayList<Cliente> clientes= new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reporte_clientes_morosos, container, false);
        rv= v.findViewById(R.id.rv_reporte_clientes_morosos);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        for (int i = 0; i < Administrador.lista_Clientes.size(); i++) {
            if (Administrador.lista_Clientes.get(i).getEstatus().equals("Compra")) {
                clientes.add(Administrador.lista_Clientes.get(i));
            }
        }
        adapter = new Adaptador_Clientes_Morosos(clientes, getContext(), rv);
        rv.setAdapter(adapter);
        return v;
    }
}
