package com.fanyafeng.testijkplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.testijkplayer.R;
import com.fanyafeng.testijkplayer.bean.DiffentBean;

import java.util.List;

/**
 * Author： fanyafeng
 * Data： 16/10/8 10:55
 * Email: fanyafeng@live.cn
 */
public class DiffentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DiffentBean> diffentBeanList;

    public DiffentAdapter(Context context, List<DiffentBean> diffentBeanList) {
        this.context = context;
        this.diffentBeanList = diffentBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View oneView = LayoutInflater.from(context).inflate(R.layout.diffent_one_layout, parent, false);
            return new OneViewHolder(oneView);
        } else if (viewType == 1) {
            View twoView = LayoutInflater.from(context).inflate(R.layout.diffent_two_layout, parent, false);
            return new TwoViewHolder(twoView);
        } else {
            View threeView = LayoutInflater.from(context).inflate(R.layout.diffent_three_layout, parent, false);
            return new ThreeViewHolder(threeView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DiffentBean diffentBean = diffentBeanList.get(position);
        int type = diffentBean.getType();
        switch (type) {
            case 0:
                OneViewHolder oneViewHolder = (OneViewHolder) holder;
                oneViewHolder.tvOneTitle.setText(diffentBean.getTitle());
                break;
            case 1:
                TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
                twoViewHolder.sdvTwo.setImageURI(diffentBean.getPic());
                twoViewHolder.tvTwoTitle.setText(diffentBean.getTitle());
                twoViewHolder.tvTwoDesc.setText(diffentBean.getDesc());
                break;
            case 2:
                ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
                threeViewHolder.sdvThree.setImageURI(diffentBean.getPic());
                threeViewHolder.tvThreeTitle.setText(diffentBean.getTitle());
                threeViewHolder.tvThreeDesc.setText(diffentBean.getDesc());
                threeViewHolder.tvThreeNote.setText(diffentBean.getNote());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return diffentBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = diffentBeanList.get(position).getType();
        return type;
    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        TextView tvOneTitle;

        public OneViewHolder(View itemView) {
            super(itemView);
            tvOneTitle = (TextView) itemView.findViewById(R.id.tvOneTitle);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdvTwo;
        TextView tvTwoTitle;
        TextView tvTwoDesc;

        public TwoViewHolder(View itemView) {
            super(itemView);
            sdvTwo = (SimpleDraweeView) itemView.findViewById(R.id.sdvTwo);
            tvTwoTitle = (TextView) itemView.findViewById(R.id.tvTwoTitle);
            tvTwoDesc = (TextView) itemView.findViewById(R.id.tvTwoDesc);
        }
    }

    class ThreeViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdvThree;
        TextView tvThreeTitle;
        TextView tvThreeDesc;
        TextView tvThreeNote;

        public ThreeViewHolder(View itemView) {
            super(itemView);
            sdvThree = (SimpleDraweeView) itemView.findViewById(R.id.sdvThree);
            tvThreeTitle = (TextView) itemView.findViewById(R.id.tvThreeTitle);
            tvThreeDesc = (TextView) itemView.findViewById(R.id.tvThreeDesc);
            tvThreeNote = (TextView) itemView.findViewById(R.id.tvThreeNote);
        }
    }
}
