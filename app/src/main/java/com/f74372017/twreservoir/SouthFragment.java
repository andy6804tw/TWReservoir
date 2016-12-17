package com.f74372017.twreservoir;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;



/**
 * A simple {@link Fragment} subclass.
 */
public class SouthFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public SouthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_south,container,false);
        recyclerView =(RecyclerView)view. findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<String>list=new ArrayList<>();
        list.add("白河水庫");
        list.add("曾文水庫");
        list.add("烏山頭水庫");
        list.add("南化水庫");
        list.add("阿公店水庫");
        list.add("牡丹水庫");
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
