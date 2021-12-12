package com.android.media.mixplayer;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.android.media.mixplayer.media.AndroidMediaController;
import com.android.media.mixplayer.media.IjkVideoView;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

        setContentView(R.layout.activity_main);

        File externalStorageDirectory = Environment.getExternalStorageDirectory();  //目录："/storage/emulated/0"
        String path = externalStorageDirectory.getAbsolutePath() + File.separator + "mvtest.mp4";
        path = externalStorageDirectory.getAbsolutePath() + File.separator + "DCIM/Camera/test.mp4";

        IjkVideoView ijkVideoView = findViewById(R.id.ijk_video_view);
        AndroidMediaController controller = new AndroidMediaController(this);
        ijkVideoView.setMediaController(controller);

        Log.e(TAG,"setVideoPath:" + path);

        ijkVideoView.setVideoURI(Uri.parse(path));
        ijkVideoView.start();
    }
}