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

public class GasBoilerStatisticsViewer extends AsyncTask<View,Void,String> {
        TextView textView_avg_setpoint_temp;
        TextView textView_avg_current_temp;
        TextView textView_max_setpoint_temp;
        TextView textView_max_current_temp;
        TextView textView_min_setpoint_temp;
        TextView textView_min_current_temp;
        TextView textView_start_time;
        TextView textView_end_time;
        TextView textView_number_mesuamer;

        String avg_setpoint_temp;
        String avg_current_temp;
        String max_setpoint_temp;
        String max_current_temp;
        String min_setpoint_temp;
        String min_current_temp;
        String st_time;
        String en_time;
        String number;

        CalculateDetails calculateDetails = new CalculateDetails();
        Context context;

        public GasBoilerStatisticsViewer(Context ctx){
            context = ctx;
        }
        @Override
        protected String doInBackground(View... params) {
            textView_avg_setpoint_temp = (TextView) params[0];
            textView_avg_current_temp = (TextView) params[1];
            textView_max_setpoint_temp = (TextView) params[2];
            textView_max_current_temp = (TextView) params[3];
            textView_min_setpoint_temp = (TextView) params[4];
            textView_min_current_temp = (TextView) params[5];
            textView_start_time = (TextView) params[6];
            textView_end_time = (TextView) params[7];
            textView_number_mesuamer = (TextView) params[8];

            try {
                avg_setpoint_temp = String.format("%.1f", calculateDetails.CalculateAverage(GasBoilerValuesGetter.SetpointTempList)) + " °C";
                avg_current_temp = String.format("%.1f", calculateDetails.CalculateAverage(GasBoilerValuesGetter.CurrentTempList)) + " °C";
                max_setpoint_temp = String.format("%.1f", calculateDetails.CalculateAverage(GasBoilerValuesGetter.SetpointTempList)) + " °C";
                max_current_temp = String.format("%.1f", calculateDetails.CalculateAverage(GasBoilerValuesGetter.CurrentTempList)) + " °C";
                min_setpoint_temp = String.format("%.1f", calculateDetails.CalculateAverage(GasBoilerValuesGetter.SetpointTempList)) + " °C";
                min_current_temp = String.format("%.1f", calculateDetails.CalculateAverage(GasBoilerValuesGetter.CurrentTempList)) + " °C";
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
                textView_avg_setpoint_temp.setText(avg_setpoint_temp);
                textView_avg_current_temp.setText(avg_current_temp);

                textView_max_setpoint_temp.setText(max_setpoint_temp);
                textView_max_current_temp.setText(max_current_temp);

                textView_min_setpoint_temp.setText(min_setpoint_temp);
                textView_min_current_temp.setText(min_current_temp);

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

