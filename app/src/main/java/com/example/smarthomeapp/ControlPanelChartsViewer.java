package com.example.smarthomeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomeapp.ui.chart_settings.ControlPanelChartsSettingsFragment;
import com.github.mikephil.charting.charts.LineChart;

public class ControlPanelChartsViewer extends AsyncTask<View,Void,String> {
    LineChart temp_chart;
    LineChart hum_chart;
    LineChart pressure_chart;
    TextView error_message;

    TextView temp_name;
    TextView hum_name;
    TextView pressure_name;

    int count = 0;

    Context context;
    //BackgroundWorker backgroundWorker = new BackgroundWorker(context);

    public ControlPanelChartsViewer(Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(View... params) {
        temp_chart = (LineChart) params[0];
        hum_chart = (LineChart) params[1];
        pressure_chart = (LineChart) params[2];
        error_message = (TextView) params[3];
        temp_name = (TextView) params[4];
        hum_name = (TextView) params[5];
        pressure_name = (TextView) params[6];
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        String mode = ControlPanelChartsSettingsFragment.choose_chart_mode;
        if ((ControlPanelValuesGetter.TemperatureList.size() == 0) &&
                (ControlPanelValuesGetter.HumidityList.size() == 0) &&
                (ControlPanelValuesGetter.PressureList.size() == 0) &&
                (ControlPanelValuesGetter.TimestampList.size() == 0))
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
            pressure_chart.setVisibility(View.VISIBLE);
            error_message.setVisibility(View.GONE);

            temp_name.setVisibility(View.VISIBLE);
            hum_name.setVisibility(View.VISIBLE);
            pressure_name.setVisibility(View.VISIBLE);

            ControlPanelChartsCreator create_chart = new ControlPanelChartsCreator();

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
            pressure_chart.setLayoutParams(layoutParams2);
            create_chart.Fill_Pressure_Chart(pressure_chart);
            RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(1300,150);
            layoutParams22.leftMargin = 15;
            layoutParams22.topMargin = 1985;
            pressure_name.setLayoutParams(layoutParams22);
        }
        else
        {
            error_message.setVisibility(View.GONE);
            if (ControlPanelChartsSettingsFragment.TEMP_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(1300,900);
                layoutParams.leftMargin = 15;
                layoutParams.topMargin = 70 + 1000*count;
                temp_chart.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams0 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams0.leftMargin = 15;
                layoutParams0.topMargin = -15 + 1000*count;
                temp_name.setLayoutParams(layoutParams0);

                ControlPanelChartsCreator create_chart = new ControlPanelChartsCreator();
                temp_chart.setVisibility(View.VISIBLE);
                temp_name.setVisibility(View.VISIBLE);
                create_chart.Fill_Temp_Chart(temp_chart);
                count++;
            }
            if (ControlPanelChartsSettingsFragment.HUM_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(1300,900);
                layoutParams1.leftMargin = 15;
                layoutParams1.topMargin = 70 + 1000*count;
                hum_chart.setLayoutParams(layoutParams1);
                RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams11.leftMargin = 15;
                layoutParams11.topMargin = -15 + 1000*count;
                hum_name.setLayoutParams(layoutParams11);

                ControlPanelChartsCreator create_chart = new ControlPanelChartsCreator();
                hum_chart.setVisibility(View.VISIBLE);
                hum_name.setVisibility(View.VISIBLE);
                create_chart.Fill_Hum_Chart(hum_chart);
                count++;
            }
            if (ControlPanelChartsSettingsFragment.PRESSURE_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(1300,900);
                layoutParams2.leftMargin = 15;
                layoutParams2.topMargin = 70 + 1000*count;
                pressure_chart.setLayoutParams(layoutParams2);
                RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams22.leftMargin = 15;
                layoutParams22.topMargin = -15 + 1000*count;
                pressure_name.setLayoutParams(layoutParams22);

                ControlPanelChartsCreator create_chart = new ControlPanelChartsCreator();
                pressure_chart.setVisibility(View.VISIBLE);
                pressure_name.setVisibility(View.VISIBLE);
                create_chart.Fill_Pressure_Chart(pressure_chart);
                count++;
            }
        }
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }
}
