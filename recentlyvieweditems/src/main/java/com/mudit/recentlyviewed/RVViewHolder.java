package com.mudit.recentlyviewed;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mudit.recentlyviewed.interfaces.IActions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mudit on 24/01/18.
 *
 * RecentlyView
 */

class RVViewHolder {

    private View rootView;
    private RecyclerView rvItemList;
    private TextView tvTitle;
    private TextView tvSubtitle;
    private boolean isTitleInCenter;
    private TextView tvActionButton;
    private RVAdapter recentlyViewAdapter;
    private IActions iActions;

    public RVViewHolder(Context context){
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_recently_view,null,false);
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        tvSubtitle = (TextView) rootView.findViewById(R.id.tv_subtitle);
        rvItemList = (RecyclerView) rootView.findViewById(R.id.rv_itemList);
        tvActionButton = (TextView) rootView.findViewById(R.id.tv_actionButton);
        rvItemList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        recentlyViewAdapter = new RVAdapter(new ArrayList<ProductModel>());
        rvItemList.setAdapter(recentlyViewAdapter);
    }

    public View getView() {
        return rootView;
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setSubTitle(String subTitle){
        tvSubtitle.setText(subTitle);
    }

    public void setActionButtonText(String text){
        tvActionButton.setText(text);
    }

    public void setTitleInCenter(boolean titleInCenter) {
        isTitleInCenter = titleInCenter;
    }

    public void updateView(List<? extends BaseProductModel> baseProductModels){
        recentlyViewAdapter.setBaseProductModels(baseProductModels);
        recentlyViewAdapter.notifyDataSetChanged();
    }

    public RecyclerView getRvItemList() {
        return rvItemList;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvSubtitle() {
        return tvSubtitle;
    }

    public void setActions(IActions iActions) {
        this.iActions = iActions;
    }
}
