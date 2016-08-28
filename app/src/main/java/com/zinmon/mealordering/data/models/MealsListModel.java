package com.zinmon.mealordering.data.models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.zinmon.mealordering.MealOrderingApp;
import com.zinmon.mealordering.data.vos.MealVO;
import com.zinmon.mealordering.events.DataEvent;
import com.zinmon.mealordering.utils.CommonInstances;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Asus on 8/18/2016.
 */
public class MealsListModel extends BaseModel {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static MealsListModel objInstance;

    private List<MealVO> mMealsList;

    private MealsListModel() {
        super();
        mMealsList = new ArrayList<>();

       dataAgent.loadMeals();
    }

    public static MealsListModel getInstance() {
        if (objInstance == null) {
            objInstance = new MealsListModel();
        }
        return objInstance;
    }
    public void loadMeals(){
        dataAgent.loadMeals();
    }

    public List<MealVO> getmMealsList() {
        return mMealsList;
    }

    public MealVO getMealById(int mealId) {
        for (MealVO meals : mMealsList) {
            if (meals.getMealId()== mealId) {
                return meals;
            }
        }
        return null;
    }

    public void notifyMealsLoaded(List<MealVO> mealsList) {
        //Notify that the data is ready - using LocalBroadcast
        mMealsList = mealsList;

        //keep the data in persistent layer.
        //AttractionVO.saveAttractions(mAttractionList);

        broadcastAttractionLoadedWithEventBus();
        //broadcastAttractionLoadedWithLocalBroadcastManager();
    }

    public void notifyErrorInLoadingMeals(String message) {
        Log.d(MealOrderingApp.TAG, message);
    }

    private void broadcastAttractionLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(MealOrderingApp.getContext()).sendBroadcast(intent);
    }

    private void broadcastAttractionLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.MealsDataLoadedEvent("extra-in-broadcast", mMealsList));
    }
}
