package com.Troldmandgo.wizardgo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Login_Activity extends AppCompatActivity implements Login_Presenter.View {

    ViewPager mPager;

    FragmentAdapter mAdapter;

    Login_Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAdapter = new FragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.container);

        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setupViewPager(mPager);
    }



    private void setupViewPager(ViewPager pager)
    {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new Login_Login_Fragment(), "Login Screen");
        adapter.addFragment(new Login_Create_Fragment(), "Create User Screen");
        pager.setAdapter(adapter);
    }

    public void setViewPager(int i)
    {
        mPager.setCurrentItem(i);
    }
}
