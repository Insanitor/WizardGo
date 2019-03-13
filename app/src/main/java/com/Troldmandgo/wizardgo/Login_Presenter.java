package com.Troldmandgo.wizardgo;

public class LoginPresenter {

    View view;

    public LoginPresenter(View view) {
        this.view = view;
    }

    public void setViewPager(int i)
    {
        ((LoginActivity)view).setViewPager(i);
    }

    public interface View
    {

    }
}
