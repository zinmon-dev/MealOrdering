package com.zinmon.mealordering;

import android.app.Application;
import android.content.Context;

/**
 * Created by Asus on 8/18/2016.
 */
public class MealOrderingApp extends Application {

    public static final String TAG = "Meal Ordering";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
