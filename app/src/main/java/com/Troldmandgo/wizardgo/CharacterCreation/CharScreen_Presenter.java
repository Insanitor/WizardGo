package com.Troldmandgo.wizardgo.CharacterCreation;

import android.support.v4.view.ViewPager;

public class CharScreen_Presenter {

    View view;

    public CharScreen_Presenter(View view) {
        this.view = view;
    }

    public void setViewPager(int i) {
        ((CharScreen_Activity) view).setViewPager(i);
    }

    private void setupViewPager(ViewPager pager) {
        view.setupViewPager(pager);
    }

    public interface View {
        void setupViewPager(ViewPager pager);

        void setViewPager(int i);
    }
}
