package com.Troldmandgo.wizardgo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Login_Login_Fragment extends Fragment {

    TextView btnCreateUser;
    TextView btnForgotUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_login, container, false);

        btnCreateUser = (TextView) view.findViewById(R.id.registerText);
        btnForgotUser = (TextView) view.findViewById(R.id.forgotText);

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

        return view;
    }
}
