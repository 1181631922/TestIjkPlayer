package com.fanyafeng.testijkplayer.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.BaseActivity;
import com.fanyafeng.testijkplayer.fragment.TracksFragment;
import com.fanyafeng.testijkplayer.widget.media.IjkVideoView;

import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class PlayerActivity extends BaseActivity implements TracksFragment.ITrackHolder {
    private IjkVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_player);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //toolbar_center_title.setText(getString(R.string.title_activity_player));
    }

    //初始化UI空间
    private void initView() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");


        videoView = (IjkVideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("http://www.jmzsjy.com/UploadFile/%E5%BE%AE%E8%AF%BE/%E5%9C%B0%E6%96%B9%E9%A3%8E%E5%91%B3%E5%B0%8F%E5%90%83%E2%80%94%E2%80%94%E5%AE%AB%E5%BB%B7%E9%A6%99%E9%85%A5%E7%89%9B%E8%82%89%E9%A5%BC.mp4");
        videoView.start();
    }

    //初始化数据
    private void initData() {
//        IjkExoMediaPlayer
    }

    @Override
    public ITrackInfo[] getTrackInfo() {
        if (videoView == null)
            return null;
        return videoView.getTrackInfo();
    }

    @Override
    public int getSelectedTrack(int trackType) {
        if (videoView == null)
            return -1;
        return videoView.getSelectedTrack(trackType);
    }

    @Override
    public void selectTrack(int stream) {
        videoView.selectTrack(stream);
    }

    @Override
    public void deselectTrack(int stream) {
        videoView.deselectTrack(stream);
    }
}
