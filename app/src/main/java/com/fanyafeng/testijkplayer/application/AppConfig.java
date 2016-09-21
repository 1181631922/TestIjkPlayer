package com.fanyafeng.testijkplayer.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.fanyafeng.testijkplayer.util.frscoutil.FrescoConfig;

/**
 * Author： fanyafeng
 * Data： 16/9/7 11:41
 * Email: fanyafeng@live.cn
 */
public class AppConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this, FrescoConfig.getsImagePipelineConfig(this));
    }
}
