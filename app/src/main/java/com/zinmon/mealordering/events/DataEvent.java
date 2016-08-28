package com.zinmon.mealordering.events;

import com.zinmon.mealordering.data.vos.MealVO;

import java.util.List;

/**
 * Created by aung on 7/9/16.
 */
public class DataEvent {
    public static class MealsDataLoadedEvent {

        private String extraMessage;
        private List<MealVO> mealsList;

        public MealsDataLoadedEvent(String extraMessage, List<MealVO> mealsList) {
            this.extraMessage = extraMessage;
            this.mealsList = mealsList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<MealVO> getMealsList() {
            return mealsList;
        }
    }


}