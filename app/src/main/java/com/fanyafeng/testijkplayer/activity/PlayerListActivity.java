package com.fanyafeng.testijkplayer.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.BaseActivity;
import com.fanyafeng.testijkplayer.adapter.PlayerListAdapter;
import com.fanyafeng.testijkplayer.adapter.VideoListAdapter;
import com.fanyafeng.testijkplayer.bean.PlayerListBean;
import com.fanyafeng.testijkplayer.view.MyItemAnimator;
import com.fanyafeng.testijkplayer.view.NestedRecyclerView;
import com.fanyafeng.testijkplayer.widget.media.IjkVideoView;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class PlayerListActivity extends BaseActivity {
    private NestedRecyclerView rvVideoList;
    private List<PlayerListBean> playerListBeanList = new ArrayList<>();
    private PlayerListAdapter videoListAdapter;
    private int currentVideoPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_player_list);


        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });
    }

    //初始化UI空间
    private void initView() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");


        for (int i = 0; i < 10; i++) {
            PlayerListBean playerListBean = new PlayerListBean();
            playerListBean.setPlayingCode(i != 0 ? 0 : 1);
            playerListBean.setVideoUrl("http://www.jmzsjy.com/UploadFile/%E5%BE%AE%E8%AF%BE/%E5%9C%B0%E6%96%B9%E9%A3%8E%E5%91%B3%E5%B0%8F%E5%90%83%E2%80%94%E2%80%94%E5%AE%AB%E5%BB%B7%E9%A6%99%E9%85%A5%E7%89%9B%E8%82%89%E9%A5%BC.mp4");
            playerListBean.setVideoImgUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473222722&di=f11b88a676fefa7e8ac601000ae9e231&src=http://p3.image.hiapk.com/uploads/allimg/141124/7730-141124100258.jpg");
//            playerListBean.setVideoImgUrl("http://img4.imgtn.bdimg.com/it/u=1712960935,1906288017&fm=21&gp=0.jpg");
            playerListBean.setDetail("2015年12月5日 - 心形符号一向被认为是爱情的象征，代表着一颗真心。 心形符号大全：♡♡ღ ❤ ❣❥❤♥. 上面是单个的纯心形符号图案，可以复制，可以作为网名来 ...");
            playerListBean.setLove("♡000" + i);
            playerListBean.setEvaluate("✎评论");
            playerListBean.setShare("⎋分享");
            playerListBeanList.add(playerListBean);
        }

        rvVideoList = (NestedRecyclerView) findViewById(R.id.rvVideoList);
        rvVideoList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    //初始化数据
    private void initData() {
        videoListAdapter = new PlayerListAdapter(this, playerListBeanList);
        rvVideoList.setAdapter(videoListAdapter);
        rvVideoList.setItemAnimator(new MyItemAnimator());

        rvVideoList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                getScollYDistance();
                int firstPosition = ((LinearLayoutManager) rvVideoList.getLayoutManager()).findFirstVisibleItemPosition();
                int lastPosition = ((LinearLayoutManager) rvVideoList.getLayoutManager()).findLastVisibleItemPosition();

                int firstCompletePosition = ((LinearLayoutManager) rvVideoList.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                int lastCompletePosition = ((LinearLayoutManager) rvVideoList.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                View currentView = null;
                View firstView = rvVideoList.getLayoutManager().findViewByPosition(firstPosition);
                if (firstCompletePosition != -1) {
                    currentView = rvVideoList.getLayoutManager().findViewByPosition(firstCompletePosition);
                }
                View lastView = rvVideoList.getLayoutManager().findViewByPosition(lastPosition);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && videoListAdapter.getItemCount() > 0) {
                    ((LinearLayoutManager) rvVideoList.getLayoutManager()).findLastVisibleItemPosition();
                    //没有的话为-1
                    if (firstCompletePosition != -1) {
                        if (currentView != null && firstCompletePosition != currentVideoPosition) {
                            currentVideoPosition = firstCompletePosition;
                            ((PlayerListBean) currentView.getTag()).setPlayingCode(1);
                            videoListAdapter.notifyItemChanged(firstCompletePosition);
                        }
                    } else {

                    }

                    if (firstView != null) {
                        if (firstPosition != currentVideoPosition) {
                            ((PlayerListBean) firstView.getTag()).setPlayingCode(0);
                            ((IjkVideoView) firstView.findViewById(R.id.videoView)).release(true);
                            videoListAdapter.notifyItemChanged(firstPosition);
                        }
                    }
                    if (lastView != null) {
                        if (lastPosition != currentVideoPosition) {
                            ((PlayerListBean) lastView.getTag()).setPlayingCode(0);
                            ((IjkVideoView) lastView.findViewById(R.id.videoView)).release(true);
                            videoListAdapter.notifyItemChanged(lastPosition);
                        }
                    }
                }
                Log.d("playerlist", "播放状态：" + ((PlayerListBean) firstView.getTag()).getPlayingCode());

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING && videoListAdapter.getItemCount() > 0) {

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }

    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rvVideoList.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        View lastVisiableChildView = layoutManager.findViewByPosition(lastPosition);
        int itemHeight = firstVisiableChildView.getHeight();
//        Log.d("nestrecycle", "first distance: " + firstVisiableChildView.getBottom() + " | " + itemHeight + "|" + position);
        Log.d("nestrecycle", "last distance: " + lastVisiableChildView.getTop()+ " | " + itemHeight + "|" + lastPosition);
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    public void onBackPressed() {
        stopVideo();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopVideo();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startVideo();
    }

    private void startVideo() {
        View view = rvVideoList.getLayoutManager().findViewByPosition(currentVideoPosition);
        if (view.getTag() != null)
            ((PlayerListBean) view.getTag()).setPlayingCode(1);
        videoListAdapter.notifyItemChanged(currentVideoPosition);
    }

    private void stopVideo() {
        for (int i = 0; i < playerListBeanList.size(); i++) {
            Log.d("playerlist", "播放code：" + playerListBeanList.get(i).getPlayingCode());
            int code = playerListBeanList.get(i).getPlayingCode();
            if (code == 1) {
                View view = rvVideoList.getLayoutManager().findViewByPosition(i);
                if (view.getTag() != null)
                    ((PlayerListBean) view.getTag()).setPlayingCode(0);
                ((IjkVideoView) view.findViewById(R.id.videoView)).release(true);
                videoListAdapter.notifyItemChanged(i);
            }
        }
    }
}
