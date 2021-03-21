package com.app.fpestaffschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class RecyclerViewAdapters extends RecyclerView.Adapter<RecyclerViewAdapters.MyViewHolder>{

    Context mContext;
    private List<Lists> mData;

    public RecyclerViewAdapters(Context mContext, List<Lists> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        android.view.View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.st_matric.setText(((Lists) mData.get(position)).getMatric());
        holder.st_name.setText(((Lists) mData.get(position)).getName());
        holder.st_level.setText(((Lists) mData.get(position)).getLevel());

        String is_click = ((Lists) mData.get(position)).getIs_click();


        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(50)
                .oval(true)
                .build();

        Picasso.get().load(mData.get(position).getImage()).transform(transformation).into(holder.st_image);

        Bundle bundle = new Bundle();
        bundle.putString("view_id", ((Lists) mData.get(position)).getId());

        holder.click.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if (is_click.equals("view_student")){

                    View_student view_student = new View_student();
                    view_student.setArguments(bundle);

                    ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, view_student).commit();
                }

                if (is_click.equals("view_notification")){

                    View_notification view_notification = new View_notification();
                    view_notification.setArguments(bundle);

                    ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, view_notification).commit();

                }

                if (is_click.equals("view_payment")){
                    View_payment view_payment = new View_payment();
                    view_payment.setArguments(bundle);

                    ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, view_payment).commit();
                }

                if (is_click.equals("view_attendance")){
                    View_attendance view_attendance = new View_attendance();
                    view_attendance.setArguments(bundle);

                    ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, view_attendance).commit();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public RecyclerViewAdapters(List<Lists> mData){
        this.mData = mData;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout click;
        private ImageView st_image;
        private TextView st_matric,st_name,st_level;


        public MyViewHolder(@NonNull android.view.View itemView) {

            super(itemView);

            st_matric = (TextView) itemView.findViewById(R.id.st_matric);
            st_name = (TextView) itemView.findViewById(R.id.st_name);
            st_level = (TextView) itemView.findViewById(R.id.st_level);
            st_image = (ImageView) itemView.findViewById(R.id.st_image);
            click = (LinearLayout) itemView.findViewById(R.id.click);

        }
    }

}
