package com.example.smarthomeapp;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class GasBoilerChartsCreator {
    public void Fill_SetpointTemp_Chart(LineChart chart)
    {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < GasBoilerValuesGetter.SetpointTempList.size(); i++)
        {
            entries.add(new Entry(i, Float.parseFloat(GasBoilerValuesGetter.SetpointTempList.get(i))));
        }

        // На основании массива точек создадим первую линию с названием
        LineDataSet dataset = new LineDataSet(entries, "Уставка, °C");
        dataset.setDrawFilled(true);
        dataset.setFillColor(Color.RED);
        dataset.setColor(Color.RED);
        dataset.setCircleColor(Color.RED);
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
        xAxis.setValueFormatter(new IndexAxisValueFormatter(GasBoilerValuesGetter.TimestampList));
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(3, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setTextSize(12f);
        // Не забудем отправить команду на перерисовку кадра, иначе график не отобразится
        chart.invalidate();
    }
    public void Fill_CurrentTemp_Chart(LineChart chart)
    {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < GasBoilerValuesGetter.CurrentTempList.size(); i++)
        {
            entries.add(new Entry(i, Float.parseFloat(GasBoilerValuesGetter.CurrentTempList.get(i))));
        }

        // На основании массива точек создадим первую линию с названием
        LineDataSet dataset = new LineDataSet(entries, "Текущая температура, %");
        dataset.setDrawFilled(true);
        dataset.setColor(Color.BLUE);
        dataset.setFillColor(Color.BLUE);
        dataset.setCircleColor(Color.BLUE);
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
        xAxis.setValueFormatter(new IndexAxisValueFormatter(GasBoilerValuesGetter.TimestampList));
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(3, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setTextSize(12f);
        // Не забудем отправить команду на перерисовку кадра, иначе график не отобразится
        chart.invalidate();
    }
    public void Fill_Status_Chart(LineChart chart)
    {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < GasBoilerValuesGetter.StatusList.size(); i++)
        {
            //entries.add(new Entry(i, 100 - 100 * Float.parseFloat(Firebase.LIST3.get(i))));
            entries.add(new Entry(i, Float.parseFloat(GasBoilerValuesGetter.StatusList.get(i))));
        }

        // На основании массива точек создадим первую линию с названием
        LineDataSet dataset = new LineDataSet(entries, "Количество осадков, %");
        dataset.setDrawFilled(true);
        dataset.setColor(Color.GREEN);
        dataset.setFillColor(Color.GREEN);
        dataset.setCircleColor(Color.GREEN);
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
        xAxis.setValueFormatter(new IndexAxisValueFormatter(GasBoilerValuesGetter.TimestampList));
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(3, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setTextSize(12f);
        // Не забудем отправить команду на перерисовку кадра, иначе график не отобразится
        chart.invalidate();
    }
}

