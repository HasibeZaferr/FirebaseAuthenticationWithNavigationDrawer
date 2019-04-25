package gelecegiyazanlar.com.gykfirebaseauthentication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gelecegiyazanlar.com.gykfirebaseauthentication.R;
import gelecegiyazanlar.com.gykfirebaseauthentication.fragments.MyNotesFragment;

public class AddNoteActivity extends AppCompatActivity {

    EditText userNoteEt;
    Button addNoteBtn;
    Button goToNotesPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        userNoteEt = (EditText) findViewById(R.id.user_notes_et);
        addNoteBtn = (Button) findViewById(R.id.add_notes_btn);
        goToNotesPage = (Button) findViewById(R.id.go_to_notes_btn);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        goToNotesPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void addNote() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("GezdigimYerler");
        String notesId = myRef.push().getKey();
        String receivedUserNote = userNoteEt.getText().toString();
        if (receivedUserNote.length() > 0) {
            myRef.child(notesId).child("sehirAdi").setValue(receivedUserNote);
            showDialog("Başarılı", "Notunuz Kaydedildi!");
        } else {
            showDialog("İşlem Başarısız", "Not alanı boş bırakılamaz!");
        }
        userNoteEt.setText("");
    }

    private void showDialog(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }

}
