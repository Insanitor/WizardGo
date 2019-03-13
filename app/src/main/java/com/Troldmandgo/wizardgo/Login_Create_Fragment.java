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

public class Login_CreateUser_Fragment extends Fragment {
    private static final String TAG = "CreateUserPage";

    TextView btnCreateAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_create, container, false);

        btnCreateAccount = (TextView) view.findViewById(R.id.createAccountButton);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Toast.makeText(getActivity(), "Create User Page", Toast.LENGTH_SHORT).show();
                ((Login_Activity) getActivity()).setViewPager(0);
            }
        });

        return view;
    }
}
