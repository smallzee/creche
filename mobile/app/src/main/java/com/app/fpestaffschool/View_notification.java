package com.app.fpestaffschool;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View_notification extends Fragment {

    public String response;
    SharedPreferences sharedPreferences;
    ImageView pic;
    TextView msg,date;
    public Func func;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_notification, container, false);

        String notification_id = getArguments().getString("view_id");

        sharedPreferences = getActivity().getSharedPreferences("ALL_USER_INFO", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("all_user_info", null);

        pic = root.findViewById(R.id.pic);
        msg = root.findViewById(R.id.msg);
        date = root.findViewById(R.id.date);

        func = new Func(getActivity());

        try {

            JSONObject object = new JSONObject(response);
            JSONArray data = object.getJSONArray("notification");



            for (int i =0; i < data.length(); i++){
                JSONObject notification_data = data.getJSONObject(i);



                if (notification_data.getString("id").equals(notification_id)){

                    getActivity().setTitle(notification_data.getString("subject"));

                    Picasso.get().load(notification_data.getString("image")).into(pic);
                    msg.setText(notification_data.getString("message"));
                    date.setText(notification_data.getString("created_at"));


                    break;
                }


            }


        }catch (JSONException e){
            e.printStackTrace();
        }

        return  root;
    }
}
