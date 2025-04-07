package com.example.appdgestionnotas;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdgestionnotas.adapters.EstudianteAdapter;
import com.example.appdgestionnotas.controller.EstudianteController;
import com.example.appdgestionnotas.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding ;
    private EstudianteController estudianteController;

    // private RecyclerView rvListaEstudiantes;
    // Declaracion del adapter para el RcyclerView
    private EstudianteAdapter estudianteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        estudianteController = new EstudianteController(this);
        estudianteController.agregarEstudiante("Juan", "12345");
        estudianteController.agregarEstudiante("María", "67890");

        // Brindar contexto al recyclerView
        binding.rvListaEstudiantes.setLayoutManager(new LinearLayoutManager(this));

        estudianteAdapter = new EstudianteAdapter(estudianteController.obtenerEstudiantes());
        binding.rvListaEstudiantes.setAdapter(estudianteAdapter);
    }
}