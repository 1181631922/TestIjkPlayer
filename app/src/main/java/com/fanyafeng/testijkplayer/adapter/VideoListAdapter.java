package com.fanyafeng.testijkplayer.adapter;

import android.content.Context;
import android.provider.Settings;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.bean.PlayerListBean;
import com.fanyafeng.testijkplayer.util.FrescoUtil;
import com.fanyafeng.testijkplayer.util.frscoutil.FrescoConfig;
import com.fanyafeng.testijkplayer.widget.media.IjkVideoView;

import java.util.List;

/**
 * Author： fanyafeng
 * Data： 16/9/7 12:07
 * Email: fanyafeng@live.cn
 */
public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PlayerListBean> playerListBeanList;

    public VideoListAdapter(Context context, List<PlayerListBean> playerListBeanList) {
        this.context = context;
        this.playerListBeanList = playerListBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_player_list_layout, parent, false);
            return new VideoListViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_player_list_layout1, parent, false);
            return new VideoListViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PlayerListBean playerListBean = playerListBeanList.get(position);
        if (holder instanceof VideoListViewHolder) {
            ((VideoListViewHolder) holder).videoView.setVideoPath(playerListBean.getVideoUrl());
            ((VideoListViewHolder) holder).videoView.setVisibility(View.VISIBLE);
//            ((VideoListViewHolder) holder).videoView.start();
            ((VideoListViewHolder) holder).tvDetail.setText(playerListBean.getDetail());
            ((VideoListViewHolder) holder).tvLove.setText(playerListBean.getLove());
            ((VideoListViewHolder) holder).tvEvaluate.setText(playerListBean.getEvaluate());
            ((VideoListViewHolder) holder).tvShare.setText(playerListBean.getShare());
            ImageRequest imageRequest = FrescoConfig.getDraweeViewImageRequest(((VideoListViewHolder) holder).sdvVideo, playerListBean.getVideoImgUrl());
            DraweeController draweeController = FrescoConfig.getSimpleDraweeController(imageRequest, ((VideoListViewHolder) holder).sdvVideo);
            ((VideoListViewHolder) holder).sdvVideo.setController(draweeController);
        } else if (holder instanceof VideoListViewHolder1) {
            ((VideoListViewHolder1) holder).tvDetail.setText(playerListBean.getDetail());
            ((VideoListViewHolder1) holder).tvLove.setText(playerListBean.getLove());
            ((VideoListViewHolder1) holder).tvEvaluate.setText(playerListBean.getEvaluate());
            ((VideoListViewHolder1) holder).tvShare.setText(playerListBean.getShare());
            ImageRequest imageRequest = FrescoConfig.getDraweeViewImageRequest(((VideoListViewHolder1) holder).sdvVideo, playerListBean.getVideoImgUrl());
            DraweeController draweeController = FrescoConfig.getSimpleDraweeController(imageRequest, ((VideoListViewHolder1) holder).sdvVideo);
            ((VideoListViewHolder1) holder).sdvVideo.setController(draweeController);
        }
    }

    @Override
    public int getItemCount() {
        return playerListBeanList.size();
    }

    class VideoListViewHolder extends RecyclerView.ViewHolder implements ViewPropertyAnimatorListener {

        RelativeLayout layoutHolder;
        IjkVideoView videoView;
        SimpleDraweeView sdvVideo;
        TextView tvDetail;
        TextView tvLove;
        TextView tvEvaluate;
        TextView tvShare;

        public VideoListViewHolder(View itemView) {
            super(itemView);
            layoutHolder = (RelativeLayout) itemView.findViewById(R.id.layoutHolder);
            videoView = (IjkVideoView) itemView.findViewById(R.id.videoView);
            sdvVideo = (SimpleDraweeView) itemView.findViewById(R.id.sdvVideo);
            tvDetail = (TextView) itemView.findViewById(R.id.tvDetail);
            tvLove = (TextView) itemView.findViewById(R.id.tvLove);
            tvEvaluate = (TextView) itemView.findViewById(R.id.tvEvaluate);
            tvShare = (TextView) itemView.findViewById(R.id.tvShare);
        }

        private void cancelAlphaAnimate(View v) {
            ViewCompat.animate(v).cancel();
        }

        private void startAlphaAnimate(View v) {
            ViewCompat.animate(v).setListener(this).alpha(0f);
        }

        public void setActive() {

        }

        public void videoBeginning() {
            videoView.setAlpha(1.0f);
            cancelAlphaAnimate(sdvVideo);
            startAlphaAnimate(sdvVideo);
        }

        public void videoStopped() {
            cancelAlphaAnimate(sdvVideo);
            videoView.setAlpha(0);
            sdvVideo.setAlpha(1.f);
            sdvVideo.setVisibility(View.VISIBLE);
        }

        public void videoResourceReady(String videoPaht) {

        }

        @Override
        public void onAnimationStart(View view) {

        }

        @Override
        public void onAnimationEnd(View view) {
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View view) {

        }
    }

    class VideoListViewHolder1 extends RecyclerView.ViewHolder {

        RelativeLayout layoutHolder;
        //        IjkVideoView videoView;
        SimpleDraweeView sdvVideo;
        TextView tvDetail;
        TextView tvLove;
        TextView tvEvaluate;
        TextView tvShare;

        public VideoListViewHolder1(View itemView) {
            super(itemView);
            layoutHolder = (RelativeLayout) itemView.findViewById(R.id.layoutHolder);
//            videoView = (IjkVideoView) itemView.findViewById(R.id.videoView);
            sdvVideo = (SimpleDraweeView) itemView.findViewById(R.id.sdvVideo);
            tvDetail = (TextView) itemView.findViewById(R.id.tvDetail);
            tvLove = (TextView) itemView.findViewById(R.id.tvLove);
            tvEvaluate = (TextView) itemView.findViewById(R.id.tvEvaluate);
            tvShare = (TextView) itemView.findViewById(R.id.tvShare);
        }


    }
}
