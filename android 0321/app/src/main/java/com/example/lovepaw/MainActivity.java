package com.example.lovepaw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lovepaw.Comm.ComminicateFragment;

public class MainActivity extends AppCompatActivity {

    private int selectedTab = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout messageLayout = findViewById(R.id.messageLayout);
        final LinearLayout comminicateLayout = findViewById(R.id.comminicateLayout);
        final LinearLayout personalLayout = findViewById(R.id.personalLayout);

        final ImageView homeImage = findViewById(R.id.homeImage);
        final ImageView messageImage = findViewById(R.id.messageImage);
        final ImageView comminicateImage = findViewById(R.id.comminicateImage);
        final ImageView personalImage = findViewById(R.id.personalImage);

        final TextView homeText = findViewById(R.id.homeText);
        final TextView messageText = findViewById(R.id.messageText);
        final TextView comminicateText = findViewById(R.id.comminicateText);
        final TextView personalText = findViewById(R.id.personalText);

        getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, HomeFragment.class,null)
                        .commit();
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedTab != 1){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, HomeFragment.class,null)
                            .commit();

                    messageText.setVisibility(View.GONE);
                    comminicateText.setVisibility(View.GONE);
                    personalText.setVisibility(View.GONE);

                    messageImage.setImageResource(R.drawable.baseline_sms_24dp);
                    comminicateImage.setImageResource(R.drawable.baseline_pets_24);
                    personalImage.setImageResource(R.drawable.baseline_cruelty_free_24dp);

                    messageLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    comminicateLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    personalLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeText.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.ic_home_black_24dp);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    selectedTab = 1;
                }

            }
        });

        messageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedTab != 2){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, MessageFragment.class,null)
                            .commit();

                    homeText.setVisibility(View.GONE);
                    comminicateText.setVisibility(View.GONE);
                    personalText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.ic_home_black_24dp);
                    comminicateImage.setImageResource(R.drawable.baseline_pets_24);
                    personalImage.setImageResource(R.drawable.baseline_cruelty_free_24dp);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    comminicateLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    personalLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    messageText.setVisibility(View.VISIBLE);
                    messageImage.setImageResource(R.drawable.baseline_sms_24dp);
                    messageLayout.setBackgroundResource(R.drawable.round_back_message_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    messageLayout.startAnimation(scaleAnimation);

                    selectedTab = 2;
                }

            }
        });

        comminicateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedTab != 3){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, ComminicateFragment.class,null)
                            .commit();

                    homeText.setVisibility(View.GONE);
                    messageText.setVisibility(View.GONE);
                    personalText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.ic_home_black_24dp);
                    messageImage.setImageResource(R.drawable.baseline_sms_24dp);
                    personalImage.setImageResource(R.drawable.baseline_cruelty_free_24dp);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    messageLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    personalLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    comminicateText.setVisibility(View.VISIBLE);
                    comminicateImage.setImageResource(R.drawable.baseline_pets_24);
                    comminicateLayout.setBackgroundResource(R.drawable.round_back_comminicate_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    comminicateLayout.startAnimation(scaleAnimation);

                    selectedTab = 3;
                }

            }
        });

        personalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedTab != 4){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, PersonalFragment.class,null)
                            .commit();

                    homeText.setVisibility(View.GONE);
                    messageText.setVisibility(View.GONE);
                    comminicateText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.ic_home_black_24dp);
                    messageImage.setImageResource(R.drawable.baseline_sms_24dp);
                    comminicateImage.setImageResource(R.drawable.baseline_pets_24);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    messageLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    comminicateLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    personalText.setVisibility(View.VISIBLE);
                    personalImage.setImageResource(R.drawable.baseline_cruelty_free_24dp);
                    personalLayout.setBackgroundResource(R.drawable.round_back_personal_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    personalLayout.startAnimation(scaleAnimation);

                    selectedTab = 4;
                }

            }
        });
    }
}