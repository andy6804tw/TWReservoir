package com.f74372017.twreservoir;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;



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
        DBAccess access=new DBAccess(getActivity(),"Water",null,1);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<DataModel> list=new ArrayList<>();
        Cursor c=access.getData(DBAccess.POSITION_FIELD+" ='南部'",null);
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            list.add(new DataModel(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7)));
            c.moveToNext();
        }
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
