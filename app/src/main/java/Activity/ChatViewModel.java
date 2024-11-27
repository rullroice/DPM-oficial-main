package Activity;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import modules.User;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends AndroidViewModel {

    private MutableLiveData<List<User>> users = new MutableLiveData<>();
    private DatabaseReference mDatabase;

    public ChatViewModel(Application application) {
        super(application);
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public void loadUsers() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null) {
                        userList.add(user);
                    }
                }
                users.setValue(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar error si es necesario
            }
        });
    }

    public void searchUsers(String query) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users.getValue()) {
            if (user.getNickname().toLowerCase().contains(query.toLowerCase())) {
                filteredUsers.add(user);
            }
        }
        users.setValue(filteredUsers);
    }
}
