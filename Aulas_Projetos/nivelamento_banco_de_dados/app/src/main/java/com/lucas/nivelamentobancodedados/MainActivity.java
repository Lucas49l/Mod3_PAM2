package com.lucas.nivelamentobancodedados;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // criar banco de dados
        // Nome do banco precisa ter a primerira letra em maiuscula
        SQLiteDatabase db = openOrCreateDatabase("Banco.db", MODE_PRIVATE, null);

        // criar tabelas
        try{
             db.execSQL("CREATE TABLE IF NOT EXISTS pessoas(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT," +
                    "idade INTEGER" +
                    ")"
            );

            db.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('joão', 30), ('Maria', 25)");

            //Consultar Cursor
            Cursor cursor = db.rawQuery("SELECT * FROM pessoas",null);
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                int id = cursor.getColumnIndex("id");
                int nome = cursor.getColumnIndex("nome");
                Log.i("TAG", "Pessoa id: ", cursor.getString("id"));
            }

        }catch (Exception e){
            Toast.makeText(this, "BANCO INDISPONIVEL!!!!!!!"
                    , Toast.LENGTH_SHORT).show();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}