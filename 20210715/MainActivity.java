package example.com.temp_humi_graphs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import example.com.temp_humi_graphs.entity.Mcu1Temp;
import example.com.temp_humi_graphs.entity.Mcu2Temp;
import example.com.temp_humi_graphs.entity.Mcu3Temp;
import example.com.temp_humi_graphs.entity.Mcu4Temp;

public class MainActivity extends AppCompatActivity {


    private static final String TAG_JSON = "dong1";
    private static final String TAG_CNT = "cnt";
    private static final String TAG_TEMP1 = "temp1";
    private static final String TAG_TEMP2 = "temp2";
    private static final String TAG_TEMP3 = "temp3";


    public static final int REQUEST_CODE_MENU = 101;
    private final String DEFAULT = "DEFAULT";
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button NodeMcu1_Temp = (Button) findViewById(R.id.NodeMcu1_Temp);
        NodeMcu1_Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowMcu1Temp.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button NodeMcu1_Humi = (Button) findViewById(R.id.NodeMcu1_Humi);
        NodeMcu1_Humi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowMcu1Humi.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button NodeMcu2_Temp = (Button) findViewById(R.id.NodeMcu2_Temp);
        NodeMcu2_Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowMcu2Temp.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button NodeMcu2_Humi = (Button) findViewById(R.id.NodeMcu2_Humi);
        NodeMcu2_Humi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowMcu2Humi.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button NodeMcu3_Temp = (Button) findViewById(R.id.NodeMcu3_Temp);
        NodeMcu3_Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowMcu3Temp.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button NodeMcu3_Humi = (Button) findViewById(R.id.NodeMcu3_Humi);
        NodeMcu3_Humi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowMcu3Humi.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button NodeMcu4_Temp = (Button) findViewById(R.id.NodeMcu4_Temp);
        NodeMcu4_Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowMcu4Temp.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button NodeMcu4_Humi = (Button) findViewById(R.id.NodeMcu4_Humi);
        NodeMcu4_Humi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowMcu4Humi.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        createNotificationChannel(DEFAULT, "default channel" , NotificationManager.IMPORTANCE_HIGH);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (true) {
                    handler.postDelayed(this,5000);
                    DataTemp dataTemp = new DataTemp();
                    dataTemp.execute("http://59.17.45.68/getjson.php");
//                    dataTemp.cancel(true);
                }
            }
        },5000);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (true) {
                    handler2.postDelayed(this,5000);
                    DataTemp dataTemp = new DataTemp();
                    dataTemp.execute("http://59.17.45.68/getjson2.php");
//                    dataTemp.cancel(true);
                }
            }
        },5000);

        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (true) {
                    handler3.postDelayed(this,5000);
                    DataTemp dataTemp = new DataTemp();
                    dataTemp.execute("http://59.17.45.68/getjson3.php");
//                    dataTemp.cancel(true);
                }
            }
        },5000);

        final Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (true) {
                    handler4.postDelayed(this,5000);
                    DataTemp dataTemp = new DataTemp();
                    dataTemp.execute("http://59.17.45.68/getjson4.php");
//                    dataTemp.cancel(true);
                }
            }
        },5000);

    }

    private void createNotificationChannel(String channelId, String channelName, int importance) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, importance));
        }

    }


    private void createNotification(String channelId, int id, String title , String text){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(text)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    void destroyNotification(int id){
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }

    public class DataTemp extends AsyncTask<String, Void, String> {

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

            mJsonString = result;

            float overTemp = 25;

            if (mJsonString.substring(6, 11).equals("dong1")) {
                if (dataValues1().get(dataValues1().size()-1).getTemp1() > overTemp
                        || dataValues1().get(dataValues1().size()-1).getTemp2() > overTemp
                        || dataValues1().get(dataValues1().size()-1).getTemp3() > overTemp) {
                    createNotification(DEFAULT, 1, "MCU1 온도가 일정범위를 벗어났습니다!", "경고");
                }
            }
            else if (mJsonString.substring(6, 11).equals("dong2")) {
                if (dataValues2().get(dataValues2().size()-1).getTemp1() > overTemp
                        || dataValues2().get(dataValues2().size()-1).getTemp2() > overTemp
                        || dataValues2().get(dataValues2().size()-1).getTemp3() > overTemp){
                    createNotification(DEFAULT , 2, "MCU2 온도가 일정범위를 벗어났습니다!" , "경고");
                }
            }
            else if(mJsonString.substring(6, 11).equals("dong3")){
                if (dataValues3().get(dataValues3().size()-1).getTemp1() > overTemp
                        || dataValues3().get(dataValues3().size()-1).getTemp2() > overTemp
                        || dataValues3().get(dataValues3().size()-1).getTemp3() > overTemp){
                    createNotification(DEFAULT , 3, "MCU3 온도가 일정범위를 벗어났습니다!" , "경고");
                }
            }
            else if(mJsonString.substring(6, 11).equals("dong4")){
                if (dataValues4().get(dataValues4().size()-1).getTemp1() > overTemp
                        || dataValues4().get(dataValues4().size()-1).getTemp2() > overTemp
                        || dataValues4().get(dataValues4().size()-1).getTemp3() > overTemp){
                    createNotification(DEFAULT , 4, "MCU4 온도가 일정범위를 벗어났습니다!" , "경고");
                }
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

                Log.d("TAG", "InsertData: Error ", e);

                return null;
            }

        }
    }


    private List<Mcu1Temp> dataValues1() {
        List<Mcu1Temp> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            JSONObject item = jsonArray.getJSONObject(jsonArray.length()-1);

            list.add(new Mcu1Temp(
                     Float.parseFloat(item.getString(TAG_TEMP1))
                    ,Float.parseFloat(item.getString(TAG_TEMP2))
                    ,Float.parseFloat(item.getString(TAG_TEMP3))));

        } catch (JSONException e) {

            Log.d("TAG", "showResult : ", e);
        }

        return list;
    }

    private List<Mcu2Temp> dataValues2() {
        List<Mcu2Temp> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("dong2");
            JSONObject item = jsonArray.getJSONObject(jsonArray.length()-1);
            list.add(new Mcu2Temp(
                    Float.parseFloat(item.getString(TAG_TEMP1))
                    ,Float.parseFloat(item.getString(TAG_TEMP2))
                    ,Float.parseFloat(item.getString(TAG_TEMP3))));

        } catch (JSONException e) {

            Log.d("TAG", "showResult : ", e);
        }

        return list;
    }
    private List<Mcu3Temp> dataValues3() {
        List<Mcu3Temp> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("dong3");
            JSONObject item = jsonArray.getJSONObject(jsonArray.length()-1);
            list.add(new Mcu3Temp(
                    Float.parseFloat(item.getString(TAG_TEMP1))
                    ,Float.parseFloat(item.getString(TAG_TEMP2))
                    ,Float.parseFloat(item.getString(TAG_TEMP3))));

        } catch (JSONException e) {

            Log.d("TAG", "showResult : ", e);
        }

        return list;
    }
    private List<Mcu4Temp> dataValues4() {
        List<Mcu4Temp> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("dong4");

            JSONObject item = jsonArray.getJSONObject(jsonArray.length()-1);
            list.add(new Mcu4Temp(
                    Float.parseFloat(item.getString(TAG_TEMP1))
                    ,Float.parseFloat(item.getString(TAG_TEMP2))
                    ,Float.parseFloat(item.getString(TAG_TEMP3))));

        } catch (JSONException e) {

            Log.d("TAG", "showResult : ", e);
        }

        return list;
    }





}