package com.karla.control_venta;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.karla.control_venta.BottomSheets.RealizarAbono;
import com.karla.control_venta.BottomSheets.RealizarVenta;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Distribuidor;

public class Invitado_Venta extends Fragment {

    Button btnBuscar;
    EditText txtNombre, txtApellidoPa, txtApellidoMa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_invitado__venta, container, false);

        setHasOptionsMenu(true);

        btnBuscar= view.findViewById(R.id.btn_abono_BuscarCliente);
        txtNombre= view.findViewById(R.id.txt_abono_Nombre);
        txtApellidoPa= view.findViewById(R.id.txt_abono_ApellidoPaterno);
        txtApellidoMa= view.findViewById(R.id.txt_abono_ApellidoMaterno);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Invitado.lista_Clientes.isEmpty() && Invitado.lista_Distribuidores.isEmpty()){
                    Toast.makeText(getContext(), "No se puede realizar ventas", Toast.LENGTH_SHORT).show();
                }else {
                    if(!txtNombre.getText().toString().trim().isEmpty() && !txtApellidoPa.getText().toString().trim().isEmpty() && !txtApellidoMa.getText().toString().trim().isEmpty()) {
                        boolean encontrado = false;
                        for (int i = 0; i < Invitado.lista_Clientes.size(); i++) {
                            if (txtNombre.getText().toString().trim().equals(Invitado.lista_Clientes.get(i).getNombre()) && txtApellidoPa.getText().toString().trim().equals(Invitado.lista_Clientes.get(i).getApellido_Paterno()) && txtApellidoMa.getText().toString().trim().equals(Invitado.lista_Clientes.get(i).getApellido_Materno())) {
                                encontrado = true;
                                if(Invitado.lista_Clientes.get(i).getDinero()==0) {
                                    RealizarVenta.cliente = Invitado.lista_Clientes.get(i);
                                    RealizarVenta bottomSheet = new RealizarVenta();
                                    bottomSheet.show(getFragmentManager(), "Realizar_Venta");
                                }else{
                                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
                                    dialogo1.setTitle("Venta no permitida");
                                    dialogo1.setMessage("El Cliente tiene una compra pendiente");
                                    dialogo1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogo1, int id) {

                                        }
                                    });
                                    dialogo1.show();
                                }
                            }
                        }
                        if (!encontrado)
                            Toast.makeText(getContext(), "No se encontró un Cliente con esos datos", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Complete todos los datos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

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
                        Invitado.Auth.signOut();
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
