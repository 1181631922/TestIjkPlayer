package com.fanyafeng.testijkplayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author： fanyafeng
 * Data： 16/7/8 10:36
 * Email: fanyafeng@live.cn
 */
public class NestedRecyclerView extends RecyclerView {
    private OnScrollListener onScrollListener;

    public NestedRecyclerView(Context context) {
        super(context);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.onScrollListener = scrollListener;
    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.OnScroll(l, t, oldl, oldt);
        }
    }

    public interface OnScrollListener {
        public void OnScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }

    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) this.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

}
