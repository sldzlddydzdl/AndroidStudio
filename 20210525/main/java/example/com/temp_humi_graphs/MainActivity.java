package example.com.temp_humi_graphs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_CODE_MENU = 101;

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


    }
}