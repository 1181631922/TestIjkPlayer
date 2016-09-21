package com.fanyafeng.testijkplayer.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

/**
 * Author： fanyafeng
 * Data： 16/9/7 11:48
 * Email: fanyafeng@live.cn
 */
public class PlayerListBean implements Parcelable {
    private String videoUrl;
    private String videoImgUrl;
    private String detail;
    private String love;
    private String evaluate;
    private String share;
    private int playingCode;//0非播放，1播放

    public PlayerListBean() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public int getPlayingCode() {
        return playingCode;
    }

    public void setPlayingCode(int playingCode) {
        this.playingCode = playingCode;
    }

    protected PlayerListBean(Parcel in) {
        videoUrl = in.readString();
        videoImgUrl = in.readString();
        detail = in.readString();
        love = in.readString();
        evaluate = in.readString();
        share = in.readString();
        playingCode = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoUrl);
        dest.writeString(videoImgUrl);
        dest.writeString(detail);
        dest.writeString(love);
        dest.writeString(evaluate);
        dest.writeString(share);
        dest.writeInt(playingCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlayerListBean> CREATOR = new Creator<PlayerListBean>() {
        @Override
        public PlayerListBean createFromParcel(Parcel in) {
            return new PlayerListBean(in);
        }

        @Override
        public PlayerListBean[] newArray(int size) {
            return new PlayerListBean[size];
        }
    };
}
