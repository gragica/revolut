package com.gragica.revolut;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gragica.revolut.databinding.RateDataBinding;
import com.gragica.revolut.entities.Rate;
import com.gragica.revolut.uiUtils.KeyboardHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {

    private List<Rate> itemList = new ArrayList<>();
    private EditText inputField;
    private Activity activity;
    private float insertedAmount = 100;
    private boolean inited;
    private TextWatcher inputTextWatcher;

    private RecyclerView recyclerView;

    RateAdapter(Activity a){
        inputTextWatcher = textWatcher();
        activity = a;
    }

    String getBaseCurrency(){
        return itemList != null && itemList.size()>0 ? itemList.get(0).code : Const.INITIAL_BASE_CURRENCY;
    }

    void setData(HashMap<String, Float> updatedList){

        //data initialization
        if (itemList.size() == 0){
            itemList.add(new Rate(Const.INITIAL_BASE_CURRENCY, insertedAmount));
            for (Map.Entry<String, Float> entry : updatedList.entrySet())
                itemList.add(new Rate(entry.getKey(), entry.getValue()));
            notifyDataSetChanged();
        }

        //data updating
        else {
            for (int i = 1; i<itemList.size(); i++){
            itemList.get(i).amount = Optional.ofNullable(updatedList.get(itemList.get(i).code)).orElse(0f);
            }
            notifyDataSetChanged();
        }

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView rv) {
        super.onAttachedToRecyclerView(rv);
        recyclerView = rv;
    }

    @NonNull
    @Override
    public RateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_rate, parent, false);
        return new RateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RateAdapter.ViewHolder holder, int position) {

        Rate rate = itemList.get(position);
        holder.binding.setRate(rate);

        //preventing unwanted periodical update of the first input field after initialization
        if (position == 0 && !inited) {
            inputField = holder.binding.etAmount;
            holder.binding.etAmount.setText(String.valueOf(insertedAmount));
            holder.binding.etAmount.setClickable(true);
            inited = true;
        }

        if (position>0) {
            holder.binding.etAmount.setText(String.valueOf(rate.amount * insertedAmount));
        }
    }



    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RateDataBinding binding;

        ViewHolder(View itemView) {
            super(itemView);

            //defining behaviour when item in the list is selected
            View.OnClickListener itemListener = v -> {
                int pos = getAdapterPosition();
                if (pos >= 0 && pos <itemList.size()) {

                    //removing previous responder
                    inputField.removeTextChangedListener(inputTextWatcher);
                    inputField.setFocusableInTouchMode(false);
                    inputField.setFocusable(false);

                    //setting up new responder
                    inputField = binding.etAmount;
                    inputField.setFocusable(true);
                    inputField.setFocusableInTouchMode(true);
                    inputField.addTextChangedListener(inputTextWatcher);

                    insertedAmount = Float.valueOf(inputField.getText().toString());

                    //updating dataset to be consistent with UI
                    Rate rate = itemList.get(pos);
                    itemList.remove(pos);
                    itemList.add(0, rate);
                    notifyItemMoved(pos, 0);

                    //preparing for user's input
                    KeyboardHelper.openKeyboard(inputField, activity);
                    inputField.setSelection(inputField.getText().length());
                }
            };

            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(itemListener);
            binding.etAmount.setOnClickListener(itemListener);
        }
    }

    private TextWatcher textWatcher(){

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                insertedAmount = s.length()>0 ? Float.valueOf(s.toString()) : 0;
                if (!(recyclerView.isComputingLayout() || recyclerView.isAnimating()))
                    notifyDataSetChanged();
            }
        };
    }

}
