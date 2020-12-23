package com.example.airplanenews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    TextView text;

    String key ="HvgVpXADX%2BfqyL7OXMhz7%2BncdAXK3fpgSXOiuHyp1owTVWNDe5we5oDd81G%2BvWkCYawjJHj2nJALQrTSj%2FuREQ%3D%3D";
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText)findViewById(R.id.edit);
        text = (TextView)findViewById(R.id.list);

    }

    public void monClick(View v){
        switch (v.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data=getXmlData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    String getXmlData(){
        StringBuffer buffer=new StringBuffer();
        String str = edit.getText().toString();
        String location = URLEncoder.encode(str);

        String queryUrl = "http://openapi.airport.kr/openapi/service/StatusOfPassengerWeahter/getPassengerArrivalsW?ServiceKey=HvgVpXADX%2BfqyL7OXMhz7%2BncdAXK3fpgSXOiuHyp1owTVWNDe5we5oDd81G%2BvWkCYawjJHj2nJALQrTSj%2FuREQ%3D%3D";
        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("검색 시작...");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if(tag.equals("item"));
                        else if(tag.equals("airline")){
                            buffer.append("항공사 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("flightId")){
                            buffer.append("항공 편명 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("scheduleDateTime")){
                            buffer.append("도착예정시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("estimatedDateTime")){
                            buffer.append("도착변경시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("airport")){
                            buffer.append("출발공항 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("gatenumber")){
                            buffer.append("탑승구 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("carousel")){
                            buffer.append("수하물 수취대 번호 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("exitnumber")){
                            buffer.append("출구 번호 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("remark")){
                            buffer.append("운항상태 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("airportcode")){
                            buffer.append("출발 공항 코드(IATA),공항코드를 이용하여 해당 공항의 여객편만 조회 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("yoil")){
                            buffer.append("출발지 요일 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("himidity")){
                            buffer.append("출발지 습도(%) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("wimage")){
                            buffer.append("날씨 이미지 url 경로 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("wind")){
                            buffer.append("출발지 풍속(m/s) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("maxtem")){
                            buffer.append("출발지 최고 기온(℃) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("mintem")){
                            buffer.append("출발지 최저 기온(℃) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("terminalId")){
                            buffer.append("터미널 구분값 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();

                        if(tag.equals("item")) buffer.append("\n");
                        break;
                }

                eventType = xpp.next();

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        buffer.append("검색 끝\n");
        return buffer.toString();

    }

}