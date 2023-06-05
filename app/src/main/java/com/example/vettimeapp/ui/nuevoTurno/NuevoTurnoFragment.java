package com.example.vettimeapp.ui.nuevoTurno;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vettimeapp.databinding.FragmentHomeBinding;
import com.example.vettimeapp.modelos.Tarea;

public class NuevoTurnoFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NuevoTurnoViewModel nuevoTurnoViewModel =
                new ViewModelProvider(this).get(NuevoTurnoViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nuevoTurnoViewModel.getTareas().observe(getViewLifecycleOwner(), tareas -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tareas);
            binding.spTarea.setAdapter(adapter);
        });

        nuevoTurnoViewModel.getMascotas().observe(getViewLifecycleOwner(), mascotas -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mascotas);
            binding.spMascota.setAdapter(adapter);
        });


        binding.dtCalendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
Log.d("Fecha", dayOfMonth + "/" + (month + 1) + "/" + year+ "Date " +"");
        });

        nuevoTurnoViewModel.setTareas();
        nuevoTurnoViewModel.setMascotas();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}