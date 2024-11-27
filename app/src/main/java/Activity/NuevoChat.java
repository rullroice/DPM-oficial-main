package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dmp.R;
import com.google.android.material.textfield.TextInputEditText;

public class NuevoChat extends AppCompatActivity {

    private TextInputEditText etChatName, etEmail, etPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_chat);

        etChatName = findViewById(R.id.etChatName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);

        findViewById(R.id.btnCreateChat).setOnClickListener(this::onCreateChat);
        findViewById(R.id.btnCancel).setOnClickListener(view -> finish());
    }

    // Método para creación de nuevo chat
    private void onCreateChat(View view) {
        String chatName = etChatName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();

        if (TextUtils.isEmpty(chatName)) {
            Toast.makeText(this, "Por favor, ingresa un nombre para el chat", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(phoneNumber) || !Patterns.PHONE.matcher(phoneNumber).matches()) {
            Toast.makeText(this, "Por favor, ingresa un número telefónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Chat creado: " + chatName, Toast.LENGTH_SHORT).show();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("chatName", chatName);
        resultIntent.putExtra("email", email);
        resultIntent.putExtra("phoneNumber", phoneNumber);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
