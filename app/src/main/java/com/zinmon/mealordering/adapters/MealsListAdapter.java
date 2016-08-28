package com.zinmon.mealordering.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zinmon.mealordering.MealOrderingApp;
import com.zinmon.mealordering.R;
import com.zinmon.mealordering.data.vos.MealVO;
import com.zinmon.mealordering.views.holders.MealsViewHolder;

import java.util.List;

/**
 * Created by Asus on 8/18/2016.
 */
public class MealsListAdapter extends RecyclerView.Adapter<MealsViewHolder> {
    private LayoutInflater mInflater;
    private List<MealVO> mMealsList;
    private MealsViewHolder.ControllerMealsItem mControllerMealsItem;

    public MealsListAdapter(List<MealVO> mealsList, MealsViewHolder.ControllerMealsItem controllerMealsItem) {
        mInflater = LayoutInflater.from(MealOrderingApp.getContext());
        mMealsList = mealsList;
        mControllerMealsItem = controllerMealsItem;
    }

    @Override
    public MealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_meals_list, parent, false);
        return new MealsViewHolder(itemView, mControllerMealsItem);
    }

    @Override
    public void onBindViewHolder(MealsViewHolder holder, int position) {
        holder.bindData(mMealsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMealsList.size();
    }

    public void setNewData(List<MealVO> newMealsList) {
        mMealsList = newMealsList;
        notifyDataSetChanged();
    }
}
