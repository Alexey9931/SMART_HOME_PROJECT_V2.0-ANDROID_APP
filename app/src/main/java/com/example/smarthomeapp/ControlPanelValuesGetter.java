package com.example.smarthomeapp;


import static com.example.smarthomeapp.ui.home.HomeFragment.time_error;
import static java.lang.Math.abs;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.example.smarthomeapp.ui.home.HomeFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlPanelValuesGetter extends AsyncTask<String,Void,String> {
    public static List<String> TemperatureList = new ArrayList<>();
    public static List<String> HumidityList = new ArrayList<>();
    public static List<String> PressureList = new ArrayList<>();
    public static List<String> TimestampList = new ArrayList<>();

    public static int ChartDaysMode = 1;

    public static String result = "";
    CalculateDetails calculateDetails = new CalculateDetails();

    Context context;

    public ControlPanelValuesGetter(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String login_url = "";
        int id = 1;
        String line = "";

        switch (ChartDaysMode) {
            case 1:
                login_url = params[0];
                break;
            case 3:
                login_url = params[1];
                break;
            case 5:
                login_url = params[2];
                break;
            case 7:
                login_url = params[3];
                break;
            default:
                break;
        }

        try {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(id), "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            result = "";
            line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            if (!result.isEmpty())
            {
                FillAllParams(result);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        TemperatureList.clear();
        HumidityList.clear();
        PressureList.clear();
        TimestampList.clear();
        Toast.makeText(context.getApplicationContext(),"Пожалуйста подождите, идет синхронизация с базой данных...",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            FillDisplayParam();
//            DisplayWeathForecast();
            Toast.makeText(context.getApplicationContext(),"Загрузка информации с БД завершена!",Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context.getApplicationContext(),"ОШИБКА загрузки информации с БД!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void FillAllParams(String result)
    {
        int i = 0;
        int k = 0;
        int id = 1;
        String Result;
        while (i < result.length())
        {
            //Temperature
            while(result.charAt(i) != '|')
            {
                i++;
            }
            Result = result.substring(k,i);
            TemperatureList.add(id-1, Result);
            i++;
            k = i;
            //Humidity
            while(result.charAt(i) != '|')
            {
                i++;
            }
            Result = result.substring(k,i);
            HumidityList.add(id-1, String.valueOf(Float.parseFloat(Result)));
            i++;
            k = i;
            //Pressure
            while(result.charAt(i) != '|')
            {
                i++;
            }
            Result = result.substring(k,i);
            PressureList.add(id-1, Result);
            i++;
            k = i;
            //Timestamp
            while(result.charAt(i) != '|')
            {
                i++;
            }
            Result = result.substring(k,i);
            TimestampList.add(id-1, Result);
            i=i+6;
            k = i;
            id++;
        }
        //Форматирование строки времени для отображение на главном окне
        String[] parts = TimestampList.get(TimestampList.size() - 1).split("[-: ]",6);
        switch(parts[1])
        {
            case "01":
                parts[1] = "Янв";
                break;
            case "02":
                parts[1] = "Фев";
                break;
            case "03":
                parts[1] = "Мар";
                break;
            case "04":
                parts[1] = "Апр";
                break;
            case "05":
                parts[1] = "Май";
                break;
            case "06":
                parts[1] = "Июн";
                break;
            case "07":
                parts[1] = "Июл";
                break;
            case "08":
                parts[1] = "Авг";
                break;
            case "09":
                parts[1] = "Сен";
                break;
            case "10":
                parts[1] = "Окт";
                break;
            case "11":
                parts[1] = "Ноя";
                break;
            case "12":
                parts[1] = "Дек";
                break;
        }
        if (i != 0)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            HomeFragment.time_for_display =
                    parts[2]+"/"+parts[1]+"/"+parts[0]+"\n"+parts[3]+":"+parts[4]+":"+parts[5];
            Date current_time = new Date();
            Date display_time = null;
            try {
                display_time = (Date)format.parse( TimestampList.get(TimestampList.size() - 1).replace(',', ' '));
                long delta = abs((current_time.getTime()- display_time.getTime()))/1000;//разница в секундах
                System.out.println("time"+delta);
                if (delta > 600)
                {
                    // TODO
//                    time_error.setVisibility(View.VISIBLE);
                }
                else
                {
                    // TODO
//                    time_error.setVisibility(View.INVISIBLE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //Форматирование времени для отображения на графиках
        for(int n = 0; n < TimestampList.size(); n++)
        {
            String[] part = TimestampList.get(n).split("[-: ]",6);
            switch(part[1])
            {
                case "01":
                    part[1] = "Янв";
                    break;
                case "02":
                    part[1] = "Фев";
                    break;
                case "03":
                    part[1] = "Мар";
                    break;
                case "04":
                    part[1] = "Апр";
                    break;
                case "05":
                    part[1] = "Май";
                    break;
                case "06":
                    part[1] = "Июн";
                    break;
                case "07":
                    part[1] = "Июл";
                    break;
                case "08":
                    part[1] = "Авг";
                    break;
                case "09":
                    part[1] = "Сен";
                    break;
                case "10":
                    part[1] = "Окт";
                    break;
                case "11":
                    part[1] = "Ноя";
                    break;
                case "12":
                    part[1] = "Дек";
                    break;
            }
            TimestampList.set(n,part[3]+":"+part[4]+" ("+part[2]+" "+part[1]+")");
        }
    }

    public void FillDisplayParam()
    {
        try {
            HomeFragment.HOME_TEMP.setText(String.format("%.1f",
                    Float.valueOf(TemperatureList.get(TemperatureList.size() - 1))));
            HomeFragment.HOME_HUM.setText(String.format("%.1f",
                    Float.valueOf(HumidityList.get(HumidityList.size() - 1))));
            HomeFragment.PRESSURE.setText(String.format("%.0f",
                    Float.valueOf(PressureList.get(PressureList.size() - 1))));
            HomeFragment.TIME.setText(HomeFragment.time_for_display);
        }
        catch (Exception e)
        {

        }
    }

//    public void DisplayWeathForecast()
//    {
//        int total_amount = 4;//кол-во интервалов
//        int num = 0;
//        int ind = 0;
//
//        List<String> PART1_str_temp = new ArrayList<>();//ул темп
//        List<String> PART2_str_temp = new ArrayList<>();
//        List<String> PART3_str_temp = new ArrayList<>();
//        List<String> PART4_str_temp = new ArrayList<>();
//        List<String> PART1_pres = new ArrayList<>();//давление
//        List<String> PART2_pres = new ArrayList<>();
//        List<String> PART3_pres = new ArrayList<>();
//        List<String> PART4_pres = new ArrayList<>();
//        List<String> PART1_rain = new ArrayList<>();//осадки
//        List<String> PART2_rain = new ArrayList<>();
//        List<String> PART3_rain = new ArrayList<>();
//        List<String> PART4_rain = new ArrayList<>();
//        List<String> PART1_speed = new ArrayList<>();//скорость
//        List<String> PART2_speed = new ArrayList<>();
//        List<String> PART3_speed = new ArrayList<>();
//        List<String> PART4_speed = new ArrayList<>();
//        List<String> data = new ArrayList<>();
//        List<Float> street_temp = new ArrayList<>();
//        List<Float> pressure = new ArrayList<>();
//        List<Float> rain = new ArrayList<>();
//        List<Float> speed = new ArrayList<>();
//
//        while(num < (ControlPanelValuesGetter.LIST11.size()/total_amount))
//        {
//            PART1_str_temp.add(ind, ControlPanelValuesGetter.LIST1.get(num));
//            PART1_pres.add(ind, ControlPanelValuesGetter.LIST9.get(num));
//            PART1_rain.add(ind, ControlPanelValuesGetter.LIST3.get(num));
//            PART1_speed.add(ind, ControlPanelValuesGetter.LIST6.get(num));
//            num++;
//            ind++;
//        }
//        street_temp.add(0,calculateDetails.CalculateAverage(PART1_str_temp));
//        pressure.add(0,calculateDetails.CalculateAverage(PART1_pres));
//        rain.add(0,calculateDetails.CalculateAverage(PART1_rain));
//        speed.add(0,calculateDetails.CalculateAverage(PART1_speed));
//        data.add(0, ControlPanelValuesGetter.LIST11.get(0).substring(0,5)+" - "+ ControlPanelValuesGetter.LIST11.get(ControlPanelValuesGetter.LIST11.size()/total_amount).substring(0,5));
//        ind = 0;
//        while(num < (2* ControlPanelValuesGetter.LIST11.size()/total_amount))
//        {
//            PART2_str_temp.add(ind, ControlPanelValuesGetter.LIST1.get(num));
//            PART2_pres.add(ind, ControlPanelValuesGetter.LIST9.get(num));
//            PART2_rain.add(ind, ControlPanelValuesGetter.LIST3.get(num));
//            PART2_speed.add(ind, ControlPanelValuesGetter.LIST6.get(num));
//            num++;
//            ind++;
//        }
//        street_temp.add(1,calculateDetails.CalculateAverage(PART2_str_temp));
//        pressure.add(1,calculateDetails.CalculateAverage(PART2_pres));
//        rain.add(1,calculateDetails.CalculateAverage(PART2_rain));
//        speed.add(1,calculateDetails.CalculateAverage(PART2_speed));
//        data.add(1, ControlPanelValuesGetter.LIST11.get(1+ ControlPanelValuesGetter.LIST11.size()/total_amount).substring(0,5)+" - "+ ControlPanelValuesGetter.LIST11.get(1+2* ControlPanelValuesGetter.LIST11.size()/total_amount).substring(0,5));
//        ind = 0;
//        while(num < (3* ControlPanelValuesGetter.LIST11.size()/total_amount))
//        {
//            PART3_str_temp.add(ind, ControlPanelValuesGetter.LIST1.get(num));
//            PART3_pres.add(ind, ControlPanelValuesGetter.LIST9.get(num));
//            PART3_rain.add(ind, ControlPanelValuesGetter.LIST3.get(num));
//            PART3_speed.add(ind, ControlPanelValuesGetter.LIST6.get(num));
//            num++;
//            ind++;
//        }
//        street_temp.add(2,calculateDetails.CalculateAverage(PART3_str_temp));
//        pressure.add(2,calculateDetails.CalculateAverage(PART3_pres));
//        rain.add(2,calculateDetails.CalculateAverage(PART3_rain));
//        speed.add(2,calculateDetails.CalculateAverage(PART3_speed));
//        data.add(2, ControlPanelValuesGetter.LIST11.get(1+2* ControlPanelValuesGetter.LIST11.size()/total_amount).substring(0,5)+" - "+ ControlPanelValuesGetter.LIST11.get(1+3* ControlPanelValuesGetter.LIST11.size()/total_amount).substring(0,5));
//        ind = 0;
//        while(num < (4* ControlPanelValuesGetter.LIST11.size()/total_amount))
//        {
//            PART4_str_temp.add(ind, ControlPanelValuesGetter.LIST1.get(num));
//            PART4_pres.add(ind, ControlPanelValuesGetter.LIST9.get(num));
//            PART4_rain.add(ind, ControlPanelValuesGetter.LIST3.get(num));
//            PART4_speed.add(ind, ControlPanelValuesGetter.LIST6.get(num));
//            num++;
//            ind++;
//        }
//        street_temp.add(3,calculateDetails.CalculateAverage(PART4_str_temp));
//        pressure.add(3,calculateDetails.CalculateAverage(PART4_pres));
//        rain.add(3,calculateDetails.CalculateAverage(PART4_rain));
//        speed.add(3,calculateDetails.CalculateAverage(PART4_speed));
//        data.add(3, ControlPanelValuesGetter.LIST11.get(1+3* ControlPanelValuesGetter.LIST11.size()/total_amount).substring(0,5)+" - "+ ControlPanelValuesGetter.LIST11.get(ControlPanelValuesGetter.LIST11.size()-1).substring(0,5));
//
//
//        int COLS = 4;
//        //for (int i = 0; i < (Firebase.LIST10.size()/4) + 1; i++)
//        int i = 0;
//        //строка с временем
//        TableRow tableRow = new TableRow(context);
//        tableRow.setBackgroundColor(Color.WHITE);
//        tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        for (int j = 0; j < COLS; j++)
//        {
//            TextView textView1 = new TextView(context);
//            textView1.setTextSize(context.getResources().getDimension(R.dimen.text_size_13));
//            textView1.setTextColor(Color.parseColor("#FF3700B3"));
//            textView1.setTypeface(null, Typeface.BOLD_ITALIC);
//            textView1.setGravity(Gravity.CENTER);
//            textView1.setText(data.get(j));
//            tableRow.addView(textView1, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
//        }
//        HomeFragment.Table.addView(tableRow, i);
//        i++;
//        //строка с картинками
//        TableRow tableRow1 = new TableRow(context);
//        tableRow1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
//        tableRow1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        for (int j = 0; j < COLS; j++)
//        {
//            ImageView image = new ImageView(context);
//
//            if ((speed.get(j) == 0f) && (rain.get(j) <= 10f))
//            {
//                image.setImageResource(R.drawable.weath_sunny);
//            }
//            else if ((speed.get(j) >= 10f) && (rain.get(j) <= 10f))
//            {
//                image.setImageResource(R.drawable.weath_only_clouds);
//            }
//            else if ((speed.get(j) < 10f) && (rain.get(j) <= 10f))
//            {
//                image.setImageResource(R.drawable.weath_cloud);
//            }
//            else if ((rain.get(j) < 90f) && (rain.get(j) > 15f))
//            {
//                image.setImageResource(R.drawable.weath_rainy);
//            } else
//            {
//                image.setImageResource(R.drawable.weath_sunny);
//            }
//
//            //image.setImageResource(R.drawable.weath_sunny);
//
//            tableRow1.addView(image, new TableRow.LayoutParams(500, 200, 0.5f));
//        }
//        HomeFragment.Table.addView(tableRow1, i);
//        i++;
//        //строка с ул температурой
//        TableRow tableRow2 = new TableRow(context);
//        tableRow2.setBackgroundColor(Color.WHITE);
//        tableRow2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        for (int j = 0; j < COLS; j++)
//        {
//            TextView textView1 = new TextView(context);
//            textView1.setTextSize(context.getResources().getDimension(R.dimen.text_size_13));
//            textView1.setTextColor(Color.parseColor("#FF3700B3"));
//            textView1.setTypeface(null, Typeface.BOLD);
//            textView1.setGravity(Gravity.CENTER);
//            textView1.setText(String.format(Locale.ROOT,"%.1f",street_temp.get(j)) + " °C");
//
//            tableRow2.addView(textView1, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
//        }
//        HomeFragment.Table.addView(tableRow2, i);
//        i++;
//        //строка с давлением
//        TableRow tableRow3 = new TableRow(context);
//        tableRow3.setBackgroundColor(Color.WHITE);
//        tableRow3.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        for (int j = 0; j < COLS; j++)
//        {
//            TextView textView1 = new TextView(context);
//            textView1.setTextSize(context.getResources().getDimension(R.dimen.text_size_13));
//            textView1.setTextColor(Color.parseColor("#FF3700B3"));
//            textView1.setTypeface(null, Typeface.BOLD);
//            textView1.setGravity(Gravity.CENTER);
//            textView1.setText(String.format(Locale.ROOT,"%.1f",pressure.get(j)) + " мм.р.с");
//
//            tableRow3.addView(textView1, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
//        }
//        HomeFragment.Table.addView(tableRow3, i);
//        i++;
//        //строка с осадками
//        TableRow tableRow4 = new TableRow(context);
//        tableRow4.setBackgroundColor(Color.WHITE);
//        tableRow4.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        for (int j = 0; j < COLS; j++)
//        {
//            TextView textView1 = new TextView(context);
//            textView1.setTextSize(context.getResources().getDimension(R.dimen.text_size_13));
//            textView1.setTextColor(Color.parseColor("#FF3700B3"));
//            textView1.setTypeface(null, Typeface.BOLD);
//            textView1.setGravity(Gravity.CENTER);
//            textView1.setText(String.format(Locale.ROOT,"%.1f",rain.get(j)) + " %");
//
//            tableRow4.addView(textView1, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
//        }
//        HomeFragment.Table.addView(tableRow4, i);
//        i++;
//        //строка с скоростью
//        TableRow tableRow5 = new TableRow(context);
//        tableRow5.setBackgroundColor(Color.WHITE);
//        tableRow5.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//        for (int j = 0; j < COLS; j++)
//        {
//            TextView textView1 = new TextView(context);
//            textView1.setTextSize(context.getResources().getDimension(R.dimen.text_size_13));
//            textView1.setTextColor(Color.parseColor("#FF3700B3"));
//            textView1.setTypeface(null, Typeface.BOLD);
//            textView1.setGravity(Gravity.CENTER);
//            textView1.setText(String.format(Locale.ROOT,"%.1f",speed.get(j)) + " м/с");
//
//            tableRow5.addView(textView1, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
//        }
//        HomeFragment.Table.addView(tableRow5, i);
//        i++;
//    }
}
