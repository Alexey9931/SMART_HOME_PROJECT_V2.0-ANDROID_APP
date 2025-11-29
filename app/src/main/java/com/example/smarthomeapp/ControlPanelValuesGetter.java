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
import java.util.Locale;

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
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            FillDisplayParam();
            Toast.makeText(context.getApplicationContext(),"Загрузка информации с БД завершена!",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context.getApplicationContext(),"ОШИБКА загрузки информации с БД!",Toast.LENGTH_SHORT).show();
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
        if (i != 0)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            HomeFragment.controlpanel_display_time =
                    parts[3]+":"+parts[4] + " " + parts[2]+"/"+parts[1]+"/"+parts[0];
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
            HomeFragment.HOME_TEMP.setText(String.format(Locale.US, "%1$+05.1f",
                    Float.valueOf(TemperatureList.get(TemperatureList.size() - 1))));
            HomeFragment.HOME_HUM.setText(String.format(Locale.US,"%1$04.1f",
                    Float.valueOf(HumidityList.get(HumidityList.size() - 1))));
            HomeFragment.PRESSURE.setText(String.format(Locale.US,"%1$03.0f",
                    Float.valueOf(PressureList.get(PressureList.size() - 1))));
            HomeFragment.CONTROL_PANEL_TIME.setText(HomeFragment.controlpanel_display_time);
        }
        catch (Exception e)
        {

        }
    }
}
