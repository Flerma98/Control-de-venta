package com.karla.control_venta.BottomSheets;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karla.control_venta.Administrador;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Distribuidor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class Agregar_Distribuidor extends BottomSheetDialogFragment {

    EditText txtDistribuidorNombres, txtDistribuidorApellido_Paterno, txtDistribuidorApellido_Materno, txtDistribuidorTelefono, txtDistribuidorCorreo, txtDistribuidorDireccion, txtDistribuidorCiudad, txtDistribuidorEstado;
    Spinner spnDistribuidorSexo;
    Button btnDistribuidorRegistrar, btnDistribuidorCancelar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.registrar_distribuidor, container, false);

        txtDistribuidorNombres= v.findViewById(R.id.txt_registro_distribuidor_nombres);
        txtDistribuidorApellido_Paterno= v.findViewById(R.id.txt_registro_distribuidor_apellido_paterno);
        txtDistribuidorApellido_Materno= v.findViewById(R.id.txt_registro_distribuidor_apellido_materno);
        txtDistribuidorTelefono= v.findViewById(R.id.txt_registro_distribuidor_telefono);
        txtDistribuidorCorreo= v.findViewById(R.id.txt_registro_distribuidor_correo);
        txtDistribuidorDireccion= v.findViewById(R.id.txt_registro_distribuidor_direccion);
        txtDistribuidorCiudad= v.findViewById(R.id.txt_registro_distribuidor_ciudad);
        txtDistribuidorEstado= v.findViewById(R.id.txt_registro_distribuidor_estado);
        spnDistribuidorSexo= v.findViewById(R.id.spn_registrar_distribuidor_sexo);
        btnDistribuidorRegistrar= v.findViewById(R.id.btn_registrar_distribuidor);
        btnDistribuidorCancelar= v.findViewById(R.id.btn_cancelar_distribuido);

        ArrayList<String> sexo= new ArrayList<>();
        sexo.add("-Seleccionar Sexo-");
        sexo.add("Masculino");
        sexo.add("Femenino");
        sexo.add("Sin Especificar");

        spnDistribuidorSexo.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.spinner_item, sexo));

        btnDistribuidorCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnDistribuidorRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtDistribuidorNombres.getText().toString().isEmpty() && !txtDistribuidorApellido_Paterno.getText().toString().isEmpty() && !txtDistribuidorApellido_Materno.getText().toString().isEmpty() && !txtDistribuidorTelefono.getText().toString().isEmpty() && !txtDistribuidorCorreo.getText().toString().isEmpty() && !txtDistribuidorDireccion.getText().toString().isEmpty() && !txtDistribuidorCiudad.getText().toString().isEmpty() && !txtDistribuidorEstado.getText().toString().isEmpty() && spnDistribuidorSexo.getSelectedItemPosition()!=0){
                    Distribuidor distribuidor = new Distribuidor();
                    int ID= 1;
                    try{
                        ID= Integer.parseInt(Administrador.lista_Distribuidores.get(Administrador.lista_Distribuidores.size()-1).getID()) + 1;
                    }catch (Exception e){}
                    distribuidor.setID(String.valueOf(ID));
                    distribuidor.setApellido_Paterno(txtDistribuidorApellido_Paterno.getText().toString().trim());
                    distribuidor.setApellido_Materno(txtDistribuidorApellido_Materno.getText().toString().trim());
                    distribuidor.setNombre(txtDistribuidorNombres.getText().toString().trim());
                    distribuidor.setTelefono(txtDistribuidorTelefono.getText().toString().trim());
                    distribuidor.setCorreo(txtDistribuidorCorreo.getText().toString().trim());
                    distribuidor.setDireccion(txtDistribuidorDireccion.getText().toString().trim());
                    distribuidor.setCiudad(txtDistribuidorCiudad.getText().toString().trim());
                    distribuidor.setEstado(txtDistribuidorEstado.getText().toString().trim());
                    distribuidor.setEstatus("Por Definir");
                    distribuidor.setSexo(spnDistribuidorSexo.getSelectedItem().toString());
                    distribuidor.setUID(UUID.randomUUID().toString());
                    Administrador.ref_Distribuidor.child(distribuidor.getUID()).setValue(distribuidor);
                    dismiss();
                }else{
                    Toast.makeText(getContext(), "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
