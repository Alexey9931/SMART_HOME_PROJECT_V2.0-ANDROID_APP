package com.example.smarthomeapp.ui.charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.ControlPanelChartsViewer;
import com.example.smarthomeapp.GasBoilerChartsViewer;
import com.example.smarthomeapp.GasBoilerValuesGetter;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.databinding.GasboilerChartsBinding;
import com.github.mikephil.charting.charts.LineChart;

public class GasBoilerChartsFragment extends Fragment {

    private GasboilerChartsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = GasboilerChartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final LineChart setpoint_temp_chart = root.findViewById(R.id.setpointtemp_chart);
        final LineChart current_temp_chart = root.findViewById(R.id.currenttemp_chart);
        final LineChart status_chart = root.findViewById(R.id.status_chart);
        final TextView error_message = root.findViewById(R.id.gasboiler_chart_error_message);

        final TextView setpoint_temp_name = root.findViewById(R.id.setpointtemp_name);
        final TextView current_temp_name = root.findViewById(R.id.currenttemp_name);
        final TextView status_name = root.findViewById(R.id.status_name);

        GasBoilerValuesGetter gasBoilerValuesGetter = new GasBoilerValuesGetter(getContext());
        gasBoilerValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboilercontroller-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboilercontroller-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboilercontroller-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboilercontroller-7day.php");

        setpoint_temp_chart.setVisibility(View.GONE);
        current_temp_chart.setVisibility(View.GONE);
        status_chart.setVisibility(View.GONE);
        error_message.setVisibility(View.GONE);

        setpoint_temp_name.setVisibility(View.GONE);
        current_temp_name.setVisibility(View.GONE);
        status_name.setVisibility(View.GONE);

        GasBoilerChartsViewer gasBoilerChartsViewer = new GasBoilerChartsViewer(getContext());
        gasBoilerChartsViewer.execute(
                root.findViewById(R.id.setpointtemp_chart),
                root.findViewById(R.id.currenttemp_chart),
                root.findViewById(R.id.status_chart),
                root.findViewById(R.id.gasboiler_chart_error_message),
                root.findViewById(R.id.setpointtemp_name),
                root.findViewById(R.id.currenttemp_name),
                root.findViewById(R.id.status_name));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}