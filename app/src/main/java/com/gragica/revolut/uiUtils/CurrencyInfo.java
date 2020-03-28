package com.gragica.revolut.uiUtils;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.gragica.revolut.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurrencyInfo {

    private static Map<String, Integer> icons;
    private static Map<String, String> names;
    static {
        icons = new HashMap<>();
        names = new HashMap<>();

        icons.put("AUD", R.drawable.aud);
        icons.put("BGN", R.drawable.bgn);
        icons.put("BRL", R.drawable.brl);
        icons.put("CAD", R.drawable.cad);
        icons.put("CHF", R.drawable.chf);
        icons.put("CNY", R.drawable.cny);
        icons.put("CZK", R.drawable.czk);
        icons.put("DKK", R.drawable.dkk);
        icons.put("GBP", R.drawable.gbp);
        icons.put("HKD", R.drawable.hkd);
        icons.put("HRK", R.drawable.hrk);
        icons.put("HUF", R.drawable.huf);
        icons.put("IDR", R.drawable.idr);
        icons.put("ILS", R.drawable.ils);
        icons.put("INR", R.drawable.inr);
        icons.put("ISK", R.drawable.isk);
        icons.put("EUR", R.drawable.eur);
        icons.put("JPY", R.drawable.jpy);
        icons.put("KRW", R.drawable.krw);
        icons.put("MXN", R.drawable.mxn);
        icons.put("MYR", R.drawable.myr);
        icons.put("NOK", R.drawable.nok);
        icons.put("NZD", R.drawable.nzd);
        icons.put("PHP", R.drawable.php);
        icons.put("PLN", R.drawable.pln);
        icons.put("RON", R.drawable.ron);
        icons.put("RUB", R.drawable.rub);
        icons.put("SEK", R.drawable.sek);
        icons.put("SGD", R.drawable.sgd);
        icons.put("THB", R.drawable.thb);
        icons.put("USD", R.drawable.usd);
        icons.put("ZAR", R.drawable.zar);

        names.put("AUD", "Australian Dollar");
        names.put("BGN", "Bulgarian Lev");
        names.put("BRL", "Brasilian Real");
        names.put("CAD", "Canadian Dollar");
        names.put("CHF", "Swiss Frank");
        names.put("CNY", "Chinese Yen");
        names.put("CZK", "Czeck koruna");
        names.put("DKK", "Danish Krone");
        names.put("GBP", "British Pound");
        names.put("HKD", "Hong Kong Dollar");
        names.put("HRK", "Croatian Kuna");
        names.put("HUF", "Hungarian Forint");
        names.put("IDR", "Indonesian Rupiah");
        names.put("ILS", "Israeli Shekel");
        names.put("INR", "Indian Rupee");
        names.put("ISK", "Islandic Krone");
        names.put("EUR", "Euro");
        names.put("JPY", "Japanese Yen");
        names.put("KRW", "South Korean Won");
        names.put("MXN", "Mexican Pesos");
        names.put("MYR", "Malaysian Ringgit");
        names.put("NOK", "Norwegian Kroner");
        names.put("NZD", "New Zealand Dollar");
        names.put("PHP", "Philippine Peso");
        names.put("PLN", "Polish Zloty");
        names.put("RON", "Romanian Leu");
        names.put("RUB", "Russian Ruble");
        names.put("SEK", "Swedish Kroner");
        names.put("SGD", "Singapore Dollar");
        names.put("THB", "Thai Baht");
        names.put("USD", "US Dollar");
        names.put("ZAR", "South African Rand");
    }

    public static int iconOf(String code){
        return Optional.ofNullable(icons.get(code)).orElse(R.drawable.ic_launcher_background);
    }

    public static String nameOf(String code){
        return Optional.ofNullable(names.get(code)).orElse("Unknown name");
    }


}
