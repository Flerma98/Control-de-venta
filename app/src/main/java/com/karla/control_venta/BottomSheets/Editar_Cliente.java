package com.karla.control_venta.BottomSheets;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class Editar_Cliente extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {

    public static Cliente cliente;
    EditText txtNombres, txtApellido_Paterno, txtApellido_Materno, txtTelefono, txtCorreo, txtCiudad, txtEstado, txtPersona_Referencia, txtDireccion_Referencia;
    TextView txtFecha_Nacimiento, txtFecha_Nacmimiento_Referencia;
    Spinner spnSexo;
    Button btnRegistrar, btnCancelar;
    boolean fecha;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.editar_cliente, container, false);
        txtNombres= v.findViewById(R.id.txt_registro_cliente_nombres);
        txtApellido_Materno= v.findViewById(R.id.txt_registro_cliente_apellido_materno);
        txtApellido_Paterno= v.findViewById(R.id.txt_registro_cliente_apellido_paterno);
        txtTelefono= v.findViewById(R.id.txt_registro_cliente_telefono);
        txtCorreo= v.findViewById(R.id.txt_registro_cliente_correo);
        txtFecha_Nacimiento= v.findViewById(R.id.txt_registrar_cliente_fecha);
        txtCiudad= v.findViewById(R.id.txt_registro_cliente_ciudad);
        txtEstado= v.findViewById(R.id.txt_registro_cliente_estado);
        spnSexo= v.findViewById(R.id.spn_registrar_cliente_sexo);
        txtPersona_Referencia= v.findViewById(R.id.txt_registro_cliente_referencia);
        txtDireccion_Referencia= v.findViewById(R.id.txt_registro_cliente_referencia_direccion);
        txtFecha_Nacmimiento_Referencia= v.findViewById(R.id.txt_registrar_cliente_referencia_fecha);
        btnRegistrar= v.findViewById(R.id.btn_registrar_cliente);
        btnCancelar= v.findViewById(R.id.btn_cancelar_cliente);

        ArrayList<String> sexo= new ArrayList<>();
        sexo.add("-Seleccionar Sexo-");
        sexo.add("Masculino");
        sexo.add("Femenino");
        sexo.add("Sin Especificar");

        spnSexo.setAdapter(new ArrayAdapter<>(getContext(), R.layout.spinner_item, sexo));

        txtNombres.setText(cliente.getNombre());
        txtApellido_Paterno.setText(cliente.getApellido_Paterno());
        txtApellido_Materno.setText(cliente.getApellido_Materno());
        txtTelefono.setText(cliente.getTelefono());
        txtCorreo.setText(cliente.getCorreo());
        txtFecha_Nacimiento.setText(cliente.getFecha_Nacimiento());
        txtCiudad.setText(cliente.getCiudad());
        txtEstado.setText(cliente.getEstado());
        spnSexo.setSelection(sexo.indexOf(cliente.getSexo()));
        txtPersona_Referencia.setText(cliente.getReferencia());
        txtDireccion_Referencia.setText(cliente.getDireccion_Referencia());
        txtFecha_Nacmimiento_Referencia.setText(cliente.getFecha_Nacimiento_Referencia());

        txtFecha_Nacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedirFecha();
                fecha= true;
            }
        });

        txtFecha_Nacmimiento_Referencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedirFecha();
                fecha= false;
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombres.getText().toString().isEmpty() && !txtApellido_Paterno.getText().toString().isEmpty() && !txtApellido_Materno.getText().toString().isEmpty() && !txtTelefono.getText().toString().isEmpty() && !txtCorreo.getText().toString().isEmpty() && !txtFecha_Nacimiento.getText().toString().isEmpty() && !txtCiudad.getText().toString().isEmpty() && !txtEstado.getText().toString().isEmpty() && spnSexo.getSelectedItemPosition()!=0 && !txtPersona_Referencia.getText().toString().isEmpty() && !txtDireccion_Referencia.getText().toString().isEmpty() && !txtFecha_Nacmimiento_Referencia.getText().toString().isEmpty()){
                    Cliente cliente_nuevo = new Cliente();
                    cliente_nuevo.setID(cliente.getID());
                    cliente_nuevo.setNombre(txtNombres.getText().toString().trim());
                    cliente_nuevo.setApellido_Paterno(txtApellido_Paterno.getText().toString().trim());
                    cliente_nuevo.setApellido_Materno(txtApellido_Materno.getText().toString().trim());
                    cliente_nuevo.setTelefono(txtTelefono.getText().toString());
                    cliente_nuevo.setCorreo(txtCorreo.getText().toString().trim());
                    cliente_nuevo.setFecha_Nacimiento(txtFecha_Nacimiento.getText().toString());
                    cliente_nuevo.setCiudad(txtCiudad.getText().toString().trim());
                    cliente_nuevo.setEstado(txtEstado.getText().toString().trim());
                    cliente_nuevo.setEstatus(cliente.getEstatus());
                    cliente_nuevo.setSexo(spnSexo.getSelectedItem().toString());
                    cliente_nuevo.setReferencia(txtPersona_Referencia.getText().toString().trim());
                    cliente_nuevo.setDireccion_Referencia(txtDireccion_Referencia.getText().toString());
                    cliente_nuevo.setFecha_Nacimiento_Referencia(txtFecha_Nacmimiento_Referencia.getText().toString());
                    cliente_nuevo.setUID(cliente.getUID());
                    Administrador.ref_Usuarios.child(cliente.getUID()).setValue(cliente_nuevo);
                    dismiss();
                }else{
                    Toast.makeText(getContext(), "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void pedirFecha(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month+=1;
        String date = dayOfMonth + "/" + month + "/" + year;
        if(fecha){
            txtFecha_Nacimiento.setText(date);
        }else{
            txtFecha_Nacmimiento_Referencia.setText(date);
        }
    }
}
