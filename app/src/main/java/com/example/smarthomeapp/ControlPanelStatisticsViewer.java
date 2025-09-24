package com.example.smarthomeapp;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ControlPanelStatisticsViewer extends AsyncTask<View,Void,String> {
        TextView textView_avg_temp;
        TextView textView_avg_hum;
        TextView textView_avg_pressure;
        TextView textView_max_temp;
        TextView textView_max_hum;
        TextView textView_max_pressure;
        TextView textView_min_temp;
        TextView textView_min_hum;
        TextView textView_min_pressure;
        TextView textView_start_time;
        TextView textView_end_time;
        TextView textView_number_mesuamer;

        String avg_temp;
        String avg_hum;
        String avg_pressure;
        String max_temp;
        String max_hum;
        String max_pressure;
        String min_temp;
        String min_hum;
        String min_pressure;
        String st_time;
        String en_time;
        String number;

        CalculateDetails calculateDetails = new CalculateDetails();
        Context context;

        public ControlPanelStatisticsViewer(Context ctx){
            context = ctx;
        }
        @Override
        protected String doInBackground(View... params) {
            textView_avg_temp = (TextView) params[0];
            textView_avg_hum = (TextView) params[1];
            textView_avg_pressure = (TextView) params[2];
            textView_max_temp = (TextView) params[3];
            textView_max_hum = (TextView) params[4];
            textView_max_pressure = (TextView) params[5];
            textView_min_temp = (TextView) params[6];
            textView_min_hum = (TextView) params[7];
            textView_min_pressure = (TextView) params[8];
            textView_start_time = (TextView) params[9];
            textView_end_time = (TextView) params[10];
            textView_number_mesuamer = (TextView) params[11];

            try {
                avg_temp = String.format("%.1f", calculateDetails.CalculateAverage(ControlPanelValuesGetter.TemperatureList)) + " °C";
                avg_hum = String.format("%.1f", calculateDetails.CalculateAverage(ControlPanelValuesGetter.HumidityList)) + " %";
                avg_pressure = String.format("%.1f", calculateDetails.CalculateAverage(ControlPanelValuesGetter.PressureList)) + " мм.рт.ст.";
                max_temp = String.format("%.1f", calculateDetails.CalculateMax(ControlPanelValuesGetter.TemperatureList)) + " °C";
                max_hum = String.format("%.1f", calculateDetails.CalculateMax(ControlPanelValuesGetter.HumidityList)) + " %";
                max_pressure = String.format("%.1f", calculateDetails.CalculateMax(ControlPanelValuesGetter.PressureList)) + " мм.рт.ст.";
                min_temp = String.format("%.1f", calculateDetails.CalculateMin(ControlPanelValuesGetter.TemperatureList)) + " °C";
                min_hum = String.format("%.1f", calculateDetails.CalculateMin(ControlPanelValuesGetter.HumidityList)) + " %";
                min_pressure = String.format("%.1f", calculateDetails.CalculateMin(ControlPanelValuesGetter.PressureList)) + " мм.рт.ст.";
                st_time = ControlPanelValuesGetter.TimestampList.get(0);
                en_time = ControlPanelValuesGetter.TimestampList.get(ControlPanelValuesGetter.TimestampList.size() - 1);
                number = Integer.toString(ControlPanelValuesGetter.TimestampList.size());
            }
            catch(Exception e)
            {

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(context.getApplicationContext(),"Пожалуйста подождите, идут вычисления...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String result) {
            if ((ControlPanelValuesGetter.TemperatureList.size() != 0) &&
                    (ControlPanelValuesGetter.HumidityList.size() != 0) &&
                    (ControlPanelValuesGetter.PressureList.size() != 0) &&
                    (ControlPanelValuesGetter.TimestampList.size() != 0))
            {
                textView_avg_temp.setText(avg_temp);
                textView_avg_hum.setText(avg_hum);
                textView_avg_pressure.setText(avg_pressure);

                textView_max_temp.setText(max_temp);
                textView_max_hum.setText(max_hum);
                textView_max_pressure.setText(max_pressure);

                textView_min_temp.setText(min_temp);
                textView_min_hum.setText(min_hum);
                textView_min_pressure.setText(min_pressure);

                textView_start_time.setText(st_time);
                textView_end_time.setText(en_time);
                textView_number_mesuamer.setText(number);
            }
            else
            {
                Toast.makeText(context.getApplicationContext(),"Ошибка вычислений! (База Данных пустая!)",Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
}

