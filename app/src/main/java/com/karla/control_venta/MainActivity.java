package com.karla.control_venta;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    Button btnSesion;
    EditText txtUsuario,txtPass;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSesion=findViewById(R.id.btn_login_Iniciar_Sesion);
        txtUsuario=findViewById(R.id.txt_login_Usuario);
        txtPass=findViewById(R.id.txt_login_Contraseña);

        Auth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    try {
                        if(user.getUid().equals("wWTEDQwSwzUhBOwmmTwwkymsyZs1")){
                            Intent intent = new Intent(MainActivity.this, Administrador.class);
                            startActivity(intent);
                            finish();
                            return;
                        }else {
                            Intent intent = new Intent(MainActivity.this, Invitado.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Ocurrió un error intentelo de nuevo", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };

        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnectedWifi(getApplicationContext()) || isConnectedMobile(getApplicationContext())) {
                    dialog = ProgressDialog.show(MainActivity.this, "Inicaindo Sesión",
                            "Iniciando con su cuenta de Usuario....", true, false);
                    if (txtUsuario.getText().toString().trim().isEmpty() || txtPass.getText().toString().trim().isEmpty()) {
                        try {
                            dialog.dismiss();
                        }catch (Exception e){}
                    } else {
                        String mail = txtUsuario.getText().toString().trim();
                        String pass = txtPass.getText().toString().trim();
                        try {
                            Auth.signInWithEmailAndPassword(mail + "@ereignis.com", pass + "@ereignis.com").addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        try { dialog.dismiss(); }catch (Exception e){}
                                    } else {
                                        Toast.makeText(MainActivity.this, "No existe una cuenta con esos datos", Toast.LENGTH_SHORT).show();
                                        try { dialog.dismiss(); }catch (Exception e){}
                                    }
                                }
                            });
                        } catch (Exception e) { Toast.makeText(MainActivity.this, "Ocurrió un error intentelo de nuevo", Toast.LENGTH_SHORT).show(); }
                    }
                }else{
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                    dialogo1.setTitle("No hay Conexión");
                    dialogo1.setMessage("Conectece a internet porfavor");
                    dialogo1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            dialogo1.dismiss();
                        }
                    });
                    dialogo1.show();
                }
            }
        });
    }

    public void RegistrarUsuarioFirebase(String Correo, String Contraseña){
        Auth.createUserWithEmailAndPassword(Correo, Contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                try {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "Usuario ya en uso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){}
            }
        });
    }

    public static boolean isConnectedWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isConnectedMobile(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Auth.addAuthStateListener(firebaseAuthListener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Auth.removeAuthStateListener(firebaseAuthListener);
    }
}
