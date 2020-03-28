package com.gragica.revolut.uiUtils;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;


public class BindingAdapters {

    @BindingAdapter("circleCurrencyFlag")
    public static void loadCurrencyFlag(ImageView view, String code) {

        Picasso.with(view.getContext())
                .load(CurrencyInfo.iconOf(code))
                .resize(40, 40)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(view);
    }

    @BindingAdapter("currencyName")
    public static void loadCurrencyName(TextView view, String code) {
        view.setText(CurrencyInfo.nameOf(code));
    }
}
