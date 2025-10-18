package com.example.smarthomeapp.ui.chart_settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.ControlPanelValuesGetter;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.databinding.ControlpanelChartsSettingsBinding;

public class ControlPanelChartsSettingsFragment extends Fragment {

    CheckBox checkBox_view_mode1;
    CheckBox checkBox_TEMP;
    CheckBox checkBox_HUM;
    CheckBox checkBox_PRESSURE;

    private ControlpanelChartsSettingsBinding binding;

    public static String choose_chart_mode = "all_other";
    public static Boolean TEMP_MODE = false;
    public static Boolean HUM_MODE = false;
    public static Boolean PRESSURE_MODE = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ControlpanelChartsSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        checkBox_view_mode1 = root.findViewById(R.id.ControlPanelChartsSettings_checkBox_view_mode1);
        checkBox_TEMP = root.findViewById(R.id.ControlPanelChartsSettings_checkBox_TEMP);
        checkBox_HUM = root.findViewById(R.id.ControlPanelChartsSettings_checkBox_HUM);
        checkBox_PRESSURE = root.findViewById(R.id.ControlPanelChartsSettings_checkBox_PRESSURE);
        RadioGroup radioGroup_display_mode = root.findViewById(R.id.ControlPanelChartsSettings_display_mode);

        checkBox_view_mode1.setOnClickListener(CheckBoxClickListener);
        checkBox_TEMP.setOnClickListener(CheckBoxClickListener);
        checkBox_HUM.setOnClickListener(CheckBoxClickListener);
        checkBox_PRESSURE.setOnClickListener(CheckBoxClickListener);

        radioGroup_display_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ControlPanelChartsSettings_radioButton_1day:
                        ControlPanelValuesGetter.ChartDaysMode = 1;
                        break;
                    case R.id.ControlPanelChartsSettings_radioButton_3day:
                        ControlPanelValuesGetter.ChartDaysMode = 3;
                        break;
                    case R.id.ControlPanelChartsSettings_radioButton_5day:
                        ControlPanelValuesGetter.ChartDaysMode = 5;
                        break;
                    case R.id.ControlPanelChartsSettings_radioButton_7day:
                        ControlPanelValuesGetter.ChartDaysMode = 7;
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
                case R.id.ControlPanelChartsSettings_checkBox_view_mode1:
                    if (checkBox.isChecked()) {
                        choose_chart_mode = "all_other";
                        checkBox_TEMP.setChecked(true);
                        checkBox_TEMP.setEnabled(false);
                        checkBox_HUM.setChecked(true);
                        checkBox_HUM.setEnabled(false);
                        checkBox_PRESSURE.setChecked(true);
                        checkBox_PRESSURE.setEnabled(false);
                    } else {
                        choose_chart_mode = "";
                        checkBox_TEMP.setChecked(false);
                        checkBox_TEMP.setEnabled(true);
                        checkBox_HUM.setChecked(false);
                        checkBox_HUM.setEnabled(true);
                        checkBox_PRESSURE.setChecked(false);
                        checkBox_PRESSURE.setEnabled(true);
                        TEMP_MODE = false;
                        HUM_MODE = false;
                        PRESSURE_MODE = false;
                    }
                    break;
                case R.id.ControlPanelChartsSettings_checkBox_TEMP:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        TEMP_MODE = true;
                    }
                    else
                    {
                        TEMP_MODE = false;
                    }
                    break;
                case R.id.ControlPanelChartsSettings_checkBox_HUM:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        HUM_MODE = true;
                    }
                    else
                    {
                        HUM_MODE = false;
                    }
                    break;
                case R.id.ControlPanelChartsSettings_checkBox_PRESSURE:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        PRESSURE_MODE = true;
                    }
                    else
                    {
                        PRESSURE_MODE = false;
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