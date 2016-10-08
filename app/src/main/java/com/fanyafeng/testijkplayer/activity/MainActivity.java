package com.fanyafeng.testijkplayer.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.BaseActivity;
import com.fanyafeng.testijkplayer.util.PermissionControl;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
//        title = getString(R.string.title_activity_main);
        isSetNavigationIcon = false;
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar_center_title.setText(getString(R.string.title_activity_main));
    }

    //初始化UI空间
    private void initView() {

    }

    //初始化数据
    private void initData() {
//        requestPermission();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnGesture:
                startActivity(new Intent(this, GestureActivity.class));
                break;
            case R.id.btnPlayer:
                startActivity(new Intent(this, PlayerActivity.class));
                break;
            case R.id.btnPlayerList:
                startActivity(new Intent(this, PlayerListActivity.class));
                break;
            case R.id.btnVideoList:
                startActivity(new Intent(this, VideoListActivity.class));
                break;
            case R.id.btnDiffent:
                startActivity(new Intent(this, DiffentListActivity.class));
                break;
        }
    }

    private void requestPermission() {
        if (PermissionControl.isGetPermissionFor(this, Manifest.permission.WRITE_SETTINGS)) {
            initData();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_SETTINGS}, PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData();
                } else {
                    Toast.makeText(this, "请求被拒绝，应用无法进行相应操作", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
