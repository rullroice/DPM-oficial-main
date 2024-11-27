package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dmp.R;
import com.google.android.material.button.MaterialButton;

import ui.Login;
import ui.Registro;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnIngresar, btnCrearSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los botones
        btnIngresar = findViewById(R.id.btnIngresar);
        btnCrearSesion = findViewById(R.id.btnCrearSesion);

        // Configurar el botón de "Ingresar"
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad de inicio de sesión
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        // Configurar el botón de "Crear Sesión"
        btnCrearSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad de registro
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });
    }
}
