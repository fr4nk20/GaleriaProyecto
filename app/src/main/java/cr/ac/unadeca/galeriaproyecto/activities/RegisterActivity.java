package cr.ac.unadeca.galeriaproyecto.activities;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Session session;
    private ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resgistryview);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        image = findViewById(R.id.imageLogin);

        session = new Session(this);
        Button registrar = findViewById(R.id.register);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    validar(username.getText().toString(),password.getText().toString());
                    goToRegistrar(username.getText().toString(),password.getText().toString());

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


        Functions.loadImage("https://i.imgur.com/i3EA0oQ.jpg", image, this);
    }

    private  void goToMain(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    private void validar(String username, String password)throws Exception{
        if(username.isEmpty())
            throw new Exception("El nombre del usuario esta vacio");
        if(password.isEmpty())
            throw new Exception("La contraseña esta vacia");

    }

    private boolean goToRegistrar(String username, String password)throws Exception{
        boolean isLoggedIn= false;
        isLoggedIn = isLoggedIn = SQLite.selectCountOf().from(Users.class).where(Users_Table.username.eq(username)).and(Users_Table.password.eq(Functions.md5(password))).hasData();

        if (isLoggedIn){
            throw new Exception("EL usuario ya existe");
        }else{
            Users user = new Users();
            user.nombre=username;
            user.username=username;
            user.password=Functions.md5(password);

            user.save();
            Session session = new Session(getApplicationContext());
            session.createLoginSession(user.id,user.nombre);
            goToMain();
        }
        return isLoggedIn;
    }
}
