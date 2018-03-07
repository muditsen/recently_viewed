package com.mudit.recentlyviewed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mudit on 02/02/18.
 */

class RVAdapter extends RecyclerView.Adapter<RVItemViewHolder> {

    private List<? extends BaseProductModel> productModels;

    public RVAdapter( List<? extends BaseProductModel> productModels){
        this.productModels = productModels;
    }

    public void setBaseProductModels(List<? extends BaseProductModel> baseProductModels) {
        this.productModels = baseProductModels;
    }

    @Override
    public RVItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recently_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RVItemViewHolder holder, int position) {
        holder.onBind(productModels.get(position));
    }

    @Override
    public int getItemCount() {
        return productModels == null ? 0 : productModels.size();
    }
}
