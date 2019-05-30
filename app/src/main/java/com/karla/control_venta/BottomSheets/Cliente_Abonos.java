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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.karla.control_venta.Adaptadores.Adaptador_Cliente_Abonos;
import com.karla.control_venta.Administrador;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Abono;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Venta;

import java.util.ArrayList;

public class Cliente_Abonos extends BottomSheetDialogFragment {

    RecyclerView rv;
    Adaptador_Cliente_Abonos adapter;
    public static Cliente cliente;
    Venta venta;
    ArrayList<Abono> abonos= new ArrayList<>();
    TextView nohay, txtcliente, txtquincenas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cliente_abonos, container, false);
        rv= v.findViewById(R.id.rv_cliente_abonos);
        nohay= v.findViewById(R.id.txtnohay);
        txtcliente= v.findViewById(R.id.txtCliente);
        txtquincenas= v.findViewById(R.id.txtQuincenas);
        for (int i = 0; i < Administrador.lista_Ventas.size(); i++) {
            if(Administrador.lista_Ventas.get(i).getIDCliente().equals(cliente.getID())){
                venta= Administrador.lista_Ventas.get(i);
            }
        }
        txtcliente.setText("Cliente: " + cliente.getNombre() + " " + cliente.getApellido_Paterno());
        txtquincenas.setText( "Quincenas: " + venta.getNumero_Quincenas());
        rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new Adaptador_Cliente_Abonos(abonos, getContext(), rv);
        rv.setAdapter(adapter);
        try{
            Administrador.ref_Abonos.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    abonos.clear();
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        Abono abono = objSnaptshot.getValue(Abono.class);
                        if(abono.getID_Cliente().equals(cliente.getID()))
                        abonos.add(abono);
                    }
                    adapter.notifyDataSetChanged();
                    if(abonos.isEmpty()){
                        nohay.setVisibility(View.VISIBLE);
                    }else{
                        nohay.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }

            });
        }catch (Exception e){ Toast.makeText(getContext(), "Ocurrió un error obteniendo los datos", Toast.LENGTH_SHORT).show();}

        return v;
    }
}
