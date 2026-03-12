package com.lucas.nivelamentobancodedados;

import android.database.SQLException;
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

        //criar banco de dados
        SQLiteDatabase db = openOrCreateDatabase("db_bancoApp.sql", MODE_PRIVATE, null);

        //criar tabelas
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS pessoas(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(100) NOT NULL," +
                    "idade INTEGER(3) NOT NULL) ");

            db.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('João', 30), ('Maria', '25')");

        }catch (SQLException e){
            throw new RuntimeException(e);

           // Toast.makeText(this, "Banco indisponivel", Toast.LENGTH_SHORT);show();


        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}