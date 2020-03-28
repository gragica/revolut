package com.gragica.revolut;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.gragica.revolut.entities.RateList;
import com.gragica.revolut.network.RevolutService;

import java.util.Timer;
import java.util.TimerTask;

public class RateActivity extends AppCompatActivity {

    ObservableField<RateList> rateListObservable = new ObservableField<>();
    RevolutService revolutService = new RevolutService();
    Timer timer;
    RateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rates);

        adapter = new RateAdapter(this);
        adapter.setHasStableIds(true);
        RecyclerView recyclerView = findViewById(R.id.rv_rates);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(-1);
            actionBar.setIcon(R.drawable.appbar_placeholder);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        revolutService.getRates(Const.INITIAL_BASE_CURRENCY, rateListObservable);
        rateListObservable.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                RateList rateList = rateListObservable.get();
                if (rateList != null)
                    adapter.setData(rateList.rates);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> revolutService.getRates(adapter.getBaseCurrency(), rateListObservable));
            }
        },0,1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
