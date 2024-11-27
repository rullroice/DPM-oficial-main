package ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dmp.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.FirebaseApp;

import Activity.MainActivity;
import Activity.menuApp;

public class Login extends AppCompatActivity {

    private MaterialButton btnIniciarSesion, btnVolver;
    private EditText txtCorreo, txtContraseña;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresar);

        // Asegúrate de que Firebase está correctamente inicializado
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this);
        }

        // Inicializar FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Inicializar los componentes de la UI
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnVolver = findViewById(R.id.btnVolver);

        // Configurar el botón "Iniciar Sesión"
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = txtCorreo.getText().toString().trim();
                String contraseña = txtContraseña.getText().toString().trim();

                // Validar campos
                if (!validarCampos(correo, contraseña)) return;

                // Autenticación con Firebase
                firebaseAuth.signInWithEmailAndPassword(correo, contraseña)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Éxito: redirigir al menú principal
                                Intent intent = new Intent(Login.this, menuApp.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Error: mostrar mensaje
                                Toast.makeText(Login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Configurar el botón "Volver"
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validarCampos(String correo, String contraseña) {
        // Validar formato del correo
        if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            txtCorreo.setError("Correo inválido");
            txtCorreo.requestFocus();
            return false;
        }

        // Validar contraseña
        if (contraseña.isEmpty()) {
            txtContraseña.setError("La contraseña no puede estar vacía");
            txtContraseña.requestFocus();
            return false;
        }
        if (contraseña.length() < 6) {
            txtContraseña.setError("La contraseña debe tener al menos 6 caracteres");
            txtContraseña.requestFocus();
            return false;
        }
        return true;
    }
}
