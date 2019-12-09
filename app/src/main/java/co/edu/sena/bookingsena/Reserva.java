package co.edu.sena.bookingsena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import co.edu.sena.bookingsena.interfaces.UsuarioService;
import co.edu.sena.bookingsena.models.AlojamientoUtilVO;
import co.edu.sena.bookingsena.models.AlojamientoVO;
import co.edu.sena.bookingsena.models.LugarVO;
import co.edu.sena.bookingsena.models.ReservaVO;
import co.edu.sena.bookingsena.models.UsuarioVO;

import co.edu.sena.bookingsena.models.modelsUtilPost.AlojamientoUtilPostVO;
import co.edu.sena.bookingsena.models.modelsUtilPost.ReservaUtilPostVO;
import co.edu.sena.bookingsena.models.modelsUtilPost.UsuarioUtilPostVO;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Reserva extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;

    EditText etResFechaRegistro, etResFechaLlegada, etResFechaSalida,
            etResEstado, etResPago;
    Button btnCancelarReserva, btnCrearReserva;
    private static Integer idAlojamiento,idUsuarioAuth;

    LugarVO lugarVO;
    ArrayList<LugarVO> lugarVOArrayList;

    Calendar calendario;
    Integer anio,mes,dia;
    DatePickerDialog miDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        showToolbar("Reserva - Introducir datos", true);



        idAlojamiento = getIntent().getExtras().getInt("idAlojamiento");
        idUsuarioAuth = 1;

        etResFechaRegistro = (EditText) findViewById(R.id.et_resFechaRegistro);
        etResFechaLlegada = (EditText) findViewById(R.id.et_resFechaLlegada);
        etResFechaSalida = (EditText) findViewById(R.id.et_resFechaSalida);
        etResEstado = (EditText) findViewById(R.id.et_resEstado);
        etResPago = (EditText) findViewById(R.id.et_resPago);

        instanciasCalendar();

        btnCancelarReserva = (Button) findViewById(R.id.btn_cancelReserva);
        btnCrearReserva = (Button) findViewById(R.id.btn_crearReserva);

        etResFechaLlegada.setOnClickListener(this);
        etResFechaSalida.setOnClickListener(this);
        btnCancelarReserva.setOnClickListener(this);
        btnCrearReserva.setOnClickListener(this);


    }

    public void showToolbar(String title, boolean upButton) {

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

    public void instanciasCalendar() {
        calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH);
        anio = calendario.get(Calendar.YEAR);

        //Agregamos fecha registro la fecha actuañ
         String fechaSeleccionada = anio + "-" + twoDigits(mes) + "-" + twoDigits(dia);
        etResFechaRegistro.setText(fechaSeleccionada);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.et_resFechaLlegada:
                miDatePickerDialog = new DatePickerDialog(Reserva.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int miAnio, int miMes, int miDia) {
                        //Muestro la fecha con el formato deseado
                        final String fechaSeleccionada = miAnio + "-" + twoDigits(miMes + 1) + "-" + twoDigits(miDia);
                        etResFechaLlegada.setText(fechaSeleccionada);
                    }
                    //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                    /**
                     *También puede cargar los valores que usted desee
                     */
                }, anio, mes, dia);

                miDatePickerDialog.show();
                break;

            case R.id.et_resFechaSalida:
                miDatePickerDialog = new DatePickerDialog(Reserva.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int miAnio, int miMes, int miDia) {
                        //Muestro la fecha con el formato deseado
                        final String fechaSeleccionada = miAnio + "-" + twoDigits(miMes + 1) + "-" + twoDigits(miDia);
                        etResFechaSalida.setText(fechaSeleccionada);
                    }
                    //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                    /**
                     *También puede cargar los valores que usted desee
                     */
                }, anio, mes, dia);

                miDatePickerDialog.show();
                break;
            case R.id.btn_cancelReserva:
                Intent intentCancelar = new Intent(Reserva.this, MainActivity.class);
                startActivity(intentCancelar);
                finish();
                break;
            case R.id.btn_crearReserva:

                if ((etResFechaLlegada.getText().toString()).isEmpty()) {
                    etResFechaLlegada.setError("Ingrese fecha llegada");
                } else {
                    etResFechaLlegada.setError(null);
                }

                if ((etResFechaSalida.getText().toString()).isEmpty()) {
                    etResFechaSalida.setError("Ingrese fecha salida");
                } else {
                    etResFechaSalida.setError(null);
                }

                if ((etResPago.getText().toString()).isEmpty()) {
                    etResPago.setError("Ingrese pago");
                } else {
                    etResPago.setError(null);
                }

                if (!(etResFechaLlegada.getText().toString()).isEmpty() & !(etResFechaSalida.getText().toString()).isEmpty() & !(etResPago.getText().toString()).isEmpty()) {

                    String fechaRegistro = etResFechaRegistro.getText().toString();
                    String fechaLlegada = etResFechaLlegada.getText().toString();
                    String fechaSalida = etResFechaSalida.getText().toString();
                    String ResEstado = etResEstado.getText().toString();
                    Double ResPago = Double.valueOf(etResPago.getText().toString());

                    ReservaUtilPostVO reservaUtilPostVO = new ReservaUtilPostVO(fechaRegistro, fechaLlegada, fechaSalida,
                                                        "", "", ResEstado, ResPago,
                                                        new AlojamientoUtilPostVO(idAlojamiento),new UsuarioUtilPostVO(idUsuarioAuth),
                                                        new UsuarioUtilPostVO(idUsuarioAuth),new UsuarioUtilPostVO(idUsuarioAuth));

                    createReservaJson(reservaUtilPostVO);
                }
                break;
        }

    }
    /*Metodo twoDigits
    * Es para que los días o meses se muestren con 2 dígitos.
      En vez de 7/7/2080 mostrará 07/07/2080.*/

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    private void createReservaJson(ReservaUtilPostVO reservaUtilPostVO) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://10.75.199.25:88")
                .baseUrl("http://192.168.137.1:88")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);

        //ENVIAMOS EL OBJETO reservaVO DE TIPO ReservaVO
        Call<ReservaUtilPostVO> call = usuarioService.savePostReserva(reservaUtilPostVO);

        call.enqueue(new Callback<ReservaUtilPostVO>() {
            @Override
            public void onResponse(Call<ReservaUtilPostVO> call, Response<ReservaUtilPostVO> response) {
                Log.i("Response1.", response.toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Reserva.this, "Reserva creado con exito", Toast.LENGTH_SHORT).show();
                            Intent intentIrAInicio = new Intent(Reserva.this, MainActivity.class);
                            startActivity(intentIrAInicio);
                            finish();
                            Log.i("post submitted to API.", response.toString());
                        }

                    } else {
                        Log.e("ERRORApi", "Unable to submit post to API.");
                    }
                }
            }

            @Override
            public void onFailure(Call<ReservaUtilPostVO> call, Throwable t) {
                Log.i("onFailure", "Returned Error conexion");
                Toast.makeText(Reserva.this, "Error conexion", Toast.LENGTH_LONG).show();
            }
        });

    }


    /*private void fetchJSON(Integer idAlojamiento) {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://10.75.199.25:88")
                .baseUrl("http://192.168.0.2:88")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);

        Call<String> call = usuarioService.getLugarById(1);

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
                Toast.makeText(Reserva.this, "Error conexion servidor", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void spinJSON(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            if (obj.optString("status").equals("200")) {
                lugarVOArrayList = new ArrayList<>();
                JSONObject dataobj = obj.getJSONObject("data");
                Log.i("onSuccessArray", dataobj.toString());

                lugarVO = new LugarVO();
                Log.i("onSuccessObject", dataobj.toString());

                lugarVO.setLugId(dataobj.getInt("lugId"));
                lugarVO.setLugNombre(dataobj.getString("lugNombre"));
                lugarVO.setLugDireccion(dataobj.getString("lugDireccion"));
                lugarVO.setLugTelefono(dataobj.getString("lugTelefono"));
                lugarVO.setLugCorreo(dataobj.getString("lugCorreo"));
                lugarVO.setLugLatitud(dataobj.getString("lugLatitud"));
                lugarVO.setLugLongitud(dataobj.getString("lugLongitud"));
                lugarVO.setLugDescripcion(dataobj.getString("lugDescripcion"));
                lugarVO.setFkMunicipio(dataobj.getJSONObject("fkMunicipio").getInt("munId"));
                lugarVO.setFkTipoLugar(dataobj.getJSONObject("fkTipoLugar").getInt("tluId"));

                lugarVOArrayList.add(lugarVO);

                for(LugarVO lugarVO: lugarVOArrayList ){
                    tvDes.setText(lugarVO.getLugNombre().toString());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

}
