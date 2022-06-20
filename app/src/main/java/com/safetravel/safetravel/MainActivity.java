package com.safetravel.safetravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.safetravel.safetravel.models.Comment;
import com.safetravel.safetravel.models.Details;
import com.safetravel.safetravel.models.Forum;
import com.safetravel.safetravel.models.Sector;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Sector> sectors = new ArrayList<>();
    public ArrayList<Forum> forums = new ArrayList<>();
    public ArrayList<Details> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Forum forum = new Forum();
        forum.addComment(new Comment("Yo pasaba por ahí y vi el robo", 123456789L,new Date()));
        forum.addComment(new Comment("Hoy en día las personas corren mucho riesgo solas", 123456789L,new Date()));
        forum.addComment(new Comment("Me encargaré de ello", 123456789L,new Date()));
        forum.addComment(new Comment("Gracias por avisar", 123456789L,new Date()));
        forums.add(forum);
        ArrayList<Integer> imagesId = new ArrayList<>();
        imagesId.add(R.drawable.sector_1_1);
        imagesId.add(R.drawable.sector_1_2);
        imagesId.add(R.drawable.sector_1_3);
        Details detail = new Details("Según los primeros elementos de la investigación por robo y entrada por la fuerza, confiada a la brigada de represión de bandas (BRB) la familia del jugador argentino “no se cruzó” con los ladrones, explicó una fuente próxima al caso. “No escucharon nada”, añadió una fuente policial.", 123456789L, new Date(), imagesId);
        details.add(detail);
        sectors.add(new Sector("Robo a mano armada", forum.getId(), detail.getId(), 20.66682, -103.39182));


        MaterialToolbar topAppBar = findViewById(R.id.top_bar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.menu);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.map:
                        fragment = new MapFragment();
                        break;
                    case R.id.account:
                        fragment = new cuentaFragment();
                        break;
                    case R.id.help:
                        fragment = new AyudaFragment();
                        break;
                    case R.id.settings:
                        ///fragment = new HomeFragment();
                        break;
                    case R.id.logout:
                        finish();
                        break;
                    default:
                        fragment = new HomeFragment();
                        break;
                }

                if(fragment != null) {
                    ft.replace(R.id.fragments_container, fragment);
                    ft.commit();
                }

                item.setChecked(true);
                drawerLayout.close();
                return false;
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragments_container, new HomeFragment());
        ft.commit();
    }
}