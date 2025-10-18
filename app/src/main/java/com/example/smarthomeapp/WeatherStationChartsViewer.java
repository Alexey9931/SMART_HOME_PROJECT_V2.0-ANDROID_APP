package com.example.smarthomeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomeapp.ui.chart_settings.ControlPanelChartsSettingsFragment;
import com.example.smarthomeapp.ui.chart_settings.WeatherStationChartsSettingsFragment;
import com.github.mikephil.charting.charts.LineChart;

public class WeatherStationChartsViewer extends AsyncTask<View,Void,String> {
    LineChart temp_chart;
    LineChart hum_chart;
    LineChart rain_chart;
    LineChart wind_speed_chart;
    TableLayout wind_direct_table;
    TextView error_message;

    TextView temp_name;
    TextView hum_name;
    TextView wind_speed_name;
    TextView rain_name;
    TextView table_name;

    int count = 0;

    Context context;
    //BackgroundWorker backgroundWorker = new BackgroundWorker(context);

    public WeatherStationChartsViewer(Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(View... params) {
        temp_chart = (LineChart) params[0];
        hum_chart = (LineChart) params[1];
        rain_chart = (LineChart) params[2];
        wind_speed_chart = (LineChart) params[3];
        wind_direct_table = (TableLayout) params[4];
        error_message = (TextView) params[5];
        temp_name = (TextView) params[6];
        hum_name = (TextView) params[7];
        wind_speed_name = (TextView) params[8];
        rain_name = (TextView) params[9];
        table_name = (TextView) params[10];

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        String mode = WeatherStationChartsSettingsFragment.choose_chart_mode;
        if ((WeatherStationValuesGetter.TemperatureList.size() == 0) &&
                (WeatherStationValuesGetter.HumidityList.size() == 0) &&
                (WeatherStationValuesGetter.WindSpeedList.size() == 0) &&
                (WeatherStationValuesGetter.WindDirectList.size() == 0) &&
                (WeatherStationValuesGetter.RainFallList.size() == 0) &&
                (WeatherStationValuesGetter.TimestampList.size() == 0))
        {
            Toast.makeText(context,"Ошибка отображения графиков! (База Данных пустая!)",Toast.LENGTH_LONG).show();
        }
        else if (mode == "nothing")
        {
            error_message.setVisibility(View.VISIBLE);
        }
        else if (mode == "all_other")
        {
            temp_chart.setVisibility(View.VISIBLE);
            hum_chart.setVisibility(View.VISIBLE);
            rain_chart.setVisibility(View.VISIBLE);
            wind_speed_chart.setVisibility(View.VISIBLE);
            wind_direct_table.setVisibility(View.VISIBLE);
            error_message.setVisibility(View.GONE);

            temp_name.setVisibility(View.VISIBLE);
            hum_name.setVisibility(View.VISIBLE);
            wind_speed_name.setVisibility(View.VISIBLE);
            rain_name.setVisibility(View.VISIBLE);
            table_name.setVisibility(View.VISIBLE);

            WeatherStationChartsCreator create_chart = new WeatherStationChartsCreator();

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(1300,900);
            layoutParams.leftMargin = 15;
            layoutParams.topMargin = 70;
            temp_chart.setLayoutParams(layoutParams);
            create_chart.Fill_Temp_Chart(temp_chart);
            RelativeLayout.LayoutParams layoutParams0 = new RelativeLayout.LayoutParams(1300,150);
            layoutParams0.leftMargin = 15;
            layoutParams0.topMargin = -15;
            temp_name.setLayoutParams(layoutParams0);

            RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(1300,900);
            layoutParams1.leftMargin = 15;
            layoutParams1.topMargin = 1070;
            hum_chart.setLayoutParams(layoutParams1);
            create_chart.Fill_Hum_Chart(hum_chart);
            RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(1300,150);
            layoutParams11.leftMargin = 15;
            layoutParams11.topMargin = 985;
            hum_name.setLayoutParams(layoutParams11);

            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(1300,900);
            layoutParams2.leftMargin = 15;
            layoutParams2.topMargin = 2070;
            wind_speed_chart.setLayoutParams(layoutParams2);
            create_chart.Fill_Wind_Speed_Chart(wind_speed_chart);
            RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(1300,150);
            layoutParams22.leftMargin = 15;
            layoutParams22.topMargin = 1985;
            wind_speed_name.setLayoutParams(layoutParams22);

            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(1300,900);
            layoutParams3.leftMargin = 15;
            layoutParams3.topMargin = 3070;
            rain_chart.setLayoutParams(layoutParams3);
            create_chart.Fill_Rain_Chart(rain_chart);
            RelativeLayout.LayoutParams layoutParams33 = new RelativeLayout.LayoutParams(1300,150);
            layoutParams33.leftMargin = 15;
            layoutParams33.topMargin = 2985;
            rain_name.setLayoutParams(layoutParams33);

            RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(1040,RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(900,RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams8.leftMargin = 25;
            layoutParams8.topMargin = 4070;
            layoutParams9.leftMargin = 100;
            layoutParams9.topMargin = 3985;
            wind_direct_table.setLayoutParams(layoutParams8);
            TODO:
//            create_chart.Fill_Wind_Direct_Table(wind_direct_table,context);
            table_name.setLayoutParams(layoutParams9);
        }
        else
        {
            error_message.setVisibility(View.GONE);
            if (WeatherStationChartsSettingsFragment.TEMP_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(1300,900);
                layoutParams.leftMargin = 15;
                layoutParams.topMargin = 70 + 1000*count;
                temp_chart.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams0 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams0.leftMargin = 15;
                layoutParams0.topMargin = -15 + 1000*count;
                temp_name.setLayoutParams(layoutParams0);

                WeatherStationChartsCreator create_chart = new WeatherStationChartsCreator();
                temp_chart.setVisibility(View.VISIBLE);
                temp_name.setVisibility(View.VISIBLE);
                create_chart.Fill_Temp_Chart(temp_chart);
                count++;
            }
            if (WeatherStationChartsSettingsFragment.HUM_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(1300,900);
                layoutParams1.leftMargin = 15;
                layoutParams1.topMargin = 70 + 1000*count;
                hum_chart.setLayoutParams(layoutParams1);
                RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams11.leftMargin = 15;
                layoutParams11.topMargin = -15 + 1000*count;
                hum_name.setLayoutParams(layoutParams11);

                WeatherStationChartsCreator create_chart = new WeatherStationChartsCreator();
                hum_chart.setVisibility(View.VISIBLE);
                hum_name.setVisibility(View.VISIBLE);
                create_chart.Fill_Hum_Chart(hum_chart);
                count++;
            }
            if (WeatherStationChartsSettingsFragment.WIND_SPEED_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(1300,900);
                layoutParams2.leftMargin = 15;
                layoutParams2.topMargin = 70 + 1000*count;
                wind_speed_chart.setLayoutParams(layoutParams2);
                RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams22.leftMargin = 15;
                layoutParams22.topMargin = -15 + 1000*count;
                wind_speed_name.setLayoutParams(layoutParams22);

                WeatherStationChartsCreator create_chart = new WeatherStationChartsCreator();
                wind_speed_chart.setVisibility(View.VISIBLE);
                wind_speed_name.setVisibility(View.VISIBLE);
                create_chart.Fill_Wind_Speed_Chart(wind_speed_chart);
                count++;
            }
            if (WeatherStationChartsSettingsFragment.RAIN_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(1300,900);
                layoutParams3.leftMargin = 15;
                layoutParams3.topMargin = 70 + 1000*count;
                rain_chart.setLayoutParams(layoutParams3);
                RelativeLayout.LayoutParams layoutParams33 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams33.leftMargin = 15;
                layoutParams33.topMargin = -15 + 1000*count;
                rain_name.setLayoutParams(layoutParams33);

                WeatherStationChartsCreator create_chart = new WeatherStationChartsCreator();
                rain_chart.setVisibility(View.VISIBLE);
                rain_name.setVisibility(View.VISIBLE);
                create_chart.Fill_Rain_Chart(rain_chart);
                count++;
            }
            if (WeatherStationChartsSettingsFragment.WIND_DIRECT_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(1040,RelativeLayout.LayoutParams.WRAP_CONTENT);
                RelativeLayout.LayoutParams layoutParams44 = new RelativeLayout.LayoutParams(900,RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams4.leftMargin = 25;
                layoutParams4.topMargin = 100 + 1000*count+30;
                layoutParams44.leftMargin = 100;
                layoutParams44.topMargin = -15 + 1000*count;
                wind_direct_table.setLayoutParams(layoutParams4);
                table_name.setLayoutParams(layoutParams44);

                WeatherStationChartsCreator create_chart = new WeatherStationChartsCreator();
                wind_direct_table.setVisibility(View.VISIBLE);
                table_name.setVisibility(View.VISIBLE);
                TODO:
//                create_chart.Fill_Wind_Direct_Table(wind_direct_table, context);
                count++;
            }
        }
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }
}
