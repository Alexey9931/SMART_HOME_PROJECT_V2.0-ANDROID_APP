package com.example.smarthomeapp.ui.charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.ControlPanelChartsCreator;
import com.example.smarthomeapp.ControlPanelValuesGetter;
import com.example.smarthomeapp.ControlPanelChartsViewer;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.databinding.ControlpanelChartsBinding;
import com.example.smarthomeapp.ui.chart_settings.ControlPanelChartsSettingsFragment;
import com.github.mikephil.charting.charts.LineChart;

public class ControlPanelChartsFragment extends Fragment {

    private ControlpanelChartsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ControlpanelChartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final LineChart temp_chart = root.findViewById(R.id.controlpanel_temp_chart);
        final LineChart hum_chart = root.findViewById(R.id.controlpanel_hum_chart);
        final LineChart pressure_chart = root.findViewById(R.id.pressure_chart);
        final TextView error_message = root.findViewById(R.id.controlpanel_chart_error_message);

        final TextView temp_name = root.findViewById(R.id.temp_name);
        final TextView hum_name = root.findViewById(R.id.hum_name);
        final TextView pressure_name = root.findViewById(R.id.pressure_name);

        ControlPanelValuesGetter controlPanelValuesGetter = new ControlPanelValuesGetter(getContext());
        controlPanelValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-7day.php");

        temp_chart.setVisibility(View.GONE);
        hum_chart.setVisibility(View.GONE);
        pressure_chart.setVisibility(View.GONE);
        error_message.setVisibility(View.GONE);

        temp_name.setVisibility(View.GONE);
        hum_name.setVisibility(View.GONE);
        pressure_name.setVisibility(View.GONE);

        ControlPanelChartsViewer controlPanelChartsViewer = new ControlPanelChartsViewer(getContext());
        controlPanelChartsViewer.execute(
                root.findViewById(R.id.controlpanel_temp_chart),
                root.findViewById(R.id.controlpanel_hum_chart),
                root.findViewById(R.id.pressure_chart),
                root.findViewById(R.id.controlpanel_chart_error_message),
                root.findViewById(R.id.temp_name),
                root.findViewById(R.id.hum_name),
                root.findViewById(R.id.pressure_name));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}