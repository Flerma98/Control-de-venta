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
import com.karla.control_venta.Tablas.Distribuidor;
import com.karla.control_venta.Tablas.Venta;

import java.util.ArrayList;

public class Adaptador_Reporte_Cobranza extends RecyclerView.Adapter<Adaptador_Reporte_Cobranza.ViewHolder> {
    public static ArrayList<Venta> ventas;
    private Context mContext;
    private View view;

    public Adaptador_Reporte_Cobranza(ArrayList<Venta> ventas, Context mcontext, View mview) {
        this.ventas= ventas;
        mContext = mcontext;
        view = mview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_reporte_cobranza, viewGroup, false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        view= viewHolder.itemView;
        final Venta venta= ventas.get(i);
        viewHolder.txtID_Distribuidor.setText(venta.getIDDistribuidor());
        viewHolder.txtID_Cliente.setText(venta.getIDCliente());
        viewHolder.txtNombre.setText(Administrador.lista_Clientes.get(Integer.parseInt(venta.getIDCliente())-1).getNombre());
        viewHolder.txtQuincenas.setText(venta.getNumero_Quincenas() + "");
        viewHolder.txtTotal.setText("$" + venta.getValor_Vale());
    }

    @Override
    public int getItemCount() {
        return ventas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtID_Distribuidor, txtID_Cliente, txtNombre, txtQuincenas, txtTotal;

        public ViewHolder(View itemView){
            super(itemView);
            txtID_Distribuidor= itemView.findViewById(R.id.rv_ID_Distribuidor);
            txtID_Cliente= itemView.findViewById(R.id.rv_ID_Cliente);
            txtNombre= itemView.findViewById(R.id.rv_Nombre);
            txtQuincenas= itemView.findViewById(R.id.rv_Quincenas);
            txtTotal= itemView.findViewById(R.id.rv_Total);
        }
    }
}
