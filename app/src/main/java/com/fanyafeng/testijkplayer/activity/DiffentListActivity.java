package com.fanyafeng.testijkplayer.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.BaseActivity;
import com.fanyafeng.testijkplayer.adapter.DiffentAdapter;
import com.fanyafeng.testijkplayer.bean.DiffentBean;

import java.util.ArrayList;
import java.util.List;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class DiffentListActivity extends BaseActivity {
    private List<DiffentBean> diffentBeanList = new ArrayList<>();
    private RecyclerView rvDiffent;
    private DiffentAdapter diffentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffent_list);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_diffent_list);

        initView();
        initData();
    }


    //初始化UI空间
    private void initView() {
        rvDiffent = (RecyclerView) findViewById(R.id.rvDiffent);
        rvDiffent.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        for (int i = 0; i < 20; i++) {
            DiffentBean diffentBean = new DiffentBean();
            diffentBean.setType(i % 3);
            diffentBean.setTitle("第" + i + "层");
            diffentBean.setPic("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQBmLLxKHEIzS3SpwPbjSE2yatwE2R40OUaRoM90LcRr6fIHQyAz_CXcTI");
            diffentBean.setDesc("这是描述");
            diffentBean.setNote("这是小标签");
            diffentBeanList.add(diffentBean);
        }
        diffentAdapter = new DiffentAdapter(this, diffentBeanList);
        rvDiffent.setAdapter(diffentAdapter);
    }

    //初始化数据
    private void initData() {

    }

}
