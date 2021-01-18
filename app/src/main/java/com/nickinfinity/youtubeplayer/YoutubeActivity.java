package com.nickinfinity.youtubeplayer;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class YoutubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    static final String GOOGLE_API_KEY = "AIzaSyC3L0kFoJ_GJ08Sojpbf2RUh4r3UnjIxcc";
    static final String YT_VIDEO_ID = "M7XM597XO94";
    static final String YT_PLAYLIST = "PLJ7A97yl9oXqruomJvjLeeK-8hvjUL4V2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflating views using java code instead of xml
        // Dynamically
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        YouTubePlayerView playerView = new YouTubePlayerView(this); // passing the context in every dynamically created view is imp
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // adding the Youtube Player View to layout file
        layout.addView(playerView);

        //initializing the playerView
        playerView.initialize(GOOGLE_API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
    final int REQUEST_CODE = 1;
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show();
        }else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)",youTubeInitializationResult.toString());
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
        }
    }
}
