package com.zinmon.mealordering.data.responses;

import com.google.gson.annotations.SerializedName;
import com.zinmon.mealordering.data.vos.MealVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 8/18/2016.
 */
public class MealsListResponse {


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("meal_list")
    private ArrayList<MealVO> mealsList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<MealVO> getMealsList() {
        return mealsList;
    }
}
