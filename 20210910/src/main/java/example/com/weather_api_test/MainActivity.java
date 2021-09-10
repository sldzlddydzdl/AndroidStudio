package example.com.weather_api_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

            //doInBackground() 로 부터 리턴된 값이 onPostExecute() 의 매개변수로 넘어오므로 s 를받는다.
            textView.setText(s);
            Log.d("onPostEx", "출력 값 : " + s);

        }
    }
}