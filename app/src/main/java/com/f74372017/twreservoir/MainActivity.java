package com.f74372017.twreservoir;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private  Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    public static ArrayList<DataModel>list;


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

        list=new ArrayList<>();
        //access.add("Title","日期","時間");
        //Cursor c=access.getData(null,null);
        //c.moveToFirst();
        // Toast.makeText(this,c.getCount()+" "+c.getString(3),Toast.LENGTH_LONG).show();
        //Toast.makeText(this,c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getCount(),Toast.LENGTH_LONG).show();
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
