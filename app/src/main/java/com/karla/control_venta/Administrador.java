package com.karla.control_venta;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karla.control_venta.BottomSheets.Agregar_Cliente;
import com.karla.control_venta.BottomSheets.Agregar_Distribuidor;
import com.karla.control_venta.Tablas.Abono;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Distribuidor;
import com.karla.control_venta.Tablas.FireBaseReference;
import com.karla.control_venta.Tablas.Venta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class Administrador extends AppCompatActivity  {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    FloatingActionButton btnFlotante;

    static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final DatabaseReference ref_Usuarios = database.getReference(FireBaseReference.REFERENCIA_USUARIOS);
    public static final DatabaseReference ref_Distribuidor = database.getReference(FireBaseReference.REFERENCIA_DISTRIBUIDORES);
    public static final DatabaseReference ref_Ventas = database.getReference(FireBaseReference.REFERENCIA_VENTAS);
    public static final DatabaseReference ref_Abonos = database.getReference(FireBaseReference.REFERENCIA_ABONOS);

    public static ArrayList<Cliente> lista_Clientes= new ArrayList<>();
    public static ArrayList<Distribuidor> lista_Distribuidores= new ArrayList<>();
    public static ArrayList<Venta> lista_Ventas= new ArrayList<>();
    public static ArrayList<Abono> lista_Abono= new ArrayList<>();

    Dialog Dialogo_Agregar_Cliente, Dialogo_Agregar_Distribuidor;

    public static FirebaseAuth Auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager (viewPager);
        btnFlotante= findViewById(R.id.fab_admin);

        try{
            ref_Abonos.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    lista_Abono.clear();
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        Abono abono = objSnaptshot.getValue(Abono.class);
                        lista_Abono.add(abono);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }

            });
        }catch (Exception e){ Toast.makeText(Administrador.this, "Ocurrió un error obteniendo los datos", Toast.LENGTH_SHORT).show();}

        try{
            ref_Ventas.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    lista_Ventas.clear();
                    for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                        Venta venta = objSnaptshot.getValue(Venta.class);
                        lista_Ventas.add(venta);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }

            });
        }catch (Exception e){ Toast.makeText(Administrador.this, "Ocurrió un error obteniendo los datos", Toast.LENGTH_SHORT).show();}

        btnFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(viewPager.getCurrentItem()){
                    case 0:
                        Agregar_Cliente bottomSheet_AgregarCliente = new Agregar_Cliente();
                        bottomSheet_AgregarCliente.show(getSupportFragmentManager(), "Agregar_Cliente");
                        break;
                    case 1:
                        Agregar_Distribuidor bottomSheet_AgregarDistribuidor = new Agregar_Distribuidor();
                        bottomSheet_AgregarDistribuidor.show(getSupportFragmentManager(), "Agregar_Distribuidor");
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        btnFlotante.setVisibility(View.VISIBLE);
                        btnFlotante.setImageDrawable(getResources().getDrawable(R.drawable.ic_agregar_cliente));
                        break;
                    case 1:
                        btnFlotante.setVisibility(View.VISIBLE);
                        btnFlotante.setImageDrawable(getResources().getDrawable(R.drawable.ic_agregar_distribuidor));
                        break;
                    case 2:
                        btnFlotante.setVisibility(View.GONE);
                        break;
                    case 3:
                        btnFlotante.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        @StringRes
        private final int[] TAB_TITLES = new int[]{R.string.tab_admin_1, R.string.tab_admin_2, R.string.tab_admin_3};
        private final Context mContext;

        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mContext.getResources().getString(TAB_TITLES[position]);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Admin_Cliente tab1 = new Admin_Cliente();
                    return tab1;
                case 1:
                    Admin_Distribuidor tab2 = new Admin_Distribuidor();
                    return tab2;
                case 2:
                    Admin_Reportes tab3 = new Admin_Reportes();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 3;
        }

    }
}
