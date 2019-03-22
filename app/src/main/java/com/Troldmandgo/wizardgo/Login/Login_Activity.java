package com.Troldmandgo.wizardgo.Login;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.Troldmandgo.wizardgo.FragmentAdapter;
import com.Troldmandgo.wizardgo.R;

public class Login_Activity extends FragmentActivity implements Login_Presenter.View {

    //ViewPager used for keeping track of different Fragments
    ViewPager mPager;

    //FragmentAdapter is used to keep track of Fragments
    //By allowing them to have a title
    FragmentAdapter mAdapter;

    //Presenter is used to change the current Fragment
    Login_Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new Login_Presenter(this);
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

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            getSupportFragmentManager().popBackStackImmediate();
        } else
            super.onBackPressed();
    }


    //Function used for setting up Fragments to the ViewPager
    public void setupViewPager(ViewPager pager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new Login_Login_Fragment(), "Login Screen");
        adapter.addFragment(new Login_Create_Fragment(), "Create User Screen");
        adapter.addFragment(new Login_Forgot_Fragment(), "Forgot User Screen");
        pager.setAdapter(adapter);
    }

    //Changes the Fragment that the ViewPager will show
    public void setViewPager(int i) {
        mPager.setCurrentItem(i);
    }
}
