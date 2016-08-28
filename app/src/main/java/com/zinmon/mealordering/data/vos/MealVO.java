package com.zinmon.mealordering.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asus on 8/18/2016.
 */
public class MealVO {
    @SerializedName("id")
    private int mealId;

    @SerializedName("name")
    private String name;

    @SerializedName("img_url")
    private String imgurl;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private String price;

    @SerializedName("ingredients")
    private String[] ingredients;

    public int getMealId() {
        return mealId;
    }

    public String getName(){
        return name;
    }

    public String getImgurl(){
        return imgurl;
    }
    public String getDescription(){
        return description;
    }
    public String getPrice(){
        return price;
    }
    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public static void saveAttractions(List<MealVO> mealsList) {

    }
}
