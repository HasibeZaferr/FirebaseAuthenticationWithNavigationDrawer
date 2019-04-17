package gelecegiyazanlar.com.gykfirebaseauthentication.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gelecegiyazanlar.com.gykfirebaseauthentication.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmailEt;
    private EditText userPasswordEt;
    private EditText userConfirmPasswordEt;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    private String userEmail;
    private String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmailEt = (EditText) findViewById(R.id.user_email_register_et);
        userPasswordEt = (EditText) findViewById(R.id.user_password_register_et);
        userConfirmPasswordEt = (EditText) findViewById(R.id.user_confirm_email_register_tv);
        registerBtn = (Button) findViewById(R.id.button_register);

        mAuth = FirebaseAuth.getInstance();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInfoAndRegister();
            }
        });

    }

    private void getUserInfoAndRegister() {
        userEmail = userEmailEt.getText().toString().trim();
        userPassword = userPasswordEt.getText().toString().trim();
        String userConfirmPassword = userConfirmPasswordEt.getText().toString();

        if(!userEmail.isEmpty() && !userPassword.isEmpty() && !userConfirmPassword.isEmpty()){
            if(userPassword.equals(userConfirmPassword)){
                register();
            }
        } else{
            Toast.makeText(getApplicationContext(), "Kayıt için tüm alanları doldurunuz!", Toast.LENGTH_LONG).show();
        }
    }

    private void register() {


        mAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "Kayıt işlemi başarılı", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(loginIntent);


                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof FirebaseAuthException) {
                    if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_WEAK_PASSWORD")){
                        Toast.makeText(getApplicationContext(), "Eksik Şifre", Toast.LENGTH_SHORT).show();

                    } else if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_INVALID_EMAIL")){
                        Toast.makeText(getApplicationContext(), "Geçersiz mail", Toast.LENGTH_SHORT).show();

                    } else if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")){
                        Toast.makeText(getApplicationContext(), "Mail zaten kayıtlı", Toast.LENGTH_SHORT).show();

                    }
                    //your other logic goes here
                }
            }
        });

    }

}
