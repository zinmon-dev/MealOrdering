package com.zinmon.mealordering.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zinmon.mealordering.MealOrderingApp;
import com.zinmon.mealordering.R;
import com.zinmon.mealordering.adapters.MealsListAdapter;
import com.zinmon.mealordering.data.models.MealsListModel;
import com.zinmon.mealordering.data.vos.MealVO;
import com.zinmon.mealordering.events.DataEvent;
import com.zinmon.mealordering.views.holders.MealsViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.rv_meals)
    RecyclerView rvMeals;

    private static final String ARG_PARAM = "ARG_PARAM";
    private String mParam;

    int gridColumnSpanCount = 1;

    private MealsListAdapter mMealsListAdapter;
    private MealsViewHolder.ControllerMealsItem controllerMealsItem;
    private BroadcastReceiver mDataLoadedBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO instructions when the new data is ready.
            String extra = intent.getStringExtra("key-for-extra");
            Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

            List<MealVO> newMealsList = MealsListModel.getInstance().getmMealsList();
            mMealsListAdapter.setNewData(newMealsList);
        }
    };

    public MainActivityFragment() {
    }

    public static MainActivityFragment newInstance(String direction) {
        MainActivityFragment fragment = new MainActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, direction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerMealsItem = (MealsViewHolder.ControllerMealsItem) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        List<MealVO> mealsList = MealsListModel.getInstance().getmMealsList();
        mMealsListAdapter = new MealsListAdapter(mealsList,controllerMealsItem );
        rvMeals.setAdapter(mMealsListAdapter);
        setupLayout();

        return rootView;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one_column:
                gridColumnSpanCount = 1;
                setupLayout();
            case R.id.two_column:
                gridColumnSpanCount = 2;
                setupLayout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupLayout() {
        rvMeals.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
    }
    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mDataLoadedBroadcastReceiver,
                new IntentFilter(MealsListModel.BROADCAST_DATA_LOADED));

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mDataLoadedBroadcastReceiver);

        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }


    public void onEventMainThread(DataEvent.MealsDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<MealVO> newMealsList = event.getMealsList();
        mMealsListAdapter.setNewData(newMealsList);
    }

}
