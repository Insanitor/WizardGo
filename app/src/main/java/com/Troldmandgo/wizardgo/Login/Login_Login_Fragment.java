package com.Troldmandgo.wizardgo.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Troldmandgo.wizardgo.CharScreen.CharScreen_Activity;
import com.Troldmandgo.wizardgo.R;


public class Login_Login_Fragment extends Fragment {

    TextView btnCreateUser;
    TextView btnForgotUser;
    Button btnLoginUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_login, container, false);

        btnCreateUser = (TextView) view.findViewById(R.id.registerText);
        btnForgotUser = (TextView) view.findViewById(R.id.forgotText);
        btnLoginUser = (Button) view.findViewById(R.id.loginButton);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Toast.makeText(getActivity(), "Create User Page", Toast.LENGTH_SHORT).show();
                ((Login_Activity) getActivity()).setViewPager(1);
            }
        });

        btnForgotUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Toast.makeText(getActivity(), "Forgot User Page", Toast.LENGTH_SHORT).show();
                ((Login_Activity) getActivity()).setViewPager(2);
            }
        });

        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Toast.makeText(getActivity(), "Login Page", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), CharScreen_Activity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
