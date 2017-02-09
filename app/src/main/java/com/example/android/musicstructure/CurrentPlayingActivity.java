package com.example.android.musicstructure;

import android.content.ContentUris;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CurrentPlayingActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    TextView titl;
    Long songId;
    Button stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_playing);
        titl = (TextView) findViewById(R.id.title);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String title = extras.getString("Title");
        songId = extras.getLong("Id");
        titl.setText(title);
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                songId);
        try {
            mediaPlayer = mediaPlayer.create(this, trackUri);
            mediaPlayer.start();
        } catch (Exception e) {
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                finish();
            }
        });
    }
}
