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

public class Payment_history extends Fragment {

    public List<Lists> mData = new ArrayList<>();
    public RecyclerView recyclerView;
    public RecyclerViewAdapters recyclerViewAdapters;
    SharedPreferences sharedPreferences;
    public String response;

    public Func func;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.payment_history, container, false);

        getActivity().setTitle("School Fees Payment History");

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

        sharedPreferences = getActivity().getSharedPreferences("payment", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("payment", null);

        String image,id,amount,status,class_name;

        try {

            JSONObject object = new JSONObject(response);

            for (int i = 0; i < object.length(); i++){

                id = object.getJSONObject(Integer.toString(i)).getString("id");
                image = object.getJSONObject(Integer.toString(i)).getString("image");
                amount = object.getJSONObject(Integer.toString(i)).getString("amount");
                status = object.getJSONObject(Integer.toString(i)).getString("status");
                class_name = object.getJSONObject(Integer.toString(i)).getString("class_name");

                mData.add(new Lists("Amount Paid : "+amount,"Payment Status : "+status,"Class Name : "+class_name,image,id,"view_payment"));

            }


        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
