package com.karla.control_venta;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karla.control_venta.Tablas.Abono;
import com.karla.control_venta.Tablas.Cliente;
import com.karla.control_venta.Tablas.Distribuidor;
import com.karla.control_venta.Tablas.FireBaseReference;
import com.karla.control_venta.Tablas.Venta;

import java.util.ArrayList;

public class Invitado extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    public static FirebaseAuth Auth = FirebaseAuth.getInstance();

    static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final DatabaseReference ref_Usuarios = database.getReference(FireBaseReference.REFERENCIA_USUARIOS);
    public static final DatabaseReference ref_Distribuidor = database.getReference(FireBaseReference.REFERENCIA_DISTRIBUIDORES);

    public static ArrayList<Cliente> lista_Clientes= new ArrayList<>();
    public static ArrayList<Distribuidor> lista_Distribuidores= new ArrayList<>();
    public static ArrayList<Venta> lista_Ventas= new ArrayList<>();
    public static ArrayList<Abono> lista_Abono= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitado);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager (viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        @StringRes
        private final int[] TAB_TITLES = new int[]{R.string.tab_invitado_1, R.string.tab_invitado_2};
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
                    Invitado_Abono tab1 = new Invitado_Abono();
                    return tab1;
                case 1:
                    Invitado_Venta tab2 = new Invitado_Venta();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
