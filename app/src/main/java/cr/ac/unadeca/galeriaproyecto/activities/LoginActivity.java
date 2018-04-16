package cr.ac.unadeca.galeriaproyecto.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import cr.ac.unadeca.galeriaproyecto.R;
import cr.ac.unadeca.galeriaproyecto.database.models.Users;

import cr.ac.unadeca.galeriaproyecto.database.models.Users_Table;
import cr.ac.unadeca.galeriaproyecto.util.Functions;
import cr.ac.unadeca.galeriaproyecto.util.Session;

/**
 * Created by pc on 11/4/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Session session;
    private ImageView image;
    MediaPlayer mp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        image = findViewById(R.id.imageLogin);
        mp = MediaPlayer.create(this, R.raw.tiny);
        session = new Session(this);
        if(session.isLoggedIn()){
            goToMain();
        }
        Button iniciar = findViewById(R.id.login);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userna = username.getText().toString();
                String passwo = password.getText().toString();
                mp.start();
                if (userna.isEmpty() || passwo.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.loginerror), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Login(userna, passwo);
                }
            }
        });


        Button registrar = findViewById(R.id.register);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegistrar();
            }
        });


        Functions.loadImage("https://i.imgur.com/i3EA0oQ.jpg", image, this);
    }

    private  void goToMain(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    private  void goToRegistrar(){
        mp.start();
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
        finish();
    }

    private boolean Login(String username, String password){
        boolean isLoggedIn= SQLite.selectCountOf().from(Users.class).where(Users_Table.username.eq(username)).and(Users_Table.password.eq(Functions.md5(password))).hasData();
        Users user = SQLite.select().from(Users.class).where(Users_Table.username.eq(username)).and(Users_Table.password.eq(Functions.md5(password))).querySingle();

        if (isLoggedIn){
            session.createLoginSession(user.id,user.nombre);
            goToMain();
        }else{
            Toast.makeText(this, getResources().getString(R.string.tryregister), Toast.LENGTH_LONG).show();
        }
        return isLoggedIn;
    }

}
