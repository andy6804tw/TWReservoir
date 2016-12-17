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
public class NorthFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public NorthFragment() {
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
        list.add("新山水庫");
        list.add("翡翠水庫");
        list.add("石門水庫");
        list.add("永和山水庫");
        list.add("寶山水庫");
        list.add("寶山第二水庫");
        list.add("明德水庫");
        list.add("鯉魚潭水庫");
        list.add("明德水庫");
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
