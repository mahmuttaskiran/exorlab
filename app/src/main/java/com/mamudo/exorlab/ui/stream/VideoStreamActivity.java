package com.mamudo.exorlab.ui.stream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Util;
import com.mamudo.exorlab.R;
import com.mamudo.exorlab.databinding.ActivityStreamBinding;


public class VideoStreamActivity extends AppCompatActivity implements Player.EventListener {
    public static final String STREAM_URL = "stream_url";
    private static final String TAG = "VideoStreamActivity";

    private String streamUrl;
    private ActivityStreamBinding binding;
    private SimpleExoPlayer player;
    private DefaultTrackSelector trackSelector;
    private BandwidthMeter bandwidthMeter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stream);
        initiateFromIntent(getIntent());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initiatePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initiatePlayer();
        }
    }

    private void hideSystemUi() {
        binding.playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    private void initiatePlayer() {
        player = new SimpleExoPlayer.Builder(getBaseContext())
                .setTrackSelector(getTrackSelector())
                .build();
        player.addListener(this);
        player.setMediaItem(MediaItem.fromUri(streamUrl));
        player.setPlayWhenReady(true);
        player.prepare();
        binding.playerView.setPlayer(player);
    }

    private TrackSelector getTrackSelector() {
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
        trackSelector.setParameters(trackSelector.buildUponParameters().setPreferredTextLanguage("en"));
        trackSelector.setParameters(trackSelector.buildUponParameters().setRendererDisabled(C.TRACK_TYPE_TEXT, false));
        return trackSelector;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        player.release();
    }

    @Override
    public void onPlaybackStateChanged(int state) {
        if (state == Player.STATE_READY) {
            binding.setIsLoading(false);
        } else if (state == Player.STATE_BUFFERING) {
            binding.setIsLoading(true);
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        Log.i(TAG, "onPlayerError" + error.toString());
    }

    private void initiateFromIntent(Intent intent) {
        streamUrl = intent.getExtras().getString(STREAM_URL);
    }
}