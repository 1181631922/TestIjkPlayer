package com.fanyafeng.testijkplayer.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.BaseActivity;
import com.fanyafeng.testijkplayer.util.MyUtils;
import com.fanyafeng.testijkplayer.widget.media.AndroidMediaController;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class GestureActivity extends BaseActivity implements GestureDetector.OnGestureListener, SensorEventListener {
    private GestureDetector gestureDetector;

    private LinearLayout layoutGesture;

    private int screenWidth;

    private int screenBrightness;
    private int screenMode;

    private AudioManager audioManager;
    private AssetManager assetManager;
    private int soundAudio;
    private int soundMode;

    private AndroidMediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_gesture);

        screenWidth = MyUtils.getScreenWidth(this);

        mediaController = new AndroidMediaController(this, false);
        try {
            screenBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            screenMode = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        soundAudio = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundMode = audioManager.getMode();

        initView();
        initData();
    }


    //初始化UI空间
    private void initView() {
        gestureDetector = new GestureDetector(this, this);

        layoutGesture = (LinearLayout) findViewById(R.id.layoutGesture);
        layoutGesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    //初始化数据
    private void initData() {

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        Log.d("gesture", "起点位置：" + e1.getX() + "终点位置：" + e2.getX());
//        Log.d("gesture", "distanceX:" + distanceX + "distanceY:" + distanceY);
//        Log.d("gesture", "屏幕宽度：" + screenWidth);

        //屏幕一分为二
        //左侧控制亮度
        if (e1.getX() < screenWidth >> 1) {
//            Log.d("gesture", "用户触摸到屏幕左侧,上下互动可以控制屏幕亮度");
//            Log.d("gesture", "亮度用户手指滑动的距离：" + (e1.getY() - e2.getY()));
            int tempScreenBrightness = 0;
            try {
                tempScreenBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            int screenChange = (int) ((e1.getY() - e2.getY()) + tempScreenBrightness);
            if (screenChange > 0 && screenChange <= 255) {
                setScreenBrightness(screenChange);
            }

        }

        //右侧控制声音
        if (e1.getX() > screenWidth >> 1) {
//            Log.d("gesture", "用户触摸到屏幕右侧,上下滑动可以控制屏幕声音");
//            Log.d("gesture", "声音用户手指滑动的距离：" + (e1.getY() - e2.getY()));
            int tempSoundAudio = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            int soundChange = (int) ((e1.getY() - e2.getY()) / 100 + tempSoundAudio);
            if (soundChange >= 0 && soundChange <= 15) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, soundChange, AudioManager.FLAG_PLAY_SOUND);
            }
        }
        return true;
    }

    private void setScreenMode(int value) {
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, value);
    }

    private void setScreenBrightness(int value) {
        Window w = getWindow();
        WindowManager.LayoutParams l = w.getAttributes();
        l.screenBrightness = value;
        w.setAttributes(l);
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, value);
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    //屏幕方向控制监听方法
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
