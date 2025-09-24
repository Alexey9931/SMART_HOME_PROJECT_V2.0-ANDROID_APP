package com.example.smarthomeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomeapp.ui.chart_settings.ControlPanelChartsSettingsFragment;
import com.example.smarthomeapp.ui.chart_settings.GasBoilerChartsSettingsFragment;
import com.github.mikephil.charting.charts.LineChart;

public class GasBoilerChartsViewer extends AsyncTask<View,Void,String> {
    LineChart setpoint_temp_chart;
    LineChart current_temp_chart;
    LineChart status_chart;
    TextView error_message;

    TextView setpoint_temp_name;
    TextView current_temp_name;
    TextView status_name;

    int count = 0;

    Context context;
    //BackgroundWorker backgroundWorker = new BackgroundWorker(context);

    public GasBoilerChartsViewer(Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(View... params) {
        setpoint_temp_chart = (LineChart) params[0];
        current_temp_chart = (LineChart) params[1];
        status_chart = (LineChart) params[2];
        error_message = (TextView) params[3];
        setpoint_temp_name = (TextView) params[4];
        current_temp_name = (TextView) params[5];
        status_name = (TextView) params[6];

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        String mode = GasBoilerChartsSettingsFragment.choose_chart_mode;
        if ((GasBoilerValuesGetter.SetpointTempList.size() == 0) &&
                (GasBoilerValuesGetter.CurrentTempList.size() == 0) &&
                (GasBoilerValuesGetter.StatusList.size() == 0) &&
                (GasBoilerValuesGetter.TimestampList.size() == 0))
        {
            Toast.makeText(context,"Ошибка отображения графиков! (База Данных пустая!)",Toast.LENGTH_LONG).show();
        }
        else if (mode == "nothing")
        {
            error_message.setVisibility(View.VISIBLE);
        }
        else if (mode == "all_other")
        {
            setpoint_temp_chart.setVisibility(View.VISIBLE);
            current_temp_chart.setVisibility(View.VISIBLE);
            status_chart.setVisibility(View.VISIBLE);
            error_message.setVisibility(View.GONE);

            setpoint_temp_name.setVisibility(View.VISIBLE);
            current_temp_name.setVisibility(View.VISIBLE);
            status_name.setVisibility(View.VISIBLE);

            GasBoilerChartsCreator create_chart = new GasBoilerChartsCreator();

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(1300,900);
            layoutParams.leftMargin = 15;
            layoutParams.topMargin = 70;
            setpoint_temp_chart.setLayoutParams(layoutParams);
            create_chart.Fill_SetpointTemp_Chart(setpoint_temp_chart);
            RelativeLayout.LayoutParams layoutParams0 = new RelativeLayout.LayoutParams(1300,150);
            layoutParams0.leftMargin = 15;
            layoutParams0.topMargin = -15;
            setpoint_temp_name.setLayoutParams(layoutParams0);

            RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(1300,900);
            layoutParams1.leftMargin = 15;
            layoutParams1.topMargin = 1070;
            current_temp_chart.setLayoutParams(layoutParams1);
            create_chart.Fill_CurrentTemp_Chart(current_temp_chart);
            RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(1300,150);
            layoutParams11.leftMargin = 15;
            layoutParams11.topMargin = 985;
            current_temp_name.setLayoutParams(layoutParams11);

            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(1300,900);
            layoutParams2.leftMargin = 15;
            layoutParams2.topMargin = 2070;
            status_chart.setLayoutParams(layoutParams2);
            create_chart.Fill_Status_Chart(status_chart);
            RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(1300,150);
            layoutParams22.leftMargin = 15;
            layoutParams22.topMargin = 1985;
            status_name.setLayoutParams(layoutParams22);
        }
        else
        {
            error_message.setVisibility(View.GONE);
            if (GasBoilerChartsSettingsFragment.SETPOINT_TEMP_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(1300,900);
                layoutParams.leftMargin = 15;
                layoutParams.topMargin = 70 + 1000*count;
                setpoint_temp_chart.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams0 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams0.leftMargin = 15;
                layoutParams0.topMargin = -15 + 1000*count;
                setpoint_temp_name.setLayoutParams(layoutParams0);

                GasBoilerChartsCreator create_chart = new GasBoilerChartsCreator();
                setpoint_temp_chart.setVisibility(View.VISIBLE);
                setpoint_temp_name.setVisibility(View.VISIBLE);
                create_chart.Fill_SetpointTemp_Chart(setpoint_temp_chart);
                count++;
            }
            if (GasBoilerChartsSettingsFragment.CURRENT_TEMP_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(1300,900);
                layoutParams1.leftMargin = 15;
                layoutParams1.topMargin = 70 + 1000*count;
                current_temp_chart.setLayoutParams(layoutParams1);
                RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams11.leftMargin = 15;
                layoutParams11.topMargin = -15 + 1000*count;
                current_temp_name.setLayoutParams(layoutParams11);

                GasBoilerChartsCreator create_chart = new GasBoilerChartsCreator();
                current_temp_chart.setVisibility(View.VISIBLE);
                current_temp_name.setVisibility(View.VISIBLE);
                create_chart.Fill_CurrentTemp_Chart(current_temp_chart);
                count++;
            }
            if (GasBoilerChartsSettingsFragment.STATUS_MODE == true)
            {
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(1300,900);
                layoutParams2.leftMargin = 15;
                layoutParams2.topMargin = 70 + 1000*count;
                status_chart.setLayoutParams(layoutParams2);
                RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(1300,150);
                layoutParams22.leftMargin = 15;
                layoutParams22.topMargin = -15 + 1000*count;
                status_name.setLayoutParams(layoutParams22);

                GasBoilerChartsCreator create_chart = new GasBoilerChartsCreator();
                status_chart.setVisibility(View.VISIBLE);
                status_name.setVisibility(View.VISIBLE);
                create_chart.Fill_Status_Chart(status_chart);
                count++;
            }
        }
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }
}
