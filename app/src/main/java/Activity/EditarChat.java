package Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dmp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.button.MaterialButton;

public class EditarChat extends AppCompatActivity {

    private TextInputEditText etChatName, etEmail, etPhoneNumber;
    private TextInputLayout tilChatName, tilEmail, tilPhoneNumber;
    private MaterialButton btnSaveChat, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_chat);

        // Inicializar las vistas
        etChatName = findViewById(R.id.etChatName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        tilChatName = findViewById(R.id.tilChatName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPhoneNumber = findViewById(R.id.tilPhoneNumber);
        btnSaveChat = findViewById(R.id.btnSaveChat);
        btnCancel = findViewById(R.id.btnCancel);

        // Simulación de carga de datos (esto debe hacerse según tus datos o base de datos)
        loadData();

        // Acciones del botón de guardar cambios
        btnSaveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        // Acción del botón cancelar (puedes agregar la funcionalidad para volver a la pantalla anterior)
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Método para simular la carga de datos existentes en los campos
    private void loadData() {

        etChatName.setText("Chat de Ejemplo");
        etEmail.setText("ejemplo@correo.com");
        etPhoneNumber.setText("1234567890");
    }

    // Método para guardar los cambios realizados
    private void saveChanges() {
        String chatName = etChatName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();

        // Validar los datos (este es un ejemplo básico)
        if (chatName.isEmpty()) {
            tilChatName.setError("Por favor ingrese un nombre de chat");
            return;
        } else {
            tilChatName.setError(null); // Si es válido, limpia el error
        }

        if (email.isEmpty()) {
            tilEmail.setError("Por favor ingrese un correo electrónico");
            return;
        } else {
            tilEmail.setError(null);
        }

        if (phoneNumber.isEmpty()) {
            tilPhoneNumber.setError("Por favor ingrese un número telefónico");
            return;
        } else {
            tilPhoneNumber.setError(null);
        }

        Toast.makeText(EditarChat.this, "Chat actualizado exitosamente", Toast.LENGTH_SHORT).show();

        finish();
    }
}
