package com.zinmon.mealordering.data.models;


import com.zinmon.mealordering.data.agents.MealsListDataAgent;
import com.zinmon.mealordering.data.agents.retrofit.RetrofitDataAgent;

import de.greenrobot.event.EventBus;

/**
 * Created by Asus on 8/7/2016.
 */
public class BaseModel {
    private static final int INIT_DATA_AGENT_OFFLINE = 1;
    private static final int INIT_DATA_AGENT_HTTP_URL_CONNECTION = 2;
    private static final int INIT_DATA_AGENT_OK_HTTP = 3;
    private static final int INIT_DATA_AGENT_RETROFIT = 4;

    protected MealsListDataAgent dataAgent;

    public BaseModel() {
        initDataAgent(INIT_DATA_AGENT_RETROFIT);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initDataAgent(int initType) {
        switch (initType) {

            case INIT_DATA_AGENT_RETROFIT:
                dataAgent = RetrofitDataAgent.getInstance();
                break;
        }
    }

    public void onEventMainThread(Object obj) {

    }
}
