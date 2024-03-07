package com.aditya.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //The scope of the player variable is limited to the MainActivity class
    MediaPlayer player;

    //function already defined in MediaPlayer class hence making it very easy
    //for playing music
    public void play(View view){
        player.start();
    }
    //function for pausing music
    public void pause(View view){
        player.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // we are associating player object to this particular music
        player=MediaPlayer.create(this,R.raw.music5);
    }
}