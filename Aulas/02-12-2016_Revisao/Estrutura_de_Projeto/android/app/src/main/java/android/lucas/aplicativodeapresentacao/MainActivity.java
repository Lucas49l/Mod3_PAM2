package android.lucas.aplicativodeapresentacao;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button    btn_cadastrar;
    TextView  txt_nome;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        iniComponents();

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_nome.setText("Bem Vindo ao curso de App");

                //Toast - Mensagem flutuante que dura alguns segundos, ela será acionada quando a função ser executada
                Toast.makeText(getApplicationContext(),"Clicou no botão", Toast.LENGTH_LONG).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void iniComponents() {
        txt_nome      = findViewById(R.id.txt_nome);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        logo          = findViewById(R.id.img_logo);
    }

}