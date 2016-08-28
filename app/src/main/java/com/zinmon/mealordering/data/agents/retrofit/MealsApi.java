package com.zinmon.mealordering.data.agents.retrofit;

import com.zinmon.mealordering.data.responses.MealsListResponse;
import com.zinmon.mealordering.utils.MealOrderingConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Asus on 8/18/2016.
 */
public interface MealsApi {
    @FormUrlEncoded
    @POST(MealOrderingConstants.API_GET_MEALS_LIST)
    Call<MealsListResponse> loadMeals(
            @Field(MealOrderingConstants.PARAM_ACCESS_TOKEN) String param);
}
