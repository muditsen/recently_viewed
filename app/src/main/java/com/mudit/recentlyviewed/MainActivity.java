package com.mudit.recentlyviewed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecentlyViewed recentlyViewed = new RecentlyViewed(this,RecentlyViewed.SQLITE);
        RVView rvView = new RVView.RVViewBuilder(this,recentlyViewed.getBaseRV()).build();
        rvView.attachToView((ViewGroup) findViewById(R.id.ll_rv));
        /*for(int i=0;i<10;i++){
            ProductModel productModel = new ProductModel();
            productModel.setSku("12312"+i);
            productModel.setName("RV "+i);
            productModel.setBrandName("Brand Name "+i);
            productModel.setDiscount("Discount "+i);
            productModel.setImageUrl("https://static1.jassets.com/p/Red-Tape-White-Checked-Regular-Fit-Casual-Shirt-9493-907215003-1-catalog_xs.webp");
            productModel.setPrice("100" + i);
            productModel.setSpecialPrice("50"+i);
            recentlyViewed.add(productModel);
        }*/

        rvView.refresh();

    }
}
