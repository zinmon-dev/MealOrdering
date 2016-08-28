package com.zinmon.mealordering.data.agents.retrofit;

import android.util.Log;

import com.zinmon.mealordering.MealOrderingApp;
import com.zinmon.mealordering.data.agents.MealsListDataAgent;
import com.zinmon.mealordering.data.models.MealsListModel;
import com.zinmon.mealordering.data.responses.MealsListResponse;
import com.zinmon.mealordering.utils.CommonInstances;
import com.zinmon.mealordering.utils.MealOrderingConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 7/9/16.
 */
public class RetrofitDataAgent implements MealsListDataAgent {

    private static RetrofitDataAgent objInstance;

    private final MealsApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MealOrderingConstants.MEALS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(MealsApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }


    @Override
    public void loadMeals() {
        Call<MealsListResponse> loadMealsListCall = theApi.loadMeals(MealOrderingConstants.ACCESS_TOKEN);
        loadMealsListCall.enqueue(new Callback<MealsListResponse>() {
            @Override
            public void onResponse(Call<MealsListResponse> call, Response<MealsListResponse> response) {
                MealsListResponse mealsListResponse = response.body();
                if (mealsListResponse == null){
                    Log.d(MealOrderingApp.TAG, "Reached this place");
                    MealsListModel.getInstance().notifyErrorInLoadingMeals(response.message());
                }
                else {
                    Log.d(MealOrderingApp.TAG, "test");
                    MealsListModel.getInstance().notifyMealsLoaded(mealsListResponse.getMealsList());
                }
            }

            @Override
            public void onFailure(Call<MealsListResponse> call, Throwable throwable) {
                MealsListModel.getInstance().notifyErrorInLoadingMeals(throwable.getMessage());
            }
        });
    }
}
