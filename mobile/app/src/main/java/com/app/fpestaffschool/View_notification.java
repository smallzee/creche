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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View_notification extends Fragment {

    public String response;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.notifications, container, false);

        String notification_id = getArguments().getString("view_id");

        sharedPreferences = getActivity().getSharedPreferences("ALL_USER_INFO", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("all_user_info", null);

        try {

            JSONObject object = new JSONObject(response);
            JSONArray data = object.getJSONArray("notification");

            for (int i =0; i < data.length(); i++){
                JSONObject notification_data = data.getJSONObject(i);

                if (notification_data.getString("id").equals(notification_id)){

                    getActivity().setTitle(notification_data.getString("subject"));

                    break;
                }


            }


        }catch (JSONException e){
            e.printStackTrace();
        }

        return  root;
    }
}
