package com.f74372017.twreservoir;

/**
 * Created by andy6804tw on 2016/12/12.
 */

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import at.grabner.circleprogress.CircleProgressView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<String>list;

    public RecyclerAdapter(ArrayList<String>list){
        this.list=list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tvWater,tvDay,tvUpdate,tvDown,tvName;
        public  CircleProgressView circleProgressView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvWater = (TextView)itemView.findViewById(R.id.tvWater);
            tvDay =(TextView)itemView.findViewById(R.id.tvDay);
            tvDown =(TextView)itemView.findViewById(R.id.tvDown);
            tvUpdate =(TextView)itemView.findViewById(R.id.tvUpdate);
            circleProgressView=(CircleProgressView)itemView.findViewById(R.id.circleView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.test, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder(v);
                return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,int i) {
        for(int j=0;j<MainActivity.Name.size();j++){
            if(list.get(i).equals(MainActivity.Name.get(j))){
                viewHolder.circleProgressView.setValue(Float.parseFloat(MainActivity.percentage.get(j)));
                viewHolder.tvName.setText(MainActivity.Name.get(j));
                viewHolder.tvWater.setText("有效蓄水量:"+MainActivity.Water.get(j)+"萬立方公頃");
                viewHolder.tvDay.setText("預測剩餘天數:"+"60天以上");
                viewHolder.tvUpdate.setText("最後更新日期:"+MainActivity.Update.get(j));
                viewHolder.tvDown.setText("今日進水量:"+MainActivity.Down.get(j));
                if(Float.parseFloat(MainActivity.percentage.get(j))<20) {
                    viewHolder.tvDay.setText("預測剩餘天數:"+"10天左右");
                    viewHolder.tvDay.setTextColor(Color.RED);
                    viewHolder.tvWater.setTextColor(Color.RED);
                    viewHolder.circleProgressView.setBarColor(Color.RED);
                    viewHolder.circleProgressView.setRimColor(0xffff9c9d);
                    viewHolder.circleProgressView.setTextColor(Color.RED);
                    viewHolder.circleProgressView.setUnitColor(0xffff9c9d);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}