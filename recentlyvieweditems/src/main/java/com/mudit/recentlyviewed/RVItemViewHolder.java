package com.mudit.recentlyviewed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mudit.recentlyviewed.interfaces.ViewBinder;

/**
 * Created by mudit on 02/02/18.
 */

class RVItemViewHolder extends RecyclerView.ViewHolder implements ViewBinder {

    private ImageView ivProductImage;
    private TextView tvBrandName;
    private TextView tvProductName;
    private TextView tvPrice;
    private TextView tvSpecialPrice;
    private TextView tvDiscount;

    public RVItemViewHolder(View itemView) {
        super(itemView);
        tvProductName = (TextView) itemView.findViewById(R.id.tv_itemTitle);
        tvBrandName = (TextView) itemView.findViewById(R.id.tv_itemSubtitle);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_itemPrice);
        ivProductImage = (ImageView) itemView.findViewById(R.id.iv_itemImage);
        tvSpecialPrice = (TextView) itemView.findViewById(R.id.tv_itemLinePrice);
        tvDiscount = (TextView) itemView.findViewById(R.id.it_itemDiscount);
    }

    @Override
    public void onBind(final BaseProductModel baseProductModel) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventFactory.getInstance().fireEvent(EventFactory.GOTO_PDP, (ProductModel) baseProductModel);
            }
        });
        tvBrandName.setText(baseProductModel.getBrandName());
        tvPrice.setText(baseProductModel.getPrice());
        tvProductName.setText(baseProductModel.getName());
        tvDiscount.setText(baseProductModel.getDiscount());
        tvSpecialPrice.setText(baseProductModel.getSpecialPrice());
        EventFactory.getInstance().fireEvent(EventFactory.IMAGE_LOAD_EVENT,baseProductModel.getImageUrl(),ivProductImage);
//        loadImageOnView.onLoadRequest(baseProductModel.getImageUrl(),ivProductImage);
    }
}
