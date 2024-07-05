package com.example.baluchi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0: return new MenuFrag();
           case 1: return new CategoryFrag();
           case 2: return new SearchFrag();
           case 3: return new OptionsFrag();
           default: return new MenuFrag();
       }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
