package cr.ac.unadeca.galeriaproyecto.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import cr.ac.unadeca.galeriaproyecto.R;
import cr.ac.unadeca.galeriaproyecto.subclase.RunImage;
import cr.ac.unadeca.galeriaproyecto.util.Functions;

/**
 * Created by pc on 11/4/2018.
 */

public class ImagenGrande extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagengrande);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ImageView imageView = findViewById(R.id.image);
        TextView textView = findViewById(R.id.descripcion);

        RunImage image = new RunImage();
        if(!getIntent().getExtras().isEmpty()) {
            image.name = getIntent().getStringExtra("name");
            image.descripcion = getIntent().getStringExtra("author");
            image.url = getIntent().getStringExtra("url");

            Functions.loadImage(image.url, imageView, this);
            textView.setText("Autor : "+ image.descripcion +"\n" + "Nombre: "+ image.name );
        }else{
            Functions.loadImage( imageView, this);
            textView.setText("No se envio ninguna información" );
        }


    }
}
