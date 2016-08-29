package com.zinmon.mealordering.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zinmon.mealordering.MealOrderingApp;
import com.zinmon.mealordering.R;
import com.zinmon.mealordering.data.vos.MealVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 8/18/2016.
 */
public class MealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_meals_name)
    TextView tvMealsName;

    @BindView(R.id.iv_meals)
    ImageView ivMeals;

//    @BindView(R.id.tv_meals_price_detail)
//    TextView tvMealsPrice;

    private ControllerMealsItem mController;
    private MealVO mMeals;

    public MealsViewHolder(View itemView, ControllerMealsItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(MealVO meals) {
        mMeals = meals;
        tvMealsName.setText(meals.getName());
        //tvMealsPrice.setText("Price : "+meals.getPrice());

        String imageUrl = meals.getImgurl();
        Glide.with(ivMeals.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivMeals);
    }

    @Override
    public void onClick(View view) {
        mController.onTapAttraction(mMeals, ivMeals);
    }

    public interface ControllerMealsItem {
        void onTapAttraction(MealVO meals, ImageView ivMeals);
    }
}
