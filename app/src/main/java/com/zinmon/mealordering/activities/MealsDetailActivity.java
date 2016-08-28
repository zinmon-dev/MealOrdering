package com.zinmon.mealordering.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zinmon.mealordering.MealOrderingApp;
import com.zinmon.mealordering.R;
import com.zinmon.mealordering.data.models.MealsListModel;
import com.zinmon.mealordering.data.vos.MealVO;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealsDetailActivity extends AppCompatActivity {
    private static final String IE_MEAL_ID = "IE_MEAL_ID";
    private int mMealId;
    private MealVO mMeals;

    @BindView(R.id.iv_meals)
    ImageView ivMeals;

    @BindView(R.id.tv_meals_price_detail)
    TextView tvMealsPrice;

    @BindView(R.id.tv_meals_desc)
    TextView tvMealsDesc;

    @BindView(R.id.tv_meals_ingrident)
    TextView tvMealsIngrident;

    private TextView tvMealsDescription;

    public  String ingident="Ingridents : " +  "\n\n" ;

    private CollapsingToolbarLayout collapsingToolbar;
    public static Intent newIntent (int mealId){
        Intent intent = new Intent(MealOrderingApp.getContext(),MealsDetailActivity.class);
        intent.putExtra(IE_MEAL_ID,mealId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this, this);
        tvMealsDescription = (TextView) findViewById(R.id.tv_meals_desc);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mMealId = getIntent().getIntExtra(IE_MEAL_ID,0);
        final MealVO mMeals = MealsListModel.getInstance().getMealById(mMealId);

        if(mMeals == null){
            throw new RuntimeException("Can't find Event obj with the title : "+mMealId);
        }
        else {
            collapsingToolbar.setTitle(mMeals.getName());
            tvMealsPrice.setText("Price :  "+ mMeals.getPrice());
            tvMealsDesc.setText(mMeals.getDescription());
            String imageUrl = mMeals.getImgurl();
            Glide.with(ivMeals.getContext())
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.stock_photo_placeholder)
                    .error(R.drawable.stock_photo_placeholder)
                    .into(ivMeals);
            String[] ingidents = mMeals.getIngredients();

            for(int index=0;index<ingidents.length;index++)
            {

                ingident = ingident + ingidents[index] + "\n\n";
            }
            tvMealsIngrident.setText(ingident);

        }


    }

}
