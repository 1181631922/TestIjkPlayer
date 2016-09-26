package com.fanyafeng.testijkplayer.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.bean.PlayerListBean;
import com.fanyafeng.testijkplayer.util.frscoutil.FrescoConfig;
import com.fanyafeng.testijkplayer.widget.media.IjkVideoView;

import java.util.List;

/**
 * Author： fanyafeng
 * Data： 16/9/19 17:36
 * Email: fanyafeng@live.cn
 */
public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder> {
    private Context context;
    private List<PlayerListBean> playerListBeanList;

    public PlayerListAdapter(Context context, List<PlayerListBean> playerListBeanList) {
        this.context = context;
        this.playerListBeanList = playerListBeanList;
    }

    @Override
    public PlayerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_player_list_layout, parent, false);
        return new PlayerListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerListViewHolder holder, int position) {
        PlayerListBean playerListBean = playerListBeanList.get(position);
        holder.videoView.setVideoPath(playerListBean.getVideoUrl());
        holder.tvDetail.setText(playerListBean.getDetail());
        holder.tvLove.setText(playerListBean.getLove());
        holder.tvEvaluate.setText(playerListBean.getEvaluate());
        holder.tvShare.setText(playerListBean.getShare());
        ImageRequest imageRequest = FrescoConfig.getDraweeViewImageRequest(holder.sdvVideo, playerListBean.getVideoImgUrl());
        DraweeController draweeController = FrescoConfig.getSimpleDraweeController(imageRequest, holder.sdvVideo);
        holder.sdvVideo.setController(draweeController);
        holder.itemView.setTag(playerListBean);

        holder.videoView.setVisibility(View.GONE);
        holder.sdvVideo.setVisibility(View.VISIBLE);

        if (playerListBean.getPlayingCode() == 0) {
            holder.videoView.setVisibility(View.GONE);
            holder.videoView.release(true);
            holder.sdvVideo.setVisibility(View.VISIBLE);
        } else {
            holder.videoView.setVisibility(View.VISIBLE);
            holder.sdvVideo.setVisibility(View.INVISIBLE);
            holder.videoView.seekTo(0);
            holder.videoView.start();
        }
    }

    @Override
    public int getItemCount() {
        return playerListBeanList.size();
    }

    class PlayerListViewHolder extends RecyclerView.ViewHolder implements ViewPropertyAnimatorListener {

        RelativeLayout layoutHolder;
        IjkVideoView videoView;
        SimpleDraweeView sdvVideo;
        TextView tvDetail;
        TextView tvLove;
        TextView tvEvaluate;
        TextView tvShare;

        public PlayerListViewHolder(View itemView) {
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
            videoBeginning();
            videoView.start();
        }

        public void deactivate() {
            videoView.pause();
            videoView.release(true);
            videoStopped();
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
            view.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onAnimationCancel(View view) {

        }
    }
}
