package com.app.fpestaffschool;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Attendance_history extends Fragment {


    public List<Lists> mData = new ArrayList<>();
    public RecyclerView recyclerView;
    public RecyclerViewAdapters recyclerViewAdapters;
    SharedPreferences sharedPreferences;
    public String response;

    public Func func;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.attendance_history, container, false);

        getActivity().setTitle("Student Attendance History");

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerViewAdapters = new RecyclerViewAdapters(mData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapters);

        func = new Func(getActivity());

        return  root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = new ArrayList<>();
        func = new Func(getActivity());

        sharedPreferences = getActivity().getSharedPreferences("attendance", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("attendance", null);

        String id,name,phone,attendance,image;

        try {

            JSONObject object = new JSONObject(response);

            for (int i =0; i < object.length(); i++){
                id = object.getJSONObject(Integer.toString(i)).getString("id");
                name = object.getJSONObject(Integer.toString(i)).getString("name");
                phone = object.getJSONObject(Integer.toString(i)).getString("phone");
                attendance = object.getJSONObject(Integer.toString(i)).getString("attendance");
                image = object.getJSONObject(Integer.toString(i)).getString("image");

                mData.add(new Lists("Attendance : "+attendance,"Name : "+name,"Mobile No : "+phone,image,id,"view_attendance"));

            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
