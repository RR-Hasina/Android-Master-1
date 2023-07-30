package com.tourisme.madatour.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import com.tourisme.madatour.R;
import com.tourisme.madatour.databinding.ActivityMainBinding;
import com.tourisme.madatour.view.fragment.dashboard.DashboardFragment;
import com.tourisme.madatour.view.fragment.home.HomeFragment;
import com.tourisme.madatour.view.fragment.notifications.NotificationsFragment;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MenuItem dashboardMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        dashboardMenuItem = navView.getMenu().findItem(R.id.navigation_dashboard);
        replaceFragment(new HomeFragment());
        toolbar.setTitle("Home");
        binding.navView.setOnItemSelectedListener(item -> {
            MenuItem itemToHide = toolbar.getMenu().findItem(R.id.search);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    toolbar.setTitle("Home");
                    itemToHide.setVisible(true); // Changez à true pour le montrer
                    break;
                case R.id.navigation_notifications:
                    replaceFragment(new NotificationsFragment());
                    toolbar.setTitle("Notifications");
                    // Cacher l'élément de menu en le rendant invisible (false) ou le montrer (true)
                    itemToHide.setVisible(false); // Changez à true pour le montrer
                    break;
            }

            return true;
        });
        navView.findViewById(R.id.navigation_dashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog(toolbar);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting){
            startActivity(new Intent(getBaseContext(), SettingsActivity.class));
        }
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog(Toolbar toolbar) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardMenuItem.setChecked(true);
                dialog.dismiss();
                toolbar.setTitle("Destinations");
                replaceFragment(new DashboardFragment());
            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Create a short is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Go live is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}