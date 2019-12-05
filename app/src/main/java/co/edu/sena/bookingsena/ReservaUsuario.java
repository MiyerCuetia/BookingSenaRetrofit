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
import co.edu.sena.bookingsena.models.ReservaUsuarioUtilVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ReservaUsuario extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    //Hacemos referencia a los componentes graficos del layout activity_main.xml

    RecyclerView recyclerViewRu;
    AdapterReserva adapter;

    ReservaUsuarioUtilVO reservaUsuarioUtilVO;
    //Generamos la lista de tipo listAlojamientoUtilVOS
    ArrayList<ReservaUsuarioUtilVO> listReservaUsuarioUtilVO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_usuario);
        showToolbar("Mis Reservas",true);

        //Llamamos al metodo fetchJSON() para obtner los objetos de tipo Lugar con el servicio get
        fetchJSON();

        recyclerViewRu = findViewById(R.id.rvReservaUsuario);
        recyclerViewRu.setLayoutManager(new LinearLayoutManager(this));

    }
    public void showToolbar(String title,boolean upButton){

        toolbar = findViewById(R.id.update_wall_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

        drawerLayout= findViewById( R.id.navigation_ru );
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
                Intent inicio= new Intent(ReservaUsuario.this,MainActivity.class);
                startActivity(inicio);
                break;

            case R.id.reservas:
                Intent misreservas= new Intent(ReservaUsuario.this,ReservaUsuario.class);
                startActivity(misreservas);
                break;

            case R.id.configuracion:
                Intent config= new Intent(ReservaUsuario.this,AccountActivity.class);
                startActivity(config);
                break;

            case R.id.compartir:
                Intent f= new Intent(ReservaUsuario.this,Reserva.class);
                startActivity(f);
                Toast.makeText(this, "Compartir", Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }

    private void fetchJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://10.75.199.25:88")
                .baseUrl("http://192.168.137.1:88")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);

        Call<String> call = usuarioService.getReservaUsuario();

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
                Toast.makeText(ReservaUsuario.this, "Error conexion servidor", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void spinJSON(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            if (obj.optString("status").equals("200")) {
                listReservaUsuarioUtilVO = new ArrayList<>();
                JSONArray dataArray = obj.getJSONArray("data");
                Log.i("onSuccessArray", dataArray.toString());

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    reservaUsuarioUtilVO = new ReservaUsuarioUtilVO();
                    Log.i("onSuccessObject", dataobj.toString());

                    reservaUsuarioUtilVO.setResId(dataobj.getInt("resId"));
                    reservaUsuarioUtilVO.setResFechaRegistro(dataobj.getString("resFechaRegistro"));
                    reservaUsuarioUtilVO.setResFechaLlegada(dataobj.getString("resFechaLlegada"));
                    reservaUsuarioUtilVO.setResFechaSalida(dataobj.getString("resFechaSalida"));
                    reservaUsuarioUtilVO.setResFechaChecking(dataobj.getString("resFechaChecking"));
                    reservaUsuarioUtilVO.setResFechaCheckout(dataobj.getString("resFechaCheckout"));
                    reservaUsuarioUtilVO.setResEstado(dataobj.getString("resEstado"));
                    reservaUsuarioUtilVO.setResPago(dataobj.getDouble("resPago"));

                    reservaUsuarioUtilVO.setAloId(dataobj.getJSONObject("fkAlojamiento").getInt("aloId"));
                    reservaUsuarioUtilVO.setAloCodigo(dataobj.getJSONObject("fkAlojamiento").getString("aloCodigo"));
                    reservaUsuarioUtilVO.setAloCapacidad(dataobj.getJSONObject("fkAlojamiento").getInt("aloCapacidad"));
                    reservaUsuarioUtilVO.setAloPrecio(dataobj.getJSONObject("fkAlojamiento").getString("aloPrecio"));
                    reservaUsuarioUtilVO.setAloDescripcion(dataobj.getJSONObject("fkAlojamiento").getString("aloDescripcion"));

                    reservaUsuarioUtilVO.setLugId(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getInt("lugId"));
                    reservaUsuarioUtilVO.setLugNombre(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getString("lugNombre"));
                    reservaUsuarioUtilVO.setLugDireccion(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getString("lugDireccion"));
                    reservaUsuarioUtilVO.setLugTelefono(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getString("lugTelefono"));
                    reservaUsuarioUtilVO.setLugCorreo(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getString("lugCorreo"));
                    reservaUsuarioUtilVO.setLugLatitud(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getString("lugLatitud"));
                    reservaUsuarioUtilVO.setLugLongitud(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getString("lugLongitud"));
                    reservaUsuarioUtilVO.setLugDescripcion(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getString("lugDescripcion"));

                    reservaUsuarioUtilVO.setMunId(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkMunicipio").getInt("munId"));
                    reservaUsuarioUtilVO.setMunNombre(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkMunicipio").getString("munNombre"));
                    reservaUsuarioUtilVO.setMunCodigo(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkMunicipio").getString("munCodigo"));

                    reservaUsuarioUtilVO.setDepId(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getInt("depId"));
                    reservaUsuarioUtilVO.setDepNombre(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getString("depNombre"));

                    reservaUsuarioUtilVO.setPaiId(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getJSONObject("fkPais").getInt("paiId"));
                    reservaUsuarioUtilVO.setPaiNombre(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getJSONObject("fkPais").getString("paiNombre"));
                    reservaUsuarioUtilVO.setPaiCodigo(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkMunicipio").getJSONObject("fkDepartamento").getJSONObject("fkPais").getString("paiCodigo"));

                    reservaUsuarioUtilVO.setTluId(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkTipoLugar").getInt("tluId"));
                    reservaUsuarioUtilVO.setTluNombre(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkLugar").getJSONObject("fkTipoLugar").getString("tluNombre"));

                    reservaUsuarioUtilVO.setTalId(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkTipoAlojamiento").getInt("talId"));
                    reservaUsuarioUtilVO.setTalNombre(dataobj.getJSONObject("fkAlojamiento").getJSONObject("fkTipoAlojamiento").getString("talNombre"));

                    reservaUsuarioUtilVO.setUsuId(dataobj.getJSONObject("fkCliente").getInt("usuId"));
                    reservaUsuarioUtilVO.setUsuIdentificacion(dataobj.getJSONObject("fkCliente").getString("usuIdentificacion"));
                    reservaUsuarioUtilVO.setUsuNombres(dataobj.getJSONObject("fkCliente").getString("usuNombres"));
                    reservaUsuarioUtilVO.setUsuGenero(dataobj.getJSONObject("fkCliente").getString("usuGenero"));
                    reservaUsuarioUtilVO.setUsuCorreo(dataobj.getJSONObject("fkCliente").getString("usuCorreo"));
                    reservaUsuarioUtilVO.setUsuTelefono(dataobj.getJSONObject("fkCliente").getString("usuTelefono"));
                    reservaUsuarioUtilVO.setUsuAvatar(dataobj.getJSONObject("fkCliente").getString("usuAvatar"));

                    reservaUsuarioUtilVO.setTidId(dataobj.getJSONObject("fkCliente").getJSONObject("fkTipoIdentificacion").getInt("tidId"));
                    reservaUsuarioUtilVO.setTipSigla(dataobj.getJSONObject("fkCliente").getJSONObject("fkTipoIdentificacion").getString("tipSigla"));
                    reservaUsuarioUtilVO.setTipNombre(dataobj.getJSONObject("fkCliente").getJSONObject("fkTipoIdentificacion").getString("tipNombre"));


                    listReservaUsuarioUtilVO.add(reservaUsuarioUtilVO);
                }
                //Creamos una instancia de la clase AdapterHoteles y le pasamos la lista

                adapter = new AdapterReserva(listReservaUsuarioUtilVO);
                Log.i("spinJSONAdapter", listReservaUsuarioUtilVO.toString());

                //Asigamos el adapter al recycleView
                recyclerViewRu.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
