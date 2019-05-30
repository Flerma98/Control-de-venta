package com.karla.control_venta.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.karla.control_venta.Administrador;
import com.karla.control_venta.BottomSheets.Editar_Distribuidor;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Distribuidor;

import java.util.ArrayList;

public class Adaptador_Clientes_Morosos extends RecyclerView.Adapter<Adaptador_Clientes_Morosos.ClientesMorososViewHolder> {
    public static ArrayList<Cliente> clientes;
    private Context mContext;
    private View view;

    public Adaptador_Clientes_Morosos(ArrayList<Cliente> cliente, Context mcontext, View mview) {
        clientes= cliente;
        mContext = mcontext;
        view = mview;
    }

    @NonNull
    @Override
    public ClientesMorososViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_reporte_morosos, viewGroup, false);
        ClientesMorososViewHolder holder= new ClientesMorososViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ClientesMorososViewHolder clientesMorososViewHolder, int i) {
        view= clientesMorososViewHolder.itemView;
        final Cliente distribuidor= clientes.get(i);
        clientesMorososViewHolder.txtID.setText(distribuidor.getID());
        clientesMorososViewHolder.txtNombre.setText(distribuidor.getNombre());
        clientesMorososViewHolder.txtEstatus.setText(distribuidor.getEstatus());
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }


    public static class ClientesMorososViewHolder extends RecyclerView.ViewHolder{

        TextView txtID, txtNombre, txtEstatus;

        public ClientesMorososViewHolder(View itemView){
            super(itemView);
            txtNombre= itemView.findViewById(R.id.rv_Nombre);
            txtID= itemView.findViewById(R.id.rv_ID);
            txtEstatus= itemView.findViewById(R.id.rv_Estatus);
        }
    }
}
