package com.example.lovepaw;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lovepaw.fragment.CatFragment;
import com.example.lovepaw.fragment.DogFragment;
import com.example.lovepaw.fragment.MouseFragment;
import com.example.lovepaw.fragment.OtherFragment;
import com.example.lovepaw.fragment.RabbitFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new DogFragment();
            case 1:
                return new CatFragment();
            case 2:
                return new RabbitFragment();
            case 3:
                return new MouseFragment();
            default:
                return new OtherFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
