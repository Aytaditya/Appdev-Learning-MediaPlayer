package com.aditya.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //The scope of the player variable is limited to the MainActivity class
    MediaPlayer player;
    AudioManager audioManager;

    //function already defined in MediaPlayer class hence making it very easy
    //for playing music
    public void play(View view){
        player.start();
    }
    //function for pausing music
    public void pause(View view){
        player.pause();
    }
    public void stop(View view){
        //after pressing stop you have to kill your app and start again
        player.stop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // we are associating player object to this particular music
        player=MediaPlayer.create(this,R.raw.music5);

    //controlling seekbar
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar seekVol=findViewById(R.id.seekVol);
        seekVol.setMax(maxVol);
        seekVol.setProgress(curVol);

        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //on change
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //on start

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //when left

            }
        });


        SeekBar seekProg=findViewById(R.id.seekProg);
        seekProg.setMax(player.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
                                            @Override
                                            public void run() {
                            seekProg.setProgress(player.getCurrentPosition());
                                            }
                                        },0,900);

                seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        player.seekTo(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

    }
}