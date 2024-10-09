package com.example.reproductora;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.constraintlayout.widget.ConstraintLayout;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    Button play_pause, btn_repetir;
    int repetir = 2, posicion = 0;

    MediaPlayer vectormp [] = new MediaPlayer[3];
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_repetir = (Button)findViewById(R.id.btn_repetir);
        play_pause = (Button)findViewById(R.id.btn_reproducir);
        vectormp [0] = MediaPlayer.create(this, R.raw.race);
        vectormp [1] = MediaPlayer.create(this, R.raw.sound);
        vectormp [2] = MediaPlayer.create(this, R.raw.tea);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;




        });
    }

    //Botón de Pausa

    public void PlayPause(View view){
        if (vectormp[posicion].isPlaying()){
            vectormp[posicion].pause();
            Toast.makeText(this,"Pausar", Toast.LENGTH_SHORT).show();
        } else {
         vectormp[posicion].start();
            Toast.makeText(this,"Reproduciendo", Toast.LENGTH_SHORT).show();
        }
    }

    //Boton de detener
    public void Stop(View view){
        if(vectormp[posicion] != null){
                vectormp[posicion].stop();

            vectormp [0] = MediaPlayer.create(this, R.raw.race);
            vectormp [1] = MediaPlayer.create(this, R.raw.sound);
            vectormp [2] = MediaPlayer.create(this, R.raw.tea);
            posicion=0;
            Toast.makeText(this,"Detener", Toast.LENGTH_SHORT).show();
    }

    }

    //Boton para repetir
    public void Repetir(View view){
        if (repetir == 1){
            vectormp[posicion].setLooping(false);
            repetir = 2;
        } else {
            vectormp[posicion].setLooping(true);
            repetir = 1;
        }
    }

    //Boton para saltar a la siguiente cancion
    public void Siguiente(View view){
        if (posicion < vectormp.length -1){

            if (vectormp[posicion].isPlaying()){
            posicion++;
            vectormp[posicion].start();
            } else {
                posicion++;
            }

        } else {
            Toast.makeText(this,"No hay canciones", Toast.LENGTH_SHORT).show();
        }
    }

    //Boton para regresar a la cancion anterior
    public void Anterior(View view){
        if (posicion >= 1){

            if (vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();

                vectormp [0] = MediaPlayer.create(this, R.raw.race);
                vectormp [1] = MediaPlayer.create(this, R.raw.sound);
                vectormp [2] = MediaPlayer.create(this, R.raw.tea);
                posicion--;

                vectormp[posicion].start();
            } else {
                posicion--;
            }

        } else {

                Toast.makeText(this,"No hay canciones", Toast.LENGTH_SHORT).show();

        }
    }

}