package com.karla.control_venta.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karla.control_venta.Administrador;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Distribuidor;

import java.util.ArrayList;

public class Adaptador_Distribuidor_Totales extends RecyclerView.Adapter<Adaptador_Distribuidor_Totales.ViewHolder> {
    public static ArrayList<Distribuidor> distribuidores;
    private Context mContext;
    private View view;

    public Adaptador_Distribuidor_Totales(ArrayList<Distribuidor> distribuidor, Context mcontext, View mview) {
        distribuidores= distribuidor;
        mContext = mcontext;
        view = mview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_rreporte_distribuidores_totales, viewGroup, false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        view= viewHolder.itemView;
        final Distribuidor distribuidor= distribuidores.get(i);
        viewHolder.txtID.setText(distribuidor.getID());
        viewHolder.txtNombre.setText(distribuidor.getNombre());
        viewHolder.txtClientes.setText(distribuidor.getClientes() + "");
    }

    @Override
    public int getItemCount() {
        return distribuidores.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtID, txtNombre, txtClientes;

        public ViewHolder(View itemView){
            super(itemView);
            txtNombre= itemView.findViewById(R.id.rv_Nombre);
            txtID= itemView.findViewById(R.id.rv_ID);
            txtClientes= itemView.findViewById(R.id.rv_Clientes);
        }
    }
}
