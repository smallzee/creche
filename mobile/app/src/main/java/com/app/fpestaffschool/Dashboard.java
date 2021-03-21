package com.app.fpestaffschool;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard extends Fragment {

    SharedPreferences parent_data;
    ImageView image;
    public TextView fname,address,email,occupation,parent_id,phone,gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dashboard, container, false);

        parent_data = getActivity().getSharedPreferences("ALL_USER_INFO", Context.MODE_PRIVATE);

        getActivity().setTitle("Dashboard");

        fname = root.findViewById(R.id.fname);
        occupation = root.findViewById(R.id.occupation);
        phone = root.findViewById(R.id.phone);
        email = root.findViewById(R.id.email);
        address = root.findViewById(R.id.address);
        gender = root.findViewById(R.id.gender);
        image = root.findViewById(R.id.profile_image);
        parent_id = root.findViewById(R.id.parent_id);

        try {

            JSONObject object = new JSONObject(parent_data.getString("all_user_info", null));

            fname.setText(object.getJSONObject("parent_data").getString("fname"));
            occupation.setText(object.getJSONObject("parent_data").getString("occupation"));
            phone.setText(object.getJSONObject("parent_data").getString("phone"));
            email.setText(object.getJSONObject("parent_data").getString("email"));
            address.setText(object.getJSONObject("parent_data").getString("address"));
            gender.setText(object.getJSONObject("parent_data").getString("gender"));
            parent_id.setText(object.getJSONObject("parent_data").getString("parent_id"));

            Picasso.get().load(object.getJSONObject("parent_data").getString("image")).into(image);

        }catch (JSONException e){
            e.printStackTrace();
        }


        return root;
    }
}
