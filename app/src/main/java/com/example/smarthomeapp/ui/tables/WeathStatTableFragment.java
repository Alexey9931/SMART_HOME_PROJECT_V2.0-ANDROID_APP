package com.example.smarthomeapp.ui.tables;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.databinding.WeatherstationTableBinding;

public class WeathStatTableFragment extends Fragment {
    private WeatherstationTableBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = WeatherstationTableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WebView browser=root.findViewById(R.id.Weathstat_Table);
        browser.setInitialScale(100);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setDisplayZoomControls(false);
        browser.loadUrl("http://alexgorlov99.ru/smarthomeproject/weatherstation-data.php");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
