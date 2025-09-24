package com.example.smarthomeapp.ui.chart_settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.WeatherStationValuesGetter;
import com.example.smarthomeapp.databinding.WeatherstationChartsSettingsBinding;

public class WeatherStationChartsSettingsFragment extends Fragment {

    CheckBox checkBox_view_mode1;
    CheckBox checkBox_TEMP;
    CheckBox checkBox_HUM;
    CheckBox checkBox_WIND_SPEED;
    CheckBox checkBox_WIND_DIRECT;
    CheckBox checkBox_RAIN;

    private WeatherstationChartsSettingsBinding binding;

    public static String choose_chart_mode = "nothing";
    public static Boolean TEMP_MODE = false;
    public static Boolean HUM_MODE = false;
    public static Boolean WIND_SPEED_MODE = false;
    public static Boolean WIND_DIRECT_MODE = false;
    public static Boolean RAIN_MODE = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = WeatherstationChartsSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        checkBox_view_mode1 = root.findViewById(R.id.WeatherStationChartsSettings_checkBox_view_mode1);
        checkBox_TEMP = root.findViewById(R.id.WeatherStationChartsSettings_checkBox_TEMP);
        checkBox_HUM = root.findViewById(R.id.WeatherStationChartsSettings_checkBox_HUM);
        checkBox_WIND_SPEED = root.findViewById(R.id.WeatherStationChartsSettings_checkBox_WINDSPEED);
        checkBox_WIND_DIRECT = root.findViewById(R.id.WeatherStationChartsSettings_checkBox_WINDDIRECT);
        checkBox_RAIN = root.findViewById(R.id.WeatherStationChartsSettings_checkBox_RAINFALL);
        RadioGroup radioGroup_display_mode = root.findViewById(R.id.WeatherStationChartsSettings_display_mode);

        checkBox_view_mode1.setOnClickListener(CheckBoxClickListener);
        checkBox_TEMP.setOnClickListener(CheckBoxClickListener);
        checkBox_HUM.setOnClickListener(CheckBoxClickListener);
        checkBox_WIND_SPEED.setOnClickListener(CheckBoxClickListener);
        checkBox_WIND_DIRECT.setOnClickListener(CheckBoxClickListener);
        checkBox_RAIN.setOnClickListener(CheckBoxClickListener);

        radioGroup_display_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.WeatherStationChartsSettings_radioButton_1day:
                        WeatherStationValuesGetter.ChartDaysMode = 1;
                        break;
                    case R.id.WeatherStationChartsSettings_radioButton_3day:
                        WeatherStationValuesGetter.ChartDaysMode = 3;
                        break;
                    case R.id.WeatherStationChartsSettings_radioButton_5day:
                        WeatherStationValuesGetter.ChartDaysMode = 5;
                        break;
                    case R.id.WeatherStationChartsSettings_radioButton_7day:
                        WeatherStationValuesGetter.ChartDaysMode = 7;
                        break;
                    default:
                        break;
                }
            }
        });

        return root;
    }
    View.OnClickListener CheckBoxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CheckBox checkBox = (CheckBox) view;
            switch (checkBox.getId()) {
                case R.id.WeatherStationChartsSettings_checkBox_view_mode1:
                    if (checkBox.isChecked()) {
                        choose_chart_mode = "all_other";
                        checkBox_TEMP.setChecked(true);
                        checkBox_TEMP.setEnabled(false);
                        checkBox_HUM.setChecked(true);
                        checkBox_HUM.setEnabled(false);
                        checkBox_WIND_SPEED.setChecked(true);
                        checkBox_WIND_SPEED.setEnabled(false);
                        checkBox_WIND_DIRECT.setChecked(true);
                        checkBox_WIND_DIRECT.setEnabled(false);
                        checkBox_RAIN.setChecked(true);
                        checkBox_RAIN.setEnabled(false);
                    } else {
                        choose_chart_mode = "";
                        checkBox_TEMP.setChecked(false);
                        checkBox_TEMP.setEnabled(true);
                        checkBox_HUM.setChecked(false);
                        checkBox_HUM.setEnabled(true);
                        checkBox_WIND_SPEED.setChecked(false);
                        checkBox_WIND_SPEED.setEnabled(true);
                        checkBox_WIND_DIRECT.setChecked(false);
                        checkBox_WIND_DIRECT.setEnabled(true);
                        checkBox_RAIN.setChecked(false);
                        checkBox_RAIN.setEnabled(true);
                        TEMP_MODE = false;
                        HUM_MODE = false;
                        WIND_SPEED_MODE = false;
                        WIND_DIRECT_MODE = false;
                        RAIN_MODE = false;
                    }
                    break;
                case R.id.WeatherStationChartsSettings_checkBox_TEMP:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        TEMP_MODE = true;
                    }
                    else
                    {
                        TEMP_MODE = false;
                    }
                    break;
                case R.id.WeatherStationChartsSettings_checkBox_HUM:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        HUM_MODE = true;
                    }
                    else
                    {
                        HUM_MODE = false;
                    }
                    break;
                case R.id.WeatherStationChartsSettings_checkBox_WINDSPEED:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        WIND_SPEED_MODE = true;
                    }
                    else
                    {
                        WIND_SPEED_MODE = false;
                    }
                    break;
                case R.id.WeatherStationChartsSettings_checkBox_WINDDIRECT:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        WIND_DIRECT_MODE = true;
                    }
                    else
                    {
                        WIND_DIRECT_MODE = false;
                    }
                    break;
                case R.id.WeatherStationChartsSettings_checkBox_RAINFALL:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        RAIN_MODE = true;
                    }
                    else
                    {
                        RAIN_MODE = false;
                    }
                    break;
            }
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}