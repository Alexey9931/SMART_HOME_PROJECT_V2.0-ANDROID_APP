package com.example.smarthomeapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthomeapp.ControlPanelValuesGetter;
import com.example.smarthomeapp.GasBoilerValuesGetter;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.WeatherStationValuesGetter;
import com.example.smarthomeapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public static ImageView imagewind;
    public static TextView STREET_TEMP;
    public static TextView STREET_HUM;
    public static TextView HOME_TEMP;
    public static TextView HOME_HUM;
    public static TextView RAIN;
    public static TextView VBat;
    public static TextView WIND_SPEED;
    public static TextView WIND_DIRECTION;
    public static TextView PRESSURE;
    public static TextView TIME;
    public static ImageView weath_forecast;
    public static TableLayout Table;
    public static ImageView time_error;

    public static String time_for_display = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Firebase firebase = new Firebase();


        imagewind = root.findViewById(R.id.imagewind);
        STREET_TEMP = root.findViewById(R.id.STREET_TEMP);
        STREET_HUM = root.findViewById(R.id.STREET_HUM);
        HOME_TEMP = root.findViewById(R.id.HOME_TEMP);
        HOME_HUM = root.findViewById(R.id.HOME_HUM);
        RAIN = root.findViewById(R.id.RAIN);
        VBat = root.findViewById(R.id.VBat);
        WIND_SPEED = root.findViewById(R.id.WIND_SPEED);
        WIND_DIRECTION = root.findViewById(R.id.WIND_DIRECTION);
        PRESSURE = root.findViewById(R.id.PRESSURE);
        TIME = root.findViewById(R.id.TIME);
        weath_forecast = root.findViewById(R.id.imageView_weath_forecast);
        Table = root.findViewById(R.id.table);
        time_error = root.findViewById(R.id.time_error);
        time_error.setVisibility(View.INVISIBLE);

        ControlPanelValuesGetter controlPanelValuesGetter = new ControlPanelValuesGetter(getContext());
        controlPanelValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-controlpanel-7day.php");

        GasBoilerValuesGetter gasBoilerValuesGetter = new GasBoilerValuesGetter(getContext());
        gasBoilerValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboilercontroller-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboilercontroller-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboilercontroller-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-gasboilercontroller-7day.php");

        WeatherStationValuesGetter weatherStationValuesGetter = new WeatherStationValuesGetter(getContext());
        weatherStationValuesGetter.execute(
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-1day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-3day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-5day.php",
                "http://alexgorlov99.ru/smarthomeproject2.0/get-weatherstation-7day.php");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}