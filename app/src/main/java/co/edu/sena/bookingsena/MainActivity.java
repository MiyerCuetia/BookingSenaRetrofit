package co.edu.sena.bookingsena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToolbar("Inicio",true);



    }
    public void showToolbar(String title,boolean upButton){

        toolbar = findViewById(R.id.update_wall_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

        drawerLayout= findViewById( R.id.navigation );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.abrir,R.string.cerrar);

        drawerLayout.addDrawerListener( toggle );
        toggle.syncState();

        navigationView = findViewById( R.id.navigation_view );
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_setting:
                //settings

                Intent settings = new Intent(this, AccountActivity.class);
                startActivity(settings);
                break;

            default:
                // select an option

        }
        return super.onOptionsItemSelected(item);
    }
    @NonNull
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.escanear:
                Intent i = new Intent( MainActivity.this,LoginActivity.class );
                startActivity( i );
                break;
            case R.id.inicio:
                Intent intent1= new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent1);
                //  Toast.makeText( this,"comandos de voz",Toast.LENGTH_SHORT ).show();
                break;

            case R.id.configuracion:
                Intent config= new Intent(MainActivity.this,AccountActivity.class);
                startActivity(config);
                break;

        }
        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }

}
