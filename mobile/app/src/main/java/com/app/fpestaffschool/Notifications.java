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

public class Notifications extends Fragment {

    public List<Lists> mData = new ArrayList<>();
    public RecyclerView recyclerView;
    public RecyclerViewAdapters recyclerViewAdapters;
    SharedPreferences sharedPreferences;
    public String response;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.notifications, container, false);

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

        String id,subject,image,message,created_at;

        try {

            JSONObject object = new JSONObject(response);
            JSONArray data = object.getJSONArray("notification");

            for (int i =0; i < data.length(); i++){
                JSONObject notification_data = data.getJSONObject(i);

                id = notification_data.getString("id");
                subject = notification_data.getString("subject");
                message = notification_data.getString("message");
                image = notification_data.getString("image");
                created_at = notification_data.getString("created_at");

                mData.add(new Lists(subject,message,created_at,image,id,"view_student"));

            }


        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
