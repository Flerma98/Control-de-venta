package com.karla.control_venta;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.karla.control_venta.Adaptadores.Adaptador_Admin_Clientes;
import com.karla.control_venta.Tablas.Cliente;

public class Admin_Cliente extends Fragment {

    RecyclerView rv_Clientes;
    TextView nohay;
    Adaptador_Admin_Clientes adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin__cliente, container, false);

        setHasOptionsMenu(true);

        rv_Clientes= view.findViewById(R.id.rv_admin_clientes);
        nohay= view.findViewById(R.id.txtnohay);

        rv_Clientes.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new Adaptador_Admin_Clientes(Administrador.lista_Clientes, getContext(), rv_Clientes, getFragmentManager());
        rv_Clientes.setAdapter(adapter);
        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_rv_anim);
        rv_Clientes.setLayoutAnimation(layoutAnimationController);
        try{
            Administrador.ref_Usuarios.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    nohay.setText("Cargando Datos");
                    Administrador.lista_Clientes.clear();
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        Cliente cliente = objSnaptshot.getValue(Cliente.class);
                        Administrador.lista_Clientes.add(cliente);
                    }
                    adapter.notifyDataSetChanged();
                    rv_Clientes.scheduleLayoutAnimation();
                    if(Administrador.lista_Clientes.isEmpty()){ nohay.setVisibility(View.VISIBLE); nohay.setText("No hay Clientes"); }else{ nohay.setVisibility(View.GONE); }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }

            });
        }catch (Exception e){ Toast.makeText(getContext(), "Ocurrió un error obteniendo los datos", Toast.LENGTH_SHORT).show();}

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu_busqueda_salir, menu);

        MenuItem ayuda= menu.findItem(R.id.salir);
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

        MenuItem searchItem= menu.findItem(R.id.busqueda);
        SearchView searchView= (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
