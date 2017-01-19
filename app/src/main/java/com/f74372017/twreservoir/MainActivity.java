package com.f74372017.twreservoir;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private  Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    public static ArrayList<DataModel>list;
    DBAccess access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        toolbar.setTitle("台灣水庫即時水情");
        setSupportActionBar(toolbar);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new NorthFragment(),"北部");
        viewPagerAdapter.addFragments(new WeastFragment(),"中部");
        viewPagerAdapter.addFragments(new SouthFragment(),"南部");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        access=new DBAccess(this,"Water",null,1);
        list=new ArrayList<>();
        //access.add("Title","日期","時間");
        Cursor c=access.getData(null,null);
        c.moveToFirst();
//        Toast.makeText(this,c.getCount()+" "+c.getString(3),Toast.LENGTH_LONG).show();
        //Toast.makeText(this,c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getCount(),Toast.LENGTH_LONG).show();
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
                            Log.e("Data",jsonObject.toString());
                            DecimalFormat df=new DecimalFormat("0.00");
                            Log.e("Data2",df.format(Double.parseDouble(jsonObject.getJSONObject("翡翠水庫").getString("percentage"))));
                            String name[]={"新山水庫","翡翠水庫","石門水庫","永和山水庫","寶山水庫","寶山第二水庫","明德水庫","鯉魚潭水庫","明德水庫","鯉魚潭水庫","德基水庫"
                                    ,"石岡壩","日月潭水庫","霧社水庫","仁義潭水庫","蘭潭水庫","白河水庫","曾文水庫","烏山頭水庫","南化水庫","阿公店水庫","牡丹水庫"};
                            Cursor c=access.getData(null,null);
                            if(c.getCount()==0){
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
                                if(!jsonObject.getJSONObject(name[0]).getString("updateAt").equals(c2.getString(3))) {
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
                                        access.update(water,day,update,down,c_name,perctange,position,DBAccess.ID_FIELD+" ="+i+1);
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

    /*private Runnable mutiThread =new Runnable(){
        public void run(){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://chihsuan.github.io/data/data.json")
                    .build();
            try{
                Response response = client.newCall(request).execute();
                String data=response.body().string();
                Log.d("data",data);

                JSONObject jsonObject =new JSONObject(data);
                //Log.e("jsonString",jsonObject.toString());
                //Log.e("percentage",jsonObject.getJSONObject("石岡壩").getString("percentage"));
                //circleProgressView.setValue(Float.parseFloat(jsonObject.getJSONObject("石門水庫").getString("percentage")));
                String name[]={"新山水庫","翡翠水庫","石門水庫","永和山水庫","寶山水庫","寶山第二水庫","明德水庫","鯉魚潭水庫","明德水庫","鯉魚潭水庫","德基水庫"
                        ,"石岡壩","日月潭水庫","霧社水庫","仁義潭水庫","蘭潭水庫","白河水庫","曾文水庫","烏山頭水庫","南化水庫","阿公店水庫","牡丹水庫"};
                for(int i=0;i<name.length;i++){
                    Water.add(jsonObject.getJSONObject(name[i]).getString("volumn"));
                    Day.add(jsonObject.getJSONObject(name[i]).getString("percentage"));
                    Update.add(jsonObject.getJSONObject(name[i]).getString("updateAt"));
                    Down.add(jsonObject.getJSONObject(name[i]).getString("daliyInflow"));
                    Name.add(jsonObject.getJSONObject(name[i]).getString("name"));
                    percentage.add(jsonObject.getJSONObject(name[i]).getString("percentage"));

                }
            }
            catch (IOException e){
                e.printStackTrace();
            }catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };*/
}
