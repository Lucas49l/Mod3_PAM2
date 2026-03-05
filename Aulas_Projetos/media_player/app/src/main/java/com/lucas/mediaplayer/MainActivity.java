package com.lucas.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SeekBar volumebar;
    private AudioManager audioManager;

    ImageButton pause, stop, play, next;
    TextView text;
    private String music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.black);
        music = "Black";

        initComponents();
        atualizarVolume();

        play.setOnClickListener(V -> {
            executarSom();
        });
        pause.setOnClickListener(V-> {
            pausarSom();
        });
        stop.setOnClickListener(V->{
            pararSom();
        });
        next.setOnClickListener(V->{
            proximoSom();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initComponents() {
        text      = findViewById(R.id.txt_text);
        play      = findViewById(R.id.btnPlay);
        pause     = findViewById(R.id.btnPause);
        stop      = findViewById(R.id.btnStop);
        next      = findViewById(R.id.btnNext);
        volumebar = findViewById(R.id.volumeBar);
    }


    public void executarSom(){
        if( mediaPlayer != null){
            mediaPlayer.start();
            text.setText(String.format("%s tocando", music));
        }
    }
    public void pausarSom(){
        if( mediaPlayer.isPlaying() ){
            mediaPlayer.pause();
            text.setText(String.format("%s em pause", music));
        }
    }
    public void pararSom() {
        if (mediaPlayer.isPlaying() ){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, R.raw.black);
            text.setText(String.format("%s em STOP", music));
        }
    }

    public void proximoSom(){
        if(music.equals("Nothing")){
            mediaPlayer = MediaPlayer.create(this, R.raw.black);
            music = "Black";
        }else{
            mediaPlayer = MediaPlayer.create(this, R.raw.nothing);
            music = "Nothing";
        }
        mediaPlayer.start();
        text.setText(String.format("%s tocando", music));
    }

    public void atualizarVolume(){
        audioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volume    = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumebar.setMax(maxVolume);
        volumebar.setProgress(volume);

        volumebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

}