package Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmp.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChatBoxActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChat;
    private EditText editMsg;
    private MaterialButton btnEnviar;
    private ImageButton btnBack;
    private DatabaseReference mDatabase;
    private String chatId;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_box);

        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editMsg = findViewById(R.id.editMsg);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnBack = findViewById(R.id.btnBack);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        chatId = getIntent().getStringExtra("chatId");

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editMsg.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    sendMessage(messageText);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendMessage(String messageText) {
        if (chatId == null) {
            Toast.makeText(this, "Error: Chat no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        String messageId = mDatabase.child("messages").child(chatId).push().getKey();
        Map<String, Object> messageValues = new HashMap<>();
        messageValues.put("senderId", currentUserId);
        messageValues.put("text", messageText);
        messageValues.put("timestamp", System.currentTimeMillis());

        mDatabase.child("messages").child(chatId).child(messageId).setValue(messageValues)
                .addOnSuccessListener(aVoid -> {
                    editMsg.setText("");
                    Toast.makeText(ChatBoxActivity.this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ChatBoxActivity.this, "Error al enviar el mensaje", Toast.LENGTH_SHORT).show();
                });
    }
}