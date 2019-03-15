package com.Troldmandgo.wizardgo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    final List<Fragment> mFragmentList = new ArrayList<>();
    final List<String> mFragmentTitleList = new ArrayList<>();

    //Constructor for the Custom Adapter
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    //Function for adding a new Fragment, along with a title for the Fragment.
    public void addFragment(Fragment fragment, String title)
    {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    //Gets a specific Fragment
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    //Gets the number of Fragments in the mFragmentList
    public int getCount() {
        return mFragmentList.size();
    }
}
