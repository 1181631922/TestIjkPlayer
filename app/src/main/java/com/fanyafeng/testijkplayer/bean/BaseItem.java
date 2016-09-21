package com.fanyafeng.testijkplayer.bean;

/**
 * Author： fanyafeng
 * Data： 16/9/13 14:41
 * Email: fanyafeng@live.cn
 */
public abstract class BaseItem {
    private final int viewType;

    public BaseItem(int viewType) {
        this.viewType = viewType;
    }

    public int getViewType() {
        return viewType;
    }
}
