package com.example.smarthomeapp.ui.charts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GasBoilerChartsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GasBoilerChartsViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}