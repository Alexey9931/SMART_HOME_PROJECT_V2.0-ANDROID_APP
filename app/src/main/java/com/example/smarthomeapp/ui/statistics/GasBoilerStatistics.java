package com.example.smarthomeapp.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.ControlPanelStatisticsViewer;
import com.example.smarthomeapp.ControlPanelValuesGetter;
import com.example.smarthomeapp.GasBoilerStatisticsViewer;
import com.example.smarthomeapp.GasBoilerValuesGetter;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.databinding.GasboilerStatisticsBinding;
import com.github.mikephil.charting.charts.PieChart;

public class GasBoilerStatistics extends Fragment {
    private GasboilerStatisticsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = GasboilerStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GasBoilerValuesGetter gasBolierValuesGetter = new GasBoilerValuesGetter(getContext());
        gasBolierValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboiler-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboiler-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboiler-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboiler-7day.php");

        GasBoilerStatisticsViewer gasBoilerStatisticsViewer = new GasBoilerStatisticsViewer(getContext());
        gasBoilerStatisticsViewer.execute(
                                            root.findViewById(R.id.GasBoilerStatistics_textView_avg_setpoint),
                                            root.findViewById(R.id.GasBoilerStatistics_textView_avg_current),
                                            root.findViewById(R.id.GasBoilerStatistics_textView_max_setpoint),
                                            root.findViewById(R.id.GasBoilerStatistics_textView_max_current),
                                            root.findViewById(R.id.GasBoilerStatistics_textView_min_setpoint),
                                            root.findViewById(R.id.GasBoilerStatistics_textView_min_current),
                                            root.findViewById(R.id.GasBoilerStatistics_textView_start_time),
                                            root.findViewById(R.id.GasBoilerStatistics_textView_end_time),
                                            root.findViewById(R.id.GasBoilerStatistics_textView_number_mesuamer));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
