package com.example.k_g;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    private Bundle bundle;

    private Bundle bundlehome;

    private GoogleSignInOptions gso;

    private GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String Mail=getIntent().getStringExtra("mail");
        String name=getIntent().getStringExtra("name");
        String dpurl=getIntent().getStringExtra("dp");


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);


        bundle=new Bundle();
        bundle.putString("mail",Mail);


        bundlehome=new Bundle();
        bundlehome.putString("mail",Mail);
        bundlehome.putString("name",name);
        bundlehome.putString("dp",dpurl);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            placement_fragment obj=new placement_fragment();
            obj.setArguments(bundlehome);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, obj).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_home:
                placement_fragment obj1=new placement_fragment();
                obj1.setArguments(bundlehome);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,obj1).commit();
                break;

//            case R.id.nav_learn: getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Learn_Fragment()).commit();
//                break;

            case R.id.nav_placed_student:
                Placed_Students_Fragment obj2=new Placed_Students_Fragment();
                obj2.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,obj2).commit();
                break;

            case R.id.nav_past_companies:
                Past_Companies_Fragment obj3=new Past_Companies_Fragment();
                obj3.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,obj3).commit();
                break;

            case R.id.logout: SignOut();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void SignOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent=new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                finish();

            }
        });
    }
}