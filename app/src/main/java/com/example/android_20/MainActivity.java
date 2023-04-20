package com.example.android_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.android_20.fragment.HomeFragment;
import com.example.android_20.fragment.InfoFragment;
import com.example.android_20.fragment.TextFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    BottomNavigationView mnBottom;
    Toolbar tb;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = findViewById(R.id.tbMainMenu);
        setSupportActionBar(tb);
        mnBottom = (BottomNavigationView) findViewById(R.id.navMenu);
        mnBottom.setOnItemSelectedListener(getListener());

        //set default
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, new HomeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

//        drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_home);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//chúng ta kích hoạt nút quay lại trong thanh hành động
        navigationView.setNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());// khi khoi tao ung dung len hien thi homefragment
    }


    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return item -> {
            switch (item.getItemId())
            {
                case R.id.mnHome:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.mnInfo:
                    loadFragment(new InfoFragment());
                    return true;
                case R.id.mnText:
                    loadFragment(new TextFragment());
                    return true;
            }
            return true;
        };
    }
    // ham load fragment
    void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itClass:
                Toast.makeText(MainActivity.this, "Class Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itDefault:
                Toast.makeText(MainActivity.this, "Default Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itInfoApps:
                Toast.makeText(MainActivity.this, "Info Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itCall:
                Toast.makeText(MainActivity.this, "Call Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}