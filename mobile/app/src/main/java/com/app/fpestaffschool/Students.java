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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Students extends Fragment {

    public List<Lists> mData = new ArrayList<>();
    public RecyclerView recyclerView;
    public RecyclerViewAdapters recyclerViewAdapters;
    SharedPreferences sharedPreferences;
    public String response;

    public Func func;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.students, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerViewAdapters = new RecyclerViewAdapters(mData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapters);

        func = new Func(getActivity());

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = new ArrayList<>();

        func = new Func(getActivity());

        sharedPreferences = getActivity().getSharedPreferences("ALL_USER_INFO", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("all_user_info", null);

        String id,name,image,class_name,gender;

        try {

            JSONObject object = new JSONObject(response);
            JSONArray data = object.getJSONArray("children_data");

            if (object.getString("total_children").equals("0")){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Dashboard()).addToBackStack(null).commit();
                func.vibrate();
                func.error_toast("No available children yet, please check later");
                return;
            }

            for (int i =0; i < data.length(); i++){
                JSONObject children_data = data.getJSONObject(i);

                id = children_data.getString("id");
                name = children_data.getString("fname");
                class_name = children_data.getString("class_name");
                image = children_data.getString("image");
                gender = children_data.getString("gender");

                mData.add(new Lists(name,class_name,gender,image,id,"view_student"));

            }


        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
