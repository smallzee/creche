package com.app.fpestaffschool.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fpestaffschool.Lists;
import com.app.fpestaffschool.R;
import com.app.fpestaffschool.RecyclerViewAdapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    public List<Lists> mData = new ArrayList<>();
    public RecyclerView recyclerView;
    public RecyclerViewAdapters recyclerViewAdapters;
    SharedPreferences sharedPreferences;
    public String response;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_children, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerViewAdapters = new RecyclerViewAdapters(mData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapters);


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = new ArrayList<>();

        sharedPreferences = getActivity().getSharedPreferences("ALL_USER_INFO", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("all_user_info", null);

        String id,name,image,class_name,gender;

        try {

            JSONObject object = new JSONObject(response);
            JSONArray data = object.getJSONArray("children_data");


            for (int i =0; i < data.length(); i++){
                JSONObject children_data = data.getJSONObject(i);

                id = children_data.getString("id");
                name = children_data.getString("fname");
                class_name = children_data.getString("class_name");
                image = children_data.getString("image");
                gender = children_data.getString("gender");


                mData.add(new Lists(name,class_name +" \n \n "+ gender,"",image,id,"true"));

            }


        }catch (JSONException e){
            e.printStackTrace();
        }



    }
}