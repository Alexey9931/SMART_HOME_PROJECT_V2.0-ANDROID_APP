package com.example.smarthomeapp.ui.charts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ControlPanelChartsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ControlPanelChartsViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}