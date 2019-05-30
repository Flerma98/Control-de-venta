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

import com.karla.control_venta.Adaptadores.Adaptador_Reporte_Cartera;
import com.karla.control_venta.Adaptadores.Adaptador_Reporte_Cobranza;
import com.karla.control_venta.Administrador;
import com.karla.control_venta.R;

public class Reporte_Cobranza extends BottomSheetDialogFragment {

    RecyclerView rv;
    Adaptador_Reporte_Cobranza adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reporte_cobranza, container, false);
        rv= v.findViewById(R.id.rv_reporte_cobranza);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new Adaptador_Reporte_Cobranza(Administrador.lista_Ventas, getContext(), rv);
        rv.setAdapter(adapter);
        return v;
    }
}
