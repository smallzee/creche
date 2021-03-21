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

public class View_payment extends Fragment {

    SharedPreferences sharedPreferences;
    public String response,payment_id;

    public Func func;
    ImageView pic;
    TextView date,payment_type,ref,amount_paid,class_n,term,session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_payment, container, false);

        getActivity().setTitle("Payment Details");
        payment_id = getArguments().getString("view_id");

        func = new Func(getActivity());

        sharedPreferences = getActivity().getSharedPreferences("payment", Context.MODE_PRIVATE);
        response = sharedPreferences.getString("payment", null);

        pic = root.findViewById(R.id.pics);
        date = root.findViewById(R.id.date);
        payment_type = root.findViewById(R.id.payment_type);
        ref = root.findViewById(R.id.ref);
        amount_paid = root.findViewById(R.id.amount_paid);
        class_n = root.findViewById(R.id.class_name);
        term = root.findViewById(R.id.term);
        session = root.findViewById(R.id.academic_session);

        String image,id,amount,refs,class_name,paid_at,terms,payment_types,academic_session;

        try {

            JSONObject object = new JSONObject(response);

            for (int i =0; i < object.length(); i++){
                id = object.getJSONObject(Integer.toString(i)).getString("id");

                if (id.equals(payment_id)){

                    image = object.getJSONObject(Integer.toString(i)).getString("image");
                    amount = object.getJSONObject(Integer.toString(i)).getString("amount");
                    refs = object.getJSONObject(Integer.toString(i)).getString("ref");
                    class_name = object.getJSONObject(Integer.toString(i)).getString("class_name");
                    paid_at = object.getJSONObject(Integer.toString(i)).getString("paid_at");
                    terms = object.getJSONObject(Integer.toString(i)).getString("term");
                    payment_types = object.getJSONObject(Integer.toString(i)).getString("payment_type");
                    academic_session = object.getJSONObject(Integer.toString(i)).getString("academic_session");

                    Picasso.get().load(image).into(pic);
                    date.setText("Paid At : "+ paid_at);
                    payment_type.setText(payment_types);
                    term.setText(terms);
                    amount_paid.setText(amount);
                    class_n.setText(class_name);
                    ref.setText(refs);
                    session.setText(academic_session);

                    break;

                }

            }


        }catch (JSONException e){
            e.printStackTrace();
        }

        return root;
    }
}
