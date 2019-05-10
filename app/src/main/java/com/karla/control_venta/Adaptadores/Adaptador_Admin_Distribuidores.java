package com.karla.control_venta.Adaptadores;

import android.content.Context;
import android.graphics.Color;
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

public class Adaptador_Admin_Distribuidores extends RecyclerView.Adapter<Adaptador_Admin_Distribuidores.DistribuidoresViewHolder> implements View.OnClickListener, Filterable {
    public static ArrayList<Distribuidor> distribuidores_source;
    public static ArrayList<Distribuidor> distribuidores_filtrados;
    private View.OnClickListener listener;
    private Context mContext;
    private View view;
    private FragmentManager fragmentManager;

    public Adaptador_Admin_Distribuidores(ArrayList<Distribuidor> distribuidor, Context mcontext, View mview, FragmentManager fragmentManager) {
        distribuidores_source = distribuidor;
        distribuidores_filtrados = distribuidor;
        mContext = mcontext;
        view = mview;
        this.fragmentManager= fragmentManager;
    }

    @NonNull
    @Override
    public DistribuidoresViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_admin_distribuidor, viewGroup, false);
        DistribuidoresViewHolder holder= new DistribuidoresViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DistribuidoresViewHolder distribuidoresViewHolder, int i) {
        view= distribuidoresViewHolder.itemView;
        final Distribuidor distribuidor= distribuidores_filtrados.get(i);
        distribuidoresViewHolder.txtNombre.setText(distribuidor.getNombre() + " " + distribuidor.getApellido_Paterno() + " " + distribuidor.getApellido_Materno());
        distribuidoresViewHolder.txtCorreo.setText(distribuidor.getCorreo());
        distribuidoresViewHolder.txtTelefono.setText(distribuidor.getTelefono());
        distribuidoresViewHolder.txtID.setText(distribuidor.getID());
        distribuidoresViewHolder.txtEstatus.setText(distribuidor.getEstatus());
        distribuidoresViewHolder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, distribuidoresViewHolder.ivMenu);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_rv_clientes);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.editar:
                                Editar_Distribuidor.distribuidor= distribuidor;
                                Editar_Distribuidor bottomSheet_Editar_Cliente = new Editar_Distribuidor();
                                bottomSheet_Editar_Cliente.show(fragmentManager, "Editar_Distribuidor");
                                return true;
                            case R.id.eliminar:
                                Administrador.ref_Distribuidor.child(distribuidor.getUID()).removeValue();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return distribuidores_filtrados.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchString = constraint.toString().trim();
                if(searchString.isEmpty()){
                    distribuidores_filtrados= distribuidores_source;
                }else{
                    ArrayList<Distribuidor> resultList= new ArrayList<>();
                    for(Distribuidor item: distribuidores_source){
                        if(item.getNombre().toLowerCase().contains(searchString.toLowerCase())){
                            resultList.add(item);
                        }
                    }
                    distribuidores_filtrados= resultList;
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values= distribuidores_filtrados;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                distribuidores_filtrados= (ArrayList<Distribuidor>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class DistribuidoresViewHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtCorreo, txtTelefono, txtID, txtEstatus;
        ImageView ivMenu;

        public DistribuidoresViewHolder(View itemView){
            super(itemView);
            txtNombre= itemView.findViewById(R.id.rv_Nombre);
            txtCorreo= itemView.findViewById(R.id.rv_Correo);
            txtTelefono= itemView.findViewById(R.id.rv_Telefono);
            txtID= itemView.findViewById(R.id.rv_ID);
            txtEstatus= itemView.findViewById(R.id.rv_Estatus);
            ivMenu= itemView.findViewById(R.id.rv_Opciones);
        }
    }
}
