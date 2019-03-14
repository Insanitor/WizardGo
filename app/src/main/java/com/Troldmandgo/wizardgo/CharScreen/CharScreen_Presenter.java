package com.Troldmandgo.wizardgo.CharScreen;

import com.Troldmandgo.wizardgo.Login.Login_Activity;

public class CharScreen_Presenter {

    View view;

    public CharScreen_Presenter(View view) {
        this.view = view;
    }

    public void setViewPager(int i)
    {
        ((CharScreen_Activity)view).setViewPager(i);
    }

    public interface View
    {

    }
}
