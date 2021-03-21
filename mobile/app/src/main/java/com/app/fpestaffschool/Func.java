package com.app.fpestaffschool;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;

public class Func {

    Activity activity;
    public int flags;

    public ProgressDialog progressDialog;
    Func(Activity myactivity){
        activity = myactivity;
    }

    void startDialog(){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Please wait..");
        //progressDialog.setContentView(R.layout.preloader_dialog);
        //LayoutInflater inflater = activity.getLayoutInflater();
        //progressDialog.setContentView(inflater.inflate(R.layout.preloader_dialog, null));
        //progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);

        progressDialog.show();
    }

    void dismissDialog(){
        progressDialog.hide();
    }

    void success_toast(String msg){
        custom_toast(msg, "success");
    }

    void error_toast(String msg){
        custom_toast(msg,"error");
    }

    void vibrate(){
        Vibrator v = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
    }

    void custom_toast(String msg, String type){
        View layout = activity.getLayoutInflater().inflate(R.layout.toast_custom, (ViewGroup)
                activity.findViewById(R.id.custom_toast_layout_id));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setTextColor(Color.WHITE);
        text.setText(msg);
        CardView lyt_card = (CardView) layout.findViewById(R.id.lyt_card);

        if(type.equals("error")) {
            lyt_card.setCardBackgroundColor(activity.getResources().getColor(R.color.red_500));
        }else {
            lyt_card.setCardBackgroundColor(activity.getResources().getColor(R.color.blue_500));
        }

        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    void full_screen(){
        flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        activity.getWindow().getDecorView().setSystemUiVisibility(flags);
        final View decorView = activity.getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });
    }
}
