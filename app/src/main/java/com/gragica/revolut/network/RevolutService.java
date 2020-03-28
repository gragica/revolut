package com.gragica.revolut.network;

import androidx.databinding.ObservableField;

import com.gragica.revolut.entities.RateList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RevolutService {

    private RevolutApi revolutApi = NetworkUtils.getInstance().getRevolutApi();

    public void getRates(String base, ObservableField<RateList> rateList){
        Disposable d = revolutApi
                .getRates(base)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    rateList.set(list);
                    rateList.notifyChange();
                }, Throwable::printStackTrace);
    }
}