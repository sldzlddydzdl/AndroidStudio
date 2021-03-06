package example.com.temp_humi_graphs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ShowMcu3Temp extends AppCompatActivity {

    Button btn_return;
    LineChart mpLineChart;
    String mJsonString;
    LineDataSet lineDataSet1;
    LineDataSet lineDataSet2;
    LineDataSet lineDataSet3;
    LineDataSet lineDataSet4;
    ArrayList<ILineDataSet> dataSets;
    LineData data;
    private static String TAG = "getjson_MainActivity";
    private static final String TAG_JSON = "dong3";
    private static final String TAG_CNT = "cnt";
    private static final String TAG_TEMP1 = "temp1";
    private static final String TAG_TEMP2 = "temp2";
    private static final String TAG_TEMP3 = "temp3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nodemcu1_temp);

        GetMcuData getData = new GetMcuData();
        getData.execute("http://192.168.43.141/getjson3.php");

        mpLineChart = findViewById(R.id.chartExample);
        mpLineChart.getAxisLeft().setStartAtZero(false);
        mpLineChart.getAxisRight().setStartAtZero(false);
        setTitle("NodeMcu3_Temperatures");
        btn_return = findViewById(R.id.main);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //?????? ???????????? ??????
            }
        });

    }

    public class GetMcuData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ShowMcu3Temp.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if (result == null) {

            } else {
                mJsonString = result;
                System.out.println("mJsonString : " + mJsonString.substring(6, 11));
                dataSets = new ArrayList<>();
                if (mJsonString.substring(6, 11).equals("dong3")) {
                    lineDataSet1 = new LineDataSet(dataValues1(), "Temp1");
                    dataSets.add(lineDataSet1);

                    lineDataSet1.setLineWidth(2);
                    lineDataSet1.setColor(Color.RED);
//                    lineDataSet1.setCircleColor(Color.BLACK);
//                    lineDataSet1.setDrawCircleHole(false);
                    lineDataSet1.setDrawCircles(false);
                }
                if (mJsonString.substring(6, 11).equals("dong3")) {
                    lineDataSet2 = new LineDataSet(dataValues2(), "Temp2");
                    dataSets.add(lineDataSet2);

                    lineDataSet2.setLineWidth(2);
                    lineDataSet2.setColor(Color.BLACK);
//                    lineDataSet2.setCircleColor(Color.BLACK);
                    lineDataSet2.setDrawCircles(false);
                }
                if (mJsonString.substring(6, 11).equals("dong3")) {
                    lineDataSet3 = new LineDataSet(dataValues3(), "Temp3");
                    dataSets.add(lineDataSet3);

                    lineDataSet3.setLineWidth(2);
                    lineDataSet3.setColor(Color.GREEN);
//                    lineDataSet3.setCircleColor(Color.BLACK);
                    lineDataSet3.setDrawCircles(false);
                }
                if(mJsonString.substring(6,11).equals("dong3")){
                    lineDataSet4 = new LineDataSet(dataValues4() ,"Avg");
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
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
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

        private ArrayList<Entry> dataValues1() {
            ArrayList<Entry> dataVals = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

                // ????????? ??? ????????? ???????????? ????????? ???????????????.
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    String cnt = item.getString(TAG_CNT);
                    String temp1 = item.getString(TAG_TEMP1);

                    dataVals.add(new Entry(i, Float.parseFloat(temp1)));

                }
            } catch (JSONException e) {

                Log.d(TAG, "showResult : ", e);
            }

            return dataVals;

        }

        private ArrayList<Entry> dataValues2() {

            ArrayList<Entry> dataVals = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

                // ????????? ??? ????????? ???????????? ????????? ???????????????.
                for (int i = 0; i <  jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    String cnt = item.getString(TAG_CNT);
                    String temp2 = item.getString(TAG_TEMP2);

                    dataVals.add(new Entry(i, Float.parseFloat(temp2)));
                }

            } catch (JSONException e) {

                Log.d(TAG, "showResult : ", e);
            }

            return dataVals;
        }

        private ArrayList<Entry> dataValues3() {

            ArrayList<Entry> dataVals = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

                // ????????? ??? ????????? ???????????? ????????? ???????????????.
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    String cnt = item.getString(TAG_CNT);
                    String temp3 = item.getString(TAG_TEMP3);

                    dataVals.add(new Entry(i, Float.parseFloat(temp3)));
                }

            } catch (JSONException e) {

                Log.d(TAG, "showResult : ", e);
            }

            return dataVals;

        }

        private ArrayList<Entry> dataValues4() {

            ArrayList<Entry> dataVals = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

                // ????????? ??? ????????? ???????????? ????????? ???????????????.
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    String cnt = item.getString(TAG_CNT);
                    String temp1 = item.getString(TAG_TEMP1);
                    String temp2 = item.getString(TAG_TEMP2);
                    String temp3 = item.getString(TAG_TEMP3);

                    float avg = (Float.parseFloat(temp1) + Float.parseFloat(temp2)
                            + Float.parseFloat(temp3))/3;
                    dataVals.add(new Entry(i, (Float)avg));
                }

            } catch (JSONException e) {

                Log.d(TAG, "showResult : ", e);
            }

            return dataVals;

        }
    }
}


