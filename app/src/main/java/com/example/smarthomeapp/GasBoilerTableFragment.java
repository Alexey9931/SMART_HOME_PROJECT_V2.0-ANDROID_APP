package com.example.smarthomeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.databinding.GasboilerDbTableFragmentBinding;

public class GasBoilerTableFragment extends Fragment {
    private GasboilerDbTableFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = GasboilerDbTableFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WebView browser=root.findViewById(R.id.GasboilerDB_Table);
        browser.setInitialScale(100);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setDisplayZoomControls(false);
        browser.loadUrl("http://alexgorlov99.ru/smarthomeproject/gasboiler-data.php");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
