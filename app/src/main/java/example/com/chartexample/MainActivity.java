package example.com.chartexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    LineChart mpLineChart;
    private final LineChart[] charts = new LineChart[4];

    private static String TAG = "getjson_MainActivity";
    private static final String TAG_JSON="dong1";
    private static final String TAG_ID = "temp1";
    private static final String TAG_NAME = "cnt";
    private static final String TAG_JSON2="dong2";
    private static final String TAG_ID2 = "temp";
    private static final String TAG_NAME2 = "cnt";
    private static final String TAG_JSON3="dong3";
    private static final String TAG_ID3 = "temp";
    private static final String TAG_NAME3 = "cnt";
    private static final String TAG_JSON4="dong4";
    private static final String TAG_ID4 = "temp";
    private static final String TAG_NAME4 = "cnt";


    ArrayList<TempData> mArrayList = new ArrayList<>();
    ArrayList<TempData> mArrayList2 = new ArrayList<>();
    ArrayList<TempData> mArrayList3 = new ArrayList<>();
    ArrayList<TempData> mArrayList4 = new ArrayList<>();
    ArrayList<TempData> list = new ArrayList<>();
    String mJsonString;
//    String mJsonString2;
//    String mJsonString3;
//    String mJsonString4;
    LineDataSet lineDataSet1;
    LineDataSet lineDataSet2;
    LineDataSet lineDataSet3;
    LineDataSet lineDataSet4;
    ArrayList<ILineDataSet> dataSets;
    ArrayList<ILineDataSet> dataSets2;
    LineData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetData task = new GetData();
        task.execute("http://192.168.43.141/getjson.php");

        GetData task2 = new GetData();
        task2.execute("http://192.168.43.141/getjson2.php");

        GetData task3 = new GetData();
        task3.execute("http://192.168.43.141/getjson3.php");

        GetData task4 = new GetData();
        task4.execute("http://192.168.43.141/getjson4.php");

        mpLineChart = findViewById(R.id.chartExample);
        mpLineChart.getAxisLeft().setStartAtZero(false);
        mpLineChart.getAxisRight().setStartAtZero(false);
        setTitle("Temperature");
//        list = showResult();
//        System.out.println("메인메인메인메인메인메인메인메인메인메인메인메인 : " + list.size());
//        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "NodeMcu1");
//        LineDataSet lineDataSet2 = new LineDataSet(dataValues2(), "NodeMcu2");
//        LineDataSet lineDataSet3 = new LineDataSet(dataValues3(), "NodeMcu3");
//        LineDataSet lineDataSet4 = new LineDataSet(dataValues4(), "NodeMcu4");

//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet1);
//        dataSets.add(lineDataSet2);
//        dataSets.add(lineDataSet3);
//        dataSets.add(lineDataSet4);
//
//        lineDataSet1.setLineWidth(2);
//        lineDataSet1.setColor(Color.RED);
//        lineDataSet1.setCircleColor(Color.BLACK);

//        lineDataSet2.setLineWidth(2);
//        lineDataSet2.setColor(Color.BLACK);
//        lineDataSet2.setCircleColor(Color.BLACK);
//
//        lineDataSet3.setLineWidth(2);
//        lineDataSet3.setColor(Color.GREEN);
//        lineDataSet3.setCircleColor(Color.BLACK);
//
//        lineDataSet4.setLineWidth(2);
//        lineDataSet4.setColor(Color.BLUE);
//        lineDataSet4.setCircleColor(Color.BLACK);



//        LineData data = new LineData(dataSets);
//        mpLineChart.setDrawGridBackground(true);
//        mpLineChart.setBackgroundColor(Color.WHITE);
//        mpLineChart.setData(data);
//        mpLineChart.invalidate();



    }


    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if (result == null){

            }
            else {
                mJsonString = result;
                System.out.println("mJsonString : " + mJsonString.substring(6,11));
                dataSets = new ArrayList<>();
                if(mJsonString.substring(6,11).equals("dong1")) {
                    lineDataSet1 = new LineDataSet(dataValues1(), "NodeMcu1");
                    dataSets.add(lineDataSet1);

                    lineDataSet1.setLineWidth(2);
                    lineDataSet1.setColor(Color.RED);
//                    lineDataSet1.setCircleColor(Color.BLACK);
//                    lineDataSet1.setDrawCircleHole(false);
                    lineDataSet1.setDrawCircles(false);
                }

                else if(mJsonString.substring(6,11).equals("dong2")){
                    lineDataSet2 = new LineDataSet(dataValues2(), "NodeMcu2");
                    dataSets.add(lineDataSet2);

                    lineDataSet2.setLineWidth(2);
                    lineDataSet2.setColor(Color.BLACK);
//                    lineDataSet2.setCircleColor(Color.BLACK);
                    lineDataSet2.setDrawCircles(false);
                }
                else if(mJsonString.substring(6,11).equals("dong3")){
                    lineDataSet3 = new LineDataSet(dataValues3(),"NodeMcu3");
                    dataSets.add(lineDataSet3);

                    lineDataSet3.setLineWidth(2);
                    lineDataSet3.setColor(Color.GREEN);
//                    lineDataSet3.setCircleColor(Color.BLACK);
                    lineDataSet3.setDrawCircles(false);
                }
                else if(mJsonString.substring(6,11).equals("dong4")){
                    lineDataSet4 = new LineDataSet(dataValues4() ,"NodeMcu4");
                    dataSets.add(lineDataSet4);

                    lineDataSet4.setLineWidth(2);
                    lineDataSet4.setColor(Color.BLUE);
//                    lineDataSet4.setCircleColor(Color.BLACK);
                    lineDataSet4.setDrawCircles(false);
                }

                data = new LineData();
                data.addDataSet(lineDataSet1);
                data.addDataSet(lineDataSet2);
                data.addDataSet(lineDataSet3);
                data.addDataSet(lineDataSet4);
                mpLineChart.setDrawGridBackground(true);
                mpLineChart.setBackgroundColor(Color.WHITE);
                mpLineChart.setData(data);
                mpLineChart.invalidate();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    private ArrayList<Entry> dataValues1(){
        ArrayList<Entry> dataVals = new ArrayList<>();
        try {
            int sum = 0;
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            System.err.println(jsonArray.length());
            int count = 0;
            // 여기서 값 고쳐야 어디까지 받을지 정할수있음.
            for(int i=0; i< 25 /*jsonArray.length()*/; i++){
                JSONObject item = jsonArray.getJSONObject(i);

                String temp1 = item.getString(TAG_ID);
                String cnt = item.getString(TAG_NAME);

                sum += Integer.parseInt(cnt);

                TempData tempData = new TempData(Double.parseDouble(temp1) , Integer.parseInt(cnt));
                mArrayList.add(tempData);

                double temp = mArrayList.get(i).getMcu1_temp();
                dataVals.add(new Entry(i , (float) temp));

                if(Double.parseDouble(temp1) > 29) {
                    count++;
                }
            }


//            System.out.println(" ***************** count : " + count);
//            System.out.println(sum);
//            for(int i = 0 ; i < mArrayList.size(); i++){
//                System.out.println("temp : " + mArrayList.get(i).getMcu1_temp()
//                        +"cnt : " + mArrayList.get(i).getMcu1_cnt());
//            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

        return dataVals;

    }

    private ArrayList<Entry> dataValues2(){

        ArrayList<Entry> dataVals = new ArrayList<>();
        try {
            int sum = 0;
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON2);

            System.err.println(jsonArray.length());
            int count = 0;
            // 여기서 값 고쳐야 어디까지 받을지 정할수있음.
            for(int i=0; i< 25 /*jsonArray.length()*/; i++){
                JSONObject item = jsonArray.getJSONObject(i);

                String temp1 = item.getString(TAG_ID2);
                String cnt = item.getString(TAG_NAME2);

                sum += Integer.parseInt(cnt);

                TempData tempData = new TempData(Double.parseDouble(temp1) , Integer.parseInt(cnt));
                mArrayList2.add(tempData);

                double temp = mArrayList2.get(i).getMcu1_temp();
                dataVals.add(new Entry(i , (float) temp));

                if(Double.parseDouble(temp1) > 29) {
                    count++;
                }
            }


//            System.out.println(" ***************** count : " + count);
//            System.out.println(sum);
//            for(int i = 0 ; i < mArrayList.size(); i++){
//                System.out.println("temp : " + mArrayList.get(i).getMcu1_temp()
//                        +"cnt : " + mArrayList.get(i).getMcu1_cnt());
//            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

        return dataVals;
    }

    private ArrayList<Entry> dataValues3(){

        ArrayList<Entry> dataVals = new ArrayList<>();
        try {
            int sum = 0;
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON3);

            System.err.println(jsonArray.length());
            int count = 0;
            // 여기서 값 고쳐야 어디까지 받을지 정할수있음.
            for(int i=0; i< 25 /*jsonArray.length()*/; i++){
                JSONObject item = jsonArray.getJSONObject(i);

                String temp1 = item.getString(TAG_ID3);
                String cnt = item.getString(TAG_NAME3);

                sum += Integer.parseInt(cnt);

                TempData tempData = new TempData(Double.parseDouble(temp1) , Integer.parseInt(cnt));
                mArrayList3.add(tempData);

                double temp = mArrayList3.get(i).getMcu1_temp();
                dataVals.add(new Entry(i , (float) temp));

                if(Double.parseDouble(temp1) > 29) {
                    count++;
                }
            }


//            System.out.println(" ***************** count : " + count);
//            System.out.println(sum);
//            for(int i = 0 ; i < mArrayList.size(); i++){
//                System.out.println("temp : " + mArrayList.get(i).getMcu1_temp()
//                        +"cnt : " + mArrayList.get(i).getMcu1_cnt());
//            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

        return dataVals;

    }

    private ArrayList<Entry> dataValues4(){

        ArrayList<Entry> dataVals = new ArrayList<>();
        try {
            int sum = 0;
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON4);

            System.err.println(jsonArray.length());
            int count = 0;
            // 여기서 값 고쳐야 어디까지 받을지 정할수있음.
            for(int i=0; i< 25 /*jsonArray.length()*/; i++){
                JSONObject item = jsonArray.getJSONObject(i);

                String temp1 = item.getString(TAG_ID4);
                String cnt = item.getString(TAG_NAME4);

                sum += Integer.parseInt(cnt);

                TempData tempData = new TempData(Double.parseDouble(temp1) , Integer.parseInt(cnt));
                mArrayList4.add(tempData);

                double temp = mArrayList4.get(i).getMcu1_temp();
                dataVals.add(new Entry(i , (float) temp));

                if(Double.parseDouble(temp1) > 29) {
                    count++;
                }
            }


//            System.out.println(" ***************** count : " + count);
//            System.out.println(sum);
//            for(int i = 0 ; i < mArrayList.size(); i++){
//                System.out.println("temp : " + mArrayList.get(i).getMcu1_temp()
//                        +"cnt : " + mArrayList.get(i).getMcu1_cnt());
//            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

        return dataVals;

    }
}
