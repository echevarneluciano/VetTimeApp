package com.example.vettimeapp.request;

import com.example.vettimeapp.modelos.Consulta;
import com.example.vettimeapp.modelos.Empleado;
import com.example.vettimeapp.modelos.Mascota;
import com.example.vettimeapp.modelos.Tarea;
import com.example.vettimeapp.modelos.Turno;
import com.example.vettimeapp.modelos.TurnosPorTarea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {

    //private static final String PATH="http://10.120.10.172:5200/api/";//"http://localhost:5500/api/";
    private static final String PATH="http://192.168.15.7:5200/api/";
    private static EndPointVetTime endPointVetTime;

    public static EndPointVetTime getEndpointVetTime(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endPointVetTime=retrofit.create(EndPointVetTime.class);

        return endPointVetTime;
    }

    public interface EndPointVetTime{

        @GET("Tareas")
        Call<List<Tarea>> obtenerTareas();

        @GET("Mascotas")
        Call<List<Mascota>> obtenerMascotas();

        @POST("Tareas/Turnos/{fecha}")
        Call<List<TurnosPorTarea>> obtenerTurnosPorFecha(@Body Tarea tarea, @Path("fecha") String fecha);

        @POST("Consultas/Turnos/{fecha}")
        Call<List<Consulta>> obtenerConsultasPorFecha(@Path("fecha") String fecha);

    }

}
