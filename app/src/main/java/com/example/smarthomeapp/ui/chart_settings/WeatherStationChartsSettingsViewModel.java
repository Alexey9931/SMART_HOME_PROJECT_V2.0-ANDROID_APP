package com.example.smarthomeapp.ui.chart_settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherStationChartsSettingsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WeatherStationChartsSettingsViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}