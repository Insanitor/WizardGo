package com.Troldmandgo.wizardgo;

public class Login_Presenter {

    View view;

    public Login_Presenter(View view) {
        this.view = view;
    }

    public void setViewPager(int i)
    {
        ((Login_Activity)view).setViewPager(i);
    }

    public interface View
    {

    }
}
