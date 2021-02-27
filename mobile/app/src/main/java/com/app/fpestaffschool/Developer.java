package com.app.fpestaffschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class Developer extends AppCompatActivity {

    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        this.setTitle("Developed By");

        profile = findViewById(R.id.profile_image);

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(50)
                .oval(true)
                .build();

        final String matric = "HC20180103922";

        Picasso.get().load(Core.IMG_URL+matric+".jpg").transform(transformation).into(profile);

    }
}