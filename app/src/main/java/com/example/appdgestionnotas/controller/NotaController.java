package com.example.appdgestionnotas.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.appdgestionnotas.model.DatabaseHelper;
import com.example.appdgestionnotas.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaController {

    private DatabaseHelper dbHelper;

    public NotaController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void agregarNota(int estudianteId, double valor) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("estudiante_id", estudianteId);
        values.put("valor", valor);
        db.insert("notas", null, values);
        db.close();
    }

    public List<Nota> obtenerNotasPorEstudiante(int estudianteId) {
        List<Nota> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE estudiante_id = ?", new String[]{String.valueOf(estudianteId)});

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Nota(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public void editarNota(int notaId, double nuevoValor) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("valor", nuevoValor);
        db.update("notas", values, "id = ?", new String[]{String.valueOf(notaId)});
        db.close();
    }

    // ❌ Eliminar una nota
    public void eliminarNota(int notaId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("notas", "id = ?", new String[]{String.valueOf(notaId)});
        db.close();
    }

    // 🧮 Calcular promedio por estudiante
    public double calcularPromedioNotas(int estudianteId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT AVG(valor) FROM notas WHERE estudiante_id = ?", new String[]{String.valueOf(estudianteId)});
        double promedio = 0;
        if (cursor.moveToFirst()) {
            promedio = cursor.getDouble(0);
        }
        cursor.close();
        return promedio;
    }
}
