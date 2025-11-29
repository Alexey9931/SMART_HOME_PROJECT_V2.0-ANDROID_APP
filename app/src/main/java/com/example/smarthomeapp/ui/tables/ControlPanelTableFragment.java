package com.example.smarthomeapp.ui.tables;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.databinding.ControlpanelTableBinding;

public class ControlPanelTableFragment extends Fragment {
    private ControlpanelTableBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ControlpanelTableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WebView browser=root.findViewById(R.id.ControlPanel_Table);
        browser.setInitialScale(100);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setDisplayZoomControls(false);
        browser.loadUrl("http://alexgorlov99.ru/smarthomeproject2.0/controlpanel-data.php");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
