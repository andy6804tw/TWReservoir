package com.f74372017.twreservoir;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import cn.fanrunqi.waveprogress.WaveProgressView;

public class SplashActivity extends AppCompatActivity {

    DBAccess access;
    private static final int one = 0X0001;
    private int progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final WaveProgressView waveProgressbar=(WaveProgressView)findViewById(R.id.waveProgressbar);
         Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progress++;
                if(progress<37)
                    waveProgressbar.setWaveColor("#9eff3b76");
                else
                    waveProgressbar.setWaveColor("#9e4394f8");
                if (progress <= 100) {
                    waveProgressbar.setCurrent(progress, progress + "%");
                    sendEmptyMessageDelayed(one, 10);
                }
                if(progress==100){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//把前頁面清除
                    startActivity(intent);
                }
            }
        };
        handler.sendEmptyMessageDelayed(one, 100);

        access=new DBAccess(this,"Water",null,1);

        /*Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();*/



    }
    @Override
    protected void onResume() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://chihsuan.github.io/data/data.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            //Log.e("Data",jsonObject.toString());
                            DecimalFormat df=new DecimalFormat("0.00");
                            //Log.e("Data2",df.format(Double.parseDouble(jsonObject.getJSONObject("翡翠水庫").getString("percentage"))));
                            String name[]={"新山水庫","翡翠水庫","石門水庫","永和山水庫","寶山水庫","寶山第二水庫","明德水庫","鯉魚潭水庫","德基水庫"
                                    ,"石岡壩","日月潭水庫","霧社水庫","仁義潭水庫","蘭潭水庫","白河水庫","曾文水庫","烏山頭水庫","南化水庫","阿公店水庫","牡丹水庫"};
                            Cursor c=access.getData(null,null);
                            //Toast.makeText(SplashActivity.this,"1",Toast.LENGTH_SHORT).show();
                            if(c.getCount()==0){//Toast.makeText(SplashActivity.this,"2初始化",Toast.LENGTH_SHORT).show();
                                for(int i=0;i<name.length;i++){
                                    String water=jsonObject.getJSONObject(name[i]).getString("volumn");
                                    String day=jsonObject.getJSONObject(name[i]).getString("percentage");
                                    String update=jsonObject.getJSONObject(name[i]).getString("updateAt");
                                    String down=jsonObject.getJSONObject(name[i]).getString("daliyInflow");
                                    String c_name=jsonObject.getJSONObject(name[i]).getString("name");
                                    String perctange=jsonObject.getJSONObject(name[i]).getString("percentage");
                                    String position;
                                    if(c_name.equals("新山水庫")||c_name.equals("新山水庫")||c_name.equals("翡翠水庫")||c_name.equals("石門水庫")||c_name.equals("永和山水庫")||
                                            c_name.equals("寶山水庫")||c_name.equals("寶山第二水庫")||c_name.equals("明德水庫")||c_name.equals("鯉魚潭水庫"))
                                        position="北部";
                                    else if(c_name.equals("鯉魚潭水庫")||c_name.equals("德基水庫")||c_name.equals("石岡壩")||c_name.equals("日月潭水庫")||c_name.equals("霧社水庫")||
                                            c_name.equals("仁義潭水庫")||c_name.equals("蘭潭水庫"))
                                        position="中部";
                                    else
                                        position="南部";

                                    access.add(water,day,update,down,c_name,perctange,position);
                                }
                            }else{
                                Cursor c2=access.getData(null,null);
                                c2.moveToFirst();
                                boolean b=false;
                                for(int i=0;i<c2.getCount();i++){
                                    if(!jsonObject.getJSONObject(name[i]).getString("updateAt").equals(c2.getString(3))){
                                        b=true;
                                        //Toast.makeText(SplashActivity.this,"進入2"+c2.getString(5)+" "+c2.getString(3)+" "+jsonObject.getJSONObject(name[i]).getString("updateAt"),Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                    c2.moveToNext();
                                }
                                if(b) {//Toast.makeText(SplashActivity.this,"3更新",Toast.LENGTH_SHORT).show();
                                    for (int i = 0; i < name.length; i++) {
                                        String water = jsonObject.getJSONObject(name[i]).getString("volumn");
                                        String day = jsonObject.getJSONObject(name[i]).getString("percentage");
                                        String update = jsonObject.getJSONObject(name[i]).getString("updateAt");
                                        String down = jsonObject.getJSONObject(name[i]).getString("daliyInflow");
                                        String c_name = jsonObject.getJSONObject(name[i]).getString("name");
                                        String perctange = jsonObject.getJSONObject(name[i]).getString("percentage");
                                        String position;
                                        if (c_name.equals("新山水庫") || c_name.equals("新山水庫") || c_name.equals("翡翠水庫") || c_name.equals("石門水庫") || c_name.equals("永和山水庫") ||
                                                c_name.equals("寶山水庫") || c_name.equals("寶山第二水庫") || c_name.equals("明德水庫") || c_name.equals("鯉魚潭水庫"))
                                            position = "北部";
                                        else if (c_name.equals("鯉魚潭水庫") || c_name.equals("德基水庫") || c_name.equals("石岡壩") || c_name.equals("日月潭水庫") || c_name.equals("霧社水庫") ||
                                                c_name.equals("仁義潭水庫") || c_name.equals("蘭潭水庫"))
                                            position = "中部";
                                        else
                                            position = "南部";
                                        access.update(water,day,update,down,c_name,perctange,position,DBAccess.ID_FIELD+" ="+(i+1));
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        super.onResume();
    }
    @Override
    protected void onDestroy() {
        access.close();
        super.onDestroy();
    }
}