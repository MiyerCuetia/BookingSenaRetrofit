package co.edu.sena.bookingsena;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.edu.sena.bookingsena.interfaces.UsuarioService;
import co.edu.sena.bookingsena.models.AlojamientoUtilVO;
import co.edu.sena.bookingsena.models.AlojamientoVO;
import co.edu.sena.bookingsena.models.LugarVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DescripcionAlo extends AppCompatActivity {
    Toolbar toolbar;

    private static String accionUsuario;
    private static Integer idAlojamiento;
    private static Integer idLugar;

    ImageView imageHotel;

    TextView tvNombreLugar,tvDireccion,tvPrecio,tvNumeroTelefonico,tvDireccionCorreo,tvTipoAlojamineto,
            tvDescAlojamiento;
    Button btnMapa,btnReservar;

    AlojamientoUtilVO alojamientoUtilVO;
    //Generamos la lista de tipo listAlojamientoUtilVOS
    ArrayList<AlojamientoUtilVO> listAlojamientoUtilVOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_alo);

        showToolbar("Detalle",true);

        accionUsuario = getIntent().getExtras().getString("accionUser");
        idAlojamiento = getIntent().getExtras().getInt("idAlojamiento");
        idLugar = getIntent().getExtras().getInt("idLugar");

        fetchJSON(idAlojamiento);

        tvNombreLugar = (TextView) findViewById(R.id.tv_nombreLugar);
        tvDireccion = (TextView) findViewById(R.id.tv_direccion);
        tvPrecio = (TextView) findViewById(R.id.tv_precio);
        tvNumeroTelefonico = (TextView) findViewById(R.id.tv_numTelefono);
        tvDireccionCorreo = (TextView) findViewById(R.id.tv_direccionCorreo);
        tvTipoAlojamineto = (TextView) findViewById(R.id.tv_tipoAlojamiento);
        tvDescAlojamiento = (TextView) findViewById(R.id.tv_descAlojamiento);

        btnMapa = (Button) findViewById(R.id.btn_mapa);
        btnReservar = (Button) findViewById(R.id.btn_reservar);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DescripcionAlo.this, GoogleMaps.class);
                Bundle b = new Bundle();
                b.putString("accionUser", accionUsuario);
                b.putInt("idLugar", idLugar);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DescripcionAlo.this, Reserva.class);
                Bundle b = new Bundle();
                b.putInt("idAlojamiento", idAlojamiento);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    public void showToolbar(String title,boolean upButton){

        toolbar = (Toolbar) findViewById(R.id.update_wall_page_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                finish();
            }
        });

    }

    private void fetchJSON(Integer idAlojamiento) {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://10.75.199.25:88")
                .baseUrl("http://192.168.137.1:88")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);

        Call<String> call = usuarioService.getAlojamientoById(idAlojamiento);

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
                Toast.makeText(DescripcionAlo.this, "Error conexion servidor", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void spinJSON(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            if (obj.optString("status").equals("200")) {
                listAlojamientoUtilVOS = new ArrayList<>();
                JSONObject dataobj = obj.getJSONObject("data");
                Log.i("onSuccessArray", dataobj.toString());

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
            for(AlojamientoUtilVO alojamientoUtilVO1: listAlojamientoUtilVOS){

                tvNombreLugar.setText(alojamientoUtilVO1.getLugNombre().toString());
                tvDireccion.setText(alojamientoUtilVO1.getLugDireccion().toString());
                tvPrecio.setText(alojamientoUtilVO1.getAloPrecio().toString());
                tvNumeroTelefonico.setText(alojamientoUtilVO1.getLugTelefono().toString());
                tvDireccionCorreo.setText(alojamientoUtilVO1.getLugCorreo().toString());
                tvTipoAlojamineto.setText(alojamientoUtilVO1.getTalNombre().toString());
                tvDescAlojamiento.setText(alojamientoUtilVO1.getLugDescripcion().toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
