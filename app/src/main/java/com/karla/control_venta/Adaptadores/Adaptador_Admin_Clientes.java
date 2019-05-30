package com.karla.control_venta.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
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
import android.widget.Toast;

import com.karla.control_venta.Administrador;
import com.karla.control_venta.BottomSheets.Cliente_Abonos;
import com.karla.control_venta.BottomSheets.Editar_Cliente;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Cliente;

import java.util.ArrayList;

public class Adaptador_Admin_Clientes extends RecyclerView.Adapter<Adaptador_Admin_Clientes.ClientesViewHolder> implements View.OnClickListener, Filterable {
    public static ArrayList<Cliente> clientes_source;
    public static ArrayList<Cliente> clientes_filtrados;
    private View.OnClickListener listener;
    private Context mContext;
    private View view;
    private FragmentManager fragmentManager;

    public Adaptador_Admin_Clientes(ArrayList<Cliente> clientes, Context mcontext, View mview, FragmentManager fragmentManager) {
        clientes_source = clientes;
        clientes_filtrados = clientes;
        mContext = mcontext;
        view = mview;
        this.fragmentManager= fragmentManager;
    }

    @NonNull
    @Override
    public ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_admin_clientes, viewGroup, false);
        ClientesViewHolder holder= new ClientesViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ClientesViewHolder clientesViewHolder, int i) {
        view= clientesViewHolder.itemView;
        final Cliente cliente= clientes_filtrados.get(i);
        clientesViewHolder.txtNombre.setText(cliente.getNombre() + " " + cliente.getApellido_Paterno() + " " + cliente.getApellido_Materno());
        clientesViewHolder.txtCorreo.setText(cliente.getCorreo());
        clientesViewHolder.txtTelefono.setText(cliente.getTelefono());
        clientesViewHolder.txtID.setText(cliente.getID());
        clientesViewHolder.txtEstatus.setText(cliente.getEstatus());
        if(cliente.getEstatus().equals("Debe")){
            clientesViewHolder.txtEstatus.setTextColor(Color.RED);
        }
        if(cliente.getEstatus().equals("No Debe")){
            clientesViewHolder.txtEstatus.setTextColor(Color.parseColor("#3A7C19"));
        }
        clientesViewHolder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, clientesViewHolder.ivMenu);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_rv_clientes);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.editar:
                                Editar_Cliente.cliente= cliente;
                                Editar_Cliente bottomSheet_Editar_Cliente = new Editar_Cliente();
                                bottomSheet_Editar_Cliente.show(fragmentManager, "Editar_Cliente");
                                return true;
                            case R.id.eliminar:

                                Administrador.ref_Usuarios.child(cliente.getUID()).removeValue();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });

        clientesViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Administrador.lista_Abono!=null && !Administrador.lista_Abono.isEmpty()) {
                    Cliente_Abonos.cliente = cliente;
                    Cliente_Abonos bottomSheet = new Cliente_Abonos();
                    bottomSheet.show(fragmentManager, "Cliente_Abonos");
                }else{
                    Toast.makeText(mContext, "No hay abonos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientes_filtrados.size();
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
                    clientes_filtrados= clientes_source;
                }else{
                    ArrayList<Cliente> resultList= new ArrayList<>();
                    for(Cliente item: clientes_source){
                        if(item.getNombre().toLowerCase().contains(searchString.toLowerCase())){
                            resultList.add(item);
                        }
                    }
                    clientes_filtrados= resultList;
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values= clientes_filtrados;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clientes_filtrados= (ArrayList<Cliente>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ClientesViewHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtCorreo, txtTelefono, txtID, txtEstatus;
        ImageView ivMenu;
        CardView cardView;

        public ClientesViewHolder(View itemView){
            super(itemView);
            cardView= itemView.findViewById(R.id.rv_cardview);
            txtNombre= itemView.findViewById(R.id.rv_Nombre);
            txtCorreo= itemView.findViewById(R.id.rv_Correo);
            txtTelefono= itemView.findViewById(R.id.rv_Telefono);
            txtID= itemView.findViewById(R.id.rv_ID);
            txtEstatus= itemView.findViewById(R.id.rv_Estatus);
            ivMenu= itemView.findViewById(R.id.rv_Opciones);
        }
    }
}
