package com.dueeeke.dkplayer.activity.extend;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.dueeeke.dkplayer.R;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.exo.ExoMediaPlayerFactory;
import com.dueeeke.videoplayer.ijk.IjkPlayerFactory;
import com.dueeeke.videoplayer.player.AndroidMediaPlayerFactory;
import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.player.PlayerFactory;

/**
 * 多播放器切换
 * Created by Devlin_n on 2017/4/7.
 */

public class SwitchPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView mVideoView;
    private StandardVideoController mController;
    private static final String URL = "http://cdnxdc.tanzi88.com/XDC/dvideo/2017/12/29/fc821f9a8673d2994f9c2cb9b27233a3.mp4";
//    private static final String URL = "http://zaixian.jingpin88.com/20180430/IGBXbalb/index.m3u8";
//    private static final String URL = "rtmp://live.hkstv.hk.lxdns.com/live/hks";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_player);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.str_switch_player);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mVideoView = findViewById(R.id.player);
        findViewById(R.id.btn_ijk).setOnClickListener(this);
        findViewById(R.id.btn_media).setOnClickListener(this);
        findViewById(R.id.btn_exo).setOnClickListener(this);

        mController = new StandardVideoController(this);
        mVideoView.setUrl(URL);
        mVideoView.setVideoController(mController);
        mVideoView.start();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
//        AbstractPlayer player = null;
//        switch (id) {
//            case R.id.btn_ijk:
//                player = new IjkPlayer(this);
//                break;
//            case R.id.btn_media:
//                player = new AndroidMediaPlayer(this);
//                break;
//            case R.id.btn_exo:
//                player = new ExoMediaPlayer(this);
//                break;
//        }
//
//        mVideoView.release();
//        mVideoView.setUrl(URL);
//        mVideoView.setVideoController(mController);
//        mVideoView.setCustomMediaPlayer(player);
//        mVideoView.start();

        PlayerFactory factory = null;
        switch (id) {
            case R.id.btn_ijk:
                factory = IjkPlayerFactory.create(this);
                break;
            case R.id.btn_media:
                factory = AndroidMediaPlayerFactory.create(this);
                break;
            case R.id.btn_exo:
                factory = ExoMediaPlayerFactory.create(this);
                break;
        }

        mVideoView.release();
        mVideoView.setUrl(URL);
        mVideoView.setVideoController(mController);
        mVideoView.setPlayerFactory(factory);
        mVideoView.start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.release();
    }


    @Override
    public void onBackPressed() {
        if (!mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
