package com.fanyafeng.testijkplayer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author： fanyafeng
 * Data： 16/10/8 10:56
 * Email: fanyafeng@live.cn
 */
public class DiffentBean implements Parcelable {
    private int type;
    private String pic;
    private String title;
    private String desc;
    private String note;

    public DiffentBean() {
    }

    protected DiffentBean(Parcel in) {
        type = in.readInt();
        pic = in.readString();
        title = in.readString();
        desc = in.readString();
        note = in.readString();
    }

    public static final Creator<DiffentBean> CREATOR = new Creator<DiffentBean>() {
        @Override
        public DiffentBean createFromParcel(Parcel in) {
            return new DiffentBean(in);
        }

        @Override
        public DiffentBean[] newArray(int size) {
            return new DiffentBean[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "DiffentBean{" +
                "type=" + type +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", note='" + note + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(type);
        parcel.writeString(pic);
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(note);
    }
}
