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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    case R.id.sectors:
                        fragment = new SectorFragment();
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