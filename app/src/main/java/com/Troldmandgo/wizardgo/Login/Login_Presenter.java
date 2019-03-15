package com.Troldmandgo.wizardgo.Login;

import android.support.v4.view.ViewPager;

import com.Troldmandgo.wizardgo.FragmentAdapter;

public class Login_Presenter {

    View view;

    public Login_Presenter(View view) {
        this.view = view;
    }

    public void setViewPager(int i) {
        ((Login_Activity) view).setViewPager(i);
    }

    public interface View {
        void setupViewPager(ViewPager pager);

        void setViewPager(int i);
    }

    public void setupViewPager(ViewPager pager) {
        view.setupViewPager(pager);
    }
}
