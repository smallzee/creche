package com.app.fpestaffschool;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class View_student extends Fragment {

    public Func func;

    SharedPreferences sharedPreferences;
    public String response, student_id;

    ImageView image_pic;
    TextView fname,application_id,class_name,
            birth,term,gender,academic_session;
    LinearLayout attendance,school_fee;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_student, container, false);

        student_id = getArguments().getString("view_id");
        func = new Func(getActivity());

        sharedPreferences = getActivity().getSharedPreferences("ALL_USER_INFO", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("all_user_info", null);

        image_pic = root.findViewById(R.id.profile_image);
        fname = root.findViewById(R.id.fname);
        application_id = root.findViewById(R.id.application_id);
        class_name = root.findViewById(R.id.class_name);
        birth = root.findViewById(R.id.birth);
        term = root.findViewById(R.id.term);
        gender = root.findViewById(R.id.gender);
        academic_session = root.findViewById(R.id.academic_session);

        attendance = root.findViewById(R.id.attendance);
        school_fee = root.findViewById(R.id.school_fee);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func.startDialog();

                StringRequest request = new StringRequest(Request.Method.POST, Core.SITE_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        func.dismissDialog();

                        try {

                            JSONObject object = new JSONObject(response);

                            if (object.getString("error").equals("0")){
                                func.error_toast(object.getString("msg"));
                                func.vibrate();
                                return;
                            }

                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("attendance",Context.MODE_PRIVATE).edit();
                            editor.putString("attendance", response.toString());
                            editor.apply();

                            getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.container, new Attendance_history()).addToBackStack(null).commit();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        func.dismissDialog();
                        func.error_toast("No internet network, try again");
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<>();
                        param.put("attendance", "");
                        param.put("student_id", student_id);
                        return param;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(request);

            }
        });

        school_fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                func.startDialog();

                StringRequest request = new StringRequest(Request.Method.POST, Core.SITE_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        func.dismissDialog();

                        try {
                            JSONObject object = new JSONObject(response);

                            if (object.getString("error").equals("0")){
                                func.error_toast(object.getString("msg"));
                                func.vibrate();
                                return;
                            }

                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("payment",Context.MODE_PRIVATE).edit();
                            editor.putString("payment", response.toString());
                            editor.apply();

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Payment_history()).addToBackStack(null).commit();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        func.dismissDialog();
                        func.error_toast("No internet network, try again");
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<>();
                        param.put("school_fee", "");
                        param.put("student_id", student_id);
                        return param;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(request);

            }
        });

        try {

            JSONObject object = new JSONObject(response);
            JSONArray data = object.getJSONArray("children_data");



            String id,image,name;

            for (int i = 0; i < data.length(); i++){
                JSONObject children_data = data.getJSONObject(i);

                id = children_data.getString("id");
                image = children_data.getString("image");
                name = children_data.getString("fname");

                if (id.equals(student_id)){

                    getActivity().setTitle(children_data.getString("fname"));
                    Picasso.get().load(image).into(image_pic);

                    fname.setText(name);
                    application_id.setText(children_data.getString("application_id"));
                    class_name.setText(children_data.getString("class_name"));
                    birth.setText(children_data.getString("birth"));

                    term.setText(children_data.getString("term"));
                    gender.setText(children_data.getString("gender"));
                    academic_session.setText(children_data.getString("academic_session"));

                    break;

                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return root;
    }
}
