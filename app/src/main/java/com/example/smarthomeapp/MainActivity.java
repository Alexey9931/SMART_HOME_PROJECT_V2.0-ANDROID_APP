package com.example.smarthomeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smarthomeapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View headerView = navigationView.getHeaderView(0);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_controlpanel_charts, R.id.nav_controlpanel_chart_setting,
                R.id.nav_controlpanel_statistics,R.id.nav_controlpanel_table,
                R.id.nav_weatherstation_charts, R.id.nav_weatherstation_chart_setting,
                R.id.nav_weatherstation_statistics, R.id.nav_weatherstation_table,
                R.id.nav_gasboiler, R.id.nav_gasboiler_charts, R.id.nav_gasboiler_chart_setting,
                R.id.nav_gasboiler_table)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_about :
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.update_home:
                ControlPanelValuesGetter controlPanelValuesGetter = new ControlPanelValuesGetter(this);
                controlPanelValuesGetter.execute(
                        "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-1day.php",
                        "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-3day.php",
                        "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-5day.php",
                        "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-7day.php");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    
}