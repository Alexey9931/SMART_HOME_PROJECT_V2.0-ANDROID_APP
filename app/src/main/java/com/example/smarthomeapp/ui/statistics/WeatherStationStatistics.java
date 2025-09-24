package com.example.smarthomeapp.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.ControlPanelStatisticsViewer;
import com.example.smarthomeapp.ControlPanelValuesGetter;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.WeatherStationStatisticsViewer;
import com.example.smarthomeapp.WeatherStationValuesGetter;
import com.example.smarthomeapp.databinding.WeatherstationStatisticsBinding;
import com.github.mikephil.charting.charts.PieChart;

public class WeatherStationStatistics extends Fragment {
    private WeatherstationStatisticsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = WeatherstationStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final PieChart WindDirectDiagram = root.findViewById(R.id.WeatherStationStatistics_WindDirectDiagram);
        WindDirectDiagram.setVisibility(View.INVISIBLE);

        WeatherStationValuesGetter weatherStationValuesGetter = new WeatherStationValuesGetter(getContext());
        weatherStationValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-7day.php");

        WeatherStationStatisticsViewer weatherStationStatisticsViewer = new WeatherStationStatisticsViewer(getContext());
        weatherStationStatisticsViewer.execute(
                                            root.findViewById(R.id.WeatherStationStatistics_textView_avg_temp),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_avg_hum),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_avg_rainfall),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_avg_windspeed),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_max_temp),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_max_hum),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_max_rainfall),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_max_windspeed),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_min_temp),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_min_hum),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_min_rainfall),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_min_windspeed),
                                            root.findViewById(R.id.WeatherStationStatistics_WindDirectDiagram),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_start_time),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_end_time),
                                            root.findViewById(R.id.WeatherStationStatistics_textView_number_mesuamer));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
