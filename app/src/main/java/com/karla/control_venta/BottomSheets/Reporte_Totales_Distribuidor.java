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
import com.karla.control_venta.Adaptadores.Adaptador_Distribuidor_Totales;
import com.karla.control_venta.Administrador;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Distribuidor;

import java.util.ArrayList;

public class Reporte_Totales_Distribuidor extends BottomSheetDialogFragment {

    RecyclerView rv;
    Adaptador_Distribuidor_Totales adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reporte_totales_distribuidor, container, false);
        rv= v.findViewById(R.id.rv_reporte_totales_distribuidor);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new Adaptador_Distribuidor_Totales(Administrador.lista_Distribuidores, getContext(), rv);
        rv.setAdapter(adapter);
        return v;
    }
}
