package com.fanyafeng.testijkplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fanyafeng.testijkplayer.bean.BaseItem;

/**
 * Author： fanyafeng
 * Data： 16/9/13 14:47
 * Email: fanyafeng@live.cn
 */
public abstract class BaseViewHolder<T extends BaseItem> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position, T iItem);
}
