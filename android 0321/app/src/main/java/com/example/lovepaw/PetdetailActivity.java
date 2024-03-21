package com.example.lovepaw;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class PetdetailActivity extends AppCompatActivity {

    TextView detailNeed,detailPetname,detailVariety,detailArea,detailMoney,detailDetail;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petdetail);

        detailNeed = findViewById(R.id.detailNeed);
        detailImage = findViewById(R.id.detailImage);
        detailPetname = findViewById(R.id.detailName);
        detailVariety = findViewById(R.id.detailVariety);
        detailArea = findViewById(R.id.detailArea);
        detailMoney = findViewById(R.id.detailMoney);
        detailDetail = findViewById(R.id.detailDetail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailNeed.setText(bundle.getString("Need"));
            detailPetname.setText(bundle.getString("Name"));
            detailVariety.setText(bundle.getString("Variety"));
            detailArea.setText(bundle.getString("Area"));
            detailMoney.setText(bundle.getString("Money"));
            detailDetail.setText(bundle.getString("Detail"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}