package com.example.smarthomeapp.ui.chart_settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.GasBoilerValuesGetter;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.databinding.GasboilerChartsSettingsBinding;

public class GasBoilerChartsSettingsFragment extends Fragment {

    CheckBox checkBox_view_mode1;
    CheckBox checkBox_SETPOINT_TEMP;
    CheckBox checkBox_CURRENT_TEMP;
    CheckBox checkBox_STATUS;

    private GasboilerChartsSettingsBinding binding;

    public static String choose_chart_mode = "nothing";
    public static Boolean SETPOINT_TEMP_MODE = false;
    public static Boolean CURRENT_TEMP_MODE = false;
    public static Boolean STATUS_MODE = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = GasboilerChartsSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        checkBox_view_mode1 = root.findViewById(R.id.GasBoilerChartsSettings_checkBox_view_mode1);
        checkBox_SETPOINT_TEMP = root.findViewById(R.id.GasBoilerChartsSettings_checkBox_SETPOINTTEMP);
        checkBox_CURRENT_TEMP = root.findViewById(R.id.GasBoilerChartsSettings_checkBox_CURRENTTEMP);
        checkBox_STATUS = root.findViewById(R.id.GasBoilerChartsSettings_checkBox_STATUS);
        RadioGroup radioGroup_display_mode = root.findViewById(R.id.GasBoilerChartsSettings_display_mode);

        checkBox_view_mode1.setOnClickListener(CheckBoxClickListener);
        checkBox_SETPOINT_TEMP.setOnClickListener(CheckBoxClickListener);
        checkBox_CURRENT_TEMP.setOnClickListener(CheckBoxClickListener);
        checkBox_STATUS.setOnClickListener(CheckBoxClickListener);

        radioGroup_display_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.GasBoilerChartsSettings_radioButton_1day:
                        GasBoilerValuesGetter.ChartDaysMode = 1;
                        break;
                    case R.id.GasBoilerChartsSettings_radioButton_3day:
                        GasBoilerValuesGetter.ChartDaysMode = 3;
                        break;
                    case R.id.GasBoilerChartsSettings_radioButton_5day:
                        GasBoilerValuesGetter.ChartDaysMode = 5;
                        break;
                    case R.id.GasBoilerChartsSettings_radioButton_7day:
                        GasBoilerValuesGetter.ChartDaysMode = 7;
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
                case R.id.GasBoilerChartsSettings_checkBox_view_mode1:
                    if (checkBox.isChecked()) {
                        choose_chart_mode = "all_other";
                        checkBox_SETPOINT_TEMP.setChecked(true);
                        checkBox_SETPOINT_TEMP.setEnabled(false);
                        checkBox_CURRENT_TEMP.setChecked(true);
                        checkBox_CURRENT_TEMP.setEnabled(false);
                        checkBox_STATUS.setChecked(true);
                        checkBox_STATUS.setEnabled(false);
                    } else {
                        choose_chart_mode = "";
                        checkBox_SETPOINT_TEMP.setChecked(false);
                        checkBox_SETPOINT_TEMP.setEnabled(true);
                        checkBox_CURRENT_TEMP.setChecked(false);
                        checkBox_CURRENT_TEMP.setEnabled(true);
                        checkBox_STATUS.setChecked(false);
                        checkBox_STATUS.setEnabled(true);
                        SETPOINT_TEMP_MODE = false;
                        CURRENT_TEMP_MODE = false;
                        STATUS_MODE = false;
                    }
                    break;
                case R.id.GasBoilerChartsSettings_checkBox_SETPOINTTEMP:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        SETPOINT_TEMP_MODE = true;
                    }
                    else
                    {
                        SETPOINT_TEMP_MODE = false;
                    }
                    break;
                case R.id.GasBoilerChartsSettings_checkBox_CURRENTTEMP:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        CURRENT_TEMP_MODE = true;
                    }
                    else
                    {
                        CURRENT_TEMP_MODE = false;
                    }
                    break;
                case R.id.GasBoilerChartsSettings_checkBox_STATUS:
                    choose_chart_mode = "";
                    if (checkBox.isChecked()) {
                        STATUS_MODE = true;
                    }
                    else
                    {
                        STATUS_MODE = false;
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