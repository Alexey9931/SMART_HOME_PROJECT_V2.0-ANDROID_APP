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

public class WeatherStationStatisticsViewer extends AsyncTask<View,Void,String> {
        TextView textView_avg_temp;
        TextView textView_avg_hum;
        TextView textView_avg_rainfall;
        TextView textView_avg_wind_speed;
        TextView textView_max_temp;
        TextView textView_max_hum;
        TextView textView_max_rainfall;
        TextView textView_max_wind_speed;
        TextView textView_min_temp;
        TextView textView_min_hum;
        TextView textView_min_rainfall;
        TextView textView_min_wind_speed;
        TextView textView_start_time;
        TextView textView_end_time;
        TextView textView_number_mesuamer;
        PieChart WindDirectDiagram;
        PieData pieData;

        String avg_temp;
        String avg_hum;
        String avg_rainfall;
        String avg_wind_speed;
        String max_temp;
        String max_hum;
        String max_rainfall;
        String max_wind_speed;
        String min_temp;
        String min_hum;
        String min_rainfall;
        String min_wind_speed;
        String st_time;
        String en_time;
        String number;

        CalculateDetails calculateDetails = new CalculateDetails();
        Context context;

        public WeatherStationStatisticsViewer(Context ctx){
            context = ctx;
        }
        @Override
        protected String doInBackground(View... params) {
            textView_avg_temp = (TextView) params[0];
            textView_avg_hum = (TextView) params[1];
            textView_avg_rainfall = (TextView) params[2];
            textView_avg_wind_speed = (TextView) params[3];
            textView_max_temp = (TextView) params[4];
            textView_max_hum = (TextView) params[5];
            textView_max_rainfall = (TextView) params[6];
            textView_max_wind_speed = (TextView) params[7];
            textView_min_temp = (TextView) params[8];
            textView_min_hum = (TextView) params[9];
            textView_min_rainfall = (TextView) params[10];
            textView_min_wind_speed = (TextView) params[11];
            WindDirectDiagram = (PieChart) params[12];
            textView_start_time = (TextView) params[13];
            textView_end_time = (TextView) params[14];
            textView_number_mesuamer = (TextView) params[15];

            try {
                avg_temp = String.format("%.1f", calculateDetails.CalculateAverage(WeatherStationValuesGetter.TemperatureList)) + " °C";
                avg_hum = String.format("%.1f", calculateDetails.CalculateAverage(WeatherStationValuesGetter.HumidityList)) + " %";
                avg_rainfall = String.format("%.1f", calculateDetails.CalculateAverage(WeatherStationValuesGetter.RainFallList)) + " %";
                avg_wind_speed = String.format("%.1f", calculateDetails.CalculateAverage(WeatherStationValuesGetter.WindSpeedList)) + " м/с";
                max_temp = String.format("%.1f", calculateDetails.CalculateMax(WeatherStationValuesGetter.TemperatureList)) + " °C";
                max_hum = String.format("%.1f", calculateDetails.CalculateMax(WeatherStationValuesGetter.HumidityList)) + " %";
                max_rainfall = String.format("%.1f", calculateDetails.CalculateMax(WeatherStationValuesGetter.RainFallList)) + " %";
                max_wind_speed = String.format("%.1f", calculateDetails.CalculateMax(WeatherStationValuesGetter.WindSpeedList)) + " м/с";
                min_temp = String.format("%.1f", calculateDetails.CalculateMin(WeatherStationValuesGetter.TemperatureList)) + " °C";
                min_hum = String.format("%.1f", calculateDetails.CalculateMin(WeatherStationValuesGetter.HumidityList)) + " %";
                min_rainfall = String.format("%.1f", calculateDetails.CalculateMin(WeatherStationValuesGetter.RainFallList)) + " %";
                min_wind_speed = String.format("%.1f", calculateDetails.CalculateMin(WeatherStationValuesGetter.WindSpeedList)) + " м/с";
                st_time = ControlPanelValuesGetter.TimestampList.get(0);
                en_time = ControlPanelValuesGetter.TimestampList.get(ControlPanelValuesGetter.TimestampList.size() - 1);
                number = Integer.toString(ControlPanelValuesGetter.TimestampList.size());

                CalculateDetails.Find_Wind_Direct_Percent(WeatherStationValuesGetter.WindDirectList);
                WindDirectDiagram.setUsePercentValues(true);
                WindDirectDiagram.getDescription().setEnabled(false);

                List<PieEntry> yVals = new ArrayList<>();
                List<Integer> colors = new ArrayList<>();
                if (CalculateDetails.NW_Count != 0) {
                    yVals.add(new PieEntry((float) CalculateDetails.NW_Count, "N-W"));
                    colors.add(Color.parseColor("#FF2A18"));
                }
                if (CalculateDetails.N_Count != 0) {
                    yVals.add(new PieEntry((float) CalculateDetails.N_Count, "N"));
                    colors.add(Color.parseColor("#FF9118"));
                }
                if (CalculateDetails.S_Count != 0) {
                    yVals.add(new PieEntry((float) CalculateDetails.S_Count, "S"));
                    colors.add(Color.parseColor("#FFE918"));
                }
                if (CalculateDetails.E_Count != 0) {
                    yVals.add(new PieEntry((float) CalculateDetails.E_Count, "E"));
                    colors.add(Color.parseColor("#ADFF18"));
                }
                if (CalculateDetails.W_Count != 0) {
                    yVals.add(new PieEntry((float) CalculateDetails.W_Count, "W"));
                    colors.add(Color.parseColor("#1AFF18"));
                }
                if (CalculateDetails.NE_Count != 0) {
                    yVals.add(new PieEntry((float) CalculateDetails.NE_Count, "N-E"));
                    colors.add(Color.parseColor("#18EBFF"));
                }
                if (CalculateDetails.SW_Count != 0) {
                    yVals.add(new PieEntry((float) CalculateDetails.SW_Count, "S-W"));
                    colors.add(Color.parseColor("#1829FF"));
                }
                if (CalculateDetails.SE_Count != 0) {
                    yVals.add(new PieEntry((float) CalculateDetails.SE_Count, "S-E"));
                    colors.add(Color.parseColor("#FF18ED"));
                }

                PieDataSet pieDataSet = new PieDataSet(yVals, "");
                pieDataSet.setColors(colors);
                pieDataSet.setValueTextSize(14f);
                pieData = new PieData(pieDataSet);
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
            if ((WeatherStationValuesGetter.TemperatureList.size() != 0) &&
                    (WeatherStationValuesGetter.HumidityList.size() != 0) &&
                    (WeatherStationValuesGetter.RainFallList.size() != 0) &&
                    (WeatherStationValuesGetter.WindSpeedList.size() != 0) &&
                    (WeatherStationValuesGetter.WindDirectList.size() != 0) &&
                    (WeatherStationValuesGetter.TimestampList.size() != 0))
            {
                WindDirectDiagram.setVisibility(View.VISIBLE);
                textView_avg_temp.setText(avg_temp);
                textView_avg_hum.setText(avg_hum);
                textView_avg_rainfall.setText(avg_rainfall);
                textView_avg_wind_speed.setText(avg_wind_speed);

                textView_max_temp.setText(max_temp);
                textView_max_hum.setText(max_hum);
                textView_max_rainfall.setText(max_rainfall);
                textView_max_wind_speed.setText(max_wind_speed);

                textView_min_temp.setText(min_temp);
                textView_min_hum.setText(min_hum);
                textView_min_rainfall.setText(min_rainfall);
                textView_min_wind_speed.setText(min_wind_speed);

                textView_start_time.setText(st_time);
                textView_end_time.setText(en_time);
                textView_number_mesuamer.setText(number);

                WindDirectDiagram.setData(pieData);
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

