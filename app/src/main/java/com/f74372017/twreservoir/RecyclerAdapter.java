package com.f74372017.twreservoir;

/**
 * Created by andy6804tw on 2016/12/12.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import me.itangqi.waveloadingview.WaveLoadingView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<DataModel>list;

    public RecyclerAdapter(ArrayList<DataModel>list){
        this.list=list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tvWater,tvDay,tvUpdate,tvDown,tvName;
        WaveLoadingView mWaveLoadingView = null;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvWater = (TextView)itemView.findViewById(R.id.tvWater);
            tvDay =(TextView)itemView.findViewById(R.id.tvDay);
            tvDown =(TextView)itemView.findViewById(R.id.tvDown);
            tvUpdate =(TextView)itemView.findViewById(R.id.tvUpdate);
            mWaveLoadingView = (WaveLoadingView) itemView.findViewById(R.id.waveLoadingView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                   /* Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder(v);
                return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,int i) {
                DecimalFormat df=new DecimalFormat("0.0");
                viewHolder.mWaveLoadingView.setCenterTitle(df.format(Double.parseDouble(list.get(i).getPercentage())));
                viewHolder.mWaveLoadingView.setProgressValue((int)Double.parseDouble(list.get(i).getPercentage()));
                viewHolder.tvName.setText(list.get(i).getName());
                viewHolder.tvWater.setText("有效蓄水量:"+list.get(i).getWater()+"立方公頃");
                viewHolder.tvDay.setText("預測剩餘天數:"+"60天以上");
                viewHolder.tvUpdate.setText("最後更新日期:"+list.get(i).getUpdate());
                viewHolder.tvDown.setText("今日進水量:"+list.get(i).getDown());
                if(Float.parseFloat(list.get(i).getPercentage())<20) {
                    viewHolder.tvDay.setText("預測剩餘天數:"+"10天左右");
                    viewHolder.tvDay.setTextColor(0Xfeb70009);
                    viewHolder.tvWater.setTextColor(0Xfeb70009);
                    viewHolder.mWaveLoadingView.setWaveColor(0X9eff3b76);
                   // viewHolder.mWaveLoadingView.setRimColor(0xffff9c9d);
                    viewHolder.mWaveLoadingView.setCenterTitleStrokeColor(0X9eff3b76);
                   viewHolder.mWaveLoadingView.setBorderColor(0x9eff3b76);

                }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}