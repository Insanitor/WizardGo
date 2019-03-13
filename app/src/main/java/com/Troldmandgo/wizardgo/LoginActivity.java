package com.Troldmandgo.wizardgo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    ViewPager mPager;

    FragmentAdapter mAdapter;

    LoginPresenter mPresenter;

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
    }

    public void OnClickCreate(View view)
    {
        mPresenter.setViewPager(0);
    }

    private void setupViewPager(ViewPager pager)
    {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(), "Fragment 1");
        pager.setAdapter(adapter);
    }

    public void setViewPager(int i)
    {
        mPager.setCurrentItem(i);
    }
}
