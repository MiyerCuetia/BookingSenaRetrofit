package co.edu.sena.bookingsena.interfaces;

import co.edu.sena.bookingsena.models.ReservaClienteVO;
import co.edu.sena.bookingsena.models.ReservaVO;
import co.edu.sena.bookingsena.models.RolVO;
import co.edu.sena.bookingsena.models.modelsUtilPost.ReservaUtilPostVO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {

    String API_ROUTE_ALOJAMIENTO = "/alojamiento";
    String API_ROUTE_LUGAR = "/lugar";
    String API_ROUTE_ALOJAMIENTOByID = "/alojamiento/{id}";
    String API_ROUTE_LUGARByID = "/lugar/{id}";
    String API_ROUTE_CREAR_RESERVA = "/reserva";

    @GET(API_ROUTE_ALOJAMIENTO)
    Call<String> getAlojamiento();

    @GET(API_ROUTE_LUGAR)
    Call<String> getLugar();

    @Headers("Content-Type: application/json")
    @GET(API_ROUTE_ALOJAMIENTOByID)
    Call<String> getAlojamientoById(@Path("id") Integer id);

    @Headers("Content-Type: application/json")
    @GET(API_ROUTE_LUGARByID)
    Call<String> getLugarById(@Path("id") Integer id);

    @POST(API_ROUTE_CREAR_RESERVA)
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8",
            "Cache-Control: max-age=640000"
    })
    Call<ReservaUtilPostVO> savePostReserva(@Body ReservaUtilPostVO reservaUtilPostVO);
}
