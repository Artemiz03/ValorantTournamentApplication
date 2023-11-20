package com.example.valoranttournament.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.valoranttournament.admin_panel_fragment;
import com.example.valoranttournament.admin_panel_fragment2;
import com.example.valoranttournament.admin_panel_fragment3;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new admin_panel_fragment();
            case 1: return new admin_panel_fragment2();
            case 2: return new admin_panel_fragment3();

            default: return new admin_panel_fragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Create Matches";
            case 1: return "Valorant Tournament";
            case 2: return "Valorant Scrimmage";

            default: return "Create Matches";
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
