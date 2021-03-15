package com.app.fpestaffschool.ui.home;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.fpestaffschool.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {


    SharedPreferences parent_data;
    ImageView image;
    public TextView fname,address,email,occupation,parent_id,phone,gender;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        parent_data = getActivity().getSharedPreferences("ALL_USER_INFO", Context.MODE_PRIVATE);

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