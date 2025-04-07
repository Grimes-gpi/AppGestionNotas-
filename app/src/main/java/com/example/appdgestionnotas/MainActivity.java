package com.example.appdgestionnotas;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appdgestionnotas.adapters.EstudianteAdapter;
import com.example.appdgestionnotas.controller.EstudianteController;
import com.example.appdgestionnotas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EstudianteController estudianteController;
    private EstudianteAdapter estudianteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar el controlador
        estudianteController = new EstudianteController(this);

        // Agregar solo si no hay datos existentes
        if (estudianteController.obtenerEstudiantes().isEmpty()) {
            estudianteController.agregarEstudiante("Juan", "12345");
            estudianteController.agregarEstudiante("María", "67890");
        }

        // Configurar RecyclerView
        binding.rvListaEstudiantes.setLayoutManager(new LinearLayoutManager(this));
        estudianteAdapter = new EstudianteAdapter(this, estudianteController.obtenerEstudiantes());
        binding.rvListaEstudiantes.setAdapter(estudianteAdapter);
    }
}
