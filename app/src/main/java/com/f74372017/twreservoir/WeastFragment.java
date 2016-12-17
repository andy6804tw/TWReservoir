package com.f74372017.twreservoir;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeastFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public WeastFragment() {
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
        ArrayList<String> list=new ArrayList<>();
        list.add("鯉魚潭水庫");
        list.add("德基水庫");
        list.add("石岡壩");
        list.add("日月潭水庫");
        list.add("霧社水庫");
        list.add("仁義潭水庫");
        list.add("蘭潭水庫");
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;

    }

}
