package example.com.weather_api_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.parsetext);

        String serviceKey = "ZhS%2F%2Fm%2FFDgh6TKntVetvzfj0YGLQOs9XsMof01ZIS7l7EXTwUDAL7x7mv0e8ZZQDxx7pZJ8khmPIT1xyIIWGMA%3D%3D";
        String pageNo = "1";
        String numOfRows = "20";
        String dataType = "JSON";
        String regId = "11B10101";

        String url = "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?"+
                "serviceKey="+serviceKey+
                "&pageNo="+pageNo +
                "&numOfRows="+numOfRows+
                "&dataType="+dataType+
                "&regId="+regId;

        NetworkTask netWorkTask = new NetworkTask(url, null);
        netWorkTask.execute();

    }

    //http://apis.data.go.kr/1360000/VilageFcstMsgService/getWthrSituation?serviceKey=ZhS%2F%2Fm%2FFDgh6TKntVetvzfj0YGLQOs9XsMof01ZIS7l7EXTwUDAL7x7mv0e8ZZQDxx7pZJ8khmPIT1xyIIWGMA%3D%3D
    // &pageNo=1
    // &numOfRows=10
    // &dataType=JSON
    // &stnId=109

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url , ContentValues values){
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... voids) {

            String result; // 결과 결과를 저장할 변수.
            RequestHttpConnection requestHttpConnection = new RequestHttpConnection();
            result = requestHttpConnection.request(url, values);

            return result;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try {
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray jsonArray = jsonObject.getJSONArray("item");
//                System.out.println("jsonArray 길이 : " + jsonArray.length());
//                for(int i = 0 ; i < jsonArray.length(); i++){
//                    JSONObject item = jsonArray.getJSONObject(i);
//                    Log.d("jsonArray : " , item.toString());
//                    textView.setText(item.getString("ta"));
//            }
                JSONObject jsonObject = new JSONObject(s);
                JSONObject response = jsonObject.getJSONObject("response");

                JSONObject responseJson = new JSONObject(response.toString());
                JSONObject body = responseJson.getJSONObject("body");
                System.out.println("str1 : " + jsonObject.toString());
                System.out.println("str2 : " + response);
                System.out.println("Json(body) : " + body);

                JSONObject itemsJson = new JSONObject(body.toString());
                JSONObject items = itemsJson.getJSONObject("items");
                System.out.println("items(Json) : " + items);
                StringBuilder sb = new StringBuilder();
                JSONArray itemArray = items.getJSONArray("item");
                for(int i = 0 ; i < itemArray.length(); i++){
                    JSONObject result = itemArray.getJSONObject(i);

                    String ta = result.getString("ta");
                    String wf = result.getString("wf");
                    sb.append(ta+ " " + wf +"\n");

                }
                textView.setText(sb.toString());

            }catch (JSONException e) {
                e.printStackTrace();
//            }
            }
            //doInBackground() 로 부터 리턴된 값이 onPostExecute() 의 매개변수로 넘어오므로 s 를받는다.
//            textView.setText(s);
            Log.d("onPostEx", "출력 값 : " + s);

        }
    }
}

//
//I/System.out: str1 : {"response":{"header":{"resultCode":"00","resultMsg":"NORMAL_SERVICE"},"body":{"dataType":"JSON","items":{"item":[{"announceTime":202109151100,"numEf":0,"regId":"11B10101","rnSt":10,"rnYn":0,"ta":"28","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":"1"},{"announceTime":202109151100,"numEf":1,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"18","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":2,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"29","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":3,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"20","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"},{"announceTime":202109151100,"numEf":4,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"26","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"}]},"pageNo":1,"numOfRows":20,"totalCount":5}}}
//        I/System.out: str2 : {"header":{"resultCode":"00","resultMsg":"NORMAL_SERVICE"},"body":{"dataType":"JSON","items":{"item":[{"announceTime":202109151100,"numEf":0,"regId":"11B10101","rnSt":10,"rnYn":0,"ta":"28","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":"1"},{"announceTime":202109151100,"numEf":1,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"18","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":2,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"29","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":3,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"20","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"},{"announceTime":202109151100,"numEf":4,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"26","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"}]},"pageNo":1,"numOfRows":20,"totalCount":5}}
//        I/System.out: Json(body) : {"dataType":"JSON","items":{"item":[{"announceTime":202109151100,"numEf":0,"regId":"11B10101","rnSt":10,"rnYn":0,"ta":"28","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":"1"},{"announceTime":202109151100,"numEf":1,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"18","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":2,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"29","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":3,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"20","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"},{"announceTime":202109151100,"numEf":4,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"26","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"}]},"pageNo":1,"numOfRows":20,"totalCount":5}
//        I/System.out: items(Json) : {"item":[{"announceTime":202109151100,"numEf":0,"regId":"11B10101","rnSt":10,"rnYn":0,"ta":"28","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":"1"},{"announceTime":202109151100,"numEf":1,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"18","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":2,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"29","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":3,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"20","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"},{"announceTime":202109151100,"numEf":4,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"26","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"}]}
//        D/onPostEx: 출력 값 : {"response":{"header":{"resultCode":"00","resultMsg":"NORMAL_SERVICE"},"body":{"dataType":"JSON","items":{"item":[{"announceTime":202109151100,"numEf":0,"regId":"11B10101","rnSt":10,"rnYn":0,"ta":"28","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":"1"},{"announceTime":202109151100,"numEf":1,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"18","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":2,"regId":"11B10101","rnSt":0,"rnYn":0,"ta":"29","wd1":"NE","wd2":"E","wdTnd":"1","wf":"맑음","wfCd":"DB01","wsIt":""},{"announceTime":202109151100,"numEf":3,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"20","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"},{"announceTime":202109151100,"numEf":4,"regId":"11B10101","rnSt":20,"rnYn":0,"ta":"26","wd1":"NE","wd2":"E","wdTnd":"1","wf":"구름많음","wfCd":"DB03","wsIt":"1"}]},"pageNo":1,"numOfRows":20,"totalCount":5}}}