package com.example.smarthomeapp;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ControlPanelChartsCreator {
    public void Fill_Temp_Chart(LineChart chart)
    {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < ControlPanelValuesGetter.TemperatureList.size(); i++)
        {
            entries.add(new Entry(i, Float.parseFloat(ControlPanelValuesGetter.TemperatureList.get(i))));
        }

        // На основании массива точек создадим первую линию с названием
        LineDataSet dataset = new LineDataSet(entries, "Температура в помещении, °C");
        dataset.setDrawFilled(true);
        dataset.setColor(Color.LTGRAY);
        dataset.setFillColor(Color.LTGRAY);
        dataset.setCircleColor(Color.LTGRAY);
        // Создадим переменную данных для графика
        LineData data = new LineData(dataset);
        // Передадим данные для графика в сам график
        chart.setData(data);
        chart.animateX(500);
        chart.setDrawBorders(true);
        chart.setDrawMarkers(true);
        chart.getDescription().setEnabled(false);

        //chart.setVisibleXRangeMaximum(4);
        //chart.setScaleXEnabled(false);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setTextSize(12f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ControlPanelValuesGetter.TimestampList));
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(3, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setTextSize(12f);
        // Не забудем отправить команду на перерисовку кадра, иначе график не отобразится
        chart.invalidate();
    }
    public void Fill_Hum_Chart(LineChart chart)
    {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < ControlPanelValuesGetter.HumidityList.size(); i++)
        {
            entries.add(new Entry(i, Float.parseFloat(ControlPanelValuesGetter.HumidityList.get(i))));
        }

        // На основании массива точек создадим первую линию с названием
        LineDataSet dataset = new LineDataSet(entries, "Влажность в помещении, %");
        dataset.setDrawFilled(true);
        dataset.setColor(Color.rgb(255, 150,0));
        dataset.setFillColor(Color.rgb(255, 150,0));
        dataset.setCircleColor(Color.rgb(255, 150,0));
        // Создадим переменную данных для графика
        LineData data = new LineData(dataset);
        // Передадим данные для графика в сам график
        chart.setData(data);
        chart.animateX(500);
        chart.setDrawBorders(true);
        chart.setDrawMarkers(true);
        chart.getDescription().setEnabled(false);

        //chart.setVisibleXRangeMaximum(4);
        //chart.setScaleXEnabled(false);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setTextSize(12f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ControlPanelValuesGetter.TimestampList));
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(3, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setTextSize(12f);
        // Не забудем отправить команду на перерисовку кадра, иначе график не отобразится
        chart.invalidate();
    }
    public void Fill_Pressure_Chart(LineChart chart)
    {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < ControlPanelValuesGetter.PressureList.size(); i++)
        {
            entries.add(new Entry(i, Float.parseFloat(ControlPanelValuesGetter.PressureList.get(i))));
        }

        // На основании массива точек создадим первую линию с названием
        LineDataSet dataset = new LineDataSet(entries, "Атмосферное давление, мм.рт.ст");
        dataset.setDrawFilled(true);
        dataset.setColor(Color.DKGRAY);
        dataset.setFillColor(Color.DKGRAY);
        dataset.setCircleColor(Color.DKGRAY);
        // Создадим переменную данных для графика
        LineData data = new LineData(dataset);
        // Передадим данные для графика в сам график
        chart.setData(data);
        chart.animateX(500);
        chart.setDrawBorders(true);
        chart.setDrawMarkers(true);
        chart.getDescription().setEnabled(false);

        //chart.setVisibleXRangeMaximum(4);
        //chart.setScaleXEnabled(false);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setTextSize(12f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ControlPanelValuesGetter.TimestampList));
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(3, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setTextSize(12f);
        // Не забудем отправить команду на перерисовку кадра, иначе график не отобразится
        chart.invalidate();
    }

}

