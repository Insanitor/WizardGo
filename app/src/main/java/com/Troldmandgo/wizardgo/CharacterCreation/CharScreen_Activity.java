package com.Troldmandgo.wizardgo.CharacterCreation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.Troldmandgo.wizardgo.FragmentAdapter;
import com.Troldmandgo.wizardgo.R;

public class CharScreen_Activity extends AppCompatActivity implements CharScreen_Presenter.View {

    ViewPager mPager;

    FragmentAdapter mAdapter;

    CharScreen_Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charscreen);

        mPresenter = new CharScreen_Presenter(this);
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



    public void setupViewPager(ViewPager pager)
    {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new CharScreen_Login_Fragment(), "Login Screen");

        pager.setAdapter(adapter);
    }

    public void setViewPager(int i)
    {
        mPager.setCurrentItem(i);
    }
}
