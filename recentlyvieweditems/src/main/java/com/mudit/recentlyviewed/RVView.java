package com.mudit.recentlyviewed;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mudit.recentlyviewed.interfaces.BaseRVBehaviour;
import com.mudit.recentlyviewed.interfaces.GoToPdp;
import com.mudit.recentlyviewed.interfaces.IActions;
import com.mudit.recentlyviewed.interfaces.LoadImageOnView;

/**
 * Created by mudit on 26/02/18.
 */

public class RVView {

    private RVViewHolder rvViewHolder;
    private BaseRVBehaviour baseRV;
    private IActions iActions;

    private LoadImageOnView loadImageOnView;

    private GoToPdp goToPdp;

    private RVView(Context context, BaseRVBehaviour baseRV, String title, String subtitle, LoadImageOnView loadImageOnView, GoToPdp goToPdp){
        this.baseRV = baseRV;
        this.loadImageOnView =loadImageOnView;
        rvViewHolder = new RVViewHolder(context);
        rvViewHolder.setTitle(title);
        rvViewHolder.setSubTitle(subtitle);
        this.goToPdp = goToPdp;

        EventFactory.getInstance().registerEvent(EventFactory.IMAGE_LOAD_EVENT, new LoadImageOnView() {
            @Override
            public void performAction(ProductModel productModel) {
                //todo nothing
            }

            @Override
            public void onLoadRequest(String url, ImageView imageView) {
                if(RVView.this.loadImageOnView !=null){
                    RVView.this.loadImageOnView.onLoadRequest(url,imageView);
                }
            }
        });

        EventFactory.getInstance().registerEvent(EventFactory.GOTO_PDP, new GoToPdp() {
            @Override
            public void performAction(ProductModel productModel) {
                if(RVView.this.goToPdp !=null){
                    RVView.this.goToPdp.performAction(productModel);
                }
            }
        });
    }

    public void setTitleTypeface(Typeface titleTypeface){
        if(titleTypeface!=null){
            rvViewHolder.getTvTitle().setTypeface(titleTypeface);
        }
    }

    public void setActions(IActions iActions) {
        this.iActions = iActions;
        rvViewHolder.setActions(iActions);
    }

    public void attachToView(ViewGroup parent){
        parent.removeView(rvViewHolder.getView());
        parent.addView(rvViewHolder.getView());

    }

    public void detachFromView(ViewGroup parent){
        parent.removeView(rvViewHolder.getView());
    }

    public void refresh(){
        if(baseRV.getProductCount() > 0){
            rvViewHolder.updateView(baseRV.getAllRows());
        }else{
            rvViewHolder.getView().setVisibility(View.GONE);
        }
    }


    public static class RVViewBuilder{

        private Context context;
        private BaseRVBehaviour rvBehaviour;


        //Optional Parameters
        private LoadImageOnView loadImageOnView;

        private GoToPdp goToPdp;

        private String title;

        private String subtitle;


        public RVViewBuilder(Context context, BaseRVBehaviour rvBehaviour){
            this.context =context;
            this.rvBehaviour = rvBehaviour;
        }

        public RVViewBuilder setLoadImageOnView(LoadImageOnView loadImageOnView) {
            this.loadImageOnView = loadImageOnView;
            return this;
        }

        public RVViewBuilder setTitle(String title){
            this.title =title;
            return this;
        }

        public RVViewBuilder setSubtitle(String subTitle){
            this.subtitle = subTitle;
            return this;
        }

        public RVViewBuilder setGoToPdp(GoToPdp goToPdp) {
            this.goToPdp = goToPdp;
            return this;
        }

        public RVView build(){
            return new RVView(context,rvBehaviour,title,subtitle,loadImageOnView,goToPdp);
        }
    }



}
