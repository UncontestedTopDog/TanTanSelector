package com.example.administrator.tantanselector.Selector;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.administrator.tantanselector.Data;
import com.example.administrator.tantanselector.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class TanTanSelectorAdapter extends RecyclerView.Adapter {

    List<Data> datas = new ArrayList<>();

    public TanTanSelectorAdapter(List<Data> datas){
        this.datas =datas ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TanTanSelectorItem counterfeitMOMOSelectorItem = new TanTanSelectorItem(parent.getContext());
        return new CounterfeitMOMOSelectorViewHolder(counterfeitMOMOSelectorItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TanTanSelectorItem) holder.itemView).bindData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class CounterfeitMOMOSelectorViewHolder extends RecyclerView.ViewHolder {
        public ImageView like ;
        public ImageView unlike ;
        public ImageView verylike ;
        public CounterfeitMOMOSelectorViewHolder(View itemView) {
            super(itemView);
            like  = itemView.findViewById(R.id.like);
            unlike  = itemView.findViewById(R.id.unlike);
            verylike  = itemView.findViewById(R.id.very_like);
        }
    }
}
