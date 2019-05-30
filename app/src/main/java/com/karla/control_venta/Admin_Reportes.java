package com.karla.control_venta;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.karla.control_venta.BottomSheets.Reporte_Cartera;
import com.karla.control_venta.BottomSheets.Reporte_Clientes_Morosos;
import com.karla.control_venta.BottomSheets.Reporte_Cobranza;
import com.karla.control_venta.BottomSheets.Reporte_Totales_Distribuidor;
import com.karla.control_venta.Tablas.Venta;

public class Admin_Reportes extends Fragment {

    CardView cvTotalesDis, cvCobranza ,cvCartera, cvClienteMoroso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin__reportes, container, false);

        cvTotalesDis= view.findViewById(R.id.cv_Reporte_Totales_Distribuidor);
        cvCobranza= view.findViewById(R.id.cv_Reporte_Cobranza);
        cvCartera= view.findViewById(R.id.cv_Reporte_Cartera);
        cvClienteMoroso= view.findViewById(R.id.cv_Reporte_Clientes_Morosos);

        cvTotalesDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Administrador.lista_Distribuidores!=null && !Administrador.lista_Distribuidores.isEmpty()) {
                    Reporte_Totales_Distribuidor bottomSheet = new Reporte_Totales_Distribuidor();
                    bottomSheet.show(getFragmentManager(), "Reporte_Totales_Distribuidores");
                }else{
                    Toast.makeText(getContext(), "No hay Distribuidores", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cvCobranza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Administrador.lista_Ventas!=null && !Administrador.lista_Ventas.isEmpty()) {
                    Reporte_Cobranza bottomSheet = new Reporte_Cobranza();
                    bottomSheet.show(getFragmentManager(), "Reporte_Cobranza");
                }else{
                    Toast.makeText(getContext(), "No hay Ventas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cvCartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Administrador.lista_Distribuidores!=null && !Administrador.lista_Distribuidores.isEmpty()) {
                    Reporte_Cartera bottomSheet = new Reporte_Cartera();
                    bottomSheet.show(getFragmentManager(), "Reporte_Cartera");
                }else{
                    Toast.makeText(getContext(), "No hay Distribuidores", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cvClienteMoroso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Administrador.lista_Clientes!=null && !Administrador.lista_Clientes.isEmpty() && Administrador.lista_Ventas!=null && !Administrador.lista_Ventas.isEmpty()) {
                    boolean encontrado = false;
                    for (int i = 0; i < Administrador.lista_Clientes.size(); i++) {
                        if(Administrador.lista_Clientes.get(i).getEstatus().equals("Compra")){
                            encontrado=true;
                        }
                    }
                    if(!encontrado){
                        Toast.makeText(getContext(), "No hay Clientes Morosos", Toast.LENGTH_SHORT).show();
                    }else{
                        Reporte_Clientes_Morosos bottomSheet = new Reporte_Clientes_Morosos();
                        bottomSheet.show(getFragmentManager(), "Reporte_Clientes_Morosos");
                    }
                }else{
                    Toast.makeText(getContext(), "No se puede desplegar el reporte", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu_salir, menu);

        MenuItem ayuda= menu.findItem(R.id.menu_item_salir);
        ayuda.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
                dialogo1.setTitle("Cerrar Sesión");
                dialogo1.setMessage("¿ Desea cerrar sesión ?");
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Administrador.Auth.signOut();
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        dialogo1.dismiss();
                    }
                });
                dialogo1.show();
                return false;
            }
        });
    }
}
