package com.example.vettimeapp.ui.nuevoTurno;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettimeapp.modelos.Mascota;
import com.example.vettimeapp.modelos.Tarea;
import com.example.vettimeapp.modelos.Turno;
import com.example.vettimeapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoTurnoViewModel extends AndroidViewModel {

    private  MutableLiveData<List<String>> mTareas;
    private MutableLiveData<List<String>> mMascotas;
    private MutableLiveData<List<String>> mHorarios;
    private Context context;


    public NuevoTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<String>> getTareas() {
        if(mTareas == null) {
            this.mTareas = new MutableLiveData<>();
        }
        return mTareas;
    }

    public LiveData<List<String>> getMascotas() {
        if(mMascotas == null) {
            this.mMascotas = new MutableLiveData<>();
        }
        return mMascotas;
    }

    public LiveData<List<String>> getHorarios() {
        if(mHorarios == null) {
            this.mHorarios = new MutableLiveData<>();
        }
        return mHorarios;
    }

    public void setTareas() {
        ArrayList<String> tareas = new ArrayList<>();
        tareas.add("Seleccione tarea...");
        try {
            ApiClient.EndPointVetTime end = ApiClient.getEndpointVetTime();
            Call<List<Tarea>> call = end.obtenerTareas();
            call.enqueue(new Callback<List<Tarea>>() {
                @Override
                public void onResponse(Call<List<Tarea>> call, Response<List<Tarea>> response) {
                    if (response.body() != null) {
                        response.body().forEach(tarea -> {
                            tareas.add(tarea.getTarea());
                        });
                        mTareas.setValue(tareas);
                    }
                }
                @Override
                public void onFailure(Call<List<Tarea>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }

    }

    public void setMascotas() {
        ArrayList<String> mascotas = new ArrayList<>();
        mascotas.add("Seleccione mascota...");
        try {
            ApiClient.EndPointVetTime end = ApiClient.getEndpointVetTime();
            Call<List<Mascota>> call = end.obtenerMascotas();
            call.enqueue(new Callback<List<Mascota>>() {
                @Override
                public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                    if (response.body() != null) {
                        response.body().forEach(mascota -> {
                            mascotas.add(mascota.getNombre());
                        });
                        mMascotas.setValue(mascotas);
                    }
                }
                @Override
                public void onFailure(Call<List<Mascota>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

    public void setHorarios() {
        ArrayList<String> horarios = new ArrayList<>();
        ArrayList<Turno> turnos = new ArrayList<>();
        horarios.add("Seleccione horario...");
        try {
            ApiClient.EndPointVetTime end = ApiClient.getEndpointVetTime();
            Call<List<Turno>> call = end.obtenerTurnos();
            call.enqueue(new Callback<List<Turno>>() {
                @Override
                public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                    if (response.body() != null) {
                        turnos.addAll(response.body());

                        turnos.forEach(turno -> {



                        });

                    }
                }
                @Override
                public void onFailure(Call<List<Turno>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
    } catch (Exception e) {
        Log.d("salida 2", e.getMessage());
    }
    }

    public void crearConsulta(String tarea, String fecha, String hora, String mascota) {



    }

}