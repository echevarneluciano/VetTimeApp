package com.example.vettimeapp.utils;

import android.os.Build;
import android.util.Log;


import com.example.vettimeapp.modelos.TurnosPorTarea;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class Utils {

    public String getDate(int anio, int mes, int dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anio, mes - 1, dia);
        Date fecha = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        String nombreDia = sdf.format(fecha);
        return nombreDia;
    }


    public List<String> getTurnoTarea(List<TurnosPorTarea> tareas, String fecha) {

        List<String> turnos = new ArrayList<>();
        String tiempoInicio = "";
        String tiempoFin ="";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date timeInicio = null;
        Date timeFin = null;
        List<Date> datesIni=new ArrayList<>();
        List<Date> datesFin=new ArrayList<>();

        for (int i = 0; i < tareas.size(); i++) {
            switch (fecha) {
                case "Monday":
                    tiempoInicio = tareas.get(i).getMonday_ini();
                    tiempoFin = tareas.get(i).getMonday_fin();
                    try {
                        timeInicio = format.parse(tiempoInicio);
                        timeFin = format.parse(tiempoFin);
                        datesIni.add(timeInicio);
                        datesFin.add(timeFin);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Tuesday":
                    tiempoInicio = tareas.get(i).getTuesday_ini();
                    tiempoFin = tareas.get(i).getTuesday_fin();
                    try {
                        timeInicio = format.parse(tiempoInicio);
                        timeFin = format.parse(tiempoFin);
                        datesIni.add(timeInicio);
                        datesFin.add(timeFin);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Wednesday":
                    tiempoInicio = tareas.get(i).getWednesday_ini();
                    tiempoFin = tareas.get(i).getWednesday_fin();
                    try {
                        timeInicio = format.parse(tiempoInicio);
                        timeFin = format.parse(tiempoFin);
                        datesIni.add(timeInicio);
                        datesFin.add(timeFin);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Thursday":
                    tiempoInicio = tareas.get(i).getThursday_ini();
                    tiempoFin = tareas.get(i).getThursday_fin();
                    try {
                        timeInicio = format.parse(tiempoInicio);
                        timeFin = format.parse(tiempoFin);
                        datesIni.add(timeInicio);
                        datesFin.add(timeFin);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Friday":
                    tiempoInicio = tareas.get(i).getFriday_ini();
                    tiempoFin = tareas.get(i).getFriday_fin();
                    try {
                        timeInicio = format.parse(tiempoInicio);
                        timeFin = format.parse(tiempoFin);
                        datesIni.add(timeInicio);
                        datesFin.add(timeFin);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                case "Saturday":
                    tiempoInicio = tareas.get(i).getSaturday_ini();
                    tiempoFin = tareas.get(i).getSaturday_fin();
                    try {
                        timeInicio = format.parse(tiempoInicio);
                        timeFin = format.parse(tiempoFin);
                        datesIni.add(timeInicio);
                        datesFin.add(timeFin);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                case "Sunday":
                    tiempoInicio = tareas.get(i).getSunday_ini();
                    tiempoFin = tareas.get(i).getSunday_fin();
                    try {
                        timeInicio = format.parse(tiempoInicio);
                        timeFin = format.parse(tiempoFin);
                        datesIni.add(timeInicio);
                        datesFin.add(timeFin);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
            }
        }

        List<String> intervalos = new ArrayList<>();

        // Iterar sobre los intervalos de inicio y fin
        for (int i = 0; i < datesIni.size(); i++) {
            Date inicio = datesIni.get(i);
            Date fin = datesFin.get(i);

            // Obtener el tiempo actual como el intervalo de inicio
            Date tiempoActual = new Date(inicio.getTime());

            // Agregar intervalos de 30 minutos hasta alcanzar el intervalo de fin
            while (tiempoActual.before(fin)) {
                intervalos.add(format.format(tiempoActual));
                tiempoActual.setTime(tiempoActual.getTime() + 30 * 60 * 1000); // Agregar 30 minutos en milisegundos
            }
        }

        return intervalos;

    }

}
