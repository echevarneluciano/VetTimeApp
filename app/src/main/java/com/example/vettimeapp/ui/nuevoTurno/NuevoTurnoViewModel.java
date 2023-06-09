package com.example.vettimeapp.ui.nuevoTurno;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettimeapp.modelos.Cliente_mascota;
import com.example.vettimeapp.modelos.Consulta;
import com.example.vettimeapp.modelos.Empleado;
import com.example.vettimeapp.modelos.Mascota;
import com.example.vettimeapp.modelos.Tarea;
import com.example.vettimeapp.modelos.Turno;
import com.example.vettimeapp.modelos.TurnosPorTarea;
import com.example.vettimeapp.request.ApiClient;
import com.example.vettimeapp.utils.Utils;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoTurnoViewModel extends AndroidViewModel {

    private  MutableLiveData<List<String>> mTareas;
    private MutableLiveData<List<String>> mMascotas;
    private MutableLiveData<List<String>> mHorarios;
    private MutableLiveData<Boolean> mReset;
    private List<Tarea> tareasDisponibles;
    private String fecha;
    private Context context;
    private String tiempoTarea="00:30:00";
    private List<Cliente_mascota> clientes_mascotas;
    private Utils utils;


    public NuevoTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        utils = new Utils();
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

    public LiveData<Boolean> getReset() {
        if(mReset == null) {
            this.mReset = new MutableLiveData<>();
        }
        return mReset;
    }

    public void setTareas() {
        ArrayList<String> tareas = new ArrayList<>();
        tareas.add("1-Seleccione tarea...");
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
                        tareasDisponibles = response.body();
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
        mascotas.add("4-Seleccione mascota...");
        try {
            ApiClient.EndPointVetTime end = ApiClient.getEndpointVetTime();
            Call<List<Cliente_mascota>> call = end.obtenerClientesMascotas();
            call.enqueue(new Callback<List<Cliente_mascota>>() {
                @Override
                public void onResponse(Call<List<Cliente_mascota>> call, Response<List<Cliente_mascota>> response) {
                    if (response.body() != null) {
                        response.body().forEach(clientemascota -> {
                           mascotas.add(clientemascota.getMascota().getNombre());
                        });
                        clientes_mascotas = response.body();
                        mMascotas.setValue(mascotas);
                    }
                }
                @Override
                public void onFailure(Call<List<Cliente_mascota>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

    public void setHorarios(int dia, int mes, int anio,String tarea) {

        String nombreDia = utils.getDate(anio, mes, dia);
        System.out.println("Nombre del día: " + nombreDia);
        fecha = anio+"-"+mes+"-"+dia;

        if(tarea.equals("1-Seleccione tarea...")) {
            Toast.makeText(context, "Seleccione una tarea", Toast.LENGTH_LONG).show();
        }else {

        Tarea tareaSeleccionada = tareasDisponibles.stream().filter(t -> t.getTarea().equals(tarea)).findFirst().get();

        ArrayList<String> horarios = new ArrayList<>();
        mHorarios.setValue(horarios);

        try {
            ApiClient.EndPointVetTime end = ApiClient.getEndpointVetTime();
            Call<List<TurnosPorTarea>> call = end.obtenerTurnosPorFecha(tareaSeleccionada, mes+"-"+dia+"-"+anio);
            Log.d("salida", call.request().url().toString());
            call.enqueue(new Callback<List<TurnosPorTarea>>() {
                @Override
                public void onResponse(Call<List<TurnosPorTarea>> call, Response<List<TurnosPorTarea>> response) {
                    if (response.body() != null) {
                        if (response.body().isEmpty()) {
                            Toast.makeText(context, "No hay turnos para esta tarea", Toast.LENGTH_LONG).show();
                        }else {
                            List<TurnosPorTarea> turnos = response.body();
                            tiempoTarea = turnos.get(0).getTiempoTarea();

                            horarios.addAll(utils.getTurnoTarea(turnos, nombreDia));

                            filtraTurnosOcupados(dia, mes, anio, horarios, tarea);


                        }
                    }
                }
                @Override
                public void onFailure(Call<List<TurnosPorTarea>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
    } catch (Exception e) {
        Log.d("salida 2", e.getMessage());
    }
}
    }

    public void filtraTurnosOcupados(int dia, int mes, int anio,List<String> turnos,String tarea) {

        List<Consulta> consultas = new ArrayList<>();

        try {
            ApiClient.EndPointVetTime end = ApiClient.getEndpointVetTime();
            Call<List<Consulta>> call = end.obtenerConsultasPorFecha(mes+"-"+dia+"-"+anio, tarea);
            Log.d("salida", call.request().url().toString());
            call.enqueue(new Callback<List<Consulta>>() {
                @Override
                public void onResponse(Call<List<Consulta>> call, Response<List<Consulta>> response) {
                    if (response.body() != null) {

                       consultas.addAll(response.body());
                       List<String> intervalosConsultas = utils.getTurnosEntregados(consultas);
                       HashSet<String> hashSet = new HashSet<>(utils.getTurnosNoEntregados(turnos, intervalosConsultas));
                       ArrayList<String> arrayList = new ArrayList<>(hashSet);
                       Collections.sort(arrayList);
                       mHorarios.setValue(arrayList);

                    }
                }
                @Override
                public void onFailure(Call<List<Consulta>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }

    }

    public void crearConsulta(String tarea, String hora, String mascota) {
        if(tarea.equals("1-Seleccione tarea...") || mascota.equals("1-Seleccione mascota...")) {
            Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_LONG).show();
        }else {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date horaDate = format.parse(hora);
            Date tiempoTareaDate = format.parse(tiempoTarea);
            Date fechaFormat = formatFecha.parse(fecha);
            String fechaString = formatFecha.format(fechaFormat);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(horaDate);
            calendar.add(Calendar.MINUTE, tiempoTareaDate.getMinutes());
            calendar.add(Calendar.HOUR, tiempoTareaDate.getHours());
            horaDate = calendar.getTime();

            String horaDateString = format.format(horaDate);
            String fehaHoraInicio = fechaString + "T" + hora;
            String fehaHoraFin = fechaString + "T" + horaDateString;
            Consulta consulta = new Consulta();
            consulta.setTiempoInicio(fehaHoraInicio);
            consulta.setTiempoFin(fehaHoraFin);
            int mascotaid = clientes_mascotas.stream().filter(c -> c.getMascota().getNombre().equals(mascota)).findFirst().get().getId();
            consulta.setCliente_mascotaId(mascotaid);
            Log.d("salida", consulta.toString());

            ApiClient.EndPointVetTime end = ApiClient.getEndpointVetTime();
            Call<Consulta> call = end.nuevaConsultas(consulta, tarea);
            call.enqueue(new Callback<Consulta>() {
                @Override
                public void onResponse(Call<Consulta> call, Response<Consulta> response) {
                    if (response.body() != null) {
                        Toast.makeText(context, "Consulta agendada, fecha: "+response.body().getTiempoInicio(), Toast.LENGTH_SHORT).show();
                        mReset.setValue(true);
                    }
                }
                @Override
                public void onFailure(Call<Consulta> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }
    }
}