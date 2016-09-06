package com.fanyafeng.testijkplayer.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.BaseActivity;
import com.fanyafeng.testijkplayer.fragment.TracksFragment;
import com.fanyafeng.testijkplayer.widget.media.AndroidMediaController;
import com.fanyafeng.testijkplayer.widget.media.IjkVideoView;

import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class PlayerActivity extends BaseActivity implements TracksFragment.ITrackHolder {
    private boolean backPressed;
    private IjkVideoView videoView;
    private TextView tvVideoInfo;
    private TextView tvPlayTime;

    private AndroidMediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        title = getString(R.string.title_activity_player);
        mediaController = new AndroidMediaController(this, false);

        initView();
        initData();
    }


    //初始化UI空间
    private void initView() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        tvVideoInfo = (TextView) findViewById(R.id.tvVideoInfo);
        videoView = (IjkVideoView) findViewById(R.id.videoView);
        tvPlayTime = (TextView) findViewById(R.id.tvPlayTime);
    }

    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tvVideoInfo.setText("duration:" + videoView.getDuration() + "\n");
                    tvVideoInfo.append("currentPosition:" + videoView.getCurrentPosition() + "\n");
                    updateTextViewWithTimeFormat(tvPlayTime, videoView.getCurrentPosition()/1000);
                    uiHandler.sendEmptyMessageDelayed(0, 200);
                    break;
            }
        }
    };

    //初始化数据
    private void initData() {
        videoView.setMediaController(mediaController);
        videoView.setVideoPath("http://www.jmzsjy.com/UploadFile/%E5%BE%AE%E8%AF%BE/%E5%9C%B0%E6%96%B9%E9%A3%8E%E5%91%B3%E5%B0%8F%E5%90%83%E2%80%94%E2%80%94%E5%AE%AB%E5%BB%B7%E9%A6%99%E9%85%A5%E7%89%9B%E8%82%89%E9%A5%BC.mp4");
        videoView.start();
        uiHandler.sendEmptyMessageDelayed(0, 200);

        tvVideoInfo.setText("duration:" + videoView.getDuration() + "\n");
        tvVideoInfo.append("currentPosition:" + videoView.getCurrentPosition() + "\n");
        tvVideoInfo.append("tTextDirection" + videoView.getTextDirection() + "\n");
    }

    @Override
    public void onBackPressed() {
        backPressed = true;
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnForword:
                // TODO: 16/9/6
                break;
            case R.id.btnStop:
                videoView.pause();
                break;
            case R.id.btnGoOn:
                videoView.start();
                break;
            case R.id.btnNext:
                break;
            case R.id.btnQuickBack:
                break;
            case R.id.btnQuickForword:
                break;
            case R.id.btnGetCurrent:
                tvVideoInfo.append("当前进度：" + videoView.getCurrentPosition() + "\n");
                tvVideoInfo.append("duration:" + videoView.getDuration() + "\n");
                tvVideoInfo.append("currentPosition:" + videoView.getCurrentPosition() + "\n");
                tvVideoInfo.append("tTextDirection" + videoView.getTextDirection() + "\n");
                break;
        }
    }

    private void updateTextViewWithTimeFormat(TextView textView, int second) {
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String stringTemp = null;
        if (0 != hh) {
            stringTemp = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            stringTemp = String.format("%02d:%02d", mm, ss);
        }
        textView.setText(stringTemp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoView.getCurrentPosition() > 0) {
            videoView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (backPressed || videoView.isBackgroundPlayEnabled()) {
            videoView.stopPlayback();
            videoView.release(true);
            videoView.stopBackgroundPlay();
        } else {
            videoView.stopBackgroundPlay();
        }
        videoView.pause();
        IjkMediaPlayer.native_profileEnd();
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
