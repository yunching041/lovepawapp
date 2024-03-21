package com.example.lovepaw;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView detailContent,detailTitle;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailContent = findViewById(R.id.detailContent);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailContent.setText(bundle.getString("Content"));
            detailTitle.setText(bundle.getString("Title"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}