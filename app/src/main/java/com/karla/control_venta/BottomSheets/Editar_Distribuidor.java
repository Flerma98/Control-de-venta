package com.karla.control_venta.BottomSheets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.karla.control_venta.Administrador;
import com.karla.control_venta.R;
import com.karla.control_venta.Tablas.Distribuidor;

import java.util.ArrayList;
import java.util.UUID;

public class Editar_Distribuidor extends BottomSheetDialogFragment {

    EditText txtDistribuidorNombres, txtDistribuidorApellido_Paterno, txtDistribuidorApellido_Materno, txtDistribuidorTelefono, txtDistribuidorCorreo, txtDistribuidorDireccion, txtDistribuidorCiudad, txtDistribuidorEstado;
    Spinner spnDistribuidorSexo;
    Button btnDistribuidorRegistrar, btnDistribuidorCancelar;

    public static Distribuidor distribuidor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.editar_distribuidor, container, false);

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

        txtDistribuidorNombres.setText(distribuidor.getNombre());
        txtDistribuidorApellido_Paterno.setText(distribuidor.getApellido_Paterno());
        txtDistribuidorApellido_Materno.setText(distribuidor.getApellido_Materno());
        txtDistribuidorTelefono.setText(distribuidor.getTelefono());
        txtDistribuidorCorreo.setText(distribuidor.getCorreo());
        txtDistribuidorDireccion.setText(distribuidor.getDireccion());
        txtDistribuidorCiudad.setText(distribuidor.getCiudad());
        txtDistribuidorEstado.setText(distribuidor.getEstado());
        spnDistribuidorSexo.setSelection(sexo.indexOf(distribuidor.getSexo()));


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
                    Distribuidor distribuidor_nuevo = new Distribuidor();
                    distribuidor_nuevo.setID(distribuidor.getID());
                    distribuidor_nuevo.setNombre(txtDistribuidorNombres.getText().toString().trim());
                    distribuidor_nuevo.setApellido_Paterno(txtDistribuidorApellido_Paterno.getText().toString().trim());
                    distribuidor_nuevo.setApellido_Materno(txtDistribuidorApellido_Materno.getText().toString().trim());
                    distribuidor_nuevo.setTelefono(txtDistribuidorTelefono.getText().toString().trim());
                    distribuidor_nuevo.setCorreo(txtDistribuidorCorreo.getText().toString().trim());
                    distribuidor_nuevo.setDireccion(txtDistribuidorDireccion.getText().toString().trim());
                    distribuidor_nuevo.setCiudad(txtDistribuidorCiudad.getText().toString().trim());
                    distribuidor_nuevo.setEstado(txtDistribuidorEstado.getText().toString().trim());
                    distribuidor_nuevo.setEstatus(distribuidor.getEstatus());
                    distribuidor_nuevo.setSexo(spnDistribuidorSexo.getSelectedItem().toString());
                    distribuidor_nuevo.setUID(distribuidor.getUID());
                    Administrador.ref_Distribuidor.child(distribuidor.getUID()).setValue(distribuidor_nuevo);
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
