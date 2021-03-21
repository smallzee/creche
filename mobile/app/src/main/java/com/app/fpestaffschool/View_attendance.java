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

import org.json.JSONException;
import org.json.JSONObject;

public class View_attendance extends Fragment {

    public String response;
    SharedPreferences sharedPreferences;
    ImageView pic;
    TextView attendance,date,names,phones;
    public Func func;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_attendance, container, false);

        getActivity().setTitle("Student Attendance Details");

        String attendance_id = getArguments().getString("view_id");

        sharedPreferences = getActivity().getSharedPreferences("attendance", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("attendance", null);

        pic = root.findViewById(R.id.pic2);

        date = root.findViewById(R.id.date);
        attendance = root.findViewById(R.id.attendance);
        names = root.findViewById(R.id.name);
        phones = root.findViewById(R.id.phone);

        String id,image,name,phone,attendances,dates;

        try {

            JSONObject object = new JSONObject(response);

            for (int i =0; i < object.length(); i++){
                id = object.getJSONObject(Integer.toString(i)).getString("id");
                name = object.getJSONObject(Integer.toString(i)).getString("name");
                image = object.getJSONObject(Integer.toString(i)).getString("image");
                phone = object.getJSONObject(Integer.toString(i)).getString("phone");
                attendances = object.getJSONObject(Integer.toString(i)).getString("attendance");
                dates = object.getJSONObject(Integer.toString(i)).getString("date");

                if (id.equals(attendance_id)){

                    names.setText(name);
                    phones.setText(phone);
                    attendance.setText(attendances);
                    Picasso.get().load(image).into(pic);
                    date.setText(dates);

                    break;
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return  root;
    }
}
