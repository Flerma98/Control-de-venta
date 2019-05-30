package com.karla.control_venta.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Abono;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Venta;

import java.util.ArrayList;

public class Adaptador_Cliente_Abonos extends RecyclerView.Adapter<Adaptador_Cliente_Abonos.ViewHolder> {
    public static ArrayList<Abono> abonos;
    public static Cliente cliente;
    private Context mContext;
    private View view;

    public Adaptador_Cliente_Abonos(ArrayList<Abono> abono, Context mcontext, View mview) {
        abonos= abono;
        mContext = mcontext;
        view = mview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_cliente_abonos, viewGroup, false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        view= viewHolder.itemView;
        final Abono abono= abonos.get(i);
            viewHolder.txtFecha.setText(abono.getFecha());
            viewHolder.txtCantidad.setText("$" + abono.getCantidad());
    }

    @Override
    public int getItemCount() {
        return abonos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtFecha, txtCantidad;

        public ViewHolder(View itemView){
            super(itemView);
            txtFecha= itemView.findViewById(R.id.rv_Fecha);
            txtCantidad= itemView.findViewById(R.id.rv_Cantidad);
        }
    }
}
