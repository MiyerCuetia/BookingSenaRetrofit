package co.edu.sena.bookingsena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.edu.sena.bookingsena.interfaces.UsuarioService;
import co.edu.sena.bookingsena.models.AlojamientoUtilVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    //Hacemos referencia a los componentes graficos del layout activity_main.xml
    EditText etLatitudDes, etLongitudDes;
    Button btnOtenerCoordenadas;
    RecyclerView recyclerViewHotel;
    AdapterHoteles adapter;
    AlojamientoUtilVO alojamientoUtilVO;
    //Generamos la lista de tipo listAlojamientoUtilVOS
    ArrayList<AlojamientoUtilVO> listAlojamientoUtilVOS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToolbar("Inicio",true);


        //Inicializamos los componentes graficos del layout activity_main.xml
        etLatitudDes = findViewById(R.id.et_latitudDes);
        etLongitudDes = findViewById(R.id.et_longitudDes);
        btnOtenerCoordenadas = findViewById(R.id.btn_obtenerCoordenadas);

        //Llamamos al metodo fetchJSON() para obtner los objetos de tipo Lugar con el servicio get
        fetchJSON();

        //Asignamos el onClickListener al btnOtenerCoordenadas para enviar la accion como tambien la latitud y longitud
        //a la actividad o clase de GoogleMaps
        btnOtenerCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latituDes = etLatitudDes.getText().toString();
                String longitudDes = etLongitudDes.getText().toString();

                if(!latituDes.isEmpty() & !longitudDes.isEmpty()){

                    Intent intent = new Intent(MainActivity.this, GoogleMaps.class);
                    Bundle b = new Bundle();
                    b.putString("accionUser", "clickBtnBuscar");
                    b.putString("latitudDes", latituDes);
                    b.putString("longitudDes", longitudDes);
                    intent.putExtras(b);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this, "Error - campos vacios.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerViewHotel = findViewById(R.id.amRvHoteles);
        recyclerViewHotel.setLayoutManager(new LinearLayoutManager(this));

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
                throw new IllegalArgumentException("menu option not implemented!!");

        }
        return super.onOptionsItemSelected(item);
    }
    @NonNull
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.inicio:
                Intent inicio= new Intent(MainActivity.this,MainActivity.class);
                startActivity(inicio);
                break;

            case R.id.escanear:
                Toast.makeText(this, "Escaner", Toast.LENGTH_SHORT).show();
                break;

            case R.id.configuracion:
                Intent config= new Intent(MainActivity.this,AccountActivity.class);
                startActivity(config);
                break;

            case R.id.compartir:
                Intent f= new Intent(MainActivity.this,Reserva.class);
                startActivity(f);
                Toast.makeText(this, "Compartir", Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }

    private void fetchJSON() {
        etLatitudDes.setText("3.449555");
        etLongitudDes.setText("-76.532526");

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://10.75.199.25:88")
                .baseUrl("http://192.168.137.1:88")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);

        Call<String> call = usuarioService.getAlojamiento();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSON(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("onFailure", "Returned Error conexion");
                Toast.makeText(MainActivity.this, "Error conexion servidor", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void spinJSON(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            if (obj.optString("status").equals("200")) {
                listAlojamientoUtilVOS = new ArrayList<>();
                JSONArray dataArray = obj.getJSONArray("data");
                Log.i("onSuccessArray", dataArray.toString());

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    alojamientoUtilVO = new AlojamientoUtilVO();
                    Log.i("onSuccessObject", dataobj.toString());

                    alojamientoUtilVO.setAloId(dataobj.getInt("aloId"));
                    alojamientoUtilVO.setAloCodigo(dataobj.getString("aloCodigo"));
                    alojamientoUtilVO.setAloCapacidad(dataobj.getInt("aloCapacidad"));
                    alojamientoUtilVO.setAloPrecio(dataobj.getString("aloPrecio"));
                    alojamientoUtilVO.setAloDescripcion(dataobj.getString("aloDescripcion"));

                    alojamientoUtilVO.setLugId(dataobj.getJSONObject("fkLugar").getInt("lugId"));
                    alojamientoUtilVO.setLugNombre(dataobj.getJSONObject("fkLugar").getString("lugNombre"));
                    alojamientoUtilVO.setLugDireccion(dataobj.getJSONObject("fkLugar").getString("lugDireccion"));
                    alojamientoUtilVO.setLugTelefono(dataobj.getJSONObject("fkLugar").getString("lugTelefono"));
                    alojamientoUtilVO.setLugCorreo(dataobj.getJSONObject("fkLugar").getString("lugCorreo"));
                    alojamientoUtilVO.setLugLatitud(dataobj.getJSONObject("fkLugar").getString("lugLatitud"));
                    alojamientoUtilVO.setLugLongitud(dataobj.getJSONObject("fkLugar").getString("lugLongitud"));
                    alojamientoUtilVO.setLugDescripcion(dataobj.getJSONObject("fkLugar").getString("lugDescripcion"));

                    alojamientoUtilVO.setMunId(dataobj.getJSONObject("fkLugar").getJSONObject("fkMunicipio").getInt("munId"));
                    alojamientoUtilVO.setMunNombre(dataobj.getJSONObject("fkLugar").getJSONObject("fkMunicipio").getString("munNombre"));
                    alojamientoUtilVO.setMunCodigo(dataobj.getJSONObject("fkLugar").getJSONObject("fkMunicipio").getString("munCodigo"));

                    alojamientoUtilVO.setDepId(dataobj.getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getInt("depId"));
                    alojamientoUtilVO.setDepNombre(dataobj.getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getString("depNombre"));

                    alojamientoUtilVO.setPaiId(dataobj.getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getJSONObject("fkPais").getInt("paiId"));
                    alojamientoUtilVO.setPaiNombre(dataobj.getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getJSONObject("fkPais").getString("paiNombre"));
                    alojamientoUtilVO.setPaiCodigo(dataobj.getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getJSONObject("fkPais").getString("paiCodigo"));

                    alojamientoUtilVO.setTluId(dataobj.getJSONObject("fkLugar").getJSONObject("fkTipoLugar").getInt("tluId"));
                    alojamientoUtilVO.setTluNombre(dataobj.getJSONObject("fkLugar").getJSONObject("fkTipoLugar").getString("tluNombre"));

                    alojamientoUtilVO.setTalId(dataobj.getJSONObject("fkTipoAlojamiento").getInt("talId"));
                    alojamientoUtilVO.setTalNombre(dataobj.getJSONObject("fkTipoAlojamiento").getString("talNombre"));


                    listAlojamientoUtilVOS.add(alojamientoUtilVO);
                }
                //Creamos una instancia de la clase AdapterHoteles y le pasamos la lista

                adapter = new AdapterHoteles(listAlojamientoUtilVOS);
                Log.i("spinJSONAdapter", listAlojamientoUtilVOS.toString());

                //Despues de haber creado el metodo setOnClickListener en la clase AdapterHoteles ya la podemos
                //asignarle el evento onClickListener
                //Al ocurrir el evento se envia el id del lugar seleccionado a la actividad o clase de GoogleMaps
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, DescripcionAlo.class);
                        Bundle b = new Bundle();
                        b.putString("accionUser", "clickItemRecyclerViewHotel");
                        b.putInt("idLugar", listAlojamientoUtilVOS.get(recyclerViewHotel.getChildAdapterPosition(view)).getLugId());
                        b.putInt("idAlojamiento", listAlojamientoUtilVOS.get(recyclerViewHotel.getChildAdapterPosition(view)).getAloId());
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });

                //Asigamos el adapter al recycleView
                recyclerViewHotel.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
