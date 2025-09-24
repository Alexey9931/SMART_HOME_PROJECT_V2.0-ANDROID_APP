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
import com.example.smarthomeapp.databinding.ControlpanelStatisticsBinding;

public class ControlPanelStatistics extends Fragment {
    private ControlpanelStatisticsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ControlpanelStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ControlPanelValuesGetter controlPanelValuesGetter = new ControlPanelValuesGetter(getContext());
        controlPanelValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-7day.php");

        ControlPanelStatisticsViewer controlPanelStatisticsViewer = new ControlPanelStatisticsViewer(getContext());
        controlPanelStatisticsViewer.execute(
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_avg_temp),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_avg_hum),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_avg_pressure),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_max_temp),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_max_hum),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_max_pressure),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_min_temp),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_min_hum),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_min_pressure),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_start_time),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_end_time),
                                            root.findViewById(R.id.СontrolPanelStatistics_textView_number_mesuamer));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
