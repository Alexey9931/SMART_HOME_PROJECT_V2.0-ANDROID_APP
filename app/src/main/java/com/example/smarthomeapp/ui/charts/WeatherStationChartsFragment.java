package com.example.smarthomeapp.ui.charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.ControlPanelChartsViewer;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.WeatherStationChartsViewer;
import com.example.smarthomeapp.WeatherStationValuesGetter;
import com.example.smarthomeapp.databinding.WeatherstationChartsBinding;
import com.github.mikephil.charting.charts.LineChart;

public class WeatherStationChartsFragment extends Fragment {

    private WeatherstationChartsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = WeatherstationChartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final LineChart temp_chart = root.findViewById(R.id.weathstat_temp_chart);
        final LineChart hum_chart = root.findViewById(R.id.weathstat_hum_chart);
        final LineChart rain_chart = root.findViewById(R.id.rain_chart);
        final LineChart wind_speed_chart = root.findViewById(R.id.wind_speed_chart);
        final TableLayout wind_direct_table = root.findViewById(R.id.table);
        final TextView error_message = root.findViewById(R.id.weatherstation_chart_error_message);

        final TextView temp_name = root.findViewById(R.id.temp_name);
        final TextView hum_name = root.findViewById(R.id.hum_name);
        final TextView wind_speed_name = root.findViewById(R.id.wind_speed_name);
        final TextView rain_name = root.findViewById(R.id.rain_name);
        final TextView wind_direct_table_name = root.findViewById(R.id.winddirect_table_name);

        WeatherStationValuesGetter weatherStationValuesGetter = new WeatherStationValuesGetter(getContext());
        weatherStationValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-7day.php");

        temp_chart.setVisibility(View.GONE);
        hum_chart.setVisibility(View.GONE);
        rain_chart.setVisibility(View.GONE);
        wind_speed_chart.setVisibility(View.GONE);
        wind_direct_table.setVisibility(View.GONE);
        error_message.setVisibility(View.GONE);

        temp_name.setVisibility(View.GONE);
        hum_name.setVisibility(View.GONE);
        wind_speed_name.setVisibility(View.GONE);
        rain_name.setVisibility(View.GONE);
        wind_direct_table_name.setVisibility(View.GONE);

        WeatherStationChartsViewer weatherStationChartsViewer = new WeatherStationChartsViewer(getContext());
        weatherStationChartsViewer.execute(
                root.findViewById(R.id.weathstat_temp_chart),
                root.findViewById(R.id.weathstat_hum_chart),
                root.findViewById(R.id.rain_chart),
                root.findViewById(R.id.wind_speed_chart),
                root.findViewById(R.id.table),
                root.findViewById(R.id.weatherstation_chart_error_message),
                root.findViewById(R.id.temp_name),
                root.findViewById(R.id.hum_name),
                root.findViewById(R.id.wind_speed_name),
                root.findViewById(R.id.rain_name),
                root.findViewById(R.id.winddirect_table_name));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}