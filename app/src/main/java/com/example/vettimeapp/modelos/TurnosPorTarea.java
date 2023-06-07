package com.example.vettimeapp.modelos;

import java.sql.Time;

public class TurnosPorTarea {
    private Time monday_ini;
    private Time monday_fin;
    private Time tuesday_ini;
    private Time tuesday_fin;
    private Time wednesday_ini;
    private Time wednesday_fin;
    private Time thursday_ini;
    private Time thursday_fin;
    private Time friday_ini;
    private Time friday_fin;
    private Time saturday_ini;
    private Time saturday_fin;
    private Time sunday_ini;
    private Time sunday_fin;
    private Integer empleadoId;

    public TurnosPorTarea() {
    }

    public TurnosPorTarea(Time monday_ini, Time monday_fin, Time tuesday_ini, Time tuesday_fin,
                          Time wednesday_ini, Time wednesday_fin, Time thursday_ini, Time thursday_fin,
                          Time friday_ini, Time friday_fin, Time saturday_ini, Time saturday_fin,
                          Time sunday_ini, Time sunday_fin, Integer empleadoId) {
        this.monday_ini = monday_ini;
        this.monday_fin = monday_fin;
        this.tuesday_ini = tuesday_ini;
        this.tuesday_fin = tuesday_fin;
        this.wednesday_ini = wednesday_ini;
        this.wednesday_fin = wednesday_fin;
        this.thursday_ini = thursday_ini;
        this.thursday_fin = thursday_fin;
        this.friday_ini = friday_ini;
        this.friday_fin = friday_fin;
        this.saturday_ini = saturday_ini;
        this.saturday_fin = saturday_fin;
        this.sunday_ini = sunday_ini;
        this.sunday_fin = sunday_fin;
        this.empleadoId = empleadoId;
    }

    public Time getMonday_ini() {
        return monday_ini;
    }

    public void setMonday_ini(Time monday_ini) {
        this.monday_ini = monday_ini;
    }

    public Time getMonday_fin() {
        return monday_fin;
    }

    public void setMonday_fin(Time monday_fin) {
        this.monday_fin = monday_fin;
    }

    public Time getTuesday_ini() {
        return tuesday_ini;
    }

    public void setTuesday_ini(Time tuesday_ini) {
        this.tuesday_ini = tuesday_ini;
    }

    public Time getTuesday_fin() {
        return tuesday_fin;
    }

    public void setTuesday_fin(Time tuesday_fin) {
        this.tuesday_fin = tuesday_fin;
    }

    public Time getWednesday_ini() {
        return wednesday_ini;
    }

    public void setWednesday_ini(Time wednesday_ini) {
        this.wednesday_ini = wednesday_ini;
    }

    public Time getWednesday_fin() {
        return wednesday_fin;
    }

    public void setWednesday_fin(Time wednesday_fin) {
        this.wednesday_fin = wednesday_fin;
    }

    public Time getThursday_ini() {
        return thursday_ini;
    }

    public void setThursday_ini(Time thursday_ini) {
        this.thursday_ini = thursday_ini;
    }

    public Time getThursday_fin() {
        return thursday_fin;
    }

    public void setThursday_fin(Time thursday_fin) {
        this.thursday_fin = thursday_fin;
    }

    public Time getFriday_ini() {
        return friday_ini;
    }

    public void setFriday_ini(Time friday_ini) {
        this.friday_ini = friday_ini;
    }

    public Time getFriday_fin() {
        return friday_fin;
    }

    public void setFriday_fin(Time friday_fin) {
        this.friday_fin = friday_fin;
    }

    public Time getSaturday_ini() {
        return saturday_ini;
    }

    public void setSaturday_ini(Time saturday_ini) {
        this.saturday_ini = saturday_ini;
    }

    public Time getSaturday_fin() {
        return saturday_fin;
    }

    public void setSaturday_fin(Time saturday_fin) {
        this.saturday_fin = saturday_fin;
    }

    public Time getSunday_ini() {
        return sunday_ini;
    }

    public void setSunday_ini(Time sunday_ini) {
        this.sunday_ini = sunday_ini;
    }

    public Time getSunday_fin() {
        return sunday_fin;
    }

    public void setSunday_fin(Time sunday_fin) {
        this.sunday_fin = sunday_fin;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }
}
