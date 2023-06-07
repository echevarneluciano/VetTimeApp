package com.example.vettimeapp.utils;

import com.example.vettimeapp.modelos.TurnosPorTarea;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public String getTurnoTarea(List<TurnosPorTarea> tareas, String fecha) {
        String turno = "";
        for (int i = 0; i < tareas.size(); i++) {
            if (i == 0) {

            } else {

            }
        }
        return turno;
    }

}
